select * from tb_game;
select * from tb_player;
select * from tb_team;
select player."name", team."name" from tb_team_player tb_tp 
	inner join tb_team team on tb_tp.team_id = team.id
	inner join tb_player player on tb_tp.player_id = player.id;
select * from tb_tournament;
select * from tb_team_tournament;
select * from tb_match;