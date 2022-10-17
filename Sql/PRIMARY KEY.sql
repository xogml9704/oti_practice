DROP TABLE members;

CREATE TABLE members(
    mid VARCHAR(10) PRIMARY KEY,
    memail varchar(50) NULL
);

INSERT INTO members VALUES ('winter', '한겨울');
INSERT INTO members VALUES ('winter', '한겨울'); -- (X)
INSERT INTO members VALUES (NULL, '한겨울'); -- (x)

-------------------------------------------------------------------

DROP TABLE members;

CREATE TABLE members(
    mid VARCHAR(10) NOT NULL,
    memail varchar(50) NULL
);
-- 방법 1
ALTER TABLE members
    MODIFY mid CONSTRAINT pk_mid PRIMARY KEY;
    
-- 방법2 (가장 많이 사용됨)
ALTER TABLE members
    ADD CONSTRAINT pk_mid PRIMARY KEY (mid);
    
DROP TABLE members;
-- 방법 3
CREATE TABLE members(
    mid VARCHAR(10) NOT NULL,
    memail varchar(50) NULL,
    CONSTRAINT pk_mid PRIMARY KEY (mid),
    CONSTRAINT u_email UNIQUE (memail)
);
    
-----------------------------------------------------------------

DROP TABLE members;

CREATE TABLE members(
    first_no VARCHAR(6) NOT NULL,
    second_no VARCHAR(7) NOT NULL,
    memail VARCHAR(50) NULL
);

ALTER TABLE members
    MODIFY (
        first_no CONSTRAINT pk_mid PRIMARY KEY,
        second_no CONSTRAINT pk_mid PRIMARY KEY
    );  -- (x)
-- (복합 키 생성)
ALTER TABLE members
    ADD CONSTRAINT pk_ssn PRIMARY KEY (first_no, second_no);

