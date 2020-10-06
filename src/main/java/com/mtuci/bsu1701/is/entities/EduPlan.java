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
@Table(name = "edu_plans")
@NoArgsConstructor
public class EduPlan {

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
    @Temporal(TemporalType.DATE)
    @JsonView(Views.Small.class)
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonView(Views.Small.class)
    @Column(name = "end_date")
    private Date endDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private EduProfile eduProfile;

    @JsonView(Views.Small.class)
    @OneToMany(mappedBy = "eduPlan",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<SemesterEduPlan> semesterEduPlans = new HashSet<>();

    @JsonView(Views.FullEduPlan.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullEduPlan.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;
}
