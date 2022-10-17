SELECT 10 + NVL(NULL, 0) FROM dual;
SELECT salary*12 + NVL(commission_pct, 0)*salary
FROM employees;

SELECT salary*12 + NVL2(commission_pct, commission_pct*salary, 0)
FROM employees;

