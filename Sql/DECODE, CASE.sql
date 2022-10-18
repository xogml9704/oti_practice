-- DECODE
SELECT employee_id, first_name, job_id, salary,
    ROUND(DECODE(job_id,
            'AD_VP', salary*1.1,
            'FI_MGR', salary*1.3,
            salary*1.2)) as upsal
FROM employees
WHERE job_id IN ('AD_VP', 'FI_MGR', 'PU_MAN');

-- CASE
SELECT employee_id, first_name, job_id, salary,
    ROUND(
     CASE job_id
        WHEN 'AD_VP' THEN salary*1.1
        WHEN 'FI_MGR' THEN salary*1.3
        ELSE salary * 1.2
     END
    ) AS upsal
FROM employees
WHERE job_id IN ('AD_VP', 'FI_MGR', 'PU_MAN');

-- 원본 데이터가 없는 CASE (조건식이 와야함)
SELECT employee_id, first_name, commission_pct,
    CASE
        WHEN commission_pct IS NULL THEN '해당사항없음'
        WHEN commission_pct > 0 THEN '수당: ' || commission_pct
    END AS comm_text
FROM employees;