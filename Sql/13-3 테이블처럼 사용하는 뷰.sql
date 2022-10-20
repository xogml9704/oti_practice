CREATE VIEW v_emp
    AS (SELECT employee_id, email, salary FROM employees);
    
SELECT * FROM v_emp;

CREATE VIEW v_emp2 
AS (
    SELECT employee_id, first_name, department_name
    FROM employees e, departments d
    WHERE e.department_id = d.department_id
);

SELECT * FROM v_emp2;

CREATE VIEW v_emp3
AS (
    SELECT employee_id, email, salary
    FROM employees
    WHERE department_id = 30
);

SELECT * FROM v_emp3;

DROP VIEW v_emp3;
-----------------------------------------------------------------------

-- VIEW를 통해서 DML(insert, update, delete) 실행

CREATE TABLE products(
    pno NUMBER PRIMARY KEY,
    pname VARCHAR(50) NOT NULL,
    pprice NUMBER DEFAULT 0 NOT NULL,
    pdate DATE DEFAULT SYSDATE NOT NULL,
    pcompany VARCHAR(50) NULL
);

CREATE OR REPLACE VIEW v_prod
AS (SELECT pno, pprice FROM products);

INSERT INTO v_prod VALUES(1, 1000);
SELECT * FROM products;

------
CREATE OR REPLACE VIEW v_emp3
AS (
    SELECT employee_id, email, salary, department_id
    FROM employees
    WHERE department_id = 30
);

update v_emp3 set department_id=50
WHERE employee_id=114;

SELECT * FROM v_emp3;