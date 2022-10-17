/*##############################

// 테이블 생성 및 수정, 이름 변경

##############################*/
-- 테이블 생성
create table emp_ddl (
    empno number(4) not null,
    ename varchar2(10) not null
);

insert into emp_ddl (empno, ename) values(1, 'winter');
insert into emp_ddl (empno, ename) values(2, 'summer');
commit;

-- 기존의 데이터가 있는 테이블에서 컬럼을 새로 추가할 떄에는
-- null로 허용하도록 해야 함
-- 데이터를 채우고 후에 not null로 변경해야 함.
alter table emp_ddl
    add (
        city varchar(10) null,
        age number(3) null
    );
    
drop table emp_ddl;

-- 테이블의 이름 변경    
alter table emp_ddl rename to employees;

-- 컬럼 이름 변경
alter table employees rename column city to ecity;

alter table employees 
    rename column age to eage;
    
-- 컬럼의 데이터 타입 변경
alter table employees
    modify (
        ename varchar2(15),
        ecity varchar2(20)
    );

-- 컬럼 삭제하기
alter table employees
    drop column eage;
    
-- 컬럼 삭제하기 (복수)
alter table employees
    drop (ename, ecity);