insert into Gamer_game_category(gamerid, gameid, categoryid) values (1, 5, 1);
insert into Gamer_game_category(gamerid, gameid, categoryid) values (1, 6, 1);

select * from gamer;
select * from game;
select * from Excited;
select * from category;
select * from Gamer_game_category;




alter table gming_group add constraint uniquegamerid Unique(gamerid)
