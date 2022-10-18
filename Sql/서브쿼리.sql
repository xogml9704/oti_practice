SELECT salary FROM employees WHERE first_name='John';

SELECT salary FROM employees WHERE employee_id=110;

SELECT employee_id, salary
FROM employees
WHERE salary > (
    SELECT salary
    FROM employees
    WHERE employee_id = 110
    ORDER BY salary
);

SELECT employee_id, salary, department_id
FROM employees
WHERE department_id IN (
    SELECT department_id
    FROM departments
    WHERE department_id < 30
);

-- 110번 보다 입사날짜가 빠른 사원
SELECT *
FROM employees
WHERE hire_date < (
    SELECT hire_date
    FROM employees
    WHERE employee_id = 110
);

-- 전체 평균 봉급보다 많은 봉급을 가져가는 사원
SELECT *
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);