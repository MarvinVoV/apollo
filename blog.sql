CREATE DATABASE blog CHARACTER SET utf8;

CREATE TABLE USER (
	user_id VARCHAR (100) NOT NULL UNIQUE,
	user_name VARCHAR (100),
	user_pwd VARCHAR (20),
	ENABLE bit,
	create_date TIMESTAMP
);

CREATE TABLE roles (
	role_id INT NOT NULL UNIQUE,
	role_name VARCHAR (100)
);

CREATE TABLE FUNCTION (
	function_id INT NOT NULL UNIQUE,
	function_name VARCHAR (100),
	parent_id INT
);

CREATE TABLE user_roles (
	user_id VARCHAR (100) NOT NULL,
	role_id INT NOT NULL
);

CREATE TABLE function_roles (
	function_id INT NOT NULL,
	role_id INT NOT NULL
);

CREATE TABLE category (
	category_id INT,
	user_id VARCHAR (50),
	category_name VARCHAR (100),
	category_order INT,
	create_date TIMESTAMP
);

CREATE TABLE articles (
	article_id INT,
	user_id VARCHAR (50),
	category_id INT,
	article_title VARCHAR (1000),
	article_content BLOB,
	create_date TIMESTAMP,
	update_date TIMESTAMP,
	page_view LONG,
	article_order INT,
	top INT
);

CREATE TABLE article_attachment (
	attachment_id INT,
	article_id INT,
	user_id VARCHAR (50),
	attachment BLOB
);

CREATE TABLE article_comment (
	comment_id INT,
	article_id INT,
	user_id VARCHAR (50),
	comment_content LONGTEXT,
	comment_date TIMESTAMP
);