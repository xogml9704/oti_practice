-- 생성자 Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   위치:        2022-10-14 15:25:19 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE orderdetails (
    ono NUMBER NOT NULL,
    pno NUMBER NOT NULL,
    qty NUMBER(3) NOT NULL
);

ALTER TABLE orderdetails ADD CONSTRAINT orderdetails_pk PRIMARY KEY ( pno,
                                                                      ono );

CREATE TABLE orders (
    ono    NUMBER NOT NULL,
    odate  DATE NOT NULL,
    userid VARCHAR2(15 BYTE) NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( ono );

CREATE TABLE products (
    pno      NUMBER NOT NULL,
    pname    VARCHAR2(50 BYTE) NOT NULL,
    pprice   NUMBER(10) NOT NULL,
    pcompany VARCHAR2(100 BYTE)
);

ALTER TABLE products ADD CONSTRAINT pprice_ck CHECK ( pprice >= 0 );

ALTER TABLE products ADD CONSTRAINT products_pk PRIMARY KEY ( pno );

CREATE TABLE users (
    userid       VARCHAR2(15 BYTE) NOT NULL,
    username     VARCHAR2(20 BYTE) NOT NULL,
    userpassword VARCHAR2(15 BYTE) NOT NULL,
    usertel      VARCHAR2(15 BYTE),
    useraddr     VARCHAR2(200 BYTE)
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( userid );

ALTER TABLE users ADD CONSTRAINT users__unv2 UNIQUE ( usertel );

ALTER TABLE orderdetails
    ADD CONSTRAINT orderdetails_orders_fk FOREIGN KEY ( ono )
        REFERENCES orders ( ono );

ALTER TABLE orderdetails
    ADD CONSTRAINT orderdetails_products_fk FOREIGN KEY ( pno )
        REFERENCES products ( pno );

ALTER TABLE orders
    ADD CONSTRAINT orders_users_fk FOREIGN KEY ( userid )
        REFERENCES users ( userid );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              9
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
