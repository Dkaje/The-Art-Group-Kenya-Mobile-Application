-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 29, 2022 at 02:04 AM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19894654_artgroup`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `reg_date`, `update_date`) VALUES
(1, 'Username', '202cb962ac59075b964b07152d234b70', '2022-11-22 08:16:30', '2022-11-22 14:01:34');

-- --------------------------------------------------------

--
-- Table structure for table `bidding`
--

CREATE TABLE `bidding` (
  `bid` int(11) NOT NULL,
  `classification` text COLLATE utf8_unicode_ci NOT NULL,
  `category` text COLLATE utf8_unicode_ci NOT NULL,
  `type` text COLLATE utf8_unicode_ci NOT NULL,
  `qty` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bidding`
--

INSERT INTO `bidding` (`bid`, `classification`, `category`, `type`, `qty`, `quantity`, `entry_date`, `update_date`) VALUES
(1, 'Ready Made', 'Soft Furnishing & Clothing', 'Cushions', 170, 0, '2022-11-22 08:37:13 pm', '2022-11-22 08:39:38 pm'),
(3, 'Raw Material', 'Calendar', 'Calendar', 66, 0, '2022-11-22 10:49:21 pm', '2022-11-22 11:02:54 pm'),
(4, 'Ready Made', 'Soft Furnishing & Clothing', 'Scarf', 20, 0, '2022-11-22 11:58:53 pm', '2022-11-22 11:58:53 pm'),
(5, 'Ready Made', 'Soft Furnishing & Clothing', 'Tshirt', 16, 16, '2022-11-23 12:05:57 am', '2022-11-29 04:25:05 AM'),
(6, 'Ready Made', 'Soft Furnishing & Clothing', 'Sash', 50, 0, '2022-11-23 12:06:38 am', '2022-11-23 12:06:39 am'),
(8, 'Ready Made', 'Signature Collection', 'Summer Landscape', 5, 0, '2022-11-23 12:07:37 am', '2022-11-23 12:07:37 am'),
(9, 'Ready Made', 'Signature Collection', 'Flamingo', 8, 0, '2022-11-23 12:08:28 am', '2022-11-23 11:48:16 am'),
(10, 'Ready Made', 'Wooden Wall Art', 'Wooden Signs', 10, 0, '2022-11-23 09:06:40 am', '2022-11-23 09:06:40 am'),
(12, 'Ready Made', 'Wooden Wall Art', 'Wooden Brooks', 6, 2, '2022-11-23 09:28:23 am', '2022-11-23 09:28:23 am'),
(14, 'Ready Made', 'Art Paints', 'Green Heron', 311, 311, '2022-11-23 10:31:46 am', '2022-11-29 04:25:30 AM'),
(15, 'Raw Material', 'Soft Furnishing & Clothing', 'Scarf', 6, 0, '2022-11-23 04:25:46 pm', '2022-11-23 04:25:46 pm');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `reg` int(11) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `custid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  `category` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`reg`, `entry`, `custid`, `product`, `category`, `type`, `price`, `quantity`, `image`, `status`, `reg_date`) VALUES
(1, 'ARTG-2GT5D2022', '2022ARTG-063', 1, 'Soft Furnishing & Clothing', 'Cushions', 500, 300, 'IMG1323423493.jpg', 'Ordered', '2022-11-22 09:03:19 pm'),
(2, 'ARTG-7GUJD2022', '2022ARTG-072', 6, 'Wooden Wall Art', 'Wooden Brooks', 91000, 1, 'IMG595309356.jpg', 'Ordered', '2022-11-28 11:57:21 am'),
(3, 'ARTG-7GUJD2022', '2022ARTG-072', 7, 'Art Paints', 'Green Heron', 800, 1, 'IMG53041476.jpg', 'Ordered', '2022-11-29 04:34:00 AM');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `county` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `entry`, `fname`, `lname`, `phone`, `email`, `username`, `password`, `county`, `status`, `remarks`, `reg_date`, `update_date`) VALUES
(1, '2022ARTG-063', 'brian', 'kinywa', '0122364527', 'briankinywa@gmail.com', 'brian', '25d55ad283aa400af464c76d713c07ad', 'Nyeri', 'Approved', NULL, '2022-11-22 14:08:11', '2022-11-22 14:50:34'),
(2, '2022ARTG-068', 'cosmus', 'yego', '0712365789', 'cosmusyego@gmail.com', 'cosmus', '25d55ad283aa400af464c76d713c07ad', 'Embu', 'Approved', NULL, '2022-11-22 17:15:07', '2022-11-22 17:15:40'),
(3, '2022ARTG-072', 'benson', 'michael', '0725364259', 'bensonmichael@gmail.com', 'benson', '25d55ad283aa400af464c76d713c07ad', 'Embu', 'Approved', NULL, '2022-11-23 13:00:45', '2022-11-23 13:01:37'),
(4, '2022ARTG-073', 'guyo', 'abdi', '0712364589', 'abdiguyo@gmail.com', 'guyo', '25d55ad283aa400af464c76d713c07ad', 'Mombasa', 'Approved', '', '2022-11-28 07:47:29', '2022-11-28 08:51:54'),
(5, '2022ARTG-074', 'Mussa', 'Maksuudi', '0112999999', 'mussa@gmail.com', 'mussaa', '25d55ad283aa400af464c76d713c07ad', 'Meru', 'Rejected', 'Invalid', '2022-11-29 00:59:13', '2022-11-29 01:02:28');

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL,
  `form` float DEFAULT 0,
  `entry` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_name` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `drive` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `drive_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_name` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `video_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cust_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cust_name` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cust_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `landmark` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `custom` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `custom_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`id`, `form`, `entry`, `driver_id`, `driver_name`, `driver_phone`, `drive`, `drive_date`, `video_id`, `video_name`, `video_phone`, `video`, `video_date`, `cust_id`, `cust_name`, `cust_phone`, `location`, `landmark`, `custom`, `custom_date`, `reg_date`) VALUES
(1, 6, 'ARTG-2GT5D2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-22 09:14:45 pm', NULL, NULL, NULL, 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Meru', 'meru town-67', 'Delivered', '2022-11-22 09:15:30 pm', '2022-11-22 09:11 pm'),
(2, 4, '2', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-22 11:15:08 pm', NULL, NULL, NULL, 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Embu', 'embu town-huruma estate', 'Delivered', '2022-11-22 11:16:21 pm', '2022-11-22 11:13 pm'),
(3, 9, 'ARTG-45SL22022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-23 09:58:21 am', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-23 09:57:22 am', '2022ARTG-063', 'brian kinywa', '0122364527', 'Vihiga', 'vihiga town-78', 'Delivered', '2022-11-23 10:15:22 am', '2022-11-23 09:44 am'),
(4, 9, 'ARTG-5JS0P2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-23 10:50:19 am', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-23 10:11:59 am', '2022ARTG-063', 'brian kinywa', '0122364527', 'Migori', 'migori town-76', 'Pending', NULL, '2022-11-23 10:05 am'),
(5, 9, 'ARTG-14X822022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-23 10:50:15 am', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-23 10:11:52 am', '2022ARTG-068', 'cosmus yego', '0712365789', 'Embu', 'embu town-34', 'Delivered', '2022-11-23 10:50:48 am', '2022-11-23 10:05 am'),
(6, 9, 'ARTG-6MHLT2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-23 10:50:11 am', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-23 10:49:41 am', '2022ARTG-068', 'cosmus yego', '0712365789', 'Migori', 'migori town-34', 'Delivered', '2022-11-23 10:50:44 am', '2022-11-23 10:48 am'),
(7, 9, 'ARTG-7TJO72022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-23 11:01:04 am', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-23 11:00:23 am', '2022ARTG-063', 'brian kinywa', '0122364527', 'Muranga', 'muranga town-76', 'Delivered', '2022-11-23 11:06:07 am', '2022-11-23 10:57 am'),
(8, 9, 'ARTG-8YMIV2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Kirinyaga', 'kirinyaga town-87', 'Pending', NULL, '2022-11-23 11:16 am'),
(9, 9, 'ARTG-9UNJ92022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Embu', 'embu twon-67', 'Pending', NULL, '2022-11-23 11:21 am'),
(10, 9, 'ARTG-0ZAMD2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Nyeri', 'nyeri town-54', 'Pending', NULL, '2022-11-23 11:25 am'),
(11, 9, 'ARTG-193DB2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-068', 'cosmus yego', '0712365789', 'Meru', 'meru town-001', 'Pending', NULL, '2022-11-23 11:30 am'),
(12, 9, 'ARTG-22JMN2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-068', 'cosmus yego', '0712365789', 'Nairobi', 'Nairobi town-CBD', 'Pending', NULL, '2022-11-23 11:41 am'),
(13, 9, 'ARTG-38CBH2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-068', 'cosmus yego', '0712365789', 'Mombasa', 'mombasa town-56', 'Pending', NULL, '2022-11-23 11:45 am'),
(14, 9, 'ARTG-4Y1692022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-068', 'cosmus yego', '0712365789', 'Garissa', 'garissa town-garissa town', 'Pending', NULL, '2022-11-23 12:10 pm'),
(15, 9, 'ARTG-53DBN2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Pending', NULL, '2022ARTG-071', 'henry mugo', '0725369423', 'Pending', NULL, '2022ARTG-063', 'brian kinywa', '0122364527', 'Narok', 'Narok town-65', 'Pending', NULL, '2022-11-23 12:47 pm'),
(16, 9, 'ARTG-6QFL52022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-29 04:49:34 AM', '2022ARTG-071', 'henry mugo', '0725369423', 'Confirmed', '2022-11-29 04:49:13 AM', '2022ARTG-072', 'benson michael', '0725364259', 'Migori', 'migori town-56', 'Delivered', '2022-11-29 04:49:56 AM', '2022-11-23 04:13 pm'),
(17, 6, 'ARTG-7GUJD2022', '2022ARTG-070', 'bravin kegesa', '0123456789', 'Delivered', '2022-11-29 04:42:38 AM', NULL, NULL, NULL, 'Pending', NULL, '2022ARTG-072', 'benson michael', '0725364259', 'Meru', 'Gatimeme Gardens-Runogone Catholic.', 'Delivered', '2022-11-29 04:42:56 AM', '2022-11-29 04:42 AM');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `county` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`id`, `entry`, `fname`, `lname`, `phone`, `email`, `username`, `password`, `county`, `status`, `remarks`, `reg_date`, `update_date`) VALUES
(1, '2022ARTG-070', 'bravin', 'kegesa', '0123456789', 'bravinkegesa@gmail.com', 'bravin', '25d55ad283aa400af464c76d713c07ad', 'Nyeri', 'Approved', NULL, '2022-11-22 18:10:01', '2022-11-22 18:10:17');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `sen` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `senid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `rec` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timing` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dater` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meme` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `sen`, `senid`, `phone`, `name`, `rec`, `message`, `timing`, `date`, `dater`, `meme`) VALUES
(1, 'Customer', '2022ARTG-072', '0725364259', 'benson', 'Servicer', 'the service was good', '04:17pm', '23-11-2022', '23-11-2022 04:17:13', 'K'),
(2, 'Customer', '2022ARTG-072', '0725364259', 'benson', 'Servicer', 'You are welcome', '04:17pm', '23-11-2022', '23-11-2022 04:17:52', 'M'),
(3, 'Customer', '2022ARTG-072', '0725364259', 'benson', 'Servicer', 'hello \nI waited for hours. but the videographer finally arrived. Thanks your services are such amazing.', '04:53AM', '29-11-2022', '29-11-2022 04:53:57', 'K'),
(4, 'Customer', '2022ARTG-072', '0725364259', 'benson', 'Servicer', 'hello Benson,\nthanks so much. please come again.', '04:54AM', '29-11-2022', '29-11-2022 04:54:56', 'M');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `cust_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alert` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notice`
--

INSERT INTO `notice` (`id`, `cust_id`, `alert`, `reg_date`) VALUES
(1, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 09:44 am'),
(2, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 10:05 am'),
(3, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 10:05 am'),
(4, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 10:48 am'),
(5, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 10:57 am'),
(6, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:16 am'),
(7, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:21 am'),
(8, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:25 am'),
(9, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:30 am'),
(10, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:41 am'),
(11, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 11:45 am'),
(12, '2022ARTG-068', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 12:10 pm'),
(13, '2022ARTG-063', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 12:47 pm'),
(14, '2022ARTG-072', 'Videographer Allocated. henry mugo phone 0725369423. Driver Allocated. bravin kegesa phone 0123456789', '2022-11-23 04:13 pm');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payid` int(11) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `mpesa` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `orders` float DEFAULT NULL,
  `ship` float DEFAULT NULL,
  `custid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `landmark` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disb` float DEFAULT 0,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payid`, `entry`, `mpesa`, `amount`, `orders`, `ship`, `custid`, `name`, `phone`, `location`, `landmark`, `status`, `comment`, `disb`, `reg_date`) VALUES
(1, 'ARTG-2GT5D2022', 'QWRUI35789', 150310, 150000, 310, '2022ARTG-063', 'brian kinywa', '0122364527', 'Meru', 'meru town-67', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-22 09:04:14 pm'),
(2, 'ARTG-7GUJD2022', 'QPJ45HH5HH', 92110, 91800, 310, '2022ARTG-072', 'benson michael', '0725364259', 'Meru', 'Gatimeme Gardens-Runogone Catholic.', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-29 04:35:37 AM');

-- --------------------------------------------------------

--
-- Table structure for table `paysup`
--

CREATE TABLE `paysup` (
  `id` int(11) NOT NULL,
  `mpesa` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `supplier` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `paysup`
--

INSERT INTO `paysup` (`id`, `mpesa`, `supplier`, `fullname`, `phone`, `amount`, `date`) VALUES
(1, 'QERTYU3678', '2022ARTG-069', 'tuitoek timon', '0123456789', 340000, '2022-11-23 10:03 am'),
(2, 'QPHDH566NN', '2022ARTG-069', 'tuitoek timon', '0123456789', 363000, '2022-11-29 04:56 AM');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qty` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `category`, `type`, `description`, `image`, `qty`, `quantity`, `price`, `reg_date`) VALUES
(1, 'Soft Furnishing & Clothing', 'Cushions', 'soft and fur', 'IMG1323423493.jpg', 340, 40, 800, '2022-11-22 08:57:12 pm'),
(2, 'Soft Furnishing & Clothing', 'Scarf', 'best scarf in Kenya', 'IMG1197547453.jpg', 20, 20, 1200, '2022-11-23 12:05:15 am'),
(3, 'Soft Furnishing & Clothing', 'Tshirt', 'Your label we give', 'IMG140655306.jpg', 30, 30, 1500, '2022-11-23 12:18:00 am'),
(4, 'Soft Furnishing & Clothing', 'Sash', 'Honouring the event', 'IMG897260428.jpg', 50, 50, 1000, '2022-11-23 12:18:10 am'),
(5, 'Signature Collection', 'Summer Landscape', 'signature collection', 'IMG1459545718.jpg', 5, 5, 1200, '2022-11-23 12:18:26 am'),
(6, 'Wooden Wall Art', 'Wooden Brooks', 'the wall art', 'IMG595309356.jpg', 4, 3, 91000, '2022-11-23 10:20:12 am'),
(7, 'Art Paints', 'Green Heron', 'art paints', 'IMG53041476.jpg', 2, 1, 800, '2022-11-23 10:35:14 am');

-- --------------------------------------------------------

--
-- Table structure for table `raw`
--

CREATE TABLE `raw` (
  `id` int(11) NOT NULL,
  `category` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qty` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `raw`
--

INSERT INTO `raw` (`id`, `category`, `type`, `description`, `image`, `qty`, `quantity`, `price`, `reg_date`) VALUES
(1, 'Calendar', 'Calendar', 'best calendar', 'IMG222055190.jpg', 258, 218, 600, '2022-11-22 10:59:27 pm'),
(2, 'Soft Furnishing & Clothing', 'Scarf', 'Kemu', 'IMG1167530613.jpg', 12, 7, 200, '2022-11-23 04:28:03 pm');

-- --------------------------------------------------------

--
-- Table structure for table `refer`
--

CREATE TABLE `refer` (
  `id` int(11) NOT NULL,
  `refere` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cust_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `refer`
--

INSERT INTO `refer` (`id`, `refere`, `category`, `type`, `cust_id`, `image`, `reg_date`) VALUES
(1, '', '', '', '', 'IMG71282654.jpg', ''),
(2, '', '', '', '', 'IMG410466902.jpg', ''),
(3, '', '', '', '', 'IMG76850020.jpg', ''),
(4, '', '', '', '', 'IMG1699425666.jpg', ''),
(5, '', '', '', '', 'IMG1974876399.jpg', ''),
(6, '', '', '', '', 'IMG1006843554.jpg', ''),
(7, '', '', '', '', 'IMG1194999570.jpg', ''),
(8, '', '', '', '', 'IMG1476711442.jpg', ''),
(9, '', '', '', '', 'IMG1923661375.jpg', ''),
(10, 'ARTG-45SL22022', 'Videography', 'Live Stream Production Services', '2022ARTG-063', 'IMG1516688006.jpg', '2022-11-23 09:54:58 am'),
(11, 'ARTG-45SL22022', 'Videography', 'Live Stream Production Services', '2022ARTG-063', 'IMG1582694165.jpg', '2022-11-23 09:55:21 am'),
(12, '', '', '', '', 'IMG1304896401.jpg', ''),
(13, 'ARTG-5JS0P2022', 'Photography', 'Real Estate Photography', '2022ARTG-063', 'IMG988951261.jpg', '2022-11-23 10:07:11 am'),
(14, 'ARTG-14X822022', 'Photography', 'Drones', '2022ARTG-068', 'IMG1295879756.jpg', '2022-11-23 10:08:05 am'),
(15, 'ARTG-5JS0P2022', 'Photography', 'Real Estate Photography', '2022ARTG-063', 'IMG1412180527.jpg', '2022-11-23 10:09:22 am'),
(16, 'ARTG-14X822022', 'Photography', 'Drones', '2022ARTG-068', 'IMG1143269154.jpg', '2022-11-23 10:10:20 am'),
(17, 'ARTG-5JS0P2022', 'Photography', 'Real Estate Photography', '2022ARTG-063', 'IMG1079899382.jpg', '2022-11-23 10:11:41 am'),
(18, 'ARTG-6MHLT2022', 'Photography', 'Real Estate Photography', '2022ARTG-068', 'IMG1546867532.jpg', '2022-11-23 10:49:20 am'),
(19, 'ARTG-6MHLT2022', 'Photography', 'Real Estate Photography', '2022ARTG-068', 'IMG1588023744.jpg', '2022-11-23 10:49:34 am'),
(20, 'ARTG-7TJO72022', 'Photography', 'Real Estate Photography', '2022ARTG-063', 'IMG134099234.jpg', '2022-11-23 10:58:39 am'),
(21, 'ARTG-7TJO72022', 'Photography', 'Real Estate Photography', '2022ARTG-063', 'IMG905494828.jpg', '2022-11-23 10:58:52 am'),
(22, 'ARTG-6QFL52022', 'Photography', 'Real Estate Photography', '2022ARTG-072', 'IMG710904319.jpg', '2022-11-23 04:14:30 pm'),
(23, 'ARTG-6QFL52022', 'Photography', 'Real Estate Photography', '2022ARTG-072', 'IMG1582929671.jpg', '2022-11-29 04:48:51 AM');

-- --------------------------------------------------------

--
-- Table structure for table `self_ord`
--

CREATE TABLE `self_ord` (
  `slf` int(11) NOT NULL,
  `dsc` float DEFAULT NULL,
  `custid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `size` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rgb` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hexa` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `green` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL,
  `motive` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `price` float DEFAULT 0,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `pay` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `fina` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `image_desgn` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `design` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `self_ord`
--

INSERT INTO `self_ord` (`slf`, `dsc`, `custid`, `name`, `phone`, `category`, `type`, `description`, `image`, `size`, `rgb`, `hexa`, `red`, `green`, `blue`, `motive`, `quantity`, `price`, `status`, `pay`, `fina`, `image_desgn`, `design`, `date`) VALUES
(1, 7, '2022ARTG-063', 'brian kinywa', '0122364527', 'Calendar', 'Calendar', '2022 calendar', 'No', 'Medium', NULL, NULL, NULL, NULL, NULL, 0, 20, 1500, 'Rejected', 'Pending', 'Pending', NULL, 'Pending', '2022-11-22 10:44:11 pm'),
(2, 7, '2022ARTG-063', 'brian kinywa', '0122364527', 'Calendar', 'Calendar', 'yearly', 'No', 'Medium', NULL, NULL, NULL, NULL, NULL, 0, 40, 2000, 'Approved', 'Ordered', 'Approved', 'IMG1730878273.jpg', 'Ready', '2022-11-22 11:01:59 pm'),
(3, 7, '2022ARTG-072', 'benson michael', '0725364259', 'Soft Furnishing & Clothing', 'Scarf', 'Kemu', 'No', 'Medium', NULL, NULL, NULL, NULL, NULL, 0, 5, 3000, 'Approved', 'Ordered', 'Pending', NULL, 'Pending', '2022-11-23 04:23:14 pm');

-- --------------------------------------------------------

--
-- Table structure for table `self_pay`
--

CREATE TABLE `self_pay` (
  `payid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `slf` int(11) DEFAULT NULL,
  `mpesa` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `orders` float DEFAULT NULL,
  `ship` float DEFAULT NULL,
  `custid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `landmark` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `design` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `disb` float DEFAULT 0,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `self_pay`
--

INSERT INTO `self_pay` (`payid`, `slf`, `mpesa`, `amount`, `orders`, `ship`, `custid`, `name`, `phone`, `location`, `landmark`, `status`, `comment`, `design`, `disb`, `reg_date`) VALUES
('ARTG-367R82022', 2, 'QWTUI35788', 80310, 80000, 310, '2022ARTG-063', 'brian kinywa', '0122364527', 'Embu', 'embu town-huruma estate', 1, 'Thanks for shopping with us. Welcome Back', 'Ready', 1, '2022-11-22 11:06:46 pm'),
('ARTG-8DK5E2022', 3, 'DHHH388DHH', 15000, 15000, 0, '2022ARTG-072', 'benson michael', '0725364259', '', '', 0, NULL, 'Pending', 0, '2022-11-29 04:38:00 AM');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `serv` int(11) NOT NULL,
  `custid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `landmark` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `charge` float DEFAULT 0,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `pay` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serv`, `custid`, `name`, `phone`, `location`, `landmark`, `category`, `type`, `description`, `charge`, `status`, `pay`, `reg_date`) VALUES
(1, '2022ARTG-068', 'cosmus yego', '0712365789', 'Embu', 'embu town-34', 'Photography', 'Drones', 'drone photography', 12000, 'Approved', 'Paid', '2022-11-22 08:25:04 pm'),
(2, '2022ARTG-063', 'brian kinywa', '0122364527', 'Vihiga', 'vihiga town-78', 'Videography', 'Live Stream Production Services', 'we bring close to you', 68000, 'Approved', 'Paid', '2022-11-23 09:38:35 am'),
(3, '2022ARTG-063', 'brian kinywa', '0122364527', 'Migori', 'migori town-76', 'Photography', 'Real Estate Photography', 'real estate photography', 60000, 'Approved', 'Paid', '2022-11-23 10:01:39 am'),
(4, '2022ARTG-068', 'cosmus yego', '0712365789', 'Migori', 'migori town-34', 'Photography', 'Real Estate Photography', 'real estate', 60000, 'Approved', 'Paid', '2022-11-23 10:44:23 am'),
(5, '2022ARTG-063', 'brian kinywa', '0122364527', 'Muranga', 'muranga town-76', 'Photography', 'Real Estate Photography', 'real estate', 60000, 'Approved', 'Paid', '2022-11-23 10:54:35 am'),
(6, '2022ARTG-063', 'brian kinywa', '0122364527', 'Kirinyaga', 'kirinyaga town-87', 'Photography', 'Real Estate Photography', 'Real estate', 60000, 'Approved', 'Paid', '2022-11-23 11:14:05 am'),
(7, '2022ARTG-063', 'brian kinywa', '0122364527', 'Embu', 'embu twon-67', 'Photography', 'Real Estate Photography', 'Real estate', 60000, 'Approved', 'Paid', '2022-11-23 11:17:44 am'),
(8, '2022ARTG-063', 'brian kinywa', '0122364527', 'Nyeri', 'nyeri town-54', 'Photography', 'Real Estate Photography', 'we are Real', 60000, 'Approved', 'Paid', '2022-11-23 11:22:52 am'),
(9, '2022ARTG-068', 'cosmus yego', '0712365789', 'Meru', 'meru town-001', 'Photography', 'Real Estate Photography', 'Estate photography', 60000, 'Approved', 'Paid', '2022-11-23 11:26:42 am'),
(10, '2022ARTG-068', 'cosmus yego', '0712365789', 'Nairobi', 'Nairobi town-CBD', 'Photography', 'Real Estate Photography', 'photography at its best', 60000, 'Approved', 'Paid', '2022-11-23 11:38:39 am'),
(11, '2022ARTG-068', 'cosmus yego', '0712365789', 'Mombasa', 'mombasa town-56', 'Photography', 'Real Estate Photography', 'photographying', 60000, 'Approved', 'Paid', '2022-11-23 11:43:11 am'),
(12, '2022ARTG-068', 'cosmus yego', '0712365789', 'Garissa', 'garissa town-garissa town', 'Photography', 'Real Estate Photography', 'best photography', 60000, 'Approved', 'Paid', '2022-11-23 11:58:18 am'),
(13, '2022ARTG-063', 'brian kinywa', '0122364527', 'Narok', 'Narok town-65', 'Photography', 'Real Estate Photography', 'Real photographying', 60000, 'Approved', 'Paid', '2022-11-23 12:42:01 pm'),
(14, '2022ARTG-072', 'benson michael', '0725364259', 'Migori', 'migori town-56', 'Photography', 'Real Estate Photography', 'real estate', 60000, 'Approved', 'Paid', '2022-11-23 04:04:14 pm');

-- --------------------------------------------------------

--
-- Table structure for table `ser_pay`
--

CREATE TABLE `ser_pay` (
  `serid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `mpesa` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `category` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serv` int(11) DEFAULT NULL,
  `custid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `landmark` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disb` float DEFAULT 0,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ser_pay`
--

INSERT INTO `ser_pay` (`serid`, `mpesa`, `amount`, `category`, `type`, `description`, `serv`, `custid`, `name`, `phone`, `location`, `landmark`, `status`, `comment`, `disb`, `reg_date`) VALUES
('ARTG-0ZAMD2022', 'QRTY269073', 60000, 'Photography', 'Real Estate Photography', 'we are Real', 8, '2022ARTG-063', 'brian kinywa', '0122364527', 'Nyeri', 'nyeri town-54', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:24:01 am'),
('ARTG-14X822022', 'QWFYU12U7E', 12000, 'Photography', 'Drones', 'drone photography', 1, '2022ARTG-068', 'cosmus yego', '0712365789', 'Embu', 'embu town-34', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-22 08:27:31 pm'),
('ARTG-193DB2022', 'QRYI378903', 60000, 'Photography', 'Real Estate Photography', 'Estate photography', 9, '2022ARTG-068', 'cosmus yego', '0712365789', 'Meru', 'meru town-001', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:28:43 am'),
('ARTG-22JMN2022', 'QWRY367890', 60000, 'Photography', 'Real Estate Photography', 'photography at its best', 10, '2022ARTG-068', 'cosmus yego', '0712365789', 'Nairobi', 'Nairobi town-CBD', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:39:43 am'),
('ARTG-38CBH2022', 'QERY367889', 60000, 'Photography', 'Real Estate Photography', 'photographying', 11, '2022ARTG-068', 'cosmus yego', '0712365789', 'Mombasa', 'mombasa town-56', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:44:16 am'),
('ARTG-45SL22022', 'QWRYU35678', 68000, 'Videography', 'Live Stream Production Services', 'we bring close to you', 2, '2022ARTG-063', 'brian kinywa', '0122364527', 'Vihiga', 'vihiga town-78', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 09:40:43 am'),
('ARTG-4Y1692022', 'QERTI35677', 60000, 'Photography', 'Real Estate Photography', 'best photography', 12, '2022ARTG-068', 'cosmus yego', '0712365789', 'Garissa', 'garissa town-garissa town', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 12:04:05 pm'),
('ARTG-53DBN2022', 'QWEER23678', 60000, 'Photography', 'Real Estate Photography', 'Real photographying', 13, '2022ARTG-063', 'brian kinywa', '0122364527', 'Narok', 'Narok town-65', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 12:43:26 pm'),
('ARTG-5JS0P2022', 'QERYII2688', 60000, 'Photography', 'Real Estate Photography', 'real estate photography', 3, '2022ARTG-063', 'brian kinywa', '0122364527', 'Migori', 'migori town-76', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 10:03:01 am'),
('ARTG-6MHLT2022', 'QERTU26788', 60000, 'Photography', 'Real Estate Photography', 'real estate', 4, '2022ARTG-068', 'cosmus yego', '0712365789', 'Migori', 'migori town-34', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 10:46:26 am'),
('ARTG-6QFL52022', 'QETUI357I9', 60000, 'Photography', 'Real Estate Photography', 'real estate', 14, '2022ARTG-072', 'benson michael', '0725364259', 'Migori', 'migori town-56', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 04:06:08 pm'),
('ARTG-7TJO72022', 'QETUI36778', 60000, 'Photography', 'Real Estate Photography', 'real estate', 5, '2022ARTG-063', 'brian kinywa', '0122364527', 'Muranga', 'muranga town-76', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 10:55:49 am'),
('ARTG-8YMIV2022', 'QERTU36788', 60000, 'Photography', 'Real Estate Photography', 'Real estate', 6, '2022ARTG-063', 'brian kinywa', '0122364527', 'Kirinyaga', 'kirinyaga town-87', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:15:42 am'),
('ARTG-9UNJ92022', 'QRTYII3678', 60000, 'Photography', 'Real Estate Photography', 'Real estate', 7, '2022ARTG-063', 'brian kinywa', '0122364527', 'Embu', 'embu twon-67', 1, 'Thanks for shopping with us. Welcome Back', 1, '2022-11-23 11:18:40 am');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `entry`, `fname`, `lname`, `phone`, `email`, `username`, `password`, `role`, `status`, `remarks`, `reg_date`, `update_date`) VALUES
(1, '2022ARTG-064', 'cyrus', 'chege', '0125534697', 'cyruschege@gmail.com', 'chege', '25d55ad283aa400af464c76d713c07ad', 'Inventory', 'Approved', NULL, '2022-11-22 16:27:39', '2022-11-22 16:29:04'),
(2, '2022ARTG-065', 'derrick', 'ngeno', '0123345879', 'ngenoderrick@gmail.com', 'derrick', '25d55ad283aa400af464c76d713c07ad', 'Finance', 'Approved', NULL, '2022-11-22 16:30:38', '2022-11-22 16:31:02'),
(3, '2022ARTG-066', 'fidel', 'yatich', '0112365427', 'fidelyatich@gmail.com', 'fidel', '25d55ad283aa400af464c76d713c07ad', 'Designer', 'Approved', NULL, '2022-11-22 16:34:37', '2022-11-22 16:35:02'),
(4, '2022ARTG-067', 'titus', 'chesir', '0714253698', 'tituschesir@gmail.com', 'titus', '25d55ad283aa400af464c76d713c07ad', 'Service', 'Approved', NULL, '2022-11-22 16:36:12', '2022-11-22 16:36:35'),
(5, '2022ARTG-071', 'henry', 'mugo', '0725369423', 'henrymugo@gmail.com', 'henry', '25d55ad283aa400af464c76d713c07ad', 'Videographer', 'Approved', NULL, '2022-11-23 06:43:16', '2022-11-23 06:43:36');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `county` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `entry`, `fname`, `lname`, `phone`, `email`, `username`, `password`, `county`, `status`, `remarks`, `reg_date`, `update_date`) VALUES
(1, '2022ARTG-069', 'tuitoek', 'timon', '0123456789', 'tuitoektimon@gmail.com', 'tuitoek', '25d55ad283aa400af464c76d713c07ad', 'Meru', 'Approved', NULL, '2022-11-22 17:51:02', '2022-11-22 17:51:46');

-- --------------------------------------------------------

--
-- Table structure for table `supply`
--

CREATE TABLE `supply` (
  `sup` int(11) NOT NULL,
  `bid` int(11) DEFAULT NULL,
  `classification` text COLLATE utf8_unicode_ci NOT NULL,
  `category` text COLLATE utf8_unicode_ci NOT NULL,
  `type` text COLLATE utf8_unicode_ci NOT NULL,
  `quantity` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `supplier` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `pay` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `supply`
--

INSERT INTO `supply` (`sup`, `bid`, `classification`, `category`, `type`, `quantity`, `price`, `description`, `image`, `supplier`, `status`, `pay`, `reg_date`) VALUES
(1, 1, 'Ready Made', 'Soft Furnishing & Clothing', 'Cushions', 340, 300, 'soft and fur', 'IMG1323423493.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-22 08:56:02 pm'),
(2, 3, 'Raw Material', 'Calendar', 'Calendar', 18, 500, 'we got you whole year', 'IMG222055190.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-22 10:57:32 pm'),
(3, 3, 'Raw Material', 'Calendar', 'Calendar', 240, 600, 'best calendar', 'IMG2142401955.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-22 11:03:58 pm'),
(4, 4, 'Ready Made', 'Soft Furnishing & Clothing', 'Scarf', 20, 1000, 'best scarf in Kenya', 'IMG1197547453.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 12:01:14 am'),
(5, 9, 'Ready Made', 'Signature Collection', 'Flamingo', 6, 800, 'the art for you', 'IMG2123878616.jpg', '2022ARTG-069', 'Pending', 'Pending', '2022-11-23 12:10:12 am'),
(6, 8, 'Ready Made', 'Signature Collection', 'Summer Landscape', 5, 800, 'signature collection', 'IMG1459545718.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 12:11:30 am'),
(7, 6, 'Ready Made', 'Soft Furnishing & Clothing', 'Sash', 50, 500, 'Honouring the event', 'IMG897260428.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 12:13:38 am'),
(8, 5, 'Ready Made', 'Soft Furnishing & Clothing', 'Tshirt', 30, 1200, 'Your label we give', 'IMG140655306.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 12:14:40 am'),
(9, 10, 'Ready Made', 'Wooden Wall Art', 'Wooden Signs', 10, 50000, 'wall art', 'IMG1159971995.jpg', '2022ARTG-069', 'Pending', 'Pending', '2022-11-23 09:11:36 am'),
(10, 11, 'Ready Made', 'Wooden Wall Art', 'Outdoor Wooden Signs', 10, 90000, 'wall arts', 'IMG776497162.jpg', '2022ARTG-069', 'Rejected', 'Pending', '2022-11-23 09:19:02 am'),
(11, 12, 'Ready Made', 'Wooden Wall Art', 'Wooden Brooks', 4, 90000, 'the wall art', 'IMG595309356.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 09:31:13 am'),
(12, 13, 'Ready Made', 'Calendar', 'Calendar', 15, 300, '2022 calendar', 'IMG2089294022.jpg', '2022ARTG-069', 'Rejected', 'Pending', '2022-11-23 10:28:43 am'),
(13, 14, 'Ready Made', 'Art Paints', 'Green Heron', 2, 300, 'art paints', 'IMG53041476.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 10:33:13 am'),
(14, 9, 'Ready Made', 'Signature Collection', 'Flamingo', 2, 100, 'simply the best', 'IMG1949231538.jpg', '2022ARTG-069', 'Pending', 'Pending', '2022-11-23 11:49:36 am'),
(15, 15, 'Raw Material', 'Soft Furnishing & Clothing', 'Scarf', 12, 200, 'Kemu', 'IMG1167530613.jpg', '2022ARTG-069', 'Approved', 'Paid', '2022-11-23 04:27:05 pm');

-- --------------------------------------------------------

--
-- Table structure for table `volume`
--

CREATE TABLE `volume` (
  `id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `ready` double DEFAULT 0,
  `custom` double DEFAULT 0,
  `service` double DEFAULT 0,
  `supplier` double DEFAULT 0,
  `balance` double DEFAULT 0,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `udate` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `volume`
--

INSERT INTO `volume` (`id`, `amount`, `ready`, `custom`, `service`, `supplier`, `balance`, `date`, `udate`) VALUES
(1, 1122730, 242420, 80310, 800000, 703000, 419730, '2022-11-22 08:29 pm', '2022-11-29 04:56 AM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bidding`
--
ALTER TABLE `bidding`
  ADD PRIMARY KEY (`bid`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`reg`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`entry`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`entry`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payid`);

--
-- Indexes for table `paysup`
--
ALTER TABLE `paysup`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `raw`
--
ALTER TABLE `raw`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `refer`
--
ALTER TABLE `refer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `self_ord`
--
ALTER TABLE `self_ord`
  ADD PRIMARY KEY (`slf`);

--
-- Indexes for table `self_pay`
--
ALTER TABLE `self_pay`
  ADD PRIMARY KEY (`payid`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serv`);

--
-- Indexes for table `ser_pay`
--
ALTER TABLE `ser_pay`
  ADD PRIMARY KEY (`serid`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`entry`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`entry`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `supply`
--
ALTER TABLE `supply`
  ADD PRIMARY KEY (`sup`);

--
-- Indexes for table `volume`
--
ALTER TABLE `volume`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bidding`
--
ALTER TABLE `bidding`
  MODIFY `bid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `reg` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notice`
--
ALTER TABLE `notice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `paysup`
--
ALTER TABLE `paysup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `raw`
--
ALTER TABLE `raw`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `refer`
--
ALTER TABLE `refer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `self_ord`
--
ALTER TABLE `self_ord`
  MODIFY `slf` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `supply`
--
ALTER TABLE `supply`
  MODIFY `sup` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `volume`
--
ALTER TABLE `volume`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
