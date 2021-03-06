create database QUANLYSACH;

USE QUANLYSACH;

CREATE TABLE NHAXUATBAN(
	MANXB NCHAR(5),
	TENNXB NVARCHAR(50),
    DIACHI NVARCHAR(50),
    SDT NVARCHAR(11),
	CONSTRAINT PK_NHAXUATBAN PRIMARY KEY(MANXB) 
);

CREATE TABLE SACH(
	MASACH NCHAR(5),
	TENSACH NVARCHAR(50),
    MANXB NCHAR(5),
	CONSTRAINT PK_SACH PRIMARY KEY(MASACH) 
);

CREATE TABLE TAIKHOAN(
	USERNAME NCHAR(20),
	PASSWORD NVARCHAR(20),
    FULLNAME NCHAR(55),
    MAVT NCHAR(20),
	CONSTRAINT PK_TAIKHOAN PRIMARY KEY(USERNAME),
    FOREIGN KEY (MAVT) REFERENCES VAITRO(MAVT)
);

CREATE TABLE VAITRO(
	MAVT NCHAR(20),
	TENVT NVARCHAR(20),
	CONSTRAINT PK_VAITRO PRIMARY KEY(MAVT) 
);

DROP TABLE TAIKHOAN;

ALTER TABLE SACH
ADD CONSTRAINT FK_SACH_NHAXUATBAN FOREIGN KEY(MANXB) REFERENCES NHAXUATBAN(MANXB);


ALTER TABLE TAIKHOAN
ADD CONSTRAINT FK_SACH_NHAXUATBAN FOREIGN KEY(MANXB) REFERENCES NHAXUATBAN(MANXB);

INSERT INTO NHAXUATBAN(MANXB,TENNXB,DIACHI,SDT)
VALUES
('NXB01','Kim Dong',"TP.HCM",'0123456789'),
('NXB02','SachVN',"HANOI",'0123456789'),
('NXB03','SachVN',"HANOI",'0123456789'),
('NXB04','SachVN',"HANOI",'0123456789'),
('NXB05','SachVN',"HANOI",'0123456789'),
('NXB06','SachVN',"HANOI",'0123456789');


INSERT INTO VAITRO(MAVT,TENVT)
VALUES
('ADMIN','Quan Tri'),
('USER','Nguoi dung');


INSERT INTO TAIKHOAN(USERNAME,PASSWORD,FULLNAME,MAVT)
VALUES
('Admin','123456',"Nguyen Thanh Long",'ADMIN'),
('User','123456',"Nguyen Van A",'USER');

INSERT INTO SACH(MASACH,TENSACH,MANXB)
VALUES
('M2','Sach CSS',"NXB01"),
('M3','Sach HTML',"NXB01"),
('M4','Sach JavaScript',"NXB01"),
('M5','Sach Java',"NXB02"),
('M6','Sach PHP',"NXB02"),
('M7','Sach Python',"NXB02");

select * from SACH;
select * from SACH where MANXB = "NXB01";

select * from TAIKHOAN where USERNAME = 'admin' and PASSWORD = '123456';
select * from TAIKHOAN where USERNAME = "admin" and PASSWORD = "123456";
SELECT * FROM NHAXUATBAN;
SELECT * FROM NHAXUATBAN LIMIT 0, 3;
select * from NHAXUATBAN limit 4,2;
DELETE FROM NHAXUATBAN WHERE MANXB = "NXB01";

SELECT count(*) FROM SACH where MANXB = "NXB01";


