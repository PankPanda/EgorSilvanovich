DROP SCHEMA IF EXISTS `People`;

CREATE SCHEMA IF NOT EXISTS `People` DEFAULT CHARACTER SET utf8;
USE `People`;

DROP TABLE IF EXISTS `People`.`Person`;

CREATE TABLE IF NOT EXISTS `People`.`Person`
(
  `id`            INT          NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR (50) NULL ,
  `sur_name`      VARCHAR (50) NULL ,
  `age`           INT          NULL,
  `salary`        DECIMAL      NULL,
  `passport`      CHAR(10)     NULL,
  `address`       VARCHAR(200) NULL,
  `date_birthday` DATE         NULL,
  `date_time`     DATETIME     NULL,
  `time_lunch`    TIMESTAMP(4) NULL,
  `letter`        MEDIUMTEXT   NULL,
  PRIMARY KEY (`id`)
);

insert into `People`.`Person` (age, salary, passport, address, date_birthday, date_time, time_lunch, letter)
values (21, 1000.0, 'asdfghjklz', 'city and street', '2000-01-01', NOW(), NOW(), 'text'),
       (30, 3000.0, 'asdfghjklz', 'city and street', '1991-01-01', NOW() + 5, NOW() + 5, 'text'),
       (18, 500.0, 'asdfghjklz', 'city and street', '2003-01-01', NOW() + 3, NOW() + 3, 'text'),
       (41, 5000.0, 'asdfghjklz', 'city and street', '1980-01-01', NOW() + 1, NOW() + 1, 'text'),
       (26, 2000.0, 'asdfghjklz', 'city and street', '1995-01-01', NOW(), NOW(), 'text');

select *
from Person
where age > 21
order by date_time;