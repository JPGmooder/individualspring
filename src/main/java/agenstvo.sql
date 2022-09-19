-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 19 2022 г., 23:00
-- Версия сервера: 10.4.10-MariaDB
-- Версия PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `agenstvo`
--

-- --------------------------------------------------------

--
-- Структура таблицы `address`
--

CREATE TABLE `address` (
  `id_address_data` bigint(20) NOT NULL,
  `address_data_fakt` varchar(100) DEFAULT NULL,
  `address_data_location` varchar(100) DEFAULT NULL,
  `address_data_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `address`
--

INSERT INTO `address` (`id_address_data`, `address_data_fakt`, `address_data_location`, `address_data_name`) VALUES
(2, 'adasdasdasdasdasdasdas', 'dafsasdasdasdasdad', NULL),
(15, 'Нахабино, Чкалова 7, 96', 'Нахабино, Чкалова 7, 96', 'Нахабино, Чкалова 7, 96'),
(17, 'улица Кирова, 7', 'улица Кирова, 7', 'улица Кирова, 7');

-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE `client` (
  `id_client` bigint(20) NOT NULL,
  `person_id_person` bigint(20) DEFAULT NULL,
  `personal_data_id_pd` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `client`
--

INSERT INTO `client` (`id_client`, `person_id_person`, `personal_data_id_pd`, `user_id`) VALUES
(6, 3, 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `client_personal_data`
--

CREATE TABLE `client_personal_data` (
  `id_pd` bigint(20) NOT NULL,
  `pd_employees_amount` int(11) NOT NULL,
  `pd_name` varchar(100) NOT NULL,
  `client_ur_data_id_ur_data` bigint(20) DEFAULT NULL,
  `adress_data_id_address_data` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `client_personal_data`
--

INSERT INTO `client_personal_data` (`id_pd`, `pd_employees_amount`, `pd_name`, `client_ur_data_id_ur_data`, `adress_data_id_address_data`) VALUES
(5, 12, 'Абоба корп', 4, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `doljnost`
--

CREATE TABLE `doljnost` (
  `id` bigint(20) NOT NULL,
  `salary` double NOT NULL,
  `doljnost_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `doljnost`
--

INSERT INTO `doljnost` (`id`, `salary`, `doljnost_name`) VALUES
(8, 12000, 'Охранник'),
(9, 1500000, 'Менеджер');

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `doljnost_id` bigint(20) DEFAULT NULL,
  `filial_id_filial` bigint(20) DEFAULT NULL,
  `person_id_person` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `employee`
--

INSERT INTO `employee` (`id`, `doljnost_id`, `filial_id_filial`, `person_id_person`, `user_id`) VALUES
(28, 9, 16, 27, 1),
(31, 8, 16, 30, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `employee_order`
--

CREATE TABLE `employee_order` (
  `order_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `employee_order`
--

INSERT INTO `employee_order` (`order_id`, `employee_id`) VALUES
(37, 28),
(37, 31);

-- --------------------------------------------------------

--
-- Структура таблицы `filial`
--

CREATE TABLE `filial` (
  `id_filial` bigint(20) NOT NULL,
  `filial_name` varchar(50) DEFAULT NULL,
  `address_id_address_data` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `filial`
--

INSERT INTO `filial` (`id_filial`, `filial_name`, `address_id_address_data`) VALUES
(14, 'Мой дом', 15),
(16, 'Абоберкер', 17);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(38);

-- --------------------------------------------------------

--
-- Структура таблицы `passport`
--

CREATE TABLE `passport` (
  `id_passport` bigint(20) NOT NULL,
  `passport_number` varchar(6) DEFAULT NULL,
  `passport_serial` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `passport`
--

INSERT INTO `passport` (`id_passport`, `passport_number`, `passport_serial`) VALUES
(1, '123456', '1234'),
(18, '123456', '1234'),
(19, '123456', '1234'),
(20, '123456', '1234'),
(23, '123456', '1234'),
(26, '123456', '1234'),
(29, '123456', '1323');

-- --------------------------------------------------------

--
-- Структура таблицы `period`
--

CREATE TABLE `period` (
  `id_period` bigint(20) NOT NULL,
  `period_end` date DEFAULT NULL,
  `period_start` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `period`
--

INSERT INTO `period` (`id_period`, `period_end`, `period_start`) VALUES
(36, '2022-09-23', '2022-09-02');

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE `person` (
  `id_person` bigint(20) NOT NULL,
  `person_age` int(11) NOT NULL,
  `person_fname` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `person_patronomyc` varchar(255) DEFAULT NULL,
  `passport_id_passport` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id_person`, `person_age`, `person_fname`, `person_name`, `person_patronomyc`, `passport_id_passport`) VALUES
(3, 123, 'Горынин', 'Константин', 'Сергеевич', 1),
(21, 10, 'Горынин', 'Константин', 'Сергеевич', NULL),
(24, 220, 'вфаывим', 'Константин', 'fsghgdffes', NULL),
(27, 120, 'Горынин', 'Константин', 'Сергеевич', NULL),
(30, 120, 'Горынин', 'qwerty', 'wqertyui', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE `product` (
  `id_product` bigint(20) NOT NULL,
  `is_accepted` bit(1) NOT NULL,
  `product_description` varchar(100) DEFAULT NULL,
  `product_price` double NOT NULL,
  `client_id_client` bigint(20) DEFAULT NULL,
  `period_id_period` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `product`
--

INSERT INTO `product` (`id_product`, `is_accepted`, `product_description`, `product_price`, `client_id_client`, `period_id_period`) VALUES
(37, b'1', 'Абоба бебрика as sadasd asd asd sad ', 122220, 6, 36);

-- --------------------------------------------------------

--
-- Структура таблицы `ur_data`
--

CREATE TABLE `ur_data` (
  `id_ur_data` bigint(20) NOT NULL,
  `ur_databic` varchar(9) DEFAULT NULL,
  `ur_datainn` varchar(10) DEFAULT NULL,
  `ur_dataogrn` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `ur_data`
--

INSERT INTO `ur_data` (`id_ur_data`, `ur_databic`, `ur_datainn`, `ur_dataogrn`) VALUES
(4, '211111111', '1231232131', '1111111111111');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `password`, `username`) VALUES
(1, '$2a$08$wZo9ggxBewIrGblvnAmiV.JIAUMwu8Y52CWQlz5x0U8hKJpvlEyGe', 'Admin'),
(2, '12345', 'sadas'),
(3, 'qwerty', 'qwerty'),
(5, '12345', 'bebra'),
(7, '12345', 'bebras');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id_address_data`);

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD KEY `FKfuhh0b2aq8oygj4nr3owrtdi` (`person_id_person`),
  ADD KEY `FKd0jr4tnp5stb5lo0cn8pivrgh` (`personal_data_id_pd`),
  ADD KEY `FKk1fi84oi1yyuswr40h38kjy1s` (`user_id`);

--
-- Индексы таблицы `client_personal_data`
--
ALTER TABLE `client_personal_data`
  ADD PRIMARY KEY (`id_pd`),
  ADD KEY `FKegjqmt7j69aj9eov5c5dm61jc` (`client_ur_data_id_ur_data`),
  ADD KEY `FKsmkca34d3kqc1n1eqpqs1oatu` (`adress_data_id_address_data`);

--
-- Индексы таблицы `doljnost`
--
ALTER TABLE `doljnost`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ksbujkka8u5tictr10xjry4v1` (`doljnost_name`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4m7bx143h7rlvoysbhlxfkwi9` (`doljnost_id`),
  ADD KEY `FK7mb3tnyxcxxdwi0j3rcw2wjtp` (`filial_id_filial`),
  ADD KEY `FK1oji4saqon2gnixrsuv92xicr` (`person_id_person`),
  ADD KEY `FK6lk0xml9r7okjdq0onka4ytju` (`user_id`);

--
-- Индексы таблицы `employee_order`
--
ALTER TABLE `employee_order`
  ADD PRIMARY KEY (`employee_id`,`order_id`),
  ADD KEY `FK4xj0swe5v2kkleflgcm8f1geg` (`order_id`);

--
-- Индексы таблицы `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id_filial`),
  ADD KEY `FKkgrghifmvns9qj049yh93fpu4` (`address_id_address_data`);

--
-- Индексы таблицы `passport`
--
ALTER TABLE `passport`
  ADD PRIMARY KEY (`id_passport`);

--
-- Индексы таблицы `period`
--
ALTER TABLE `period`
  ADD PRIMARY KEY (`id_period`);

--
-- Индексы таблицы `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id_person`),
  ADD KEY `FKm70ohahlkv4x5tmqe3nph0heh` (`passport_id_passport`);

--
-- Индексы таблицы `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `FKfv4eu04dg770mkjedmv1rcphp` (`client_id_client`),
  ADD KEY `FKe9cbtlae90m1ulpy0s2hfhrrn` (`period_id_period`);

--
-- Индексы таблицы `ur_data`
--
ALTER TABLE `ur_data`
  ADD PRIMARY KEY (`id_ur_data`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKd0jr4tnp5stb5lo0cn8pivrgh` FOREIGN KEY (`personal_data_id_pd`) REFERENCES `client_personal_data` (`id_pd`),
  ADD CONSTRAINT `FKfuhh0b2aq8oygj4nr3owrtdi` FOREIGN KEY (`person_id_person`) REFERENCES `person` (`id_person`),
  ADD CONSTRAINT `FKk1fi84oi1yyuswr40h38kjy1s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `client_personal_data`
--
ALTER TABLE `client_personal_data`
  ADD CONSTRAINT `FKegjqmt7j69aj9eov5c5dm61jc` FOREIGN KEY (`client_ur_data_id_ur_data`) REFERENCES `ur_data` (`id_ur_data`),
  ADD CONSTRAINT `FKsmkca34d3kqc1n1eqpqs1oatu` FOREIGN KEY (`adress_data_id_address_data`) REFERENCES `address` (`id_address_data`);

--
-- Ограничения внешнего ключа таблицы `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK1oji4saqon2gnixrsuv92xicr` FOREIGN KEY (`person_id_person`) REFERENCES `person` (`id_person`),
  ADD CONSTRAINT `FK4m7bx143h7rlvoysbhlxfkwi9` FOREIGN KEY (`doljnost_id`) REFERENCES `doljnost` (`id`),
  ADD CONSTRAINT `FK6lk0xml9r7okjdq0onka4ytju` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK7mb3tnyxcxxdwi0j3rcw2wjtp` FOREIGN KEY (`filial_id_filial`) REFERENCES `filial` (`id_filial`);

--
-- Ограничения внешнего ключа таблицы `employee_order`
--
ALTER TABLE `employee_order`
  ADD CONSTRAINT `FK4xj0swe5v2kkleflgcm8f1geg` FOREIGN KEY (`order_id`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKeafhnqxj86sg3botet6e53ar8` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `filial`
--
ALTER TABLE `filial`
  ADD CONSTRAINT `FKkgrghifmvns9qj049yh93fpu4` FOREIGN KEY (`address_id_address_data`) REFERENCES `address` (`id_address_data`);

--
-- Ограничения внешнего ключа таблицы `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FKm70ohahlkv4x5tmqe3nph0heh` FOREIGN KEY (`passport_id_passport`) REFERENCES `passport` (`id_passport`);

--
-- Ограничения внешнего ключа таблицы `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKe9cbtlae90m1ulpy0s2hfhrrn` FOREIGN KEY (`period_id_period`) REFERENCES `period` (`id_period`),
  ADD CONSTRAINT `FKfv4eu04dg770mkjedmv1rcphp` FOREIGN KEY (`client_id_client`) REFERENCES `client` (`id_client`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
