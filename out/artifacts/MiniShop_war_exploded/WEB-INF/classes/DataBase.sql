-- 删除数据库
DROP DATABASE IF EXISTS minishop;
-- 创建数据库
CREATE DATABASE minishop CHARACTER SET UTF8;
-- 使用数据库
USE minishop
-- 删除数据表
DROP TABLE IF EXISTS shopcar ;
DROP TABLE IF EXISTS details ;
DROP TABLE IF EXISTS orders ;
DROP TABLE IF EXISTS member ;
DROP TABLE IF EXISTS commodity ;
DROP TABLE IF EXISTS genre ;
DROP TABLE IF EXISTS admin ;


-- 创建数据表
-- 商品类型表
CREATE TABLE genre (
    gid INT AUTO_INCREMENT ,
    title VARCHAR(200) NOT NULL ,
    CONSTRAINT pk_gid PRIMARY KEY(gid)
) ENGINE=innodb;

-- 管理员信息表
CREATE TABLE admin (
    aid VARCHAR(50) ,
    password VARCHAR(32) NOT NULL ,
    lastDate DATETIME ,
    CONSTRAINT pk_aid PRIMARY KEY(aid)
) ENGINE=innodb;

-- 用户信息表
CREATE TABLE member (
    mid VARCHAR(50) ,
    password VARCHAR(32) NOT NULL ,
    name VARCHAR(50) ,
    phone VARCHAR(50) ,
    address VARCHAR(100) ,
    regDate DATETIME NOT NULL ,
    CONSTRAINT pk_mid PRIMARY KEY(mid)
) ENGINE=innodb;

-- 创建商品信息表
CREATE TABLE commodity (
    cid INT AUTO_INCREMENT ,
    gid INT ,
    aid VARCHAR(50) ,
    title VARCHAR(50) ,
    pubDate DATETIME ,
    price FLOAT ,
    amount INT ,
    note TEXT ,
    photo VARCHAR(100) ,
    status INT ,
    CONSTRAINT pk_cid PRIMARY KEY(cid) ,
    CONSTRAINT fk_gid FOREIGN KEY(gid) REFERENCES genre(gid) ON DELETE SET NULL ,
    CONSTRAINT fk_aid FOREIGN KEY(aid) REFERENCES admin(aid) ON DELETE SET NULL
) ENGINE=innodb;

-- 创建订单信息表
CREATE TABLE orders (
    oid INT AUTO_INCREMENT ,
    mid VARCHAR(50) ,
    name VARCHAR(50) ,
    phone VARCHAR(50) ,
    address VARCHAR(100) ,
    creDate DATETIME ,
    totalPay FLOAT ,
    CONSTRAINT pk_oid PRIMARY KEY(oid) ,
    CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) ENGINE=innodb;

-- 创建订单详情表
CREATE TABLE details (
    odid INT AUTO_INCREMENT ,
    oid INT NOT NULL ,
    cid INT ,
    title VARCHAR(50) NOT NULL ,
    price FLOAT NOT NULL ,
    amount INT NOT NULL ,
    CONSTRAINT pk_odid PRIMARY KEY(odid) ,
    CONSTRAINT fk_oid FOREIGN KEY(oid) REFERENCES orders(oid) ON DELETE CASCADE ,
    CONSTRAINT fk_cid FOREIGN KEY(cid) REFERENCES commodity(cid) ON DELETE SET NULL
) ENGINE=innodb;

-- 购物车信息表
CREATE TABLE shopcar(
    cid INT ,
    mid VARCHAR(50) ,
    amount INT,
    CONSTRAINT fk_cid3 FOREIGN KEY(cid) REFERENCES commodity(cid) ON DELETE CASCADE ,
    CONSTRAINT fk_mid3 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) ENGINE=innodb;

-- 编写测试数据
-- 增加商品分类信息
INSERT INTO genre(title) VALUES ('书本') ;
INSERT INTO genre(title) VALUES ('3C产品') ;
INSERT INTO genre(title) VALUES ('生活用品') ;
INSERT INTO genre(title) VALUES ('办公用品') ;
-- 增加管理员信息
INSERT INTO admin(aid, password) VALUES ('admin', '21232F297A57A5A743894A0E4A801FC3') ;
-- 增加用户信息
INSERT INTO member(mid, password, regdate) VALUES ('jamesmok', 'D5F7BC98839C1134E8A1F5FAA2AA4238', '2021-11-17') ;
INSERT INTO member(mid, password, regdate) VALUES ('james', 'B4CC344D25A2EFE540ADBF2678E2304C', '2021-12-11') ;

