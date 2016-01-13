use bank;
create table account (
  id int auto_increment,
  number varchar(26),
  balance bigint,
  primary key(id)
);