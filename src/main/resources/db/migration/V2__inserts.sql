insert into roles (name)
values
('ROLE_STUDENT'), ('ROLE_EMPLOYEE'),('ROLE_ADMIN');

insert into users (username, password, email,name,surname,position )
values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'min_obr@gmail.com','Иван','Сидоров','Независимый наблюдатель'), --password: 100
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'rector@gmail.com','Алексей','Иванов','Ректор'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'prorector1@gmail.com','Митрофан','Конюхов','Проректор по финансам'),
('user4', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'prorector2@gmail.com','Михаил','Васильев','Проректор по международной деятельности'),
('user5', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'prorector3@gmail.com','Олег','Дмитриев','Проректор по учебной деятельности'),
('user6', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'dekan1@gmail.com','Ильзат','Алиев','Декан факультета 1'),
('user7', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'dekan2@gmail.com','Алексей','Миранчук','Декан факультета 2'),
('user8', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'dekan3@gmail.com','Василий','Брежнев','Декан факультета 3');

insert into users_roles (user_id, role_id)
values
(1, 2), (1, 3),
(2, 2), (2, 3),
(3, 2), (3, 3),
(4, 2), (4, 3),
(5, 2), (5, 3),
(6, 2);
(7, 2);
(8, 2);

insert into edu_departments(name,codename,elder_id)
values
('Министерство Образования','minobr',1);

insert into edu_departments(name,codename,elder_id,head_id)
values
('Ректорат','rectorate',2,1);

insert into edu_departments(name,codename,elder_id,head_id)
values
('Деканат1','decanate',2,1);

update users SET department_id = 2 where id!=1;

insert into edu_semesterplans(number,start_date,end_date) values (1,'1999-12-11','1999-12-11');