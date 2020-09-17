create table OLINGO."A0000Users" (
                                       "Id"               integer NOT NULL constraint a0000users_pk primary key,
                                       "Birthday"         varchar(36),
                                       "BusinessEmail"    varchar(10),
                                       "CitizenServiceNr" varchar(36),
                                       "FullName"         varchar(40),
                                       "Gender"           varchar(10),
                                       "JobFunction"      varchar(36),
                                       "LandlinePhone"    varchar(40),
                                       "MobilePhone"      varchar(40),
                                       "Nationality"      varchar(40),
                                       "Nickname"         varchar(40),
                                       "PrivateEmail"     varchar(40),
                                       "TillDate"         TIMESTAMP WITH TIME ZONE,
                                       "UserName"         varchar(40),
                                       "UserImage"        varchar(40));

INSERT INTO OLINGO."A0000Users" VALUES (0, 'Bd', 'Be', 'Csnr', 'TestActor05', 'M', 'Jf', 'lp', 'mp', 'nat', 'Jan',    'pe', '2020-05-01', 'us', null);
INSERT INTO OLINGO."A0000Users" VALUES (1, 'Bd', 'Be', 'Csnr', 'Fn1', 'M', 'Jf', 'lp', 'mp', 'nat', 'Jan',    'pe', '2020-05-01', 'us', null);
INSERT INTO OLINGO."A0000Users" VALUES (2, 'Bd', 'Be', 'Csnr', 'Fn2', 'M', 'Jf', 'lp', 'mp', 'nat', 'Piet',   'pe', '2020-05-01', 'us', null);
INSERT INTO OLINGO."A0000Users" VALUES (3, 'Bd', 'Be', 'Csnr', 'Fn3', 'M', 'Jf', 'lp', 'mp', 'nat', 'Klaas',  'pe', '2020-05-01', 'us', null);
INSERT INTO OLINGO."A0000Users" VALUES (4, 'Bd', 'Be', 'Csnr', 'Fn4', 'M', 'Jf', 'lp', 'mp', 'nat', 'Willem', 'pe', '2020-05-01', 'us', null);
INSERT INTO OLINGO."A0000Users" VALUES (5, 'Bd', 'Be', 'Csnr', 'Fn5', 'F', 'Jf', 'lp', 'mp', 'nat', 'Anita',  'pe', '2020-05-01', 'us', '../media/Woman_avatar_02.png');