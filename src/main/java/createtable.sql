
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

CREATE TABLE topic (

topic_id VARCHAR(50) NOT NULL,

topic_title VARCHAR(50) NOT NULL,

topic_content VARCHAR(100) NOT NULL,

topic_category VARCHAR(8) NOT NULL,

PRIMARY KEY (topic_id)

);

CREATE TABLE reply (

reply_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,

INCREMENT BY 1),

reply_content VARCHAR(50) NOT NULL,

PRIMARY KEY (reply_id)

);
