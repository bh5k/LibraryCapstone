INSERT INTO MOVIE (ID, title,lead_actors, director,screen_writer,release_date,genre,rating,is_available,quantity) VALUES ( 3000, 'Inception', 'Leonardo DiCaprio, Ellen Page', 'Christopher Nolan', 'Christopher Nolan','2010-07-16', 'FICTION', 8.8,true ,2);
INSERT INTO MOVIE (ID, title,lead_actors, director,screen_writer,release_date,genre,rating,is_available,quantity) VALUES ( 3100, 'The Shawshank Redemption', 'Tim Robbins, Morgan Freeman', 'Frank Darabont ', 'Frank Darabont','1994-09-23', 'ROMANCE', 9.3,true,3 );
INSERT INTO MOVIE (ID, title,lead_actors, director,screen_writer,release_date,genre,rating,is_available,quantity) VALUES ( 3300, 'Pulp Fiction', 'John Travolta, Uma Thurman', 'Quentin Tarantino ', 'Quentin Tarantino','2008-07-18', 'ACTION' , 7.5, false,4 );
INSERT INTO MOVIE (ID, title,lead_actors, director,screen_writer,release_date,genre,rating,is_available,quantity) VALUES (4400, 'The Godfather', 'Marlon Brando, Al Pacino ', 'Francis Ford Coppola  ', 'Mario Puzo','1972-03-24', 'ROMANCE' , 5,true ,7);

INSERT INTO AUTHOR (ID, name) VALUES ( 1, 'Wole Shoyinka' );
INSERT INTO AUTHOR (ID, name) VALUES (2, 'Tolani Shoneye' );
INSERT INTO AUTHOR (ID, name) VALUES ( 3, 'Lani Good' );
INSERT INTO AUTHOR (ID, name) VALUES ( 4, 'Milena Sanchez' );

INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 500, 'Mary had a little lamb',1 , 'FICTION',3,true);
INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 600, 'Blah Blah sheep',2 , 'THRILLER',4,true);
INSERT INTO BOOK (ID, title, author_id, genre, quantity,is_available) VALUES ( 700, 'Upon the old solid rock',3, 'ACTION',3,true);
INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 800, 'Mirror Mirror',1 , 'FICTION',4,true);


INSERT INTO ADDRESS_T (ID, LINE_ONE, CITY, POST_CODE, COUNTRY) VALUES (  1000,'43 Jenson street', 'Nottingham', 'NG6 5TY','UK' );
INSERT INTO ADDRESS_T (ID, LINE_ONE, CITY, POST_CODE, COUNTRY) VALUES ( 1100,'65 Broadson street', 'Nottingham', 'NG8 9TY','UK' );
INSERT INTO ADDRESS_T (ID, LINE_ONE, CITY, POST_CODE, COUNTRY) VALUES ( 1200,'73 Rhysen street', 'Nottingham', 'NG76OY','UK' );

INSERT INTO USER_T (ID, NAME, ADDRESS_ID, PHONE_NUMBER,EMAIL) VALUES ( 10,'Tolu Adetomiwa',1000,'07347284950', 'toluadetomiwa@gmail.com' );
INSERT INTO USER_T (ID, NAME, ADDRESS_ID, PHONE_NUMBER,EMAIL) VALUES ( 20,'Tise Oludayomi',1100,'07347583840', 'tiseoludayomi@gmail.com' );
INSERT INTO USER_T (ID, NAME, ADDRESS_ID,PHONE_NUMBER,EMAIL) VALUES ( 30,'Dami Bankole',1200,'07347254780', 'damibankole@gmail.com' );