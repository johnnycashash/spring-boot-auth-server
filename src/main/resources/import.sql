INSERT INTO oauth_client_details 
            (client_id, 
             client_secret, 
             resource_ids, 
             scope, 
             authorized_grant_types, 
             web_server_redirect_uri, 
             authorities, 
             access_token_validity, 
             refresh_token_validity, 
             additional_information, 
             autoapprove) 
VALUES     ( 'USER_CLIENT_APP', 
'{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 
'USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE', 
'APP_MANAGEMENT,APP_MYAPPLICATION', 
'authorization_code,password,refresh_token,implicit,client_credentials', 
'http://localhost:8080/', 
NULL, 
900, 
3600, 
'{}', 
NULL); 

INSERT INTO permission 
            (NAME) 
VALUES      ('can_create_user'), 
            ('can_update_user'), 
            ('can_read_user'), 
            ('can_delete_user'), 
            ('can_create_process'); 

INSERT INTO role 
            (NAME) 
VALUES      ('role_admin'), 
            ('role_user'); 

INSERT INTO permission_role 
            (permission_id, 
             role_id) 
VALUES      (1, 
             1), 
            (2, 
             1), 
            (3, 
             1), 
            (4, 
             1), 
            (5, 
             1), 
            (5, 
             2); 

INSERT INTO application 
            (NAME) 
VALUES      ('APP_MANAGEMENT'), 
            ('APP_MYAPPLICATION'); 

INSERT INTO users 
            (username, 
             password, 
             email, 
             enabled, 
             account_expired, 
             credentials_expired, 
             account_locked) 
VALUES      ( 'Tiger', 
'{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 
'tiger@gmail.com', 
'true', 
'false', 
'false', 
'false'), 
            ('Mouse', 
'{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 
'mouse@gmail.com', 
'true', 
'false', 
'false', 
'false'); 

INSERT INTO role_application_user 
            (role_id, 
             application_id, 
             users_id) 
VALUES      (1, 
             1, 
             1), 
            (1, 
             2, 
             1), 
            (2, 
             2, 
             1), 
            (2, 
             2, 
             2); 