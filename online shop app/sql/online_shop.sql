-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 2022 年 07 月 25 日 19:38
-- 伺服器版本： 10.4.21-MariaDB
-- PHP 版本： 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫: `online_shop`
--

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `id` int(10) NOT NULL,
  `product_id` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `buyer` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `location` varchar(100) NOT NULL,
  `price` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(1, 'baby bird', 'Repair Moisturizing | Moisturi', '38', '4210EA/online_shop/Products_php/images/a.jpg', 'sam', '12345678'),
(2, 'Avene Soothing Special Care Mo', 'Moisturize | Whitening | Japan', '48', '4210EA/online_shop/Products_php/images/b.jpg', 'sam', '12345678'),
(3, 'Avene Repair Cream', 'Moisturizing | Moist | Japan', '786', '4210EA/online_shop/Products_php/images/c.jpg', 'sam', '12345678'),
(4, 'Winona Su-Sensitive Moisturizi', 'Moisturizing | Repair the dama', '123', '4210EA/online_shop/Products_php/images/d.jpg', 'sam', '12345678'),
(5, 'OLAY OLAY', 'moisturizing | oil-control', '474', '4210EA/online_shop/Products_php/images/e.jpg', 'sam', '12345678'),
(6, 'Nature Hall Mens Glacier Mois', 'moisturizing', '456', '4210EA/online_shop/Products_php/images/f.jpg', 'sam', '12345678');

-- --------------------------------------------------------

--
-- 資料表結構 `shopping_carts`
--

CREATE TABLE `shopping_carts` (
  `id` int(10) NOT NULL,
  `title` varchar(30) NOT NULL,
  `describe` varchar(200) NOT NULL,
  `price` varchar(10) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `shopping_carts`
--

INSERT INTO `shopping_carts` (`id`, `title`, `describe`, `price`, `photo`, `owner`, `phone`, `email`) VALUES
(15, 'Winona Su-Sensitive Moisturizi', 'Moisturizing | Repair the dama', '123', '4210EA/online_shop/Products_php/images/d.jpg', 'sam', '12345678', 'sam@gmail.com'),
(16, 'OLAY OLAY', 'moisturizing | oil-control', '474', '4210EA/online_shop/Products_php/images/e.jpg', 'sam', '12345678', 'sam@gmail.com'),
(17, 'Winona Su-Sensitive Moisturizi', 'Moisturizing | Repair the dama', '123', '4210EA/online_shop/Products_php/images/d.jpg', 'sam', '12345678', 'ulric@gmail.com'),
(18, 'Avene Soothing Special Care Mo', 'Moisturize | Whitening | Japan', '48', '4210EA/online_shop/Products_php/images/b.jpg', 'sam', '12345678', 'sam@gmail.com'),
(19, 'baby bird', 'Repair Moisturizing | Moisturi', '38', '4210EA/online_shop/Products_php/images/a.jpg', 'sam', '12345678', 'ulric@gmail.com');

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'sam', 'sam@gmail.com', 'd54d1702ad0f8326224b817c796763c9'),
(8, 'ulric', 'ulric@gmail.com', 'd54d1702ad0f8326224b817c796763c9');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `shopping_carts`
--
ALTER TABLE `shopping_carts`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `shopping_carts`
--
ALTER TABLE `shopping_carts`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
