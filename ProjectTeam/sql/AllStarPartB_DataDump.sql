use ProjectTeam;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Person.csv'
INTO TABLE Person
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Team.csv'
INTO TABLE Team
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Coach.csv'
INTO TABLE Coach
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/AssistantCoach.csv'
INTO TABLE AssistantCoach
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Nominee.csv'
INTO TABLE Nominee
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Player.csv'
INTO TABLE Player
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Game.csv'
INTO TABLE Game
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Evaluation.csv'
INTO TABLE Evaluation
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;

LOAD DATA LOCAL INFILE 'C:/Users/Kenneth/Downloads/ProjectB/AllStarData/Play.csv'
INTO TABLE Play
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
;