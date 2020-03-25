CREATE TABLE users
(
   id integer not null primary key,
   username   varchar(60) not null unique,
   password varchar (60) not null,
   role varchar (10) not null,
   active boolean not null
);
