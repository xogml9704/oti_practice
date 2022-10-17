-- 컬럼 내용을 연산할 경우에는 별칭을 붙여주는 것이 좋다.
SELECT first_name, salary, salary*12 AS yearsal
FROM employees;