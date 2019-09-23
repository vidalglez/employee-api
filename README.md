# messenger-jaxrs-app
This project includes training exercises for JAX-RS

### DB configuration
Create an employee table in PostgreSQL like the following:
```sh
create table Employee (
  id_employee serial primary key not null, 
  FirstName varchar,
  MiddleInitial varchar,
  LastName varchar,
  DateOfBirth date,
  DateOfEmployment date,
  Status boolean
)
```
### Running API in your local environment

Modify application.properties file and follow internal instructions to provide your DB connections (DB url, username and password)

Also you will need to define a username and password for basic authentication example, these credentials will be required when DELETE method for Employees API is invoked

### Local execution

If you have mvn installed in your local environment, run:
```sh
$ mvn spring-boot:run
```

Once your application is up and running go to your localhost URL: http://localhost:8080/index.jsp

To ingest an external source of employees you can create a json file like the following:

```sh
[
    {
        "firstName": "Mike",
        "middleInitial": "Doe",
        "lastName": "Mayers",
        "status": "true",
        "dateOfBirth": "2017-07-26",
        "dateOfEmployment": "2018-04-05"
    },
    {
        "firstName": "David",
        "middleInitial": "Brown",
        "lastName": "Gilmour",
        "status": "true",
        "dateOfBirth": "2010-11-02",
        "dateOfEmployment": "2016-04-30"
    }
]
```

### Endpoints available:

List all employees [GET]: http://localhost:8080/employee-api/employees/

Find employee by id [GET]: http://localhost:8080/employee-api/employees/id

Create employee [POST]: http://localhost:8080/employee-api/employees/

Example Body:
```sh
{
    "firstName": "Henry",
    "middleInitial": "William",
    "lastName": "Spencer",
    "dateOfBirth": "2019-09-15",
    "dateOfEmployment": "2019-02-01"
}
```

Update employee [PUT]: http://localhost:8080/employee-api/employees/id

Example Body:
```sh
{
    "firstName": "Henry",
    "middleInitial": "Peter",
    "lastName": "Jackson",
    "dateOfBirth": "2019-08-15",
    "dateOfEmployment": "2018-02-01"
}
```

Delete employee [DELETE]: http://localhost:8080/employee-api/employees/id, you will need to provide 
Authorization Header as Basic Auth and provide same username and password as configured in application.properties

### Frameworks and tools used
- JAX-RS Jersey
- Spring
- JPA & Hibernate
- Lombok


### TODOS
- HATEOS links
- Unit tests
- Integration tests
- Swagger
