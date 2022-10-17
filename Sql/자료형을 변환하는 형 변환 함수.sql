SELECT  salary, 
        ROUND(salary/31),
        ROUND(salary/31, 2),
        TRUNC(salary/31),
        TRUNC(salary/31, 2),
        CEIL(salary/31),
        FLOOR(salary/31),
        MOD(salary, 31)
FROM employees;

CREATE TABLE boards (
    bno number primary Key,
    btitle VARCHAR(50) NOT NULL,
    bdate DATE NOT NULL
);

INSERT INTO boards VALUES(1, '제목', sysdate);

SELECT * FROM boards;

-- 날짜 연산 (일수 기준)
SELECT sysdate, sysdate+14 FROM dual;
SELECT TO_DATE('2022-12-31', 'YYYY-MM-dd') - sysdate FROM dual;

-- 날짜 함수
SELECT sysdate, ADD_MONTHS(sysdate, 1) FROM dual;
SELECT MONTHS_BETWEEN(TO_DATE('2022-12-31', 'YYYY-MM-dd'), sysdate) FROM dual;
SELECT sysdate, NEXT_DAY(sysdate, '금') FROM dual;
SELECT sysdate, LAST_DAY(sysdate) FROM dual;

-- 변환 함수
SELECT hire_date fROM employees;
SELECT TO_CHAR(sysdate, 'yyyy.mm.dd hh24:mi:ss am') FROM employees;
SELECT TO_CHAR(salary, 'L99,999.00') FROM employees;
INSERT INTO boards VALUES(2, '제목2', '2022.10.17');
SELECT * FROM boards WHERE bdate='2022.10.17';
SELECT * FROM boards WHERE bdate >= '2022-10-16';
SELECT TO_DATE('2022.12.31', 'yyyy.mm.dd') - sysdate from dual;

