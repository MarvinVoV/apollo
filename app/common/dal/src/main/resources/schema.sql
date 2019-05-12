create table if not exists articles
(
  id           int       not null auto_increment comment 'primary key',
  category_id  int comment 'article"s category id',
  ref_note_id  int comment 'reference YuQue note ID',
  ref_book_id  int comment 'reference YuQue book ID',
  title        varchar(200) comment 'title',
  digest       longtext comment 'digest',
  content_md   longblob comment 'markdown format data',
  content_html longblob comment 'html format data',
  top          tinyint default 0 comment 'stick flag',
  tag          varchar(200) comment 'some tags',
  invisible    tinyint default 1 comment '0 as invisible, 1 as visible',
  status       int(1)  default 1 comment '0 as invalid, 1 as valid',
  view_count   int comment 'view count',
  like_count   int comment 'like count',
  gmt_create   timestamp not null comment 'create time',
  gmt_modified timestamp not null comment 'modified time',
  primary key (id),
  index idx_category_id (category_id)
);
create table if not exists categories
(
  id           int          not null auto_increment,
  name         varchar(200) not null,
  status       tinyint default 1,
  gmt_create   timestamp    not null,
  gmt_modified timestamp    not null,
  primary key (id)
);