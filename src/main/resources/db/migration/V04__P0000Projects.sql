create table DEV_GREENTRAK00.P0000PROJECTS(
                                     "Id"          INTEGER not null constraint P0000PROJECTS_PK primary key,
                                     "StartDate"   TIMESTAMP WITH TIME ZONE,
                                     "ProjectName" VARCHAR(20),
                                     "ProjectCode" VARCHAR(20),
                                     "ProjOwner"   VARCHAR(20),
                                     "Description" VARCHAR(320)
);

create table DEV_GREENTRAK00."P0000ProjMembers"(
                                          "P0000ProjectId" INTEGER,
                                          "A0000UserId"    LONGVARBINARY
);

INSERT INTO DEV_GREENTRAK00."P0000ProjMembers" VALUES (0, 'f2a3b1deee884b2885729d6afc856116');

INSERT INTO DEV_GREENTRAK00.P0000PROJECTS VALUES ( 0, '2020-05-16 12:00:00.000000+02:00', 'Zuidasdok', 'ZUIDPLUS00', 'Heijmans Infra',
'De verbreding van 4 naar 6 rijstroken en ondergronds brengen van Rijksweg A10 Zuid;
De herinrichting van de verkeersknooppunten Amstel en De Nieuwe Meer;
Het uitbreiden en vernieuwen van het station Amsterdam Zuid;
Het opnieuw inrichten van het stationsgebied.');
