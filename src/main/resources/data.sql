INSERT IGNORE INTO `card-task-db`.auth_role (description,is_disabled,name) VALUES
	 ('ADMIN_ROLE',0,'ADMIN'),
	 ('MEMBER_ROLE',0,'MEMBER');

INSERT IGNORE INTO `card-task-db`.auth_role_user (user_id,role_id) VALUES
	 (1,1),
	 (2,2),
	 (3,2),
	 (4,1);
INSERT IGNORE INTO `card-task-db`.auth_user (email,name,password) VALUES
	 ('userman@userman.com','usermanager','$2a$10$suErkscQiqeM7Ek1Yv.LJOr9A/qhDhyqZrqqyTkn/ceyq3ag799Vq'),
	 ('user1@user1.com','User1','$2a$10$suErkscQiqeM7Ek1Yv.LJOr9A/qhDhyqZrqqyTkn/ceyq3ag799Vq'),
	 ('user2@user2.com','User2','$2a$10$suErkscQiqeM7Ek1Yv.LJOr9A/qhDhyqZrqqyTkn/ceyq3ag799Vq'),
	 ('admin@admin.com','ADMIN','$2a$10$suErkscQiqeM7Ek1Yv.LJOr9A/qhDhyqZrqqyTkn/ceyq3ag799Vq');
INSERT IGNORE INTO `card-task-db`.task_card (color,created_on,description,name,card_status,user_id) VALUES
	 ('#678578','2024-04-22','description','card-name1',0,10),
	 ('#678579','2024-04-22','description','card-name2',0,10);
