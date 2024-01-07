CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      roles VARCHAR(255)  NULL
);

INSERT INTO user VALUES ('zeinab','123456');
