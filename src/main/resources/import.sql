insert into ACCOUNT values(1, 'mi','female','new','123','mi@gmail.com');
insert into ACCOUNT values(2, 'mj','female','new','123','mj@gmail.com');
insert into ACCOUNT values(3, 'mm','female','new','123','mm@gmail.com');

CREATE TABLE ACCOUNT(ID LONG PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255),GENDER VARCHAR(255),ROLE VARCHAR(255),PHONE VARCHAR(255), EMAIL VARCHAR(255));
insert into ACCOUNT (NAME,GENDER,ROLE,PHONE,EMAIL) values('mi','female','new','123','mi@gmail.com');
insert into ACCOUNT (NAME,GENDER,ROLE,PHONE,EMAIL) values('mj','female','new','123','mj@gmail.com');
insert into ACCOUNT (NAME,GENDER,ROLE,PHONE,EMAIL) values('mm','female','new','123','mm@gmail.com');
