create database AppDB;

use AppDB;

CREATE TABLE `contacts` (
  `name` varchar(45),
  `size` varchar(45),
  `doctype` varchar(100),
  `photo` longblob,
  `root` varchar(50)
) 