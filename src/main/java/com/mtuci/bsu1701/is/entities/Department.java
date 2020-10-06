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
@Table(name = "edu_departments")
@NoArgsConstructor
public class Department {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "name")
    private String name;

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "codename")
    private String codename;

    @NotNull
    @JsonView(Views.Small.class)
    @OneToOne
    @JoinColumn(name = "elder_id", referencedColumnName = "id", nullable = false)
    private User elder;

    @JsonView(Views.BigDepartment.class)
    @OneToOne
    @JoinColumn(name = "head_id", referencedColumnName = "id", nullable = false)
    private User header;

    @JsonView(Views.Small.class)
    @OneToMany(mappedBy = "department",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<User> employees = new HashSet<>();
}
