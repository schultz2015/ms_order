/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`order` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `order`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminid` int NOT NULL,
  `adminname` varchar(20) NOT NULL,
  `adminpassword` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `admin` */

insert  into `admin`(`adminid`,`adminname`,`adminpassword`) values (1,'admin','123456');

/*Table structure for table `house` */

DROP TABLE IF EXISTS `house`;

CREATE TABLE `house` (
  `houseid` int NOT NULL,
  `address` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `housename` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`houseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `house` */

insert  into `house`(`houseid`,`address`,`housename`) values (1,'理塘县','雪豹小屋'),(2,'福建省平潭县坛南湾','岚风小院'),(3,'山东省青岛市市北区馆陶路','雅居'),(4,'广西玉林','周长旅舍');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `houseid` int DEFAULT NULL,
  `roomid` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `roomname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `truename` varchar(20) DEFAULT NULL,
  `start` varchar(20) DEFAULT NULL,
  `end` varchar(20) DEFAULT NULL,
  `cancel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;

/*Data for the table `order` */

insert  into `order`(`orderid`,`userid`,`houseid`,`roomid`,`price`,`tel`,`roomname`,`truename`,`start`,`end`,`cancel`) values (19,1,1,1,600,'19153884566','雪山全景单人间','禤意岳','2023-01-11','2023-01-16',' '),(20,3,1,1,600,'18542584565','雪山全景单人间','邓舟昌','2023-02-22','2023-02-23',' '),(21,3,2,1,300,'18542584565','青年单人间','邓舟昌','2023-03-23','2023-03-29',' '),(26,1,3,1,500,'19153884566','精致大床房','禤意岳','2023-06-27','2023-06-30',' '),(27,1,3,3,800,'19153884566','豪华大床房','禤意岳','2023-07-11','2023-07-12',' '),(31,6,2,3,500,'11125548','海景单人间','zone','2023-07-28','2023-07-29','退订'),(32,6,1,1,1800,'11125548','豪华雪景大床房','zone','2023-07-13','2023-07-16',' '),(33,6,1,1,1800,'11125548','豪华雪景大床房','zone','2023-07-26','2023-07-29',' '),(34,6,1,1,600,'11125548','豪华雪景大床房','zone','2023-07-30','2023-07-31',' '),(35,6,1,1,2400,'11125548','豪华雪景大床房','zone','2023-07-18','2023-07-22',' '),(36,8,1,2,3600,'123456','雪景双人间','xuan','2023-07-13','2023-07-22','退订'),(37,8,1,2,1600,'123456','雪景双人间','xuan','2023-07-23','2023-07-27',' '),(38,8,2,3,3500,'123456','海景单人间','xuan','2023-07-20','2023-07-27',' '),(39,8,1,3,1600,'123456','全景雪景房','xuan','2023-07-14','2023-07-16','取消退订');

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `houseid` int NOT NULL,
  `roomid` int NOT NULL,
  `price` int DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `roomname` varchar(20) DEFAULT NULL,
  `roomimg` text CHARACTER SET utf8mb3 COLLATE utf8_general_ci,
  PRIMARY KEY (`houseid`,`roomid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `room` */

insert  into `room`(`houseid`,`roomid`,`price`,`type`,`roomname`,`roomimg`) values (1,1,600,'无早餐·2人','豪华雪景大床房','1.1.png'),(1,2,400,'无早餐·2人','雪景双人间','1.2.png'),(1,3,800,'免费早餐·2人','全景雪景房','1.3.png'),(2,1,300,'无早餐·1人','青年单人间','2.1.png'),(2,2,500,'免费早餐·2人','海景大床房','2.2.png'),(2,3,500,'免费早餐·1人','海景单人间','2.3.png'),(2,4,400,'免费早餐·1人','海岸全景房','海边民宿.png'),(3,1,500,'免费早餐·2人','精致大床房','3.1.png'),(3,2,500,'免费早餐·2人','温馨大床房','3.2.png'),(3,3,800,'免费早餐·2人','豪华大床房','3.3.png'),(4,1,600,'无','大床房','u=3217354218,370.png');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `truename` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`password`,`truename`,`tel`,`salt`) values (8,'xuan','ba1431529187ce1b93a93beba9e92b67','xuan','123456','5F189373-E793-419E-B3B7-7DEBA0596F93');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
