Application for Management of Book bibliography

DB

RDBMS:MySQL

database:biblio
user:bibdev

1. create database

cd C:\mysql\bin
mysql -u root -p
source ./db/dba/database/biblio.sql

select Host,User,Password from mysql.user;

2. create user


## その他コマンド
# データベース

# 接続しているデータベース
select database();

# データベース一覧
show databases;

# データベース切り替え
user dbname;

# テーブルの一覧
show tables;
show tables from db_name;

