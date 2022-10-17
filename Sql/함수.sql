SELECT last_name, UPPER(last_name), LOWER(last_name)
FROM employees;

SELECT first_name
FROM employees
WHERE LOWER(first_name) LIKE '%st%';

SELECT email FROM employees;
SELECT INITCAP(email) FROM employees;

CREATE TABLE members (
    mid varchar(50) NOT NULL,
    mname VARCHAR(50) NOT NULL
);

INSERT INTO members VALUES('fall', '한가을');

SELECT mname, length(mname), lengthb(mname), lengthc(mname) FROM members;

SELECT first_name, substr(first_name, 5), substr(first_name, -3, 2)
FROM employees;

SELECT phone_number, replace(phone_number, ',')
FROM employees;

SELECT first_name, RPAD(substr(first_name, 1, 3), 6, '*')
FROM employees;

SELECT (first_name || ', ' || last_name) as name
FROM employees;

SELECT CONCAT(first_name, CONCAT(',', last_name)) as name
FROM employees;