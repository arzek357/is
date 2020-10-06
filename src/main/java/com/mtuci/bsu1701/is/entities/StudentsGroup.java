package com.mtuci.bsu1701.is.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtuci.bsu1701.is.utils.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "edu_studentsgroups")
@NoArgsConstructor
public class StudentsGroup {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "codename")
    private String codename;

    @NotNull
    @JsonView(Views.Small.class)
    @OneToOne
    @JoinColumn(name = "elder_id", referencedColumnName = "id", nullable = false)
    private User elder;

    @NotNull
    @JsonView(Views.Small.class)
    @OneToOne
    @JoinColumn(name = "edu_plan_id", referencedColumnName = "id", nullable = false)
    private EduPlan eduPlan;

    @JsonView(Views.Small.class)
    @OneToMany(mappedBy = "studentsGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<User> students = new HashSet<>();
}
