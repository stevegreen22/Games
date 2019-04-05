
select * from Gamer where name like "Steve";

select * from gamer;
select * from game;
insert into game(id, name) values (5, "Hellas");
insert into game(id, name) values(6, "GWT");
select * from Excited;
insert into Excited(id, gamer, game) values(5, 1, 5);
insert into Excited(id, gamer, game) values(6, 1, 6);


select game from Excited
Inner join
(select id as gamerid from Gamer where name like "steve") as gamerid
on Excited.gamer = gamerid;