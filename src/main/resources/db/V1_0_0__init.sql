DROP TABLE IF EXISTS zxl_user ;
CREATE TABLE zxl_user (
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_name VARCHAR(64) NOT NULL COMMENT '姓名',
  login_name VARCHAR(16) NOT NULL COMMENT '登录名',
  loign_pwd VARCHAR(32) NOT NULL COMMENT '登录密码',
  sex INT NOT NULL COMMENT '性别',
  birth_day DATE COMMENT '出生日期',
  create_time DATETIME NOT NULL COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL COMMENT '修改时间',
  PRIMARY KEY (id)
) COMMENT='人员表';