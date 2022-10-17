DROP TABLE members;

CREATE TABLE members (
    mid VARCHAR(20) PRIMARY KEY,
    mname VARCHAR(20) NOT NULL,
    mdate DATE DEFAULT sysdate NOT NULL,
    mcity VARCHAR(10) DEFAULT '서울' NULL,
    mgrade CHAR(1) DEFAULT 'C' CHECK(mgrade IN ('A', 'B', 'C'))
);

INSERT INTO members
    VALUES('winter', '홍길동', DEFAULT, DEFAULT, DEFAULT);
    
INSERT INTO members (mid, mname)
    VALUES('summer', '하여름');
    
INSERT INTO members (mid, mname, mcity)
    VALUES('fall', '하여름', null);

SELECT * FROM members;