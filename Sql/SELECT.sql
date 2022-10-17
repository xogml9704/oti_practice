-- 전체 사원의 정보 가져오기
SELECT * FROM employees;

-- 전체 사원의 특정 컬럼 정보 가져오기
SELECT employee_id, first_name, last_name, department_id
FROM employees;

-- 사원들이 소속되어 있는 부서 번호 가져오기(중복을 제거)
SELECT DISTINCT department_id FROM employees;

-- 직무번호와 부서번호를 결합해서 중복을 제거
SELECT DISTINCT job_id, department_id from employees;

SELECT job_id, department_id from employees;
SELECT ALL job_id, department_id from employees;