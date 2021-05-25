
conn system/oracle as sysdba;


grant create table, create sequence to system;


create table students (
  studentId number(6),		
  studentNM varchar2(20),	
  kor number(6),		
  eng number(6),		
  math number(6),		
  science number(6)	
  primary key(studentId)		
);


grant insert,update,delete,select on students to system;


create sequence member_seq
start with 10001
increment by 1
minvalue 10001
cache 10;


grant alter, select on member_seq to system;


commit;


insert into students values(10001, 'aa', 90, 80, 70, 80);
insert into students values(10002, 'bb', 100, 65, 78, 88);
insert into students values(10003, 'cc', 33, 45, 52, 64);
insert into students values(10004, 'dd', 70, 89, 94, 72);
insert into students values(10005, 'ee', 90, 80, 70, 80);
insert into students values(10006, 'ff', 90, 80, 70, 80) ;


commit;


SELECT sum(*) as total 
FROM students  
group by studentId
order by total desc;