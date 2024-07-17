CREATE DATABASE atm;
USE atm;
CREATE TABLE list(
id INT PRIMARY KEY auto_increment,
ac_no INT,
name VARCHAR(20),
balance INT);
INSERT INTO list (id,ac_no,name,balance)VALUES ("1","1111","Mevini","10000");
