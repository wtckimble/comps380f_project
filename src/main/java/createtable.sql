
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

CREATE TABLE topic (
    topic_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    topic_title VARCHAR(50) NOT NULL,
    topic_content VARCHAR(100) NOT NULL,
    topic_category VARCHAR(8) NOT NULL,
    topic_author VARCHAR (50) NOT NULL,
    PRIMARY KEY (topic_id)
);

CREATE TABLE reply (
    reply_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    reply_content VARCHAR(50) NOT NULL,
    reply_author VARCHAR(50) NOT NULL,
    topic_id INTEGER NOT NULL,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (topic_id) REFERENCES topic(topic_id)
);
 
CREATE TABLE POLL (
    pollid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    topic VARCHAR(50) NOT NULL,
    optionone VARCHAR(50) NOT NULL,
    optiontwo VARCHAR(50) NOT NULL,
    optionthree VARCHAR(50) NOT NULL,
    optionfour VARCHAR(50) NOT NULL,
    PRIMARY KEY (pollid)
);

CREATE TABLE Vote (
    voteid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    pollid INTEGER NOT NULL,
    username VARCHAR(50) NOT NULL,
    choice VARCHAR(50) NOT NULL,
    PRIMARY KEY (voteid),
    FOREIGN KEY (pollid) REFERENCES POLL(pollid)
);

create table attachments (
    attachmentid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    name VARCHAR(100) NOT NULL,
    content blob NOT NULL,
    mime VARCHAR(50) NOT NULL,
    reply_id INTEGER,
    topic_id INTEGER,
    PRIMARY KEY (attachmentid),
    FOREIGN KEY (topic_id) references topic(topic_id),
    foreign key(reply_id) references reply(reply_id)
);