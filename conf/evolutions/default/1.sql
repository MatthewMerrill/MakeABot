# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bot_hook (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  maker                         varchar(255),
  hook_url                      varchar(255),
  constraint pk_bot_hook primary key (id)
);

create table forum_comment (
  id                            bigint auto_increment not null,
  parent_post_id                bigint,
  user_id                       bigint,
  message_body                  varchar(255),
  constraint pk_forum_comment primary key (id)
);

create table forum_post (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  user_id                       bigint,
  parent_section_id             bigint,
  constraint pk_forum_post primary key (id)
);

create table forum_section (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  prevent_posts                 tinyint(1) default 0,
  parent_section_id             bigint,
  constraint pk_forum_section primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(16),
  email                         varchar(255),
  constraint pk_user primary key (id)
);

alter table forum_comment add constraint fk_forum_comment_parent_post_id foreign key (parent_post_id) references forum_post (id) on delete restrict on update restrict;
create index ix_forum_comment_parent_post_id on forum_comment (parent_post_id);

alter table forum_comment add constraint fk_forum_comment_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_forum_comment_user_id on forum_comment (user_id);

alter table forum_post add constraint fk_forum_post_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_forum_post_user_id on forum_post (user_id);

alter table forum_post add constraint fk_forum_post_parent_section_id foreign key (parent_section_id) references forum_section (id) on delete restrict on update restrict;
create index ix_forum_post_parent_section_id on forum_post (parent_section_id);

alter table forum_section add constraint fk_forum_section_parent_section_id foreign key (parent_section_id) references forum_section (id) on delete restrict on update restrict;
create index ix_forum_section_parent_section_id on forum_section (parent_section_id);


# --- !Downs

alter table forum_comment drop foreign key fk_forum_comment_parent_post_id;
drop index ix_forum_comment_parent_post_id on forum_comment;

alter table forum_comment drop foreign key fk_forum_comment_user_id;
drop index ix_forum_comment_user_id on forum_comment;

alter table forum_post drop foreign key fk_forum_post_user_id;
drop index ix_forum_post_user_id on forum_post;

alter table forum_post drop foreign key fk_forum_post_parent_section_id;
drop index ix_forum_post_parent_section_id on forum_post;

alter table forum_section drop foreign key fk_forum_section_parent_section_id;
drop index ix_forum_section_parent_section_id on forum_section;

drop table if exists bot_hook;

drop table if exists forum_comment;

drop table if exists forum_post;

drop table if exists forum_section;

drop table if exists user;

