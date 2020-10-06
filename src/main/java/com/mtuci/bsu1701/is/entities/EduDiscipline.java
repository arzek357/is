package com.mtuci.bsu1701.is.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.mtuci.bsu1701.is.utils.Views;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "edu_disciplines")
@NoArgsConstructor
public class EduDiscipline {

    @AllArgsConstructor
    @Getter
    public enum FinalEvent {
        EXAM("Экзамен"),
        TEST("Зачет");
        private String rus;
    }

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

    @JsonView(Views.BigEduDiscipline.class)
    @ManyToMany
    @JoinTable(name = "teachers_disciplines",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private Set<User> teachers = new HashSet<>();

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name="final_event")
    @Enumerated(EnumType.STRING)
    private FinalEvent finalEvent;

    @NotNull
    @JsonView(Views.BigEduDiscipline.class)
    @Column(name="lectures_number")
    private Integer lecturesNumber;

    @NotNull
    @JsonView(Views.BigEduDiscipline.class)
    @Column(name="seminars_number")
    private Integer seminarsNumber;

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name="labworks_number")
    private Integer labworksNumber;

    @JsonView(Views.FullEduDiscipline.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullEduDiscipline.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;
}
