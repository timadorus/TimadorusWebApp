CREATE TABLE "CCLASS"
(CCLASS_ID BIGINT NOT NULL,
CCLASSID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
DESCRIPTION VARCHAR(256),
NAME VARCHAR(256),
PRIMARY KEY (CCLASS_ID));

CREATE TABLE "CHARACTER"
("KEY" BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
CHARCLASS_CCLASS_ID_OID BIGINT,
CHARACTERID VARCHAR(256),
COMPLETE CHAR(1) NOT NULL,
FACTION_FACTION_ID_OID BIGINT,
GENDER VARCHAR(256),
HAIRCOLOR VARCHAR(256),
NAME VARCHAR(256),
POTSTATS LONG VARCHAR FOR BIT DATA,
RACE_RACE_ID_OID BIGINT,
SKINCOLOR VARCHAR(256),
TEMPSTATS LONG VARCHAR FOR BIT DATA,
USERNAME VARCHAR(256),
PRIMARY KEY ("KEY"));

CREATE TABLE "CONFIGURATION"
(ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
"KEY" VARCHAR(256),
VALUE VARCHAR(256),
PRIMARY KEY (ID));

CREATE TABLE "FACTION"
(FACTION_ID BIGINT NOT NULL,
DESCRIPTION VARCHAR(256),
FACTIONID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
NAME VARCHAR(256),
RACE_RACE_ID_OID BIGINT,
PRIMARY KEY (FACTION_ID));

CREATE TABLE "RACE"
(RACE_ID BIGINT NOT NULL,
DESCRIPTION VARCHAR(256),
NAME VARCHAR(256),
RACEID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
PRIMARY KEY (RACE_ID));

CREATE TABLE "SEQUENCE_TABLE"
(SEQUENCE_NAME VARCHAR(255) NOT NULL,
NEXT_VAL BIGINT NOT NULL,
PRIMARY KEY (SEQUENCE_NAME));

CREATE TABLE "SKILL"
(SKILL_ID BIGINT NOT NULL,
ACTIONTYPE VARCHAR(256),
CALCTYPE VARCHAR(256),
COST INT NOT NULL,
DEFLABEL VARCHAR(256),
DESCRIPTION VARCHAR(1024),
ITEM INT NOT NULL,
LEVELBN INT NOT NULL,
LOCALEDESCDEFAULT CHAR,
LOCALEDESCLABEL VARCHAR(256),
LOCALEDESCLANGUAGE VARCHAR(256),
LVLBONUSCAT VARCHAR(256),
NAME VARCHAR(256),
RANK INT NOT NULL,
RKBN INT NOT NULL,
STAT1 VARCHAR(256),
STAT2 VARCHAR(256),
STATBN INT NOT NULL,
TOTAL INT NOT NULL,
SKILLLIST_KEY_OWN BIGINT,
SKILLLIST_INTEGER_IDX INT,
SKILLLISTLEVEL1_KEY_OWN BIGINT,
SKILLLISTLEVEL1_INTEGER_IDX INT,
PRIMARY KEY (SKILL_ID));

CREATE TABLE "STAT"
(STAT_ID BIGINT NOT NULL,
DESCRIPTION VARCHAR(256),
NAME VARCHAR(256),
POTSTAT INT,
TEMPSTAT INT,
STATLIST_KEY_OWN BIGINT,
STATLIST_INTEGER_IDX INT,
PRIMARY KEY (STAT_ID));

CREATE TABLE "USER"
(ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
ACTIVATIONCODE VARCHAR(32),
ACTIVE CHAR(1),
DISPLAYNAME VARCHAR(20),
EMAIL VARCHAR(50),
GEBURTSTAG VARCHAR(10),
NACHNAME VARCHAR(50),
PASSWORD VARCHAR(50),
USERNAME VARCHAR(20),
VORNAME VARCHAR(50),
PRIMARY KEY (ID));

ALTER TABLE "USER"
ADD CONSTRAINT "USER_U1"
UNIQUE ("USERNAME");

ALTER TABLE "USER"
ADD CONSTRAINT "USER_U2"
CHECK (ACTIVE IN ('Y','N') OR ACTIVE IS NULL);

CREATE TABLE "CAMPAIGN"
(CAMPAIGNID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 BESCHREIBUNG VARCHAR(256),
 NAME VARCHAR(256),
 USERNAME VARCHAR(256),
 PRIMARY KEY (CAMPAIGNID));
 
 CREATE TABLE "FRACTION"
(FRACTIONID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 CAMPAIGNNAME VARCHAR(256),
 NAME VARCHAR(256),
 ANZEIGENAME VARCHAR(256),
 BESCHREIBUNG VARCHAR(256),
 INFORMATIONEN VARCHAR(256),
 PRIMARY KEY (FRACTIONID));

