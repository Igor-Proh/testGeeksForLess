DROP DATABASE IF EXISTS geeksforless ;
CREATE DATABASE geeksforless;
USE geeksforless;

DROP TABLE IF EXISTS equation;
DROP TABLE IF EXISTS root;

CREATE TABLE equation (
    equation_id INT PRIMARY KEY AUTO_INCREMENT,
    equation_value VARCHAR(225)
);

CREATE TABLE root (
    root_id INT PRIMARY KEY AUTO_INCREMENT,
    equation_id INT,
    root_value VARCHAR(225),
    FOREIGN KEY (equation_id) REFERENCES equation(equation_id)
);