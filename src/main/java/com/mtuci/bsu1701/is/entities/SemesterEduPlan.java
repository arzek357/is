package com.mtuci.bsu1701.is.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mtuci.bsu1701.is.utils.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "edu_semesterplans")
@NoArgsConstructor
public class SemesterEduPlan {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "number")
    private Integer semesterNumber;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonView(Views.BigSemesterEduPlan.class)
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonView(Views.BigSemesterEduPlan.class)
    @Column(name = "end_date")
    private Date endDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private EduPlan eduPlan;

    @JsonView(Views.BigSemesterEduPlan.class)
    @ManyToMany
    @JoinTable(name = "edu_semesterplans_edu_disciplines",
            joinColumns = @JoinColumn(name = "edusemester_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "edu_discipline_id"))
    private Set<EduDiscipline> disciplines = new HashSet<>();

    @JsonView(Views.FullSemesterEduPlan.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullSemesterEduPlan.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;
}
