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
  PRIMARY KEY (`id`));


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
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `deep_stroll`.`drawing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author_id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `introduction` VARCHAR(100) NOT NULL,
  `labels` VARCHAR(255) NOT NULL,
  `url` VARCHAR(1000) NOT NULL,
  `classify_id` INT NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:下架',
  `like_num` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `deep_stroll`.`classify_article` (
  `classify_article_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`classify_article_id`));
  
  INSERT INTO `deep_stroll`.`classify_article` (`name`) VALUES ('综合');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`classify_drawing` (
  `classify_drawing_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:删除',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`classify_drawing_id`));
  
  INSERT INTO `deep_stroll`.`classify_drawing` (`name`) VALUES ('综合');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`theme` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `background_url` VARCHAR(100) NOT NULL,
  `bgm_url` VARCHAR(100) NOT NULL,
  `is_default` INT NOT NULL DEFAULT 0 COMMENT '0:非默认 1：默认',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
  INSERT INTO `deep_stroll`.`theme` (`name`, `background_url`, `bgm_url`, `is_default`) VALUES ('默认主题', 'https://deep-stroll-1303131952.cos.ap-beijing.myqcloud.com/defaultTheme.jpg', 'defaultBgmUrl', '1');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
INSERT INTO `deep_stroll`.`admin` (`id`, `account`, `password`, `state`) VALUES ('1', 'admin', 'admin', '0');
  
CREATE TABLE IF NOT EXISTS `deep_stroll`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `introduction` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:删除',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('1', 'chiefAdmin', 'Chief administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('2', 'rightAdmin', 'right administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('3', 'userAdmin', 'user administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('4', 'workAdmin', 'work administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('5', 'themeAdmin', 'theme administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('6', 'userFunctionAdmin', 'user function administrator');
INSERT INTO `deep_stroll`.`role` (`id`, `name`, `introduction`) VALUES ('7', 'logAdmin', 'log administrator');


CREATE TABLE IF NOT EXISTS `deep_stroll`.`admin_role` (
  `admin_role_id` INT NOT NULL AUTO_INCREMENT,
  `admin_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_role_id`));

INSERT INTO `deep_stroll`.`admin_role` (`admin_id`, `role_id`) VALUES ('1', '1');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`back_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `path` VARCHAR(100) NULL,
  `parent_id` INT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('1', '管理员管理', '/ManageRights');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('2', '用户管理', '/ManageUsers');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('3', '作品管理', '/ManageWork');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`, `parent_id`) VALUES ('4', '文章管理', '/ManageArticles', '3');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`, `parent_id`) VALUES ('5', '手绘管理', '/ManageDrawings', '3');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`, `parent_id`) VALUES ('6', '分类管理', '/ManageClassifys', '3');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`, `parent_id`) VALUES ('7', '标签管理', '/ManageLabels', '3');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('8', '主题管理', '/Managethemes');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('9', '用户功能管理', '/ManageFunctions');
INSERT INTO `deep_stroll`.`back_menu` (`id`, `name`, `path`) VALUES ('10', '日志管理', '/ManageLogs');


CREATE TABLE IF NOT EXISTS `deep_stroll`.`role_menu` (
  `role_menu_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_menu_id`));

INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('1', '1', '1');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('2', '1', '2');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('3', '1', '3');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('4', '1', '4');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('5', '1', '5');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('6', '1', '6');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('7', '1', '7');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('8', '1', '8');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('9', '1', '9');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('10', '1', '10');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('11', '2', '1');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('12', '3', '2');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('13', '4', '3');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('14', '4', '4');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('15', '4', '5');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('16', '4', '6');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('17', '4', '7');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('18', '5', '8');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('19', '6', '9');
INSERT INTO `deep_stroll`.`role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES ('20', '7', '10');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`home_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `path` VARCHAR(45) NULL,
  `parent_id` INT NULL,
  `state` INT NOT NULL DEFAULT 0 COMMENT '0:正常 1:不可见',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
INSERT INTO `deep_stroll`.`home_menu` (`id`, `name`, `path`) VALUES ('1', '文章', '/article');
INSERT INTO `deep_stroll`.`home_menu` (`id`, `name`, `path`) VALUES ('2', '手绘', '/drawings');

CREATE TABLE IF NOT EXISTS `deep_stroll`.`log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `admin_id` INT NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;