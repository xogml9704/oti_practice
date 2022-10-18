SELECT department_id, salary
FROM employees
WHERE department_id IN (10, 20, 30)
ORDER BY department_id;

SELECT department_id, sum(salary)
FROm employees
WHERE department_id IN (10, 20, 30)
GROUP BY department_id
ORDER BY department_id;

SELECT department_id, job_id, max(salary)
FROM employees
WHERE department_id IN (10, 20, 30)
GROUP BY department_id, job_id
ORDER BY department_id;

SELECT department_id, avg(salary)
FROM employees
WHERE department_id IN (10, 20, 30)
GROUP BY department_id
HAVING department_id = 10
ORDER BY department_id;