SELECT email, department_name, city
FROM
    (
    SELECT email, department_id
    FROM employees
    WHERE department_id IN (10, 20)
    ) e,
    (
    SELECT department_id, department_name, location_id
    FROM departments
    ) d,
    (
        SELECT location_id, city
        FROM locations
    ) l
WHERE e.department_id = d.department_id AND
    d.location_id = l.location_id;

--------------------------------------------------------
WITH
e as ( SELECT email, department_id FROM employees WHERE department_id IN (10, 20)),
d as ( SELECT department_id, department_name, location_id FROM departments),
l as ( SELECT location_id, city FROM locations)
SELECT email, department_name, city
FROM e, d, l
WHERE e.department_id = d.department_id AND
    d.location_id = l.location_id;
    
-------------------------------------------------------
SELECT employee_id, department_id, salary
FROM employees e1
WHERE salary > (
    SELECT min(salary) FROM employees e2
    WHERE e2.department_id = e1.department_id
)
ORDER BY department_id, salary
