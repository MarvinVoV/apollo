CREATE DATABASE blog
  CHARACTER SET utf8;

CREATE TABLE USER (
  user_id     VARCHAR(100) NOT NULL UNIQUE,
  user_name   VARCHAR(100),
  user_pwd    VARCHAR(20),
  email       VARCHAR(50),
  status      BIT,
  header      BLOB,
  create_date TIMESTAMP
);

CREATE TABLE roles (
  role_id   INT NOT NULL UNIQUE,
  role_name VARCHAR(100)
);

CREATE TABLE FUNCTION (
  function_id   INT NOT NULL UNIQUE,
  function_name VARCHAR(100),
  parent_id     INT
);

CREATE TABLE user_roles (
  user_id VARCHAR(100) NOT NULL,
  role_id INT          NOT NULL
);

CREATE TABLE function_roles (
  function_id INT NOT NULL,
  role_id     INT NOT NULL
);

CREATE TABLE category (
  category_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id        VARCHAR(50),
  category_name  VARCHAR(100),
  category_order INT,
  status         INT,
  create_date    TIMESTAMP
);

-- category order field is auto_increment
CREATE TRIGGER category_order_trigger BEFORE INSERT ON category FOR EACH ROW
  BEGIN
    DECLARE c_order INT;
    SET c_order = (
      SELECT (max(category_id) + 1)
      FROM
        category
    );
    SET NEW.category_order = c_order;
  END;

CREATE TABLE articles (
  id            VARCHAR(50) NOT NULL PRIMARY KEY,
  user_id       VARCHAR(50),
  category_id   INT,
  title         VARCHAR(1000),
  type          VARCHAR(100),
  content       LONGTEXT,
  tags          VARCHAR(1000),
  reference     VARCHAR(1000),
  digest        VARCHAR(1000),
  is_hide       BIT,
  allow_comment BIT,
  auto_index    BIT,
  is_top        BIT,
  create_date   TIMESTAMP,
  update_date   TIMESTAMP,
  page_view     BIGINT,
  article_order INT
);

CREATE TABLE article_attachment (
  id          VARCHAR(36) UNIQUE, -- uuid
  user_id     VARCHAR(50),
  file_type   VARCHAR(20),
  file_name   VARCHAR(200),
  content     LONGBLOB,
  file_size   LONG,
  upload_time TIMESTAMP
);

CREATE TABLE article_comment (
  id VARCHAR (50) NOT NULL PRIMARY KEY,
  parent_id VARCHAR (50),
  article_id VARCHAR (50),
  user_id VARCHAR (50),
  content LONGTEXT,
  comments LONGTEXT,  -- reply comments , json format
  date TIMESTAMP
);


CREATE TABLE relation (
  user_id VARCHAR (100) NOT NULL,
  follower_id VARCHAR (100) NOT NULL,
  rel_type INT COMMENT '1 follow, 2 blacklist',
  create_time TIMESTAMP,
  PRIMARY KEY (user_id, follower_id)
);


INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');

INSERT INTO function VALUES (1, 'test_function', NULL);
INSERT INTO function VALUES (2, 'user_role_function', NULL);

INSERT INTO function_roles VALUES (1, 1);
INSERT INTO function_roles VALUES (2, 2);