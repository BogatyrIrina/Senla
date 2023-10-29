package com.senla.courses.controller;

import com.senla.courses.mapper.UserMapper;
import com.senla.courses.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/entities/{id}")
        public String getEntity(Model model, @PathVariable Long id) {
            Entity entity = entityService.getEntityById(id);
            EntityDto entityDto = entityMapper.entityToDto(entity);
            model.addAttribute("entity", entityDto);
            return "entity";
        }

        @GetMapping("/entities/create")
        public String createEntity(Model model) {
            EntityDto entityDto = new EntityDto();
            model.addAttribute("entity", entityDto);
            return "create_entity";
        }

        @PostMapping("/entities")
        public String saveEntity(@ModelAttribute("entity") EntityDto entityDto) {
            Entity entity = entityMapper.dtoToEntity(entityDto);
            entityService.saveEntity(entity);
            return "redirect:/entities";
        }

        @GetMapping("/entities/{id}/edit")
        public String editEntity(Model model, @PathVariable Long id) {
            Entity entity = entityService.getEntityById(id);
            EntityDto entityDto = entityMapper.entityToDto(entity);
            model.addAttribute("entity", entityDto);
            return "edit_entity";
        }

        @PostMapping("/entities/{id}")
        public String updateEntity(@PathVariable Long id, @ModelAttribute("entity") EntityDto entityDto) {
            Entity entity = entityMapper.dtoToEntity(entityDto);
            entity.setId(id);
            entityService.updateEntity(entity);
            return "redirect:/entities";
        }

        @PostMapping("/entities/{id}/delete")
        public String deleteEntity(@PathVariable Long id) {
            entityService.deleteEntity(id);
            return "redirect:/entities";
        }

        @GetMapping("/entities")
        public String getAllEntities(Model model) {
            List<Entity> entities = entityService.getAllEntities();
            List<EntityDto> entityDtos = entityMapper.entitiesToDtos(entities);
            model.addAttribute("entities", entityDtos);
            return "entities";
        }

        @GetMapping("/entities/test")
        public void testCrudOperations() {
            // Вставка двух записей
            EntityDto entity1 = new EntityDto();
            entity1.setName("Entity 1");
            EntityDto createdEntity1 = entityMapper.entityToDto(entityService.saveEntity(entityMapper.dtoToEntity(entity1)));

            EntityDto entity2 = new EntityDto();
            entity2.setName("Entity 2");
            EntityDto createdEntity2 = entityMapper.entityToDto(entityService.saveEntity(entityMapper.dtoToEntity(entity2)));

            // Чтение первой записи
            EntityDto readEntity = entityMapper.entityToDto(entityService.getEntityById(createdEntity1.getId()));

            // Удаление второй записи
            entityService.deleteEntity(createdEntity2.getId());

            // Обновление первой записи
            readEntity.setName("Updated Entity 1");
            entityService.updateEntity(entityMapper.dtoToEntity(readEntity));

            // Повторное чтение первой записи
            EntityDto rereadEntity = entityMapper.entityToDto(entityService.getEntityById(createdEntity1.getId()));
        }
    }
}
