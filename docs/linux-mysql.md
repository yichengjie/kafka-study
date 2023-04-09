#### 忘记密码
1. vi打开/etc/my.cnf文件在[mysqld]下添加一行
    ```text
    skip-grant-tables
    ```
2. 保存配置文件后，重启MySQL服务
    ```text
    service mysqld restart
    ```
3. 再次进入MySQL命令行，输入密码时直接回车，就会进入MySQL数据库了
    ```text
    mysql -uroot -p
    ```
4. 刷新权限
    ```text
    flush privileges;
    ```
5. 修改密码
    ```text
    ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123';
    ```
#### 防火墙端口开放
1. 开放3306端口
    ```text
    firewall-cmd --zone=public --add-port=3306/tcp --permanent
    ```
2. 重新加载策略
    ```text
    firewall-cmd --reload
    ```