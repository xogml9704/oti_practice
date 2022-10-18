-- 등가 조인(내부 조인 - Inner Join)
SELECT email, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;

-- SQL 92
SELECT email, department_name
FROM employees e 
inner join departments d ON e.department_id = d.department_id;

-- SQL 99
SELECT email, department_name
FROM employees e NATURAL JOIN departments d;

SELECT email, department_name
FROM employees e JOIN departments d USING (department_id);

SELECT email, department_name
FROM employees e join departments d
                 on (e.department_id = d.department_id);
-----------------------------------------------------------                 
--등가 조인 (외부 조인 = Outer Join)
-----------------------------------------------------------

SELECT email, department_name
FROM employees e, departments d
WHERE e.department_id(+) = d.department_id;

SELECT email, department_name
FROM employees e RIGHT OUTER JOIN departments d
ON e.department_id = d.department_id;
----------------------------------------------------------

DROP TABLE boards;
DROP TABLE members;

CREATE TABLE members (
    mid VARCHAR2(10) PRIMARY KEY,
    mname VARCHAR2(20) NOT NULL
);

CREATE TABLE boards (
    bno NUMBER PRIMARY KEY,
    btitle VARCHAR(100) NOT NULL,
    mid VARCHAR2(10) REFERENCES members(mid) ON DELETE CASCADE,
    mname VARCHAR2(20) NOT NULL
);

INSERT INTO members VALUES('spring', '김하늘');
INSERT INTO members VALUES('summer', '하여름');
INSERT INTO members VALUES('fall', '단풍잎');
INSERT INTO members VALUES('winter', '동장군');
commit;

INSERT INTO boards VALUES(1, '제목1', 'spring', '김하늘');
INSERT INTO boards VALUES(2, '제목2', 'fall', '단풍잎');
INSERT INTO boards VALUES(3, '제목3', 'spring', '이바다');
commit;

SELECT m.mid, m.mname, bno, btitle, b.mid, b.mname
FROM members m, boards b
WHERE m.mid = b.mid;

SELECT mid, mname, bno, btitle
FROM members m NATURAL JOIN boards b;