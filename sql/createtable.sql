CREATE TABLE `yangguang_movie` (
  `thunder_pid` varchar(45) NOT NULL,
  `title` varchar(200) NOT NULL DEFAULT '',
  `description` longtext,
  `ftp_url` varchar(5000) NOT NULL DEFAULT '',
  PRIMARY KEY (`thunder_pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;