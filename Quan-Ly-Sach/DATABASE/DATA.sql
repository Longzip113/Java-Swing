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

ALTER TABLE SACH
ADD CONSTRAINT FK_SACH_NHAXUATBAN FOREIGN KEY(MANXB) REFERENCES KHOA(MANXB);

