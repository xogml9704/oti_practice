SELECT email, phone_number, department_name
FROM employees, departments
WHERE employees.department_id = departments.department_id;

SELECT email, phone_number, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;

SELECT employee_id, email, phone_number, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;
SELECT *
FROM employees e, departments d
WHERE e.department_id = d.department_id;


SELECT email, phone_number, e.department_id, d.department_id, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;
----------------------------------------------------------

SELECT email, phone_number, e.department_id, department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id
    AND e.department_id = 20
    AND salary > 100;
-------------------------------------------------------------
SELECT employee_id, phone_number, city
FROM employees e, departments d, locations l
WHERE e.department_id = e.department_id
AND d.location_id = l.location_id;

--------------------------------------------------------------

SELECT employee_id, phone_number, region_name
FROM employees e, departments d, locations l, countries c, regions r
WHERE e.department_id = e.department_id
AND d.location_id = l.location_id
AND l.country_id = c.country_id
AND c.region_id = r.region_id;
---------------------------------------------------------------

SELECT e1.employee_id as 사번, e1.first_name as 이름, e2.first_name as "관리자 이름"
FROM employees e1, employees e2
WHERE e1.employee_id = e2.manager_id;
----------------------------------------------------------------

CREATE TABLE grade (
    grade_id CHAR(1) primary key,
    low_salary NUMBER(8, 2) NOT NULL,
    hi_salary NUMBER(8, 2) NOT NULL
);

INSERT INTO grade VALUES('A', 3001, 9999);
INSERT INTO grade VALUES('B', 2001, 3000);
INSERT INTO grade VALUES('C', 1401, 2000);
INSERT INTO grade VALUES('D', 1201, 1400);
INSERT INTO grade VALUES('E', 700, 1200);
commit;

---------------------------------------------------------------

SELECT employee_id, salary, grade_id
FROM employees e, grade g
WHERE e.salary BETWEEN g.low_salary AND g.hi_salary;