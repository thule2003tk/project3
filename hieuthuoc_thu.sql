-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2025 at 08:25 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hieuthuoc_thu`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_thu`
--

CREATE TABLE `admin_thu` (
  `id` int(11) NOT NULL,
  `ten_dang_nhap` varchar(100) NOT NULL,
  `mat_khau` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_thu`
--

INSERT INTO `admin_thu` (`id`, `ten_dang_nhap`, `mat_khau`) VALUES
(1, 'admin', 'admin123'),
(2, 'quanly', 'qlthuoc2025');

-- --------------------------------------------------------

--
-- Table structure for table `baiviet_thu`
--

CREATE TABLE `baiviet_thu` (
  `id` int(11) NOT NULL,
  `tieu_de` varchar(255) NOT NULL,
  `noi_dung` text NOT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `ngay_dang` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `baiviet_thu`
--

INSERT INTO `baiviet_thu` (`id`, `tieu_de`, `noi_dung`, `hinh_anh`, `ngay_dang`) VALUES
(1, 'Lợi ích của Vitamin C', 'Vitamin C giúp tăng sức đề kháng, làm đẹp da...', 'vitamin_c_blog.png', '2025-02-10 08:00:00'),
(2, 'Cách sử dụng kháng sinh đúng cách', 'Kháng sinh cần được sử dụng đúng liều lượng và theo hướng dẫn của bác sĩ.', 'khang_sinh.png', '2025-01-25 15:30:00'),
(3, 'Cách nhận biết và điều trị cảm cúm', 'Cảm cúm là bệnh phổ biến, cần bổ sung vitamin và nghỉ ngơi...', 'cam_cum.png', '2025-02-12 10:00:00'),
(4, 'Tác dụng của Omega-3 đối với sức khỏe', 'Omega-3 giúp bảo vệ tim mạch và giảm viêm...', 'omega3.png', '2025-01-30 14:45:00'),
(5, 'Cách uống thuốc đúng cách', 'Hướng dẫn sử dụng thuốc an toàn và hiệu quả...', 'uong_thuoc_dung.png', '2025-02-05 09:20:00');

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang_thu`
--

CREATE TABLE `chitietdonhang_thu` (
  `id` int(11) NOT NULL,
  `donhang_id` int(11) NOT NULL,
  `thuoc_id` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `gia_ban` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietdonhang_thu`
--

INSERT INTO `chitietdonhang_thu` (`id`, `donhang_id`, `thuoc_id`, `so_luong`, `gia_ban`) VALUES
(1, 1, 1, 2, 15000.00),
(2, 1, 3, 1, 50000.00),
(3, 2, 2, 5, 20000.00),
(4, 3, 3, 1, 50000.00),
(5, 2, 1, 1, 18000.00),
(6, 2, 3, 2, 12000.00),
(7, 3, 4, 1, 30000.00),
(8, 3, 5, 2, 22000.00),
(9, 4, 2, 1, 25000.00),
(10, 4, 1, 2, 18000.00),
(11, 1, 5, 1, 22000.00);

-- --------------------------------------------------------

--
-- Table structure for table `donhang_thu`
--

CREATE TABLE `donhang_thu` (
  `id` int(11) NOT NULL,
  `khachhang_id` int(11) NOT NULL,
  `ngay_dat` datetime DEFAULT current_timestamp(),
  `tong_tien` decimal(10,2) NOT NULL,
  `trang_thai` varchar(50) DEFAULT 'Chờ xử lý'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donhang_thu`
--

INSERT INTO `donhang_thu` (`id`, `khachhang_id`, `ngay_dat`, `tong_tien`, `trang_thai`) VALUES
(1, 1, '2025-02-19 10:30:00', 35000.00, 'Chờ xử lý'),
(2, 2, '2025-02-18 14:45:00', 100000.00, 'Đã giao'),
(3, 3, '2025-02-17 09:15:00', 50000.00, 'Đang giao hàng'),
(4, 2, '2025-02-18 12:00:00', 60000.00, 'Đã giao'),
(5, 3, '2025-02-16 17:30:00', 90000.00, 'Chờ xử lý'),
(6, 4, '2025-02-15 14:10:00', 45000.00, 'Đang giao hàng'),
(7, 1, '2025-02-14 09:20:00', 75000.00, 'Hoàn thành');

-- --------------------------------------------------------

--
-- Table structure for table `giohang_thu`
--

CREATE TABLE `giohang_thu` (
  `id` int(11) NOT NULL,
  `khachhang_id` int(11) DEFAULT NULL,
  `thuoc_id` int(11) DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `giohang_thu`
--

INSERT INTO `giohang_thu` (`id`, `khachhang_id`, `thuoc_id`, `so_luong`) VALUES
(1, 1, 2, 3),
(2, 2, 3, 1),
(3, 3, 1, 5),
(4, 1, 4, 2),
(5, 2, 5, 4),
(6, 3, 2, 1),
(7, 1, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang_thu`
--

CREATE TABLE `khachhang_thu` (
  `id` int(11) NOT NULL,
  `ho_ten` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `so_dien_thoai` varchar(15) NOT NULL,
  `dia_chi` text NOT NULL,
  `mat_khau` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang_thu`
--

INSERT INTO `khachhang_thu` (`id`, `ho_ten`, `email`, `so_dien_thoai`, `dia_chi`, `mat_khau`) VALUES
(1, 'Nguyễn Văn A', 'nguyenvana@gmail.com', '0912345678', 'Hà Nội', '123456'),
(2, 'Trần Thị B', 'tranthib@gmail.com', '0987654321', 'TP.HCM', 'abcdef'),
(3, 'Lê Hoàng C', 'lehoangc@gmail.com', '0978123456', 'Đà Nẵng', 'password'),
(4, 'Phạm Văn D', 'phamvand@gmail.com', '0909123123', 'Cần Thơ', 'matkhau1'),
(5, 'Hoàng Minh E', 'hoangminhe@gmail.com', '0934555666', 'Hải Phòng', 'mkhoangminh'),
(6, 'Vũ Thị F', 'vuthif@gmail.com', '0977888999', 'Nha Trang', 'passvuthi'),
(7, 'Trần Đức G', 'tranducg@gmail.com', '0966111222', 'Huế', 'tranduc123');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap_thu`
--

CREATE TABLE `nhacungcap_thu` (
  `id` int(11) NOT NULL,
  `ten_ncc` varchar(255) NOT NULL,
  `dia_chi` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `so_dien_thoai` varchar(15) NOT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap_thu`
--

INSERT INTO `nhacungcap_thu` (`id`, `ten_ncc`, `dia_chi`, `email`, `so_dien_thoai`, `website`) VALUES
(1, 'Công ty Dược A', '123 Đường ABC, TP.HCM', 'duocA@example.com', '0123456789', 'https://duocA.com'),
(2, 'Công ty Dược B', '456 Đường XYZ, Hà Nội', 'duocB@example.com', '0987654321', 'https://duocB.com');

-- --------------------------------------------------------

--
-- Table structure for table `thuoc_thu`
--

CREATE TABLE `thuoc_thu` (
  `id` int(11) NOT NULL,
  `ten_thuoc` varchar(255) NOT NULL,
  `loai_thuoc` varchar(100) NOT NULL,
  `thanh_phan` text DEFAULT NULL,
  `cong_dung` text DEFAULT NULL,
  `huong_dan` text DEFAULT NULL,
  `han_su_dung` date NOT NULL,
  `gia_ban` decimal(10,2) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `ncc_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thuoc_thu`
--

INSERT INTO `thuoc_thu` (`id`, `ten_thuoc`, `loai_thuoc`, `thanh_phan`, `cong_dung`, `huong_dan`, `han_su_dung`, `gia_ban`, `so_luong`, `hinh_anh`, `ncc_id`) VALUES
(1, 'Paracetamol 500mg', 'Không kê đơn', 'Paracetamol', 'Giảm đau, hạ sốt', 'Uống 1 viên mỗi 6 giờ khi cần', '2026-12-31', 15000.00, 100, 'https://cdn.hellobacsi.com/wp-content/uploads/2017/05/cach-dung-thuoc-paracetamol-500mg.jpg', 0),
(2, 'Amoxicillin 500mg', 'Kê đơn', 'Amoxicillin', 'Kháng sinh điều trị nhiễm khuẩn', 'Uống 1 viên mỗi 8 giờ', '2025-06-30', 20000.00, 50, 'https://www.shutterstock.com/image-illustration/amoxicillin-antibiotic-medication-used-treat-600nw-2225010241.jpg', 0),
(3, 'Vitamin C 1000mg', 'Không kê đơn', 'Vitamin C', 'Tăng sức đề kháng', 'Uống 1 viên/ngày', '2027-08-15', 50000.00, 200, 'https://nhathuocthanthien.com.vn/wp-content/uploads/2023/02/dgm_nttt_Vitamin-c-1000-mg-zinc-rosehip-600x600.jpg', 0),
(4, 'Ibuprofen 400mg', 'Không kê đơn', 'Ibuprofen', 'Giảm đau, hạ sốt, chống viêm', 'Uống 1 viên mỗi 8 giờ', '2026-10-20', 18000.00, 120, 'https://m.media-amazon.com/images/I/61Upj5KyXkL.__AC_SX300_SY300_QL70_ML2_.jpg', 0),
(5, 'Metformin 500mg', 'Kê đơn', 'Metformin', 'Điều trị tiểu đường', 'Uống 1 viên/ngày sau ăn', '2025-12-15', 25000.00, 80, 'https://www.ricktroy.com/wp-content/uploads/2023/03/metformin-glucophage-tablets.jpg', 0),
(6, 'Cetirizine 10mg', 'Không kê đơn', 'Cetirizine', 'Chống dị ứng, giảm ngứa', 'Uống 1 viên/ngày', '2026-05-10', 12000.00, 150, 'https://m.media-amazon.com/images/I/61W1+lp9cLL._AC_SX679_.jpg', 0),
(7, 'Omeprazole 20mg', 'Kê đơn', 'Omeprazole', 'Điều trị trào ngược dạ dày', 'Uống 1 viên trước ăn sáng', '2025-11-01', 30000.00, 60, 'https://m.media-amazon.com/images/I/51Ncn0vy9aS._AC_UL480_FMwebp_QL65_.jpg', 0),
(8, 'Acetylcysteine 200mg', 'Không kê đơn', 'Acetylcysteine', 'Long đờm, trị ho', 'Pha 1 gói với nước ấm, uống sau ăn', '2026-09-30', 22000.00, 90, 'https://cdn.youmed.vn/tin-tuc/wp-content/uploads/2022/08/acetylcystein-stada-200mg-2-e1659698933614.jpg', 0),
(9, 'Paracetamol', 'Thuốc giảm đau', 'Paracetamol 500mg', 'Giảm đau, hạ sốt', 'Uống 1 viên mỗi 6 giờ', '2026-12-31', 20000.00, 100, 'https://medlatec.vn/media/15591/content/20200421_paracetamol-3.jpg', 1),
(10, 'Amoxicillin', 'Thuốc kháng sinh', 'Amoxicillin 500mg', 'Điều trị nhiễm khuẩn', 'Uống sau ăn', '2025-10-15', 50000.00, 50, 'https://m.media-amazon.com/images/I/71xta48ZwhL._AC_UL480_FMwebp_QL65_.jpg', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_thu`
--
ALTER TABLE `admin_thu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`);

--
-- Indexes for table `baiviet_thu`
--
ALTER TABLE `baiviet_thu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chitietdonhang_thu`
--
ALTER TABLE `chitietdonhang_thu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `donhang_id` (`donhang_id`),
  ADD KEY `thuoc_id` (`thuoc_id`);

--
-- Indexes for table `donhang_thu`
--
ALTER TABLE `donhang_thu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `khachhang_id` (`khachhang_id`);

--
-- Indexes for table `giohang_thu`
--
ALTER TABLE `giohang_thu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `khachhang_id` (`khachhang_id`),
  ADD KEY `thuoc_id` (`thuoc_id`);

--
-- Indexes for table `khachhang_thu`
--
ALTER TABLE `khachhang_thu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `so_dien_thoai` (`so_dien_thoai`);

--
-- Indexes for table `nhacungcap_thu`
--
ALTER TABLE `nhacungcap_thu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `thuoc_thu`
--
ALTER TABLE `thuoc_thu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ncc_id` (`ncc_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_thu`
--
ALTER TABLE `admin_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `baiviet_thu`
--
ALTER TABLE `baiviet_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `chitietdonhang_thu`
--
ALTER TABLE `chitietdonhang_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `donhang_thu`
--
ALTER TABLE `donhang_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `giohang_thu`
--
ALTER TABLE `giohang_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `khachhang_thu`
--
ALTER TABLE `khachhang_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nhacungcap_thu`
--
ALTER TABLE `nhacungcap_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `thuoc_thu`
--
ALTER TABLE `thuoc_thu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdonhang_thu`
--
ALTER TABLE `chitietdonhang_thu`
  ADD CONSTRAINT `chitietdonhang_thu_ibfk_1` FOREIGN KEY (`donhang_id`) REFERENCES `donhang_thu` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitietdonhang_thu_ibfk_2` FOREIGN KEY (`thuoc_id`) REFERENCES `thuoc_thu` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `donhang_thu`
--
ALTER TABLE `donhang_thu`
  ADD CONSTRAINT `donhang_thu_ibfk_1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang_thu` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `giohang_thu`
--
ALTER TABLE `giohang_thu`
  ADD CONSTRAINT `giohang_thu_ibfk_1` FOREIGN KEY (`khachhang_id`) REFERENCES `khachhang_thu` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `giohang_thu_ibfk_2` FOREIGN KEY (`thuoc_id`) REFERENCES `thuoc_thu` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `thuoc_thu`
--
ALTER TABLE `thuoc_thu`
  ADD CONSTRAINT `thuoc_thu_ibfk_1` FOREIGN KEY (`ncc_id`) REFERENCES `nhacungcap_thu` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
