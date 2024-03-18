SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Reply;
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Deal;
DROP TABLE IF EXISTS Favorite;
DROP TABLE IF EXISTS Building;
DROP TABLE IF EXISTS Notice;
DROP TABLE IF EXISTS User;




/* Create Tables */

CREATE TABLE Building
(
	bid int NOT NULL AUTO_INCREMENT,
	-- 시군구 + 읍면 + 동리
	location varchar(32) COMMENT '시군구 + 읍면 + 동리',
	bname varchar(32),
	btype varchar(8),
	address varchar(128),
	addressRoad varchar(128),
	number varchar(6),
	approve varchar(8),
	PRIMARY KEY (bid)
);


CREATE TABLE Comment
(
	cid int NOT NULL AUTO_INCREMENT,
	bid int NOT NULL,
	uid varchar(16) NOT NULL,
	content varchar(300),
	file varchar(300),
	regDateTime datetime DEFAULT (CURRENT_TIME),
	PRIMARY KEY (cid)
);


CREATE TABLE Deal
(
	did int NOT NULL AUTO_INCREMENT,
	bid int NOT NULL,
	bname varchar(32),
	area double,
	checkYear varchar(6),
	checkDay int,
	money int,
	dong varchar(8) DEFAULT '0',
	floor int DEFAULT 0,
	builtYear int DEFAULT 2024,
	PRIMARY KEY (did)
);


CREATE TABLE Favorite
(
	fid int NOT NULL AUTO_INCREMENT,
	uid varchar(16) NOT NULL,
	bid int NOT NULL,
	PRIMARY KEY (fid)
);


CREATE TABLE Notice
(
	nid int NOT NULL AUTO_INCREMENT,
	title varchar(30),
	content varchar(300),
	regDate date DEFAULT (CURRENT_DATE),
	isDeleted int DEFAULT 0,
	PRIMARY KEY (nid)
);


CREATE TABLE Reply
(
	rid int NOT NULL AUTO_INCREMENT,
	cid int NOT NULL,
	bid int NOT NULL,
	uid varchar(16) NOT NULL,
	content varchar(300),
	regDateTime datetime DEFAULT (CURRENT_TIME),
	PRIMARY KEY (rid)
);


CREATE TABLE User
(
	uid varchar(16) NOT NULL,
	pwd varchar(32) NOT NULL,
	uname varchar(32) NOT NULL,
	email varchar(64),
	regDate date DEFAULT (CURRENT_DATE),
	location varchar(32),
	tel varchar(16),
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE Comment
	ADD FOREIGN KEY (bid)
	REFERENCES Building (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Deal
	ADD FOREIGN KEY (bid)
	REFERENCES Building (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Favorite
	ADD FOREIGN KEY (bid)
	REFERENCES Building (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (bid)
	REFERENCES Building (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (cid)
	REFERENCES Comment (cid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Comment
	ADD FOREIGN KEY (uid)
	REFERENCES User (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Favorite
	ADD FOREIGN KEY (uid)
	REFERENCES User (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (uid)
	REFERENCES User (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



