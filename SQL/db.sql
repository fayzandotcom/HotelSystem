CREATE TABLE `payment` (
  `pay_id` int(8) NOT NULL AUTO_INCREMENT,
  `pay_mode_id` int(8) NOT NULL,
  `pay_amount` double NOT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

CREATE TABLE `payment_mode` (
  `paym_id` int(8) NOT NULL AUTO_INCREMENT,
  `paym_name` varchar(20) NOT NULL,
  `paym_desc` text,
  PRIMARY KEY (`paym_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `reservation` (
  `res_id` int(8) NOT NULL AUTO_INCREMENT,
  `res_u_id` int(8) NOT NULL,
  `res_room_id` int(8) NOT NULL,
  `res_start_date` datetime NOT NULL,
  `res_end_date` datetime NOT NULL,
  `res_pay_id` int(8) NOT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `u_id` int(8) NOT NULL AUTO_INCREMENT,
  `u_fname` varchar(25) NOT NULL,
  `u_lname` varchar(25) NOT NULL,
  `u_username` varchar(40) NOT NULL,
  `u_password` varchar(40) NOT NULL,
  `u_role` varchar(20) NOT NULL,
  `u_email` varchar(40) NOT NULL,
  `u_dob` datetime DEFAULT NULL,
  `u_gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_username_UNIQUE` (`u_username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


