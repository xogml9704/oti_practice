--------------------------------
-- Sequence (시퀀스)
--------------------------------
DROP TABLE boards;
CREATE TABLE boards (
    bno NUMBER PRIMARY KEY,
    btitle VARCHAR(100) NOT NULL,
    bcontent CLOB NOT NULL
);

-- Sequence 생성
CREATE SEQUENCE seq_boards_bno;

SELECT seq_boards_bno.NEXTVAL FROM dual;
SELECT seq_boards_bno.CURRVAL FROM dual;

INSERT INTO boards VALUES (seq_boards_bno.nextval, '제목', '내용');
SELECT * FROM boards;

CREATE SEQUENCE seq_test1;
SELECT seq_test1.nextval from dual;