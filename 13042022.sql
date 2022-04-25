/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.12-log : Database - zoo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zoo` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `zoo`;

/*Table structure for table `animal` */

DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
  `ANIMAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTO` varchar(500) NOT NULL,
  `DESCRIPTION` varchar(1000) NOT NULL,
  `LATITUDE` varchar(500) NOT NULL,
  `LONGTITUDE` varchar(500) NOT NULL,
  PRIMARY KEY (`ANIMAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `animal` */

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `BANK_ID` bigint(100) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NUMBER` bigint(20) NOT NULL,
  `AMOUNT` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `BOOKING` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `PACKAGE_ID` varchar(20) NOT NULL,
  `DATE` date NOT NULL,
  `TIME` time NOT NULL,
  `STATUS` varchar(30) NOT NULL,
  `ENTRY_TIME` time DEFAULT NULL,
  `EXIT_TIME` time DEFAULT NULL,
  PRIMARY KEY (`BOOKING`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

insert  into `booking`(`BOOKING`,`USER_ID`,`PACKAGE_ID`,`DATE`,`TIME`,`STATUS`,`ENTRY_TIME`,`EXIT_TIME`) values 
(1,1,'1','2021-12-08','10:10:00','','12:11:00','12:00:00'),
(2,2,'2','2021-12-06','10:11:00','','12:12:00','12:01:00');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `COMPLAINT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) NOT NULL,
  `COMPLAINT` varchar(1000) NOT NULL,
  `REPLAY` varchar(1000) NOT NULL,
  `STATUS` varchar(500) NOT NULL,
  `DATE` date NOT NULL,
  PRIMARY KEY (`COMPLAINT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`COMPLAINT_ID`,`USER_ID`,`COMPLAINT`,`REPLAY`,`STATUS`,`DATE`) values 
(1,'1','DEALY','hlooo','replayed','2001-01-08'),
(2,'','','','pending','0000-00-00');

/*Table structure for table `emergency` */

DROP TABLE IF EXISTS `emergency`;

CREATE TABLE `emergency` (
  `EMERGENCY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) NOT NULL,
  `MASSAGE` varchar(1000) DEFAULT NULL,
  `DATE` date NOT NULL,
  `TIME` time NOT NULL,
  `STATUS` varchar(50) NOT NULL,
  `LATITUDE` varchar(500) DEFAULT NULL,
  `LONGTITUDE` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`EMERGENCY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `emergency` */

insert  into `emergency`(`EMERGENCY_ID`,`USER_ID`,`MASSAGE`,`DATE`,`TIME`,`STATUS`,`LATITUDE`,`LONGTITUDE`) values 
(1,'1','salad missing','2021-12-13','07:09:44','COMPLETED','77','76'),
(2,'2','missing','2021-12-20','07:09:44','COMPLETED','77','76'),
(3,'3','sfs','2022-01-01','07:09:44','COMPLETED','77','76'),
(4,'4','service bad','2021-12-28','00:00:09','COMPLETED',NULL,NULL);

/*Table structure for table `gallary` */

DROP TABLE IF EXISTS `gallary`;

CREATE TABLE `gallary` (
  `GALLARY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`GALLARY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `gallary` */

insert  into `gallary`(`GALLARY_ID`,`IMAGE`,`DESCRIPTION`) values 
(6,'/static/gallery/25594.jpg','yjhjh'),
(7,'/static/gallery/food feeding.jpg','girraffe'),
(8,'/static/gallery/Safari-3408d5db8779be47c8330518e1887c457e67068d349151b592aa20b7ebb4e61e4.png','desert safari');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `LOGIN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`LOGIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`LOGIN_ID`,`USER_NAME`,`PASSWORD`,`TYPE`) values 
(1,'ADMIN123','0000','ADMIN'),
(2,'zamin123','7377','ADMIN'),
(3,'hisham123','1234','ADMIN');

/*Table structure for table `package` */

DROP TABLE IF EXISTS `package`;

CREATE TABLE `package` (
  `PACKAGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) NOT NULL,
  `IMAGE_1` varchar(100) NOT NULL,
  `IMAGE_2` varchar(100) DEFAULT NULL,
  `IMAGE_3` varchar(100) DEFAULT NULL,
  `DISCRIPTION` varchar(1000) NOT NULL,
  `COST` int(11) NOT NULL,
  PRIMARY KEY (`PACKAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `package` */

insert  into `package`(`PACKAGE_ID`,`TITLE`,`IMAGE_1`,`IMAGE_2`,`IMAGE_3`,`DISCRIPTION`,`COST`) values 
(9,'all in one','/static/tourist_place/BURGER1.jpg','/static/tourist_place/food feeding.jpg','/static/tourist_place/tiger.jpg','all in one combo',750),
(11,'yyyyy','/static/gallery/25594.jpg','/static/tourist_place/ROG-Zephyrus-Duo-15-x-C.A.CHOU-Taipei-Cyber-city-4K(Top-Screen).png','/static/tourist_place/ROG-Zephyrus-Duo-15-x-C.A.CHOU-Taipei-Cyber-city-4K(Top-Screen).png','gghgh',76767);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `PAYMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `BANK_ID` varchar(20) NOT NULL,
  `BOOKING_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`PAYMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staffid` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staff` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `DOB` date NOT NULL,
  `GENDER` varchar(20) NOT NULL,
  `EMAIL` varchar(20) DEFAULT NULL,
  `PHONE` bigint(20) NOT NULL,
  `PLACE` varchar(50) NOT NULL,
  `POST` varchar(20) NOT NULL,
  `PIN` int(11) NOT NULL,
  `DISTRICT` varchar(20) DEFAULT NULL,
  `IMAGE` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`USER_ID`,`LOGIN_ID`,`NAME`,`DOB`,`GENDER`,`EMAIL`,`PHONE`,`PLACE`,`POST`,`PIN`,`DISTRICT`,`IMAGE`) values 
(1,'1','zamin','2001-08-30','male','zaminasmin111@gmail.',9207007377,'nadapuram','nadapuram',673504,'kozhikode','/static/my/zamin.jpg'),
(2,'2','sahal','2001-02-08','male','sahalzzali2@gmail.co',8590305256,'nadapuram','nadapuram',673504,'kozhikode','/static/my/sahal.jpg'),
(3,'3','hisham','2001-01-06','male','muhammedhishamkallil',8547314600,'vanimel','vanimel',673506,'kozhikode','/static/my/hisham.jpg'),
(4,'4','pranav','2001-09-18','male','pranavsvlm@gmail.com',9496474060,'valayam','valayam',673517,'kozhikode','/static/my/pranav.jpg');

/*Table structure for table `visitors` */

DROP TABLE IF EXISTS `visitors`;

CREATE TABLE `visitors` (
  `VISITORS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `ENTRY_TIME` int(11) NOT NULL,
  `EXIT_TIME` time NOT NULL,
  PRIMARY KEY (`VISITORS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `visitors` */

insert  into `visitors`(`VISITORS_ID`,`DATE`,`ENTRY_TIME`,`EXIT_TIME`) values 
(1,'2021-12-22',11,'21:00:00'),
(2,'2021-12-06',11,'22:00:33');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
