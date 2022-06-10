## How to run Application

Running application from command line using Docker, this is the cleanest way.
In order for this approach to work, of course, you need to have Docker installed in your local environment.

* From the root directory you can run the following command:<br/>
  ```docker-compose -f docker/docker-compose.yml up --build -d```
* Application will be running on: http://localhost:8080
* To stop it you can open other terminal in the same directory, and then run the following command:<br/>
  ```docker-compose -f docker/docker-compose.yml down```

* Open Api Doc to http://localhost:8080/swagger-ui/index.html