create database login;
--password :- ser@123
use login

create table signin(
username varchar(255),
password varchar(100),
);

select * from signin;

use login  --- use data base

select * from SignIn; --- retrive data from table

sp_rename 'SingIn','SignIn'  --- rename the table name

-- For Register form with SignUp table
create table SignUp(
username varchar(255),
password varchar(255),
emailid varchar(255),
phoneNumber varchar(10)
);

ALTER TABLE SignUp
ALTER COLUMN phoneNumber VARCHAR(20);


select *from SignUp;


======================
select * from SignIn
-- alter column in SignIn table
ALTER TABLE SignIn
ADD logout_time DATETIME;


ALTER TABLE SignIn
ADD login_time DATETIME DEFAULT CURRENT_TIMESTAMP;

--- to modify column In Sign IN --> when to again same user to login no need to enter duplicate record
--- in a table


ALTER TABLE SignIn
ADD CONSTRAINT unique_username UNIQUE (username);

--- if above query to be execute in table than show error because duplicate record already exists
-- First need to remove duplicate recoerd from table
--- but this query will deleted all duplicate record
DELETE FROM SignIn
WHERE username IN (
    SELECT username
    FROM (
        SELECT username,
               ROW_NUMBER() OVER(PARTITION BY username ORDER BY (SELECT NULL)) AS row_num
        FROM SignIn
    ) AS duplicates
    WHERE row_num > 1
);

---


select * from SignIn
select * from SignUp

--- If user want to check last 10 days or month record In or Out or date
--- In this situaion to need create another table history stored data in same table when to user 
--- login or logout and date

CREATE TABLE LoginHistory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    action_type VARCHAR(10),
    action_time DATETIME
);

select * from LoginHistory

CREATE TABLE LoginHistory (
    id INT PRIMARY KEY IDENTITY,
    username VARCHAR(255),
    action_type VARCHAR(10),
    action_time DATETIME
);


use login

select * from SignIn

CREATE TABLE SignIn (
    username VARCHAR(255),
    password VARCHAR(100),
    login_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    logout_time DATETIME
);

 DROP TABLE SignIn;

