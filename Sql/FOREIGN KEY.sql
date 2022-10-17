DROP TABLE members;
DROP TABLE freeboards;

CREATE TABLE members (
    mid VARCHAR(10) NOT NULL,
    mname VARCHAR(20) NOT NULL
);

ALTER TABLE members
    ADD CONSTRAINT pk_mid PRIMARY KEY (mid);
    
CREATE TABLE freeboards (
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) NOT NULL
);

ALTER TABLE freeboards
 ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
 
 -----------------------------------------------------------------
 
 INSERT INTO members VALUES ('fall', '한겨울');
 INSERT INTO freeboards VALUES (1, '단풍', 'fall');
 INSERT INTO freeboards VALUES (2, '단풍', 'winter'); -- (o)
 
 -----------------------------------------------------------------
 
 DROP TABLE freeboards;
 -- 방법 1
 CREATE TABLE freeboards(
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) CONSTRAINT fk_writer REFERENCES members (mid)
 );
 
 ALTER TABLE freeboards
    ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
    
-- 방법 2
CREATE TABLE freeboards(
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) NOT NULL,
    CONSTRAINT fk_writer FOREIGN KEY (bwriter) REFERENCES members (mid)
);

ALTER TABLE freeboards
    ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
    
-- 방법 3
CREATE TABLE freeboards(
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) NOT NULL
);

ALTER TABLE freeboards
    ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
    
ALTER TABLE freeboards
    ADD CONSTRAINT fk_writer FOREIGN KEY (bwriter) REFERENCES members (mid);
    
 INSERT INTO members VALUES ('fall', '한겨울');
 INSERT INTO freeboards VALUES (1, '단풍', 'fall');
 INSERT INTO freeboards VALUES (2, '단풍', 'winter'); -- (x)    
 
 ------------------------------------------------------------------------------
 
-- 참조하고 있는 거는 삭제할 수 없음
 DELETE FROM members WHERE mid='fall';
 DELETE FROM freeboards WHERE bno=1;
 
-- 방법1
-- 모든 참조하는 키를 포함한 행을 삭제
 CREATE TABLE freeboards(
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) NOT NULL
);

ALTER TABLE freeboards
    ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
    
ALTER TABLE freeboards
    ADD CONSTRAINT fk_writer 
    FOREIGN KEY (bwriter) 
    REFERENCES members (mid) ON DELETE CASCADE;
    
INSERT INTO freeboards VALUES (1, '단풍', 'fall');

DELETE FROM members where mid='fall';

-- 방법2
-- 부분만 NULL 처리
 DROP TABLE freeboards;
 CREATE TABLE freeboards(
    bno NUMBER NOT NULL,
    btitle VARCHAR(50) NOT NULL,
    bwriter VARCHAR(10) NULL
);

ALTER TABLE freeboards
    ADD CONSTRAINT pk_bno PRIMARY KEY (bno);
    
ALTER TABLE freeboards
    ADD CONSTRAINT fk_writer 
    FOREIGN KEY (bwriter) 
    REFERENCES members (mid) ON DELETE SET NULL;
    
INSERT INTO freeboards VALUES (1, '단풍', 'fall');
DELETE FROM members WHERE mid='fall';