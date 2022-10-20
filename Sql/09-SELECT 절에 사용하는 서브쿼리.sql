SELECT employee_id, first_name, job_id,
        salary, grade_id, department_id
FROM employees, grade
WHERE salary BETWEEN low_salary AND hi_salary
    AND department_id = 20;
    
SELECT employee_id, first_name, job_id, salary, department_id, grade_id
FROM
    (
            SELECT employee_id, first_name, job_id, salary, department_id
            FROM employees
            WHERE department_id = 30
    ) s,
    grade g
WHERE salary BETWEEN low_salary AND hi_salary;

----------------------------------------------------------
SELECT employee_id, first_name, job_id, salary, department_id,
    (SELECT grade_id FROM grade WHERE e.salary BETWEEN low_salary AND hi_salary) AS grade_id
FROM employees e
WHERE department_id = 30;
-----------------------------------------------------------
SELECT employee_id, first_name, job_id, department_name
FROM employees
WHERE job_id = 'ST_CLERK';
-- 방법1 Join
SELECT employee_id, first_name, job_id, e.department_id, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id AND
    job_id = 'ST_CLERK';
    
-- 방법2 FROM 서브 쿼리 테이블
SELECT employee_id, first_name, job_id, e.department_id, department_name
FROM (SElECT employee_id, first_name, job_id, department_id FROM employees WHERE job_id = 'ST_CLERK') e,
        departments d
WHERE e.department_id = d.department_id;

-- 방법3 SELECT 서브 쿼리 테이블
SELECT employee_id, first_name, job_id,
    department_id, (SELECT department_name FROM departments WHERE e.department_id = department_id) department_name
FROM employees e
WHERE job_id = 'ST_CLERK';