-- 봉급액 순으로 오름차순으로 사원번호와 봉급 가져오기
SELECT employee_id, salary
FROM employees
ORDER BY salary ASC;

-- 봉급액 순으로 내림차으로 사원번호와 봉급 가져오기
SELECT employee_id, salary
FROM employees
ORDER BY salary DESC;

-- 부서별로 올림차순으로 정렬하고,
-- 같은 부서내에서 봉급을 내림차순으로 가져오시오
SELECT department_id, salary
FROM employees
ORDER BY department_id ASC, salary DESC;