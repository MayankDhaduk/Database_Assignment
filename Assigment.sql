/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - assignment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`assignment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `assignment`;

/*Table structure for table `depttable` */

DROP TABLE IF EXISTS `depttable`;

CREATE TABLE `depttable` (
  `Deptno` int(2) NOT NULL,
  `Dname` varchar(14) DEFAULT NULL,
  `loc` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`Deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `depttable` */

insert  into `depttable`(`Deptno`,`Dname`,`loc`) values 
(10,'ACCOUNTING','NEW YORK'),
(20,'REASERCH','DALLAS'),
(30,'SALES','CHICAGO'),
(40,'OPERATIONS','BOSTON');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `Empno` int(4) NOT NULL,
  `Ename` varchar(10) DEFAULT NULL,
  `Job` varchar(9) DEFAULT NULL,
  `Mgr` int(4) DEFAULT NULL,
  `Hiredate` date DEFAULT NULL,
  `Sal` double DEFAULT NULL,
  `Comm` double DEFAULT NULL,
  `Deptno` int(2) DEFAULT NULL,
  PRIMARY KEY (`Empno`),
  KEY `Deptno` (`Deptno`),
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`Deptno`) REFERENCES `depttable` (`Deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `emp` */

insert  into `emp`(`Empno`,`Ename`,`Job`,`Mgr`,`Hiredate`,`Sal`,`Comm`,`Deptno`) values 
(7369,'SMITH','CLERK',7902,'1980-12-17',8600,NULL,20),
(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30),
(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30),
(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20),
(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30),
(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30),
(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10),
(7788,'SCOTT','ANALYST',7566,'1987-06-11',3000,NULL,20),
(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10),
(7844,'TURNER','SALESMAN',7698,'1981-08-09',1500,0,30),
(7876,'ADAMS','CLERK',7788,'1987-07-13',1100,NULL,20),
(7900,'JAMES','CLERK',7698,'1981-03-12',950,NULL,30),
(7902,'FORD','ANALYST',7566,'1981-03-12',3000,NULL,20),
(7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10);

/*Table structure for table `employee_log` */

DROP TABLE IF EXISTS `employee_log`;

CREATE TABLE `employee_log` (
  `Emp_id` int(5) NOT NULL AUTO_INCREMENT,
  `Log_date` date DEFAULT NULL,
  `New_Salary` int(10) DEFAULT NULL,
  `Actionn` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `employee_log` */

insert  into `employee_log`(`Emp_id`,`Log_date`,`New_Salary`,`Actionn`) values 
(1,NULL,800,NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `Rno` int(2) DEFAULT NULL,
  `sname` varchar(40) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `pin` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `student` */

/* Trigger structure for table `emp` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `Newsalary` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `Newsalary` BEFORE UPDATE ON `emp` FOR EACH ROW BEGIN

insert into employee_log(NEW_Salary) values(old.sal);
    END */$$


DELIMITER ;

/* Procedure structure for procedure `pro` */

/*!50003 DROP PROCEDURE IF EXISTS  `pro` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pro`(in Deptno int)
BEGIN

	select Ename,Dname from emp e join depttable d on e.Deptno=d.Deptno;

	END */$$
DELIMITER ;

/*Table structure for table `v1` */

DROP TABLE IF EXISTS `v1`;

/*!50001 DROP VIEW IF EXISTS `v1` */;
/*!50001 DROP TABLE IF EXISTS `v1` */;

/*!50001 CREATE TABLE  `v1`(
 `Ename` varchar(10) ,
 `job` varchar(9) ,
 `dname` varchar(14) ,
 `loc` varchar(13) 
)*/;

/*View structure for view v1 */

/*!50001 DROP TABLE IF EXISTS `v1` */;
/*!50001 DROP VIEW IF EXISTS `v1` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v1` AS (select `e`.`Ename` AS `Ename`,`e`.`Job` AS `job`,`d`.`Dname` AS `dname`,`d`.`loc` AS `loc` from (`emp` `e` join `depttable` `d` on(`e`.`Deptno` = `d`.`Deptno`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
