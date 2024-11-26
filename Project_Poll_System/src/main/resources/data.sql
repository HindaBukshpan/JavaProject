CREATE TABLE questions (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(225) NOT NULL,
    option1 VARCHAR(225) NOT NULL,
    option2 VARCHAR(225) NOT NULL,
    option3 VARCHAR(225) NOT NULL,
    option4 VARCHAR(225) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO questions (title,option1, option2, option3, option4)
VALUES('How do you usually get to work or school?', 'Car', 'Public transport', 'Walking', 'Cycling'),
      ('What time of day are you most productive?', 'Morning', 'Afternoon', 'Evening', 'Late night'),
      ('Which type of vacation do you prefer?', 'Beach resort', 'Adventure travel', 'Cultural exploration', 'Staycation'),
      ('How do you usually start your day?', 'With exercise', 'With a coffee or tea', 'Checking emails', 'I don’t have a set routin');


CREATE TABLE answers (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    question_id INT NOT NULL,
    answer VARCHAR(225) NOT NULL,
    PRIMARY KEY (user_id, question_id)
);