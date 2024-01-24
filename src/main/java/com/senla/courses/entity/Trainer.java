package com.senla.courses.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainers", schema = "my_study")
public class Trainer {
    @Id
    @GeneratedValue(generator = "trainers_seq")
    @SequenceGenerator(name = "trainers_seq", sequenceName = "trainers_id_seq", schema = "my_study", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(mappedBy = "trainers", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToOne(mappedBy = "trainer", fetch = FetchType.LAZY)
    private Training training;

}




