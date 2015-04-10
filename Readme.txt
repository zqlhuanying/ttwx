基于天天微信平台进行二次开发：
1.若需要使用注册功能，则需要添加邮箱信息：
config/spring-email.xml中相关的信息
com.fjx.common.mail.vo中fromUser信息
2.修改数据库信息
config/db.properties中相关信息


注意事项：
1.在“配置授权”一栏，当点击“更新授权”之后，公众账号信息将被重新初始化，(URL、Token、验证状态、验证码将重新生成)，即此时需要重新和微信进行验证