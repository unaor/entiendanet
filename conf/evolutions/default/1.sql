# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_user (
  user_id                   bigint not null,
  user_first_name           varchar(255),
  user_surname              varchar(255),
  email                     varchar(255),
  hashed_password           varchar(255),
  is_admin                  boolean,
  business_data_business_id bigint,
  registration_date         timestamp,
  constraint uq_app_user_email unique (email),
  constraint pk_app_user primary key (user_id))
;

create table business_data (
  business_id               bigint not null,
  business_name             varchar(255),
  business_address          varchar(255),
  business_phone            bigint,
  business_fax              bigint,
  business_zip_code         bigint,
  business_state            varchar(255),
  business_country          varchar(255),
  constraint pk_business_data primary key (business_id))
;

create table client (
  client_id                 bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  country                   varchar(255),
  business                  varchar(255),
  constraint uq_client_email unique (email),
  constraint pk_client primary key (client_id))
;

create sequence user_seq;

create sequence business_data_seq;

create sequence client_seq;

alter table app_user add constraint fk_app_user_businessData_1 foreign key (business_data_business_id) references business_data (business_id) on delete restrict on update restrict;
create index ix_app_user_businessData_1 on app_user (business_data_business_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists app_user;

drop table if exists business_data;

drop table if exists client;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

drop sequence if exists business_data_seq;

drop sequence if exists client_seq;

