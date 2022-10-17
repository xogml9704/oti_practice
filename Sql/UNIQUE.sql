drop table member;

create table member (
    mid varchar(10) UNIQUE NOT NULL,
    memail varchar(50) CONSTRAINT u_memail UNIQUE,
    mname varchar(10) not null
);

insert into member values ('fall', 'fall@mycompamy.com', '홍길동');

select * from member;

----------------------------------------------------------------------

drop table member;

create table member(
    mid varchar(10) not null,
    memail varchar(10) null,
    mname varchar(10) not null
);

ALTER TABLE member
    MODIFY (
        mid CONSTRAINT u_mid UNIQUE,
        memail CONSTRAINT u_memail UNIQUE
    );
    
ALTER TABLE member
    DROP CONSTRAINT u_memail;