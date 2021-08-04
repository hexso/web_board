CREATE TABLE IF NOT EXISTS `test`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `nickname` VARCHAR(30) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profile_url` VARCHAR(512) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC))

CREATE TABLE IF NOT EXISTS `test`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `contents` VARCHAR(512) NULL,
  `created_time` DATETIME NOT NULL DEFAULT now(),
  `updated_time` DATETIME NOT NULL DEFAULT now(),
  `likes` INT NULL DEFAULT 0,
  `readcnt` INT NULL DEFAULT 0,
  `type` VARCHAR(32) NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC),
  CONSTRAINT `author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `test`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `test`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contents` VARCHAR(256) NOT NULL,
  `author_id` INT NOT NULL,
  `post_id` INT NOT NULL,

`updated_time` DATETIME NOT NULL DEFAULT now(),

  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC),
  INDEX `post_id_idx` (`post_id` ASC),
  CONSTRAINT `comment_author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `test`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `test`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `test`.`multimedia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `type` VARCHAR(16) NOT NULL,
  `url` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `multimedia_idx` (`post_id` ASC),
  CONSTRAINT `multimedia_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `test`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



INSERT INTO users(username, nickname, password) VALUES('JunhoKim','nickname','1234');

INSERT INTO posts(title, contents, author_id) VALUES('첫번째 글','helloworld', '1');

INSERT INTO comments(contents, author_id, post_id) VALUES('첫번째 댓글', '1', '1');

INSERT INTO multimedia(post_id, type, url) VALUES('1','photo','https://t1.daumcdn.net/cfile/tistory/240814485574155029');