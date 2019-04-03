# mysql -u root -p
# 输入密码
CREATE DATABASE travel CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'dubaohao'@'%'
  IDENTIFIED BY '19950324';
GRANT CREATE,INSERT,DROP,DELETE,ALTER,SELECT,UPDATE ON travel.* TO 'dubaohao'@'%'
IDENTIFIED BY '19950324';

CREATE TABLE g_user (
  g_id        INTEGER(8)  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(255) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  age         VARCHAR (10),
  tel         VARCHAR (255) NOT NULL,
  description VARCHAR(255),
  wxappid     VARCHAR(50),
  company     VARCHAR(255),
  money       INTEGER(8) DEFAULT '0'
);
CREATE TABLE v_user (
  u_id        INTEGER(8)  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(255) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  age         VARCHAR (255) NOT NULL,
  tel         VARCHAR (255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  wxappid     VARCHAR(50) NOT NULL,
  money       INTEGER(8) DEFAULT '0'
);
CREATE TABLE category (
  c_id       INTEGER(8) PRIMARY key NOT NULL AUTO_INCREMENT,
  g_id       INTEGER(8) NOT NULL,
  g_category VARCHAR(255) NOT NULL,
  FOREIGN KEY (g_id) REFERENCES g_user (g_id)
);
CREATE TABLE hodometer (
  ho_id        INTEGER(8)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ho_name      VARCHAR(255)  NOT NULL,
  picture      VARCHAR(255) NOT NULL,
  g_id         INTEGER(8)   NOT NULL,
  price        DECIMAL (8,2)   NOT NULL,
  g_category   VARCHAR(255) NOT NULL,
  create_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  status       VARCHAR(255) NOT NULL,
  progress     VARCHAR(255) NOT NULL,
  ho_url       VARCHAR(255) NOT NULL,
  ho_num_up INTEGER(8) DEFAULT '1000',
  ho_num_down INTEGER(8) DEFAULT '0',
  strategy_url VARCHAR(255) NOT NULL,
  FOREIGN KEY (g_id) REFERENCES g_user (g_id)
);
CREATE TABLE t_group (
  group_id          INTEGER(8)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  group_number      VARCHAR(255) COMMENT '团单编号',
  ho_id             INTEGER(8)   NOT NULL
  COMMENT '查询行程表信息',
  g_id         INTEGER(8)   NOT NULL COMMENT '作者',
  t_id              INTEGER(8)   NOT NULL
  COMMENT '作者',
  t_num_up            INTEGER(8)   NOT NULL DEFAULT '1000'
  COMMENT '人数限制',
  t_num             INTEGER(8)   NOT NULL DEFAULT '1'
  COMMENT '报名人数',
  pay_num  INTEGER(8) NOT NULL ,
  create_date       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_date       TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  go_date DATE COMMENT '出发时间',
  status            VARCHAR(255) NOT NULL
  COMMENT '团单当前状态',
  verification_code VARCHAR(255) NOT NULL
  COMMENT '二维码',
  FOREIGN KEY (ho_id) REFERENCES hodometer (ho_id)
);
CREATE TABLE t_order (
  order_id     INTEGER(8)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  order_number VARCHAR(255)  NOT NULL
  COMMENT '订单编号',
  group_number      VARCHAR(255)
  COMMENT '团单编号',
  ho_id        INTEGER(8)   NOT NULL
  COMMENT '查询行程表信息',
  g_id         INTEGER(8)   NOT NULL COMMENT '导游',
  v_id         INTEGER(8)   NOT NULL COMMENT '游客'，
  order_num         DECIMAL (8,0)   NOT NULL
  COMMENT '报名人数',
  money        DECIMAL (8,2)   NOT NULL
  COMMENT '需要支付的费用',
  name         VARCHAR(255) NOT NULL
  COMMENT '报名人姓名',
  tel          VARCHAR(255) NOT NULL
  COMMENT '预留电话',
  wx           VARCHAR(255) NOT NULL
  COMMENT '预留微信',
  order_status VARCHAR(255) NOT NULL
  COMMENT '支付状态',
  order_progress VARCHAR(255) NOT NULL
  COMMENT '订单状态',
  create_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  update_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  go_date       DATE NULL COMMENT '出发时间',
  FOREIGN KEY (ho_id) REFERENCES hodometer (ho_id)
);
CREATE TABLE demand (
  demand_id     INTEGER(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  demand_time   VARCHAR(255),
  demand_day    INTEGER(8),
  demand_where  VARCHAR(255),
  demand_number INTEGER(8),
  demand_money  INTEGER(8),
  g_id          INTEGER(8),
  way           VARCHAR(255),
  other         VARCHAR(255),
  FOREIGN KEY (g_id) REFERENCES g_user (g_id)
);

