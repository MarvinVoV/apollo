create database if not exists apollo;

use apollo;
-- table user
create table if not exists apollo.user(
  id int not null auto_increment,
  account varchar(100) not null,
  age int(3),
  gender tinyint,
  status tinyint default 1,
  gmt_create timestamp not null,
  gmt_modified timestamp not null,
  primary key (id)
);

-- article
create table if not exists apollo.article(
  id int not null auto_increment,
  user_id int not null,
  category_id int,
  title varchar(200),
  content blob,
  top tinyint default 0,
  tag varchar(200),
  invisible tinyint default 1,
  status int(1) default 1,
  gmt_create timestamp not null,
  gmt_modified timestamp not null,
  primary key (id)
);

-- category
create table if not exists apollo.category(
  id int not null auto_increment,
  name varchar(200) not null,
  status tinyint default 1,
  gmt_create timestamp not null,
  gmt_modified timestamp not null,
  primary key (id)
);