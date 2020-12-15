create database stm_db;
create user 'stm_user'@'localhost' identified by 'qweqwe';
grant select,create,alter,references,index,drop,insert,update,delete on stm_db.* to 'stm_user'@'localhost';