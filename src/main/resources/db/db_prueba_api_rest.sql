DROP TABLE IF EXISTS `films`;

CREATE TABLE `films` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `episode_id` varchar(255) DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
