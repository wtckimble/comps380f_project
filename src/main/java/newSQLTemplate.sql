/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kimble
 * Created: 2017年4月6日
 */

CREATE TABLE users (

username VARCHAR(50) NOT NULL,

password VARCHAR(50) NOT NULL,

PRIMARY KEY (username)

);

CREATE TABLE user_roles (

user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,

INCREMENT BY 1),

username VARCHAR(50) NOT NULL,

role VARCHAR(50) NOT NULL,

PRIMARY KEY (user_role_id),

FOREIGN KEY (username) REFERENCES users(username)

);

INSERT INTO users VALUES ('keith', 'keithpw');

INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');

INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');

INSERT INTO users VALUES ('maria', 'mariapw');

INSERT INTO user_roles(username, role) VALUES ('maria', 'ROLE_USER');