USE world;

CREATE TABLE review (
  review_id INT AUTO_INCREMENT PRIMARY KEY,  -- 리뷰 식별번호
  user_id INT NOT NULL,  -- 유저 식별번호
  movie_id INT NOT NULL,  -- 영화 식별번호
  rating INT NOT NULL,  -- 평점
  review TEXT NOT NULL,  -- 리뷰 본문
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 생성일
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 변경일
);