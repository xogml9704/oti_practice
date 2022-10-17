drop table employees;

create table employees (
    eno number(4) not null,
    ename varchar(15) not null
);

insert into employees (eno, ename) values (1, '홍길동');
insert into employees (eno, ename) values (2, '감자바');
commit;

select * from employees;

-- 모든 행을 삭제
delete from employees;
rollback;

-- 실제 테이블의 저장 공간을 초기화
-- rollback 대상이 아님.
truncate table employees;