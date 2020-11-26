CREATE SCHEMA IF NOT EXISTS `deep_stroll` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `telephone` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NULL,
  `gender` INT NULL,
  `e_mail` VARCHAR(45) NULL,
  `sign` VARCHAR(100) NULL,
  `avatar` VARCHAR(255) NULL,
  `vip` INT NOT NULL DEFAULT 0 COMMENT '0：普通 1：vip',
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:冻结',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author_id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `cover` VARCHAR(255) NULL,
  `introduction` VARCHAR(100) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `labels` VARCHAR(255) NOT NULL,
  `classify_id` INT NOT NULL,
  `like_num` INT NOT NULL DEFAULT 0,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:下架',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`drawing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author_id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `introduction` VARCHAR(100) NOT NULL,
  `labels` VARCHAR(255) NOT NULL,
  `url` VARCHAR(540) NOT NULL,
  `classify_id` INT NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:下架',
  `like_num` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`classify_article` (
  `classify_article_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`classify_article_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`classify_drawing` (
  `classify_drawing_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:删除',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`calssify_drawing_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`theme` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `background_url` VARCHAR(100) NOT NULL,
  `bgm_url` VARCHAR(100) NOT NULL,
  `is_default` INT NOT NULL DEFAULT 0 COMMENT '0:非默认 1：默认',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `introduction` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:删除',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`admin_role` (
  `admin_role_id` INT NOT NULL AUTO_INCREMENT,
  `admin_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_role_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`back_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `path` VARCHAR(100) NULL,
  `parent_id` INT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`role_menu` (
  `role_menu_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_menu_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`home_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `path` VARCHAR(45) NULL,
  `parent_id` INT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:不可见',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT 
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `deep_stroll`.`log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `admin_id` INT NOT NULL,
  `type` INT NOT NULL COMMENT '0插入 1修改 2删除',
  `table_name` VARCHAR(45) NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;