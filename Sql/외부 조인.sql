SELECT employee_id, e.department_id, d.department_id, department_name
FROM employees e, departments d
WHERE e.department_id(+) = d.department_id;

-- 사원이 없는 부서의 번호와 이름
SELECT d.department_id, department_name
FROM employees e, departments d
WHERE e.department_id(+) = d.department_id AND
    employee_id IS NULL;

