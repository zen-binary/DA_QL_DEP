CREATE DATABASE DA_QL_DEP
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
	MaVach VARCHAR(20),
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
	MoTa NVARCHAR(MAX),
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
	SoLuong INT,
	NgayTao DATE,
	NgaySua DATE,
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
	NgaySinh DATE,
	GioiTinh INT,
	Sdt VARCHAR(20),
	Email VARCHAR(50),
	DiaChi NVARCHAR(MAX),
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
	MoTa NVARCHAR(MAX),
	TinhTrang INT
)

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
GO


INSERT INTO dbo.CHATLIEU
(
    Ma,
    Ten,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'CL001', -- Ma - varchar(20)
    N'Nhựa', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'CL002', -- Ma - varchar(20)
    N'Gỗ', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'CL003', -- Ma - varchar(20)
    N'Thủy Tinh', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
GO

INSERT INTO dbo.SIZE
(
    Ma,
    KichCo,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'S01', -- Ma - varchar(20)
    38, -- KichCo - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'S02', -- Ma - varchar(20)
    39, -- KichCo - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'S03', -- Ma - varchar(20)
    40, -- KichCo - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
GO
INSERT dbo.LOAIDEP
(
    Ma,
    Ten,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'LD001', -- Ma - varchar(20)
    N'Sốp', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'LD002', -- Ma - varchar(20)
    N'Da', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'LD003', -- Ma - varchar(20)
    N'Nhựa', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )

GO

INSERT INTO dbo.NSX
(
    Ma,
    Ten,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'Nsx001', -- Ma - varchar(20)
    N'Tilo', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'Nsx002', -- Ma - varchar(20)
    N'Holi', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'Nsx003', -- Ma - varchar(20)
    N'Oomoo', -- Ten - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
GO
INSERT INTO dbo.DEP
(
    Ma,
    Ten,
    HinhAnh,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'D001', -- Ma - varchar(20)
    N'Tông', -- Ten - nvarchar(50)
    NULL, -- HinhAnh - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'D002', -- Ma - varchar(20)
    N'Tổ ong', -- Ten - nvarchar(50)
    NULL, -- HinhAnh - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'D003', -- Ma - varchar(20)
    N'ChipChip', -- Ten - nvarchar(50)
    NULL, -- HinhAnh - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
GO
INSERT INTO dbo.MAUSAC
(
    Ma,
    MauSac,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'M001', -- Ma - varchar(20)
    N'Đỏ', -- MauSac - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'M002', -- Ma - varchar(20)
    N'Xanh', -- MauSac - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'M003', -- Ma - varchar(20)
    N'Vàng', -- MauSac - nvarchar(50)
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
GO
INSERT INTO dbo.KHUYENMAI
(
    Ma,
    Ten,
    PhanTramGiam,
    SoLuong,
    NgayTao,
    NgaySua,
    NgayBatDau,
    NgayKetThuc,
    TinhTrang,
    MoTa
)
VALUES
(   'KM001', -- Ma - varchar(20)
    N'Khai Chương Phầm Mềm', -- Ten - nvarchar(50)
    50, -- PhanTramGiam - float
    10, -- SoLuong - int
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    NULL, -- NgayBatDau - date
    NULL, -- NgayKetThuc - date
    0, -- TinhTrang - int
    N'Khai trương phầm mềm ql dép'  -- MoTa - nvarchar(max)
    )

GO



INSERT INTO dbo.KHACHHANG
(
    Ma,
    Ten,
    NgaySinh,
    GioiTinh,
    Sdt,
    Email,
    DiaChi,
    DiemTichLy,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'KH001', -- Ma - varchar(20)
    N'Lại Van Hien', -- Ten - nvarchar(50)
    '01-01-1990', -- NgaySinh - date
    0, -- GioiTinh - int
    '1234567899', -- Sdt - varchar(20)
    'Abc@anc.com', -- Email - varchar(50)
    N'Hà Nội', -- DiaChi - nvarchar(max)
    0, -- DiemTichLy - int
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'KH002', -- Ma - varchar(20)
    N'Lại Van Hồ', -- Ten - nvarchar(50)
    '01-01-1990', -- NgaySinh - date
    1, -- GioiTinh - int
    '1234567899', -- Sdt - varchar(20)
    'Abc@anc.com', -- Email - varchar(50)
    N'Hà Nội', -- DiaChi - nvarchar(max)
    0, -- DiemTichLy - int
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   'KH003', -- Ma - varchar(20)
    N'Lại Van Oan', -- Ten - nvarchar(50)
    '01-01-1990', -- NgaySinh - date
    0, -- GioiTinh - int
    '1234567899', -- Sdt - varchar(20)
    'Abc@anc.com', -- Email - varchar(50)
    N'Hà Nội', -- DiaChi - nvarchar(max)
    0, -- DiemTichLy - int
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
go


INSERT INTO dbo.HOADON
(
    Ma,
    IdND,
    IdKH,
    IdKM,
    TongTien,
    ThanhTien,
    NgayThanhToan,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   'HD001', -- Ma - varchar(20)
    2, -- IdND - int
    1, -- IdKH - int
    NULL, -- IdKM - int
    1000, -- TongTien - float
    1000, -- ThanhTien - float
    NULL, -- NgayThanhToan - date
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    1  -- TinhTrang - int
    ),
(   'HD002', -- Ma - varchar(20)
    2, -- IdND - int
    2, -- IdKH - int
    NULL, -- IdKM - int
    1000, -- TongTien - float
    1000, -- ThanhTien - float
    NULL, -- NgayThanhToan - date
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    1  -- TinhTrang - int
    ),
(   'HD003', -- Ma - varchar(20)
    2, -- IdND - int
    2, -- IdKH - int
    NULL, -- IdKM - int
    1000, -- TongTien - float
    1000, -- ThanhTien - float
    NULL, -- NgayThanhToan - date
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    1  -- TinhTrang - int
    )
GO

INSERT INTO dbo.CHITIETDEP
(
    MaVach,
    HinhAnh,
    IdDep,
    IdLoaiDep,
    IdSize,
    IdMauSac,
    IdChatLieu,
    IdNSX,
    SoLuong,
    GiaNhap,
    GiaBan,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   NULL, -- MaVach - varchar(20)
    NULL, -- HinhAnh - nvarchar(max)
    1, -- IdDep - int
    1, -- IdLoaiDep - int
    1, -- IdSize - int
    1, -- IdMauSac - int
    1, -- IdChatLieu - int
    1, -- IdNSX - int
    10, -- SoLuong - int
    121, -- GiaNhap - float
    212, -- GiaBan - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   NULL, -- MaVach - varchar(20)
    NULL, -- HinhAnh - nvarchar(max)
    1, -- IdDep - int
    2, -- IdLoaiDep - int
    3, -- IdSize - int
    2, -- IdMauSac - int
    1, -- IdChatLieu - int
    2, -- IdNSX - int
    20, -- SoLuong - int
    183, -- GiaNhap - float
    212, -- GiaBan - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    ),
(   NULL, -- MaVach - varchar(20)
    NULL, -- HinhAnh - nvarchar(max)
    2, -- IdDep - int
    2, -- IdLoaiDep - int
    1, -- IdSize - int
    2, -- IdMauSac - int
    2, -- IdChatLieu - int
    2, -- IdNSX - int
    20, -- SoLuong - int
    183, -- GiaNhap - float
    212, -- GiaBan - float
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )


INSERT INTO dbo.HOADONCHITIET
(
    IdHD,
    IdCTD,
    DonGia,
    SoLuong,
    NgayTao,
    NgaySua,
    TinhTrang
)
VALUES
(   2, -- IdHD - int
    1, -- IdCTD - int
    12, -- DonGia - float
    2, -- SoLuong - int
    NULL, -- NgayTao - date
    NULL, -- NgaySua - date
    0  -- TinhTrang - int
    )
SELECT * FROM dbo.CHUCVU
SELECT *FROM dbo.NGUOIDUNG WHERE Ten LIKE '%%'
SELECT *FROM dbo.KHUYENMAI