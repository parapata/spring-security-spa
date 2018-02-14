CREATE
    TABLE users(
        user_id VARCHAR(16) PRIMARY KEY
        ,password VARCHAR(100)
        ,first_name VARCHAR(30)
        ,last_name VARCHAR(30)
        ,email VARCHAR(100)
    );
CREATE
    TABLE customers(
        id INT PRIMARY KEY AUTO_INCREMENT
        ,first_name VARCHAR(30)
        ,last_name VARCHAR(30)
    );
