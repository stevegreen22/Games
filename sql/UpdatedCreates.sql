Create table gamer_game_category(
gamerid integer not null,
gameid integer not null,
categoryid integer not null,
Primary key(gamerid, gameid));

Create table category(
id integer not null auto_increment, 
name varchar(30) not null, 
value integer not null,
constraint pk primary key(id));

Create Table gaming_group(
id integer not null auto_increment,
name varchar(100) not null,
constraint pk primary key(id));
alter table gaming_group add constraint uniquegamerid Unique(id);


Create Table gaming_group_members(
groupid integer not null,
gamerid integer not null,
constraint pk primary key(groupid, gamerid));

CREATE TABLE game (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `gamer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `active` int(11) NOT NULL,
  PRIMARY KEY (`id`)
)