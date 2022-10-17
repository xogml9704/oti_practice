SELECT email, salary*12 as yearsal
FROM employees
WHERE salary*12 > 200000;

SELECT first_name
FROM employees
WHERE first_name >= 'A' AND first_name < 'C';

SELECT email, job_id
FROM employees
WHERE job_id != 'IT_PROG' AND job_id <> 'FI_ACCOUNT';

SELECT email, job_id
FROM employees
WHERE NOT (job_id != 'IT_PROG' AND job_id <> 'FI_ACCOUNT');


SELECT email, job_id
FROM employees
WHERE job_id = 'IT_PROG' OR job_id = 'FI_ACCOUNT';

SELECT email, job_id
FROM employees
WHERE job_id IN ('IT_PROG', 'FI_ACCOUNT');

SELECT email, job_id
FROM employees
WHERE NOT job_id IN ('IT_PROG', 'FI_ACCOUNT');

SELECT email, job_id
FROM employees
WHERE job_id NOT IN ('IT_PROG', 'FI_ACCOUNT');

SELECT email, salary
FROM employees
WHERE salary >= 5000 AND salary <= 7000;

SELECT email, salary
FROM employees
WHERE salary BETWEEN 5000 AND 7000;

SELECT first_name
FROM employees
WHERE first_name LIKE '%St%';

SELECT first_name, email
FROM employees
WHERE first_name LIKE '%ev%' OR email LIKE '%ev%';

SELECT email
FROM employees
WHERE email LIKE '_L%';

SELECT employee_id, first_name
FROM employees
WHERE department_id IS NULL;

SELECT employee_id, first_name
FROM employees
WHERE department_id IS NOT NULL;

SELECT * FROM employees WHERE salary IS NULL;
SELECT * FROM employees WHERE commission_pct IS NULL;

SELECT salary * 12 + commission_pct * salary AS yearsal 
FROM employees;

SELECT email, phone_number
FROM employees
WHERE department_id = 10 OR department_id = 20;

SELECT email, phone_number
FROM employees
WHERE department_id IN(10, 20);

SELECT email, phone_number, department_id
FROM employees
WHERE department_id = 10

UNION

SELECT email, phone_number, department_id
FROM employees
WHERE department_id = 20;