# Assignment 3: Java Web API Creation With Hibernate

## Assignment
For the assignment it was necessary for the application to be constructed in Spring Web. It should comprise of a database made in PostgreSQL through Hibernate with a RESTful API with all the CRUD functions and more. The database will store information about characters, movies, and franchises. Also give relations to the franchise and movies and movies and characters. We chose Star Wars as our reference material.

## How To Use

#### List of API Endpoints
- Create a franchise
- Create a movie
- Create a character
- Read a franchise from ID
- Read all franchises
- Read all the movies in a franchise
- Read all the characters in a franchise
- Read a movie from ID
- Read all movies
- Read all characters from ID
- Read a character from ID
- Read all characters
- Update a franchise
- Update a movie
- Update a character
- Update a character in a movie
- Update a movies in a franchise
- Delete a franchise
- Delete a movie
- Delete a character

### Running Locally
Make sure to run a postgres container using this in terminal:

`docker run -d --name postgres -e POSTGRES_PASSWORD=supersecretpassword -e POSTGRES_DB=mydb -p 5432:5432 postgres:14-alpine`

Run the program and open the following in the browser:
`http://localhost:8080/swagger-ui/index.html`

### Running through Heroku
Click the Heroku link up top.

#### To create:

Click the appropriate information you want to create and click try it out:
- remove the id from the response body as this will be auto incremented.
- remove the relations from the response body.
- Add the information you want to add. 
- click execute

#### To read:

Click the appropriate information you want to read and click try it out:
- With get all just click execute
- with get by id give an id number for the information you want to get.
- click execute

#### To update:

Click the appropriate information you want to update and click try it out:
- remove the relations from the response body.
- give the id number of the information you want to change.
- Also insert the id number within the response body
- give the information you want to update
- click execute

#### To update relations
Click the appropriate information you want to update and click try it out:
- give the id number of the information you want to change.
- pass in the body the id's of the information(relation) you want to add
- click execute

#### To delete:

Click the appropriate information you want to delete and click try it out:
- give the id of the information you want to delete.
- click execute



## Future
Future work may include:
- front-end to add to the database.

## Built With
[IntelliJ IDEA](https://www.jetbrains.com/idea/)

[Spring Framework](https://spring.io/)

[Hibernate](https://hibernate.org/)

[PostgreSQL](https://www.postgresql.org/)

[Swagger](https://swagger.io/)

[Docker](https://www.docker.com/)

[Heroku](https://www.heroku.com/)

## Credits
[Igor Figueiredo](https://github.com/Igor-GF)

[Timothy](https://github.com/TimothyBlom)

[Savannah Borst](https://github.com/savannah-borst)

## License
[MIT](https://choosealicense.com/licenses/mit/)