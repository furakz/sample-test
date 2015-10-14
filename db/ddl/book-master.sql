SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS m_book_author_rel;
DROP TABLE IF EXISTS m_author;
DROP TABLE IF EXISTS m_book;
DROP TABLE IF EXISTS m_book_bak;
DROP TABLE IF EXISTS m_role;




/* Create Tables */

-- 著者マスタ
CREATE TABLE m_author
(
	id int NOT NULL COMMENT 'ID',
	name varchar(300) NOT NULL COMMENT '名称',
	name2 varchar(300) COMMENT '名称２',
	kana_name varchar(300) COMMENT '名称かな',
	description text COMMENT '略歴',
	PRIMARY KEY (id)
) COMMENT = '著者マスタ';


-- 書籍マスタ
CREATE TABLE m_book
(
	id int NOT NULL COMMENT 'ID',
	book_code varchar(20) NOT NULL COMMENT '書籍コード',
	name varchar(300) NOT NULL COMMENT '名称',
	name2 varchar(300) COMMENT '名称２',
	kana_name varchar(300) COMMENT '名称かな',
	author_name varchar(300) COMMENT '著者名',
	publisher varchar(150) COMMENT '発行所',
	paper_size varchar(20) COMMENT '版型',
	series varchar(150) COMMENT 'シリーズ',
	-- 0: 単行本, 1, 巻数本
	is_part_flg char(1) COMMENT '巻数本フラグ',
	number int COMMENT '巻数',
	total_volume int COMMENT '総巻数',
	publish_year int COMMENT '発行年',
	edition_type char(1) COMMENT '版区分',
	edition_number int COMMENT '版数',
	edition_info varchar(150) COMMENT '版情報',
	isbn varchar(20) COMMENT 'ISBN',
	bound_side varchar(20) COMMENT '綴じ側',
	pages int COMMENT 'ページ数',
	height decimal COMMENT '縦',
	width decimal COMMENT '横',
	depth decimal COMMENT '幅',
	weight decimal COMMENT '重量(g)',
	PRIMARY KEY (id),
	UNIQUE (book_code, number, edition_number)
) COMMENT = '書籍マスタ';


-- 書籍著者関連マスタ
CREATE TABLE m_book_author_rel
(
	id int NOT NULL COMMENT 'ID',
	book_id int NOT NULL COMMENT '書籍ID',
	author_id int NOT NULL COMMENT '著者ID',
	role_code varchar(20) NOT NULL COMMENT '役割コード',
	seq int COMMENT '順序',
	primary_flg int(1) COMMENT '代表フラグ',
	PRIMARY KEY (id)
) COMMENT = '書籍著者関連マスタ';


-- 書籍マスタ_bak
CREATE TABLE m_book_bak
(
	id int NOT NULL COMMENT 'ID',
	book_code varchar(20) NOT NULL COMMENT '書籍コード',
	-- 1:book, 2:magazine
	type char(1) NOT NULL COMMENT '区分',
	name varchar(300) NOT NULL COMMENT '名称',
	name2 varchar(300) COMMENT '名称２',
	kana_name varchar(300) COMMENT '名称かな',
	author_name varchar(300) COMMENT '著者名',
	publisher varchar(150) COMMENT '発行所',
	series varchar(150) COMMENT 'シリーズ',
	-- 0: 単行本, 1, 巻数本
	is_part_flg char(1) COMMENT '巻数本フラグ',
	number int COMMENT '巻数',
	total_volume int COMMENT '総巻数',
	this_edit_this_info varchar(100) COMMENT 'この版のこの刷の情報',
	-- ex. 2000年10月20日　第1刷発行
	first_edit_info varchar(100) COMMENT '初版情報',
	first_edit_year int(4) COMMENT '初版発行年',
	this_edit_first_info varchar(100) COMMENT 'この版の初刷情報',
	this_edit_first_year int(4) COMMENT 'この版の初刷発行年',
	copy_right varchar(100) COMMENT 'コピーライト',
	isbn varchar(20) COMMENT 'ISBN',
	bound_side varchar(20) COMMENT '綴じ側',
	pages int COMMENT 'ページ数',
	paper_size varchar(20) COMMENT '版型',
	height decimal COMMENT '縦',
	width decimal COMMENT '横',
	depth decimal COMMENT '幅',
	weight decimal COMMENT '重量(g)',
	cycle varchar(20) COMMENT '雑誌用サイクル',
	mz_volume int COMMENT '雑誌用巻数',
	mz_number int COMMENT '雑誌用号数',
	mz_total_number int COMMENT '雑誌用通巻号数',
	PRIMARY KEY (id)
) COMMENT = '書籍マスタ_bak';


-- 役割マスタ
CREATE TABLE m_role
(
	role_code varchar(20) NOT NULL COMMENT '役割コード',
	name varchar(60) NOT NULL COMMENT '役割名',
	PRIMARY KEY (role_code)
) COMMENT = '役割マスタ';



