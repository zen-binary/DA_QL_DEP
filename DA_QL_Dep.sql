﻿CREATE DATABASE DA_QL_DEP
GO

USE DA_QL_DEP
GO

DROP DATABASE DA_QL_DEP

--ChucVu
CREATE TABLE CHUCVU(
Id INT PRIMARY KEY IDENTITY(1,1),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50)
)

--NguoiDung
CREATE TABLE NGUOIDUNG(
Id INT PRIMARY KEY IDENTITY(1,1),
IdCV INT,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50),
NgaySinh DATE,
Email VARCHAR(MAX),
Sdt VARCHAR(20),
GioiTinh INT,
DiaChi NVARCHAR(100),
MatKhau VARCHAR(MAX),
NgayTao DATE,
NgaySua DATE,
TrangThai INT,
HinhAnh NVARCHAR(MAX)
)

--Dep
CREATE TABLE DEP(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	HinhAnh NVARCHAR(50),
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--LoaiDep
CREATE TABLE LOAIDEP(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--Size
CREATE TABLE SIZE(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	KichCo FLOAT,
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--NSX
CREATE TABLE NSX(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--ChatLieu
CREATE TABLE CHATLIEU(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--ChatLieu
CREATE TABLE MAUSAC(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	MauSac NVARCHAR(50),
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)


--ChiTietDep
CREATE TABLE CHITIETDEP(
	Id INT PRIMARY KEY IDENTITY(1,1),
	MaVach VARCHAR(20) UNIQUE,
	HinhAnh NVARCHAR(MAX),
	IdDep INT,
	IdLoaiDep INT,
	IdSize INT,
	IdMauSac INT,
	IdChatLieu INT,
	IdNSX INT,
	SoLuong INT,
	GiaNhap FLOAT,
	GiaBan FLOAT,
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)

--KhuyenMai
CREATE TABLE KHUYENMAI(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	PhanTramGiam FLOAT,
	NgayBatDau DATE,
	NgayKetThuc DATE,
	TinhTrang INT,
	MoTa NVARCHAR(MAX)
)

--KhachHang
CREATE TABLE KHACHHANG(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	Ten NVARCHAR(50),
	Sdt VARCHAR(20),
	DiemTichLy INT,
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT,
)



--HoaDon
CREATE TABLE HOADON(
	Id INT PRIMARY KEY IDENTITY(1,1),
	Ma VARCHAR(20) UNIQUE,
	IdND INT,
	IdKH INT,
	IdKM INT,
	TongTien FLOAT,
	ThanhTien FLOAT,
	NgayThanhToan DATE,
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)
DROP TABLE dbo.HOADONCHITIET
--HoaDonChiTiet
CREATE TABLE HOADONCHITIET(
	Id INT PRIMARY KEY IDENTITY(1,1),
	IdHD INT,
	IdCTD INT,
	DonGia FLOAT,
	SoLuong INT,
	NgayTao DATE,
	NgaySua DATE,
	TinhTrang INT
)
GO

ALTER TABLE dbo.NGUOIDUNG ADD CONSTRAINT FK_ND_cV FOREIGN KEY(IdCV) REFERENCES dbo.CHUCVU(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_MS FOREIGN KEY(IdMauSac) REFERENCES dbo.MAUSAC(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_CL FOREIGN KEY(IdChatLieu) REFERENCES dbo.CHATLIEU(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_NSX FOREIGN KEY(IdNSX) REFERENCES dbo.NSX(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_SIZE FOREIGN KEY(IdSize) REFERENCES dbo.SIZE(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_LOAIDEP FOREIGN KEY(IdLoaiDep) REFERENCES dbo.LOAIDEP(Id)

ALTER TABLE dbo.CHITIETDEP ADD CONSTRAINT FK_CTD_DEP FOREIGN KEY(IdDep) REFERENCES dbo.DEP(Id)

ALTER TABLE dbo.HOADON ADD CONSTRAINT FK_HD_KH FOREIGN KEY(IdKH) REFERENCES dbo.KHACHHANG(Id)

ALTER TABLE dbo.HOADON ADD CONSTRAINT FK_HD_ND FOREIGN KEY(IdND) REFERENCES dbo.NGUOIDUNG(Id)

ALTER TABLE dbo.HOADON ADD CONSTRAINT FK_HD_KM FOREIGN KEY(IdKM) REFERENCES dbo.KHUYENMAI(Id)

ALTER TABLE dbo.HOADONCHITIET ADD CONSTRAINT FK_HDCT_HD FOREIGN KEY(IdHD) REFERENCES dbo.HOADON(Id)

ALTER TABLE dbo.HOADONCHITIET ADD CONSTRAINT FK_HDCT_CTD FOREIGN KEY(IdCTD) REFERENCES dbo.CHITIETDEP(Id)


---Insert--
--Chuc vụ--
INSERT INTO dbo.CHUCVU
(
    Ma,
    Ten
)
VALUES
(   'CV001', -- Ma - varchar(20)
    N'Quản Lý'  -- Ten - nvarchar(50)
    ),
(   'CV002', -- Ma - varchar(20)
    N'Nhân Viên'  -- Ten - nvarchar(50)
    )
GO
    
---NGuoiDUng
INSERT INTO dbo.NGUOIDUNG
(
    IdCV,
    Ma,
    Ten,
    NgaySinh,
    Email,
    Sdt,
    GioiTinh,
    DiaChi,
    MatKhau,
    NgayTao,
    NgaySua,
    TrangThai,
    HinhAnh
)
VALUES
(   1, -- IdCV - int
    'ND001', -- Ma - varchar(20)
    N'Lại Văn Chiến', -- Ten - nvarchar(50)
    '01-01-2000', -- NgaySinh - date
    'abc@abc.com', -- Email - varchar(max)
    '0123456789', -- Sdt - varchar(20)
    1, -- GioiTinh - int
    N'Hà Nội', -- DiaChi - nvarchar(100)
    '123', -- MatKhau - varchar(max)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0, -- TrangThai - int
    NULL  -- HinhAnh - nvarchar(max)
    ),
(   2, -- IdCV - int
    'ND002', -- Ma - varchar(20)
    N'Lại Văn B', -- Ten - nvarchar(50)
    '02-02-1000', -- NgaySinh - date
    'abc@abc.com', -- Email - varchar(max)
    '0123456789', -- Sdt - varchar(20)
    1, -- GioiTinh - int
    N'Hà Nội', -- DiaChi - nvarchar(100)
    '123', -- MatKhau - varchar(max)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0, -- TrangThai - int
    NULL  -- HinhAnh - nvarchar(max)
    )






