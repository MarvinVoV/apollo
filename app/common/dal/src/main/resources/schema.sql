create table if not exists article(
  id int not null auto_increment,
  category_id int,
  title varchar(200),
  content longblob,
  top tinyint default 0,
  tag varchar(200),
  invisible tinyint default 1,
  status int(1) default 1,
  pv int,
  gmt_create timestamp not null,
  gmt_modified timestamp not null,
  primary key (id),
  index idx_category_id(category_id)
);
create table if not exists category(
  id int not null auto_increment,
  name varchar(200) not null,
  status tinyint default 1,
  gmt_create timestamp not null,
  gmt_modified timestamp not null,
  primary key (id)
);