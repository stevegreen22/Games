Create table Category(
id integer not null auto_increment, 
name varchar(30) not null, 
value integer not null,
constraint pk primary key(id));

insert into category(id, name, value) values (1, "Excited", 5);
insert into category(id, name, value) values (2, "Want", 3);
insert into category(id, name, value) values (3, "Like", 2);
insert into category(id, name, value) values (4, "lolnope", -10);


select * from category;