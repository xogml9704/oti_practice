DROP TABLE freeboards;
DROP TABLE members;

-- 방법1
CREATE TABLE members (
    mid VARCHAR(20) PRIMARY KEY,
    mname VARCHAR(20) NOT NULL,
    mage number(3) CHECK (mage>0 and mage<200),
    mgrade CHAR(1) CHECK (mgrade in('A', 'B', 'C'))
);

INSERT INTO members
    VALUES ('winter', '한겨울', 25, 'D');
-------------------------------------------------------------------
DROP TABLE members;
-- 방법2
CREATE TABLE members (
    mid VARCHAR(20) PRIMARY KEY,
    mname VARCHAR(20) NOT NULL,
    mage number(3) NOT NULL,
    mgrade char(1) NOT NULL,
    CONSTRAINT ck_mage CHECK (mage > 0 AND mage < 200),
    CONSTRAINT ck_mgrade CHECK (mgrade IN ('A', 'B', 'C'))
);

-- 방법3
DROP TABLE members;

CREATE TABLE members (
    mid VARCHAR(20) NOT NULL,
    mname VARCHAR(20) NOT NULL,
    mage number(3) NOT NULL,
    mgrade char(1) NOT NULL
);

ALTER TABLE members
    ADD CONSTRAINT pk_mid PRIMARY KEY (mid);
    
ALTER TABLE members
    ADD CONSTRAINT ck_mage CHECK (mage > 0 AND mage < 200);
    
ALTER TABLE members
    ADD CONSTRAINT ck_mgrade CHECK (mgrade IN ('A', 'B', 'C'));