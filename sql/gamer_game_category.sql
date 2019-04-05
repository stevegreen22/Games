SELECT * FROM gametest.Gamer_game_category;


SELECT categoryid, gameid from gamer_game_category 
INNER JOIN (select id as gamer_id from gamer where name like "steve") as gamer_id
ON gamer_game_category.gamerid=gamer_id 
INNER JOIN (select name, id from game) 
on gamer_game_category.gameid=Game.id
order by categoryid asc;

SELECT 
	gamer_game_category.gamerid, 
	gamer_game_category.gameid,
    gamer.name as gamer_name, 
    game.name as game_name
FROM gamer_game_category
    inner JOIN gamer
        ON gamer.id = gamer_game_category.gamerid
    inner JOIN game
        ON game.id = gamer_game_category.gameid
WHERE gamer_game_category.categoryid=1 AND gamer.name like "steve"
order by game.name asc; 


Create table Gamer_game_category(
gamerid integer not null,
gameid integer not null,
categoryid integer not null,
Primary key(gamerid, gameid));


