CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    age INT DEFAULT 0 NOT NULL,
    city VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    num_of_house INT NOT NULL,
    joining_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (id)
);

INSERT INTO users (first_name, last_name, email, age, city, street, num_of_house, joining_date)
VALUES('Hinda', 'Bukshpan', 'hb@gmail.com', 26, 'Beitar Illit', 'Harav Shach', 64, current_date),
      ('Sory', 'Ernreich', 'se@gmail.com', 31, 'Jerusaleim', 'Rashi', 3, current_date),
      ('Chasi', 'Fridman', 'cf@gmail.com', 28, 'Tverya', 'Hazvi', 13, current_date),
      ('Dvory', 'Ekshtain', 'de@gmail.com', 23, 'Modiyn Ellit', 'Tar', 41, current_date),
      ('Bruchy', 'Brizel', 'bb@gmail.com', 19, 'Beitar Illit', 'Chatam Sofer', 9, current_date),
      ('Golda', 'Chanun', 'gc@gmail.com', 73, 'Ramot', 'Ramot Polin', 63, current_date);
