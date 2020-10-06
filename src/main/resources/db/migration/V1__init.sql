drop table if exists edu_departments CASCADE;
drop table if exists edu_directions CASCADE;
drop table if exists edu_disciplines CASCADE;
drop table if exists edu_plans CASCADE;
drop table if exists edu_profiles CASCADE;
drop table if exists edu_semesterplans CASCADE;
drop table if exists edu_semesterplans_edu_disciplines CASCADE;
drop table if exists edu_studentsgroups CASCADE;
drop table if exists roles CASCADE;
drop table if exists teachers_disciplines CASCADE;
drop table if exists users CASCADE;
drop table if exists users_roles CASCADE;
create table edu_departments (id bigint generated by default as identity, name varchar(255) not null, codename varchar(255) not null, elder_id bigint not null, head_id bigint, primary key (id));
create table edu_directions (id bigint generated by default as identity, codename varchar(255) not null, created_at timestamp default current_timestamp, fullname varchar(255) not null, updated_at timestamp default current_timestamp, primary key (id));
create table edu_disciplines (id bigint generated by default as identity, created_at timestamp default current_timestamp, final_event varchar(255) not null, labworks_number integer not null, lectures_number integer not null, name varchar(255) not null, seminars_number integer not null, updated_at timestamp default current_timestamp, primary key (id));
create table edu_plans (id bigint generated by default as identity, created_at timestamp default current_timestamp, end_date date not null, name varchar(255) not null, start_date date not null, updated_at timestamp default current_timestamp, edu_profile_id bigint, primary key (id));
create table edu_profiles (id bigint generated by default as identity, codename varchar(255) not null, created_at timestamp default current_timestamp, fullname varchar(255) not null, updated_at timestamp default current_timestamp, edu_direction_id bigint, primary key (id));
create table edu_semesterplans (id bigint generated by default as identity, created_at timestamp default current_timestamp, end_date date not null, number integer not null, start_date date not null, updated_at timestamp default current_timestamp, edu_plan_id bigint, primary key (id));
create table edu_semesterplans_edu_disciplines (edusemester_plan_id bigint not null, edu_discipline_id bigint not null, primary key (edusemester_plan_id, edu_discipline_id));
create table edu_studentsgroups (id bigint generated by default as identity, codename varchar(255) not null, edu_plan_id bigint not null, elder_id bigint not null, primary key (id));
create table roles (id bigint generated by default as identity, created_at timestamp default current_timestamp, name varchar(255) not null, updated_at timestamp default current_timestamp, primary key (id));
create table teachers_disciplines (user_id bigint not null, discipline_id bigint not null, primary key (user_id, discipline_id));
create table users (id bigint generated by default as identity, created_at timestamp default current_timestamp, email varchar(255), name varchar(255), password varchar(255) not null, surname varchar(255), position varchar(255), updated_at timestamp default current_timestamp, username varchar(255) not null, department_id bigint, students_group_id bigint, primary key (id));
create table users_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
alter table edu_departments add constraint FKbrg1uib39p8oekelb6wqgjkb9 foreign key (elder_id) references users;
alter table edu_departments add constraint FK16nxibfcmpgvs6oi9xf987fsq foreign key (head_id) references users;
alter table edu_plans add constraint FKg70wqtaktih1lg4oaae6h4bjo foreign key (edu_profile_id) references edu_profiles;
alter table edu_profiles add constraint FKpt6j999oy4j5lx6ipa10xaslf foreign key (edu_direction_id) references edu_directions;
alter table edu_semesterplans add constraint FKh4odc85tgkqne2rlhh1xymexq foreign key (edu_plan_id) references edu_plans;
alter table edu_semesterplans_edu_disciplines add constraint FK7h32mtw4k772yyo0a2kyffd9k foreign key (edu_discipline_id) references edu_disciplines;
alter table edu_semesterplans_edu_disciplines add constraint FKhckqdu2i2b49kg4xkwyctck0g foreign key (edusemester_plan_id) references edu_semesterplans;
alter table edu_studentsgroups add constraint FKnctm11gyfjc7dqicb2d3m2qr foreign key (edu_plan_id) references edu_plans;
alter table edu_studentsgroups add constraint FK72lro9h8nswfwv4r77f25p9pw foreign key (elder_id) references users;
alter table teachers_disciplines add constraint FKhp5gxgvfa5y2rjsqdlvwbs161 foreign key (discipline_id) references users;
alter table teachers_disciplines add constraint FK624c429t7x8rwh3ysb0u0qe7q foreign key (user_id) references edu_disciplines;
alter table users add constraint FKk33209gdm9msrdx5a36cd987h foreign key (department_id) references edu_departments;
alter table users add constraint FKhgadvrj981du2so7v8ibsb0ak foreign key (students_group_id) references edu_studentsgroups;
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles;
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;