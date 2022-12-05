### 创建密钥仓库以及CA
1. 创建密匙仓库,用户存储证书文件
    ```text
    keytool -keystore server.keystore.jks -alias hello_kafka -validity 100000 -genkey
    ```
2. 创建CA
    ```text
    openssl req -new -x509 -keyout ca-key -out ca-cert -days 100000
    ```
3. 将生成的CA添加到客户端信任库
    ```text
    keytool -keystore client.truststore.jks -alias CARoot -import -file ca-cert
    ```
4. 为broker提供信任库以及所有客户端签名了密钥的CA证书
    ```text
    keytool -keystore server.truststore.jks -alias CARoot -import -file ca-cert
    ```
### 签名证书，用自己生成的CA来签名前面生成的证书
1. 签名证书，用自己生成的CA来签名前面生成的证书
    ```text
    keytool -keystore server.keystore.jks -alias hello_kafka -certreq -file cert-file
    ```
2. 用CA签名：
   ```text
   openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days 100000 -CAcreateserial -passin pass:hello123
   ```
3. 导入CA的证书和已签名的证书到密钥仓库
   ```text
   keytool -keystore server.keystore.jks -alias CARoot -import -file ca-cert
   keytool -keystore server.keystore.jks -alias hello_kafka -import -file cert-signed
   ```