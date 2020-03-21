CREATE TABLE oauth_client_details
  ( 
     client_id               VARCHAR(255) NOT NULL PRIMARY KEY, 
     client_secret           VARCHAR(255) NOT NULL, 
     resource_ids            VARCHAR(255) DEFAULT NULL, 
     scope                   VARCHAR(255) DEFAULT NULL, 
     authorized_grant_types  VARCHAR(255) DEFAULT NULL, 
     web_server_redirect_uri VARCHAR(255) DEFAULT NULL, 
     authorities             VARCHAR(255) DEFAULT NULL, 
     access_token_validity   INT DEFAULT NULL, 
     refresh_token_validity  INT DEFAULT NULL, 
     additional_information  VARCHAR(4096) DEFAULT NULL, 
     autoapprove             VARCHAR(255) DEFAULT NULL 
  ); 

CREATE SEQUENCE permission_seq; 

CREATE TABLE permission 
  ( 
     id   INT PRIMARY KEY DEFAULT NEXTVAL ('PERMISSION_seq'), 
     name VARCHAR(60) UNIQUE 
  ); 

CREATE SEQUENCE role_seq; 

CREATE TABLE ROLE 
  ( 
     id   INT PRIMARY KEY DEFAULT NEXTVAL ('ROLE_seq'), 
     name VARCHAR(60) UNIQUE 
  ); 

CREATE TABLE permission_role 
  ( 
     permission_id INT, 
          FOREIGN KEY(permission_id) REFERENCES permission(id), 
          role_id       INT, 
     FOREIGN KEY(role_id) REFERENCES ROLE(id) 
  ); 

CREATE SEQUENCE application_seq; 

CREATE TABLE application 
  ( 
     id   INT PRIMARY KEY DEFAULT NEXTVAL ('APPLICATION_seq'), 
     name VARCHAR(60) UNIQUE 
  ); 

CREATE SEQUENCE users_seq; 

CREATE TABLE users 
  ( 
     id                  INT PRIMARY KEY DEFAULT NEXTVAL ('USERS_seq'), 
     username            VARCHAR(24) UNIQUE NOT NULL, 
     password            VARCHAR(255) NOT NULL, 
     email               VARCHAR(255) NOT NULL, 
     enabled             BOOLEAN NOT NULL, 
     account_expired     BOOLEAN NOT NULL, 
     credentials_expired BOOLEAN NOT NULL, 
     account_locked      BOOLEAN NOT NULL 
  ); 

CREATE TABLE role_application_user 
  ( 
     role_id        INT, 
          FOREIGN KEY(role_id) REFERENCES ROLE(id), 
          application_id INT, 
          FOREIGN KEY(application_id) REFERENCES application(id), 
          users_id       INT, 
     FOREIGN KEY(users_id) REFERENCES users(id), 
     PRIMARY KEY(users_id, application_id, role_id) 
  ); 