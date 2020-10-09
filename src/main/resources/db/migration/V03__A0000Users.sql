-- SET schema "DEV_GREENTRAK00";
create schema "DEV_GREENTRAK00";

create table DEV_GREENTRAK00.A0000USERS (
                                       ID                 LONGVARBINARY,
                                       "BirthDay"         varchar(36),
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
                                       "UserImage"        varchar(40),
                                       PRIMARY KEY (ID));

INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('04a47a9852374cc8b2fac629bd8f019e', 'Bd', 'Be', 'Csnr', 'TestActor05', 'M', 'Jf', 'lp', 'mp', 'nat', 'Jan',    'pe', '2020-05-01', 'us', null);
INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('4e65220e9332442eb0bcd1b5b16c1db8', 'Bd', 'Be', 'Csnr', 'Fn1', 'M', 'Jf', 'lp', 'mp', 'nat', 'Jan',    'pe', '2020-05-01', 'us', null);
INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('9dfa89d32387455ebe800ed77c200fa3', 'Bd', 'Be', 'Csnr', 'Fn2', 'M', 'Jf', 'lp', 'mp', 'nat', 'Piet',   'pe', '2020-05-01', 'us', null);
INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('a7de5d729e0343419e7da348aaa8eac2', 'Bd', 'Be', 'Csnr', 'Fn3', 'M', 'Jf', 'lp', 'mp', 'nat', 'Klaas',  'pe', '2020-05-01', 'us', null);
INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('caa70247ad7c4b688949edec7e879ae2', 'Bd', 'Be', 'Csnr', 'Fn4', 'M', 'Jf', 'lp', 'mp', 'nat', 'Willem', 'pe', '2020-05-01', 'us', null);
INSERT INTO DEV_GREENTRAK00.A0000USERS VALUES ('f2a3b1deee884b2885729d6afc856116', 'Bd', 'Be', 'Csnr', 'Anita', 'F', 'Jf', 'lp', 'mp', 'nat', 'Anita',  'pe', '2020-05-01', 'us', '../media/Woman_avatar_02.png');

ALTER TABLE OLINGO."AttendanceEventsAll" ADD CONSTRAINT "FK_AttendanceEventsAll_PARENT_ID" FOREIGN KEY ("UserID") REFERENCES DEV_GREENTRAK00.A0000USERS (ID);