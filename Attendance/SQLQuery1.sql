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
