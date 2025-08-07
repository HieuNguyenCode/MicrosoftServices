DROP DATABASE IF EXISTS microservice;
CREATE DATABASE IF NOT EXISTS microservice;
USE microservice;

-- Bảng department
DROP TABLE IF EXISTS department;
CREATE TABLE department
(
    IDDepartment CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    `Name`       VARCHAR(100)                            NOT NULL UNIQUE,
    total_member  INT UNSIGNED,
    Type         ENUM ('DEV','TEST','SCRUM_MASTER','PM') NOT NULL,
    created_at    DATETIME             DEFAULT (NOW())
);

-- Bảng account
DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    IDAccount    CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    user_name     VARCHAR(50) NOT NULL UNIQUE,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50),
    IDDepartment CHAR(36)    NOT NULL,
    FOREIGN KEY (IDDepartment) REFERENCES department (IDDepartment)
);

-- Bảng user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    IDUser       CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    Email        VARCHAR(100) NOT NULL UNIQUE,
    `Password`   VARCHAR(120) NOT NULL,
    UserName     VARCHAR(50)  NOT NULL UNIQUE,
    FirstName    VARCHAR(50),
    LastName     VARCHAR(50),
    AccessToken  VARCHAR(255),
    RefreshToken VARCHAR(255)
);

-- Bảng refreshtoken
DROP TABLE IF EXISTS refreshtoken;
CREATE TABLE refreshtoken
(
    IDRefreshToken CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    ExpiryDate     DATETIME(6)  NOT NULL,
    Token          VARCHAR(255) NOT NULL UNIQUE,
    IDUser         CHAR(36),
    FOREIGN KEY (IDUser) REFERENCES `user` (IDUser)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    IDRole CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    `Name` ENUM ('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER') NOT NULL
);


DROP TABLE IF EXISTS userroles;
CREATE TABLE userroles
(
    IDUser CHAR(36) NOT NULL,
    IDRole CHAR(36) NOT NULL,
    PRIMARY KEY (IDUser, IDRole),
    CONSTRAINT FOREIGN KEY (IDRole) REFERENCES role (IDRole),
    CONSTRAINT FOREIGN KEY (IDUser) REFERENCES `user` (IDUser)
);

INSERT INTO department(`Name`, total_member, Type, created_at)
VALUES ('Marketing', 1, 'DEV', '2020-03-05'),
       ('Sale', 2, 'TEST', '2020-03-05'),
       ('Bảo vệ', 3, 'SCRUM_MASTER', '2020-03-07'),
       ('Nhân sự', 4, 'PM', '2020-03-08'),
       ('Kỹ thuật', 5, 'DEV', '2020-03-10'),
       ('Tài chính', 6, 'SCRUM_MASTER', NOW()),
       ('Phó giám đốc', 7, 'PM', NOW()),
       ('Giám đốc', 8, 'TEST', '2020-04-07'),
       ('Thư kí', 9, 'PM', '2020-04-07'),
       ('Bán hàng', 10, 'DEV', '2020-04-09');

INSERT INTO account(user_name, IDDepartment)
VALUES ('dangblack', (SELECT IDDepartment FROM department WHERE `Name` = 'Kỹ thuật')),
       ('quanganh', (SELECT IDDepartment FROM department WHERE `Name` = 'Marketing')),
       ('vanchien', (SELECT IDDepartment FROM department WHERE `Name` = 'Marketing')),
       ('cocoduongqua', (SELECT IDDepartment FROM department WHERE `Name` = 'Marketing')),
       ('doccocaubai', (SELECT IDDepartment FROM department WHERE `Name` = 'Sale')),
       ('khabanh', (SELECT IDDepartment FROM department WHERE `Name` = 'Sale')),
       ('huanhoahong', (SELECT IDDepartment FROM department WHERE `Name` = 'Sale')),
       ('tungnui', (SELECT IDDepartment FROM department WHERE `Name` = 'Thư kí')),
       ('duongghuu', (SELECT IDDepartment FROM department WHERE `Name` = 'Bán hàng'));

INSERT INTO role(`Name`)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_ADMIN');