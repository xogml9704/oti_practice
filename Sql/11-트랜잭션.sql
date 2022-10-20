DROP TABLE accounts;

CREATE TABLE accounts (
    ano VARCHAR2(10) PRIMARY KEY,
    owner VARCHAR2(10) NOT NULL,
    balance NUMBER NOT NULL
);

-- 트랜잭션 시작
INSERT INTO accounts VALUES ('111-111', '홍길동', 10000);
INSERT INTO accounts VALUES ('111-112', '감자바', 0);
commit;
--트랜잭션 종료

--트랜잭션 시작
UPDATE accounts SET balance=balance-1000 WHERE ano='111-111';
UPDATE accounts SET balance=balance+1000 WHERE ano='111-112';
rollback;
--트랜잭션 종료

--트랜잭션 시작
INSERT INTO accounts VALUES ('111-113', '홍길서', 10000);
INSERT INTO accounts VALUES ('111-114', '홍길남', 0);
-- ...
savepoint insertedPoint;

UPDATE accounts SET balance=balance-1000 WHERE ano='111-111';
UPDATE accounts SET balance=balance+1000 WHERE ano='111-112';
-- ...
savepoint updatePoint;

DELETE FROM accounts;

-- ...
ROLLBACK TO updatedPoint;
-- 트랜잭션 종료
SELECT * FROM accounts;