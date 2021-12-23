-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-06-29 09:20:17
-- 伺服器版本： 10.4.19-MariaDB
-- PHP 版本： 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `online_shop`
--

-- --------------------------------------------------------

--
-- 資料表結構 `products`
--

CREATE TABLE `products` (
  `id` int(10) NOT NULL,
  `title` varchar(30) NOT NULL,
  `describe` varchar(200) NOT NULL,
  `price` varchar(10) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `title`, `describe`, `price`, `photo`, `owner`, `phone`) VALUES
(1, 'baby bird', 'Repair Moisturizing | Moisturi', '38', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/a.jpg', 'sam', '12345678'),
(2, 'Avene Soothing Special Care Mo', 'Moisturize | Whitening | Japan', '48', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/b.jpg', 'sam', '12345678'),
(3, 'Avene Repair Cream', 'Moisturizing | Moist | Japan', '786', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/c.jpg', 'sam', '12345678'),
(4, 'Winona Su-Sensitive Moisturizi', 'Moisturizing | Repair the dama', '123', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/d.jpg', 'sam', '12345678'),
(5, 'OLAY OLAY', 'moisturizing | oil-control', '474', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/e.jpg', 'sam', '12345678'),
(6, 'Nature Hall Mens Glacier Mois', 'moisturizing', '456', 'http://192.168.242.216/4210EA/online_shop/Products_php/images/f.jpg', 'sam', '12345678');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
