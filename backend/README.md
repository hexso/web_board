CREATE TABLE `users` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL COLLATE 'utf8mb4_general_ci',
`nickname` VARCHAR(30) NOT NULL COLLATE 'utf8mb4_general_ci',
`password` VARCHAR(45) NOT NULL COLLATE 'utf8mb4_general_ci',
`profile_url` VARCHAR(512) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
`authority` INT(11) NOT NULL DEFAULT '1',
PRIMARY KEY (`id`) USING BTREE,
UNIQUE INDEX `nickname_UNIQUE` (`nickname`) USING BTREE
)

CREATE TABLE `posts` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`title` VARCHAR(45) NOT NULL COLLATE 'utf8mb4_general_ci',
`contents` VARCHAR(512) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
`created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`likes` INT(11) NULL DEFAULT '0',
`readcnt` INT(11) NULL DEFAULT '0',
`type` VARCHAR(32) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
`top_image_url` VARCHAR(512) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
`author_id` INT(11) NOT NULL,
PRIMARY KEY (`id`) USING BTREE,
INDEX `author_id_idx` (`author_id`) USING BTREE,
CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `test`.`users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=11
;

CREATE TABLE `comments` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`contents` VARCHAR(256) NOT NULL COLLATE 'utf8mb4_general_ci',
`author_id` INT(11) NOT NULL,
`post_id` INT(11) NOT NULL,
`updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) USING BTREE,
INDEX `author_id_idx` (`author_id`) USING BTREE,
INDEX `post_id_idx` (`post_id`) USING BTREE,
CONSTRAINT `comment_author_id` FOREIGN KEY (`author_id`) REFERENCES `test`.`users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `test`.`posts` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;


CREATE TABLE `multimedia` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`post_id` INT(11) NOT NULL,
`type` VARCHAR(16) NOT NULL COLLATE 'utf8mb4_general_ci',
`url` VARCHAR(512) NOT NULL COLLATE 'utf8mb4_general_ci',
PRIMARY KEY (`id`) USING BTREE,
INDEX `multimedia_idx` (`post_id`) USING BTREE,
CONSTRAINT `multimedia_id` FOREIGN KEY (`post_id`) REFERENCES `test`.`posts` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=8
;



INSERT INTO users(username, nickname, password) VALUES('JunhoKim','nickname','1234');

INSERT INTO posts(title, contents, author_id) VALUES('첫번째 글','helloworld', '1');

INSERT INTO comments(contents, author_id, post_id) VALUES('첫번째 댓글', '1', '1');

INSERT INTO multimedia(post_id, type, url) VALUES('1','photo','https://t1.daumcdn.net/cfile/tistory/240814485574155029');