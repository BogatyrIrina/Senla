package com.senla.courses.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainings", schema = "my_study")
@NamedEntityGraph(name = "graph.Training.trainer",
        attributeNodes = @NamedAttributeNode("trainer"))
@NamedEntityGraph(name = "graph.Training.users",
        attributeNodes = @NamedAttributeNode("users"))
public class Training {
    @Id
    @GeneratedValue(generator = "trainings_seq")
    @SequenceGenerator(name = "trainings_seq", sequenceName = "trainings_id_seq", schema = "my_study", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDateTime date;
    @Column(name = "total_count")
    private Integer totalCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @ManyToMany(mappedBy = "trainings", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<User> users;
}
