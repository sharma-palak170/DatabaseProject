Create Schema ProjectTeam;

use ProjectTeam;

Create table Person(
SSN char(9),
address varchar(200),
firstName varchar(200),
lastName varchar(200),
birthdate date,
yearsUniversity integer,
universityName varchar(50),
primary key(SSN)
);

Create table Team(
teamID integer,
teamName varchar(200),
gamesWon integer,
gamesLost integer,
universityName varchar(200),
teamRank integer,
primary key (teamID)
);

Create table Coach(
CSSN char(9),
yearsCoaching integer,
teamsWon integer,
teamsInSF integer,
CteamID integer,
primary key(CSSN),
foreign key (CSSN) references Person(SSN) on delete cascade,
foreign key (CteamID) references Team(teamID) on delete set null
);

Create table AssistantCoach(
ASSN char(9),
yearsAsstCoach integer,
specialization varchar(200),
primary key(ASSN),
foreign key (ASSN) references Person(SSN) on delete cascade
);

Create table Nominee(
NSSN char(9),
teamSelection varchar(200),
yearsTeam integer,
position varchar(200),
primary key(NSSN),
foreign key (NSSN) references Person(SSN) on delete cascade
);

Create table Player(
PSSN char(9),
PteamID integer,
primary key(PSSN),
foreign key (PSSN) references Nominee(NSSN) on delete cascade,
foreign key (PteamID) references Team(teamID) on delete set null
);

Create table Game(
gameID integer,
gameDate date,
place varchar(200),
winningTeam varchar(200),
winningScore integer,
losingScore integer,
primary key(gameID)
);

Create table Evaluation(
evaluationID integer,
CoachSSN char(9),
NomineeSSN char(9),
ACoachSSN char(9),
evaluationDate date,
evaluationScore integer,
primary key(evaluationID),
foreign key (CoachSSN) references Coach(CSSN) on delete set null,
foreign key (NomineeSSN) references Nominee(NSSN) on delete cascade,
foreign key (ACoachSSN) references AssistantCoach(ASSN) on delete set null
);

Create table Play(
playteamID integer,
playgameID integer,
primary key(playteamID, playgameID),
foreign key (playteamID) references Team(teamID) on delete cascade,
foreign key (playgameID) references Game(gameID) on delete cascade
);







