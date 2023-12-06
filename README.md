##  Create Database and User in MySQL

```
CREATE DATABASE pasal DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'pasal'@'localhost' IDENTIFIED BY 'pasal';
GRANT ALL ON pasal.* to 'pasal'@'localhost';
flush privileges;
```

## Run Backend Grails app
```shell
./gradlew server:bootRun
```

## Run User Facing app
Go inside client folder
```shell
cd client
```
```
cd server
grails
run-app
```

Install dependencies
```shell
yarn or npm install
```
Start server
```shell 
yarn start or npm start
```

## Client Configuration
The hostname should be changed to
```https://nuptse.local:8000```

Where `nuptse` is the name of the client.

Host names can be updated editing the hosts files 

For Linux `/etc/hosts`
For Windows `C:\System32\windows\drivers\etc\hosts`

Host name configuration

127.0.0.1 nuptse.local

