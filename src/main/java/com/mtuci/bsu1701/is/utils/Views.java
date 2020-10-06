package com.mtuci.bsu1701.is.utils;

public final class Views {
    public interface Id {}

    public interface Small extends Id{}

    public interface Big extends Small{}

    public interface BigUser extends Big{}
    public interface FullUser extends BigUser{}

    public interface BigEduDiscipline extends Big{}
    public interface FullEduDiscipline extends BigEduDiscipline{}

    public interface BigSemesterEduPlan extends Big{}
    public interface FullSemesterEduPlan extends BigSemesterEduPlan{}

    public interface BigEduProfile extends Big{}
    public interface FullEduProfile extends BigEduProfile{}

    public interface BigEduPlan extends Big{}
    public interface FullEduPlan extends BigEduPlan{}

    public interface BigEduDirection extends Big{}
    public interface FullEduDirection extends BigEduDirection{}

    public interface BigDepartment extends Big{}
    public interface FullDepartment extends BigDepartment{}
}
