----------------------
--ROWNUM // 데이터를 가져오는 순서
----------------------

SELECT employee_id, first_name
FROM employees;

SELECT rownum, employee_id, first_name
FROM employees;

SELECT ROWNUM, employee_id, first_name
FROM employees
ORDER BY employee_id;
--

SELECT ROWNUM, first_name, salary
FROM (
    SELECT first_name, salary
    FROM employees
    ORDER BY salary
);
--
SELECT ROWNUM, first_name, salary
FROM (
    SELECT first_name, salary
    FROM employees
    ORDER BY salary
)
WHERE ROWNUM <= 5;
--
SELECT ROWNUM, first_name, salary
FROM (
    SELECT first_name, salary
    FROM employees
    ORDER BY salary
)
WHERE ROWNUM > 5;
------------------------------------------
--Paging(페이징) 처리
------------------------------------------

SELECT ROWNUM, employee_id, first_name
FROM (
    SELECT ROWNUM as rnum, employee_id, first_name
    FROM (
        SELECT employee_id, first_name
        FROM employees
        ORDER BY employee_id
    )
    WHERE ROWNUM <= 50 --(pageNO*rowsPerPage)
)
WHERE rnum >= 41;--(pageNO-1)*rowPerPage + 1;

/* Pager 설명

[처음] 1, 2, 3, 4, 5 [다음][맨끝]
[처음][이전] 6, 7, 8, 9, 10 [다음][맨끝]
[처음][이전] 11, 12, 13, 14, 15 [다음][맨끝]
...
[처음][이전] 96, 97 [맨끝]

페이지당 행수(rowPerPage) : 10 행
그룹당 페이지 수(pagesPerGroup) : 5 페이지

전체 행수(totalRows) : 973 행

전체 페이지수 : Math.ceil((doble)totalRows / rowsPerPage)
각 페이지 시작 번호 : (page-1) * rowPerPage + 1
각 페이지 끝 번호 : page * rowPerPage
각 그룹의 시작 페이지 번호 : (group-1) * pagesPerGroup + 1
각 그룹의 끝 페이지 번호 : group * pagesPerGroup
*/

