-- ORDER BY는 WHERE 위에 위치해야 한다.
SELECT email, job_id, hire_date
FROM employees
WHERE department_id = 30
ORDER BY hire_date;

-- 조건식은 AND 또는 OR로 연결할 수 있다.
SELECT email, job_id, salary, hire_date
FROM employees
WHERE department_id = 30 AND salary > 3000
ORDER BY hire_date;

-- 
SELECT email, job_id, salary,department_id, hire_date
FROM employees
WHERE department_id = 30 OR department_id = 20
ORDER BY department_id, hire_date;