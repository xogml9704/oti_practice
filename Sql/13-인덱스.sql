SELECT * FROM members WHERE mname='홍길동';

SELECT * FROM members WHERE mname like '%길동%';

CREATE INDEX ix_member_mname
ON members(mname);

SELECT * FROM members WHERE mtel like '%1234%' AND mcity='서울';

CREATE INDEX ix_member_mtel_mcity
ON members(mtel, mcity);

DROP INDEX ix_member_mtel_mcity;