CREATE DATABASE QuizDB;
USE QuizDB;
CREATE TABLE Login (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    College VARCHAR(100) NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Name VARCHAR(100) NOT NULL,
    PRN VARCHAR(20) NOT NULL
);
CREATE TABLE Info (
    Name VARCHAR(100),
    PRN VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    college_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    prn VARCHAR(255) NOT NULL,
    UNIQUE (prn)  -- Ensure each PRN is unique
);
select * from users;
DROP TABLE login;
show tables;
select * from users;
DELETE FROM users;
commit;
select * from users;
DELETE FROM users;
select * from users;
TRUNCATE TABLE users;
select * from users;
commit;
select * from users;
commit;

