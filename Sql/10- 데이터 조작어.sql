DROP TABLE boards;
DROP TABLE members;

CREATE TABLE members (
    mid VARCHAR2(20) primary key,
    mname VARCHAR2(20) NOT NULL,
    memail VARCHAR2(100) UNIQUE NULL,
    mage NUMBER CHECK (mage > 0 AND mage < 300),
    mtel VARCHAR2(20),
    mcity VARCHAR2(20) DEFAULT '서울' NOT NULL
);

INSERT INTO members VALUES('winter', '눈송이', 'snow@mycompany.com', 25, '010-123-1234', '부산');
INSERT INTO members VALUES('spring', '봄동이', null, 25, null, default);
INSERT INTO members (mid, mname) VALUES('summer', '하여름');

SELECT * FROM members;

CREATE TABLE boards(
    bno NUMBER PRIMARY key,
    btitle VARCHAR2(100) NOT NULL,
    bcontent CLOB NOT NULL,
    bwriter VARCHAR2(20) REFERENCES members (mid) ON DELETE CASCADE,
    bdate DATE DEFAULT sysdate NOT NULL
);

INSERT INTO boards VALUES (1, '제목1', '내용1', 'winter', sysdate);
INSERT INTO boards VALUES (2, '제목2', '내용2', 'winter', '2022.12.25');
INSERT INTO boards VALUES (3, '제목3', '내용3', 'summer', DEFAULT);
INSERT INTO boards (bno, btitle, bcontent, bwriter) VALUES (4, '제목4', '내용4', 'summer');
INSERT INTO boards VALUES (5, '제목5', '내용5', 'winter', TO_DATE('12.25.2022', 'MM.DD.YYYY'));
-------------------------------------------------------------------------------------------------

UPDATE members
SET memail='ice@mycompany.com', mage=mage+1, mtel='010-321-4321'
WHERE mid='winter';

UPDATE boards SET bwriter=(SELECT mid FROM members WHERE memail='ice@mycompany.com')
WHERE bwriter='winter';

-----------------------------------------------------------------------------------------------

DELETE FROM boards
WHERE bwriter='winter';

DELETE FROM members
WHERE mid='winter';

DELETE FROM members
WHERE mid IN ('summer', 'fall');