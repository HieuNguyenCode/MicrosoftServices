DROP DATABASE IF EXISTS microservice;
CREATE DATABASE IF NOT EXISTS microservice;
USE microservice;

-- Bảng department
DROP TABLE IF EXISTS department;
CREATE TABLE department
(
    Id_department CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    `name`       VARCHAR(100)                            NOT NULL UNIQUE,
    total_member  INT UNSIGNED,
    type         ENUM ('DEV','TEST','SCRUM_MASTER','PM') NOT NULL,
    created_at    DATETIME             DEFAULT (NOW())
);

-- Bảng account
DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    Id_account    CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    user_name     VARCHAR(50) NOT NULL UNIQUE,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50),
    Id_department CHAR(36)    NOT NULL,
    FOREIGN KEY (Id_department) REFERENCES department (Id_department)
);

-- Bảng user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    Id_user       CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    email        VARCHAR(100) NOT NULL UNIQUE,
    `password`   VARCHAR(120) NOT NULL,
    user_name     VARCHAR(50)  NOT NULL UNIQUE,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50)
);

-- Bảng refreshtoken
DROP TABLE IF EXISTS refreshtoken;
CREATE TABLE refreshtoken
(
    Id_refreshtoken CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    Keyaccesstoken CHAR(36) NOT NULL,
    Keyrefreshtoken CHAR(36) NOT NULL,
    Id_user         CHAR(36) NOT NULL,
    expiry_date  DATETIME NOT NULL,
    updated_at     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Id_user) REFERENCES `user` (Id_user)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    Id_role CHAR(36) PRIMARY KEY DEFAULT (uuid()),
    `name` ENUM ('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER') NOT NULL
);


DROP TABLE IF EXISTS userroles;
CREATE TABLE userroles
(
    Id_user CHAR(36) NOT NULL,
    Id_role CHAR(36) NOT NULL,
    PRIMARY KEY (Id_user, Id_role),
    CONSTRAINT FOREIGN KEY (Id_role) REFERENCES role (Id_role),
    CONSTRAINT FOREIGN KEY (Id_user) REFERENCES `user` (Id_user)
);

INSERT INTO department(`Name`, total_member, type, created_at)
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

INSERT INTO account(user_name, account.Id_department)
VALUES ('dangblack', (SELECT Id_department FROM department WHERE `Name` = 'Kỹ thuật')),
       ('quanganh', (SELECT Id_department FROM department WHERE `Name` = 'Marketing')),
       ('vanchien', (SELECT Id_department FROM department WHERE `Name` = 'Marketing')),
       ('cocoduongqua', (SELECT Id_department FROM department WHERE `Name` = 'Marketing')),
       ('doccocaubai', (SELECT Id_department FROM department WHERE `Name` = 'Sale')),
       ('khabanh', (SELECT Id_department FROM department WHERE `Name` = 'Sale')),
       ('huanhoahong', (SELECT Id_department FROM department WHERE `Name` = 'Sale')),
       ('tungnui', (SELECT Id_department FROM department WHERE `Name` = 'Thư kí')),
       ('duongghuu', (SELECT Id_department FROM department WHERE `Name` = 'Bán hàng'));

INSERT INTO role(`Name`)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_ADMIN');