USE world;

CREATE TABLE movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_cd VARCHAR(50) NOT NULL,
    movie_nm VARCHAR(255) NOT NULL,
    open_dt DATE NOT NULL,
    sales_amt BIGINT NOT NULL,
    sales_share DECIMAL(5,2) NOT NULL,
    sales_acc BIGINT NOT NULL,
    audi_cnt INT NOT NULL,
    audi_acc INT NOT NULL,
    scrn_cnt INT NOT NULL,
    show_cnt INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
