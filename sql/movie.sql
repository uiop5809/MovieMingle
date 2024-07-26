CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    poster_url VARCHAR(255),
    overview TEXT,
    vote_average DOUBLE,
    release_date VARCHAR(255)
);
