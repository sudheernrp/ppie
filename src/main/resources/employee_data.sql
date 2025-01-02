create table employees(
id int not null AUTO_INCREMENT,
first_name varchar(50) ,
last_name varchar(50) ,
age int ,
email_address varchar(100) ,
PRIMARY KEY (id),
UNIQUE (email_address)
);
insert into employees(first_name,last_name,age,email_address) values ('Sudheer','P','32','sudheer@pie.com');
insert into employees(first_name,last_name,age,email_address) values ('Waseem','A','30','waseem@pie.com');
insert into employees(first_name,last_name,age,email_address) values ('Nitin','K','32','nitin@pie.com');
insert into employees(first_name,last_name,age,email_address) values ('Manju','G','38','manju@pie.com');
insert into employees(first_name,last_name,age,email_address) values ('Sunil','R','33','sunil@pie.com');
