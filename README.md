# Travel
>  个人使用Sringboot练习之前的项目【后端】

包含，行程，组团，类别，订单，用户和导游等业务逻辑，整体的业务逻辑比较简单，只为熟悉流程和开发操作。

主要分为dao、service、controller 3层，

dao层：为数据库字段实例化，通过JPA实现repository连接数据库操作，

service层：实现基本的业务逻辑，和数据增删改查

controller层：实现API和基本的参数获取以及处理

enums：公共错误枚举类

util：抽离公共代码，工具类

VO：API出口格式化封装