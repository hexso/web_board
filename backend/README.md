CREATE TABLE board(
    no INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    type TEXT NULL,
    title TEXT NOT NULL,
    contents TEXT NULL,
    member TEXT NOT NULL,
    created_time datetime default NOW(),
    updated_time datetime default NOW(),
    likes INT default 0,
    counts INT default 0
)charset=utf8mb4;

INSERT INTO board(type, title,contents,member) VALUES('자유게시판', '헬로우',' 처음쓰는 내용 및 게시판', 'hacko');