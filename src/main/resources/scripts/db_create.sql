DROP DATABASE if exists blog_db;

CREATE DATABASE if not exists blog_db;

USE blog_db;


CREATE TABLE users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    username VARCHAR(60)  NOT NULL,
    password VARCHAR(60)  NOT NULL,
    email    VARCHAR(200) NOT NULL,
    role     VARCHAR(32)  NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    user_id BIGINT       NOT NULL,
    title   VARCHAR(100) NOT NULL,
    content TEXT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories
(
    id   INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE post_category
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    post_id BIGINT NOT NULL,
    category_id  INT    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);