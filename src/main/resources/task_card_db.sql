INSERT INTO `card-task-db`.auth_role (description,is_disabled,name) VALUES
	 ('ADMIN_ROLE',0,'ADMIN'),
	 ('MEMBER_ROLE',0,'MEMBER');
INSERT INTO `card-task-db`.auth_role_user (user_id,role_id) VALUES
	 (4,1),
	 (5,2);
INSERT INTO `card-task-db`.auth_user (email,name,password) VALUES
	 ('ikatanyi@gmail.com','kennedy','$2a$10$nKAqQ5DoVbYHqVCkRYQzfux.zHIbHGH3J5MjXfJDSTs3nDV8SDg3S'),
	 ('doe@gmail.com','John doe','$2a$10$PCiMwv40hJtRnjYatdCf9OJzWTIZ.8cxoBfPj3UHTuEtaT1Dj7OX2');
INSERT INTO `card-task-db`.task_card (color,created_on,description,name,card_status,user_id) VALUES
	 ('#678542','2024-04-22','description','card-name',0,5),
	 ('#678578','2024-04-22','description','card-name1',0,5);
