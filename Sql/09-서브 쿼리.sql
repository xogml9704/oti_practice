SELECT salary FROM employees;
SELECT max(salary) from employees;

SELECT employee_id, first_name
FROM employees
WHERE salary = (SELECT max(salary) FROM employees);

SELECT employee_id, first_name
FROM employees
WHERE department_id IN (
    SELECT department_id FROM departments WHERE location_id=1700
);

-- IN 연산자
SELECT employee_id, first_name, job_id
FROM employees
WHERE salary IN (SELECT max(salary)
    FROM employees
    GROUP BY department_id);
    
-- ANY 연산자
SELECT employee_id, department_id
FROM employees
WHERE department_id = ANY (10, 20);

SELECT employee_id, salary, department_id
FROM employees
WHERE department_id = 100 AND
    salary >= ANY(8000, 9000, 10000);
    
SELECT employee_id, salary, department_id
FROM employees
WHERE department_id = 100 AND
    salary >= ANY(
        SELECT AVG(salary)
        FROM employees
        GROUP BY department_id
);

-- ALL 연산자
SELECT employee_id, salary, department_id
FROM employees
WHERE department_id=100 AND
    salary >= ALL (8000, 9000, 10000);
    
SELECT employee_id, salary, department_id
FROM employees
WHERE department_id = 100 AND
    salary >= ALL (
        SELECT AVG(salary)
        FROM employees
        GROUP BY department_id
);

-- EXISTS 연산자
SELECT employee_id, department_id
FROM employees e
WHERE EXISTS (
    SELECT department_name 
    FROM departments 
    WHERE department_id = (e.department_id + 200)
);

-- 게시물을 전혀 작성하지 않은 멤버의 아이디와 이름을 가져오시오
-- JOIN 방식을 사용해서
SELECT m.mid, m.mname
FROM members m, boards b
WHERE m.mid = b.mid(+)
AND bno IS NULL;

-- SubQuery 방식
SELECT mid, mname
FROM members m
WHERE exists (
    SELECT bno
    FROM boards b
    WHERE b.mid = m.mid
);

-- 근무도시가 시애틀인 사원의 이메일과 전화번호를 가져오시오
-- join
SELECT email, phone_number
FROM employees e ,departments d, locations l
WHERE e.department_id = d.department_id
AND d.location_id = l.location_id
AND city = 'Seattle';

-- Subquery
SELECT email, phone_number
FROM employees e
WHERE department_id IN(
    SELECT department_id
    FROM departments d, locations l
    WHERE d.location_id = l.location_id
    AND city = 'Seattle'
);

SELECT email, phone_number
FROM employees
WHERE department_id IN (
    SELECT department_id
    FROM departments
    WHERE location_id = (
        SELECT location_id
        FROM locations
        WHERE city = 'Seattle'
    )
);

SELECT employee_id, salary, department_id
FROM employees
WHERE (department_id, salary) IN (
    SELECT department_id, max(salary)
    FROM employees
    GROUP BY department_id
);