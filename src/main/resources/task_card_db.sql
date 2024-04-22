INSERT INTO `card-task-db`.auth_role (description,is_disabled,name) VALUES
	 ('ADMIN_ROLE',0,'ADMIN'),
	 ('MEMBER_ROLE',0,'MEMBER');
INSERT INTO `card-task-db`.auth_role_user (user_id,role_id) VALUES
	 (8,1),
	 (11,1),
	 (9,2),
	 (10,2);
INSERT INTO `card-task-db`.auth_user (email,name,password) VALUES
	 ('userman@userman.com	','usermanager','$2a$10$H0pqr2ASpZIFmzA68czx5uo6GABKXskHeu5E4wB/SktfgBOPsps8G'),
	 ('user1@user1.com','User1','$2a$10$H0pqr2ASpZIFmzA68czx5uo6GABKXskHeu5E4wB/SktfgBOPsps8G'),
	 ('user2@user2.com','User2','$2a$10$H0pqr2ASpZIFmzA68czx5uo6GABKXskHeu5E4wB/SktfgBOPsps8G'),
	 ('admin@admin.com','ADMIN','$2a$10$H0pqr2ASpZIFmzA68czx5uo6GABKXskHeu5E4wB/SktfgBOPsps8G');
INSERT INTO `card-task-db`.task_card (color,created_on,description,name,card_status,user_id) VALUES
	 ('#678578','2024-04-22','description','card-name1',0,10),
	 ('#678579','2024-04-22','description','card-name2',0,10);
