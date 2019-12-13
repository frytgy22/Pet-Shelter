
/*1. Выбрать все фильмы и отсортировать по названию*/
SELECT * FROM Movies ORDER BY Movies.Title;


/*2. Выбрать все фильмы и отсортировать по году выпуска*/
SELECT * FROM Movies ORDER BY Movies.ReleaseYear;


/*3. Выбрать все фильмы и отсортировать по рейтингу*/
SELECT * FROM Movies ORDER BY Movies.Rating DESC


/*4. Выбрать все фильмы, выпущенные в прошлом году */
SELECT * FROM Movies WHERE Movies.ReleaseYear = YEAR(CURDATE()) - 1;

SELECT * FROM Movies WHERE Movies.ReleaseYear = '2018';


/*5. Выбрать все фильмы по заданному жанру (комедия, боевик, ...)*/
SELECT Movies.Title, Genres.GenreName FROM MovieGenres
NATURAL JOIN Movies
NATURAL JOIN Genres
WHERE Genres.GenreName = 'Comedy';

SELECT Movies.Title, Genres.GenreName FROM MovieGenres
JOIN Movies ON MovieGenres.MovieId = Movies.MovieId
JOIN Genres ON MovieGenres.GenreId = Genres.GenreId
WHERE Genres.GenreName = 'Action'

SELECT Movies.Title, Genres.GenreName FROM Movies
JOIN MovieGenres ON Movies.MovieId = MovieGenres.MovieId
JOIN Genres ON MovieGenres.GenreId = Genres.GenreId
WHERE Genres.GenreName = 'Mystic'


/*6. Выбрать все фильмы по заданному актеру */
SELECT Movies.Title, Actors.FirstName,Actors.LastName FROM MovieActor 
JOIN Movies ON Movies.MovieId=MovieActor.MovieId 
JOIN Actors ON Actors.ActorId=MovieActor.ActorId
WHERE Actors.FirstName = 'Дэниел'

SELECT Movies.Title, Actors.FirstName, Actors.LastName  FROM Movies
JOIN MovieActor ON Movies.MovieId = MovieActor.MovieId
JOIN Actors ON Actors.ActorId = MovieActor.ActorId
WHERE Actors.FirstName = 'Брэд' AND Actors.LastName = 'Питт'

SELECT Movies.Title, Actors.FirstName, Actors.LastName FROM MovieActor 
NATURAL JOIN Movies 
NATURAL JOIN Actors 
WHERE Actors.LastName = 'Калкин'


/*7. Выбрать все фильмы по заданному продюсеру*/
SELECT Movies.Title, Movies.ReleaseYear, Movies.Rating, Movies.Plot, Movies.MovieLength
,Directors.FirstName, Directors.LastName FROM Movies NATURAL JOIN Directors 
WHERE Directors.FirstName = 'Шон';

SELECT Movies.Title, Directors.FirstName, Directors.LastName FROM Movies JOIN Directors
ON Directors.DirectorId=Movies.DirectorId WHERE Directors.FirstName = 'Криc' AND Directors.LastName = 'Коламбус';


