# REST Backend Component

The REST backend component represents the main server that handles interaction with the database. Other components communicate with the REST backend in order to store or retrieve
data.

### Initial Requirements

The initial requirements given for this component were as follows:
- the component must be a standalone application, with its own connection to the database
- the component must provide a REST endpoint for other components to access or alter data through
- the REST endpoint must be accessible over HTTPS (as opposed to plain HTTP)
- the component must listen for messages sent by a patient sensor over a RabbitMQ message queue and update the database accordingly

### Implementation

The component is a Spring Boot application, with the database connection implemented using Hibernate. The code follows a controller-service-repository structure - the controller
receives requests and forwards them to the services, the service contains the business logic and delegates database access to the repositories, while the repository handles direct
interactions with the database.
Finally, in order to ensure that the application does not start until the database container is ready, a simple shell script is run on startup to check for proper initialisation.

### Usage

To start the REST backend component, use `docker-compose up rest-backend`. This will automatically start any other requisite components, such as the database. Once the component
is running, the REST endpoints it provides can be accessed under `https://rest-backend:8080/` at their respective mappings; for example, `https://rest-backend:8080/patients`.

Note that if the Docker container has not been built yet (or if one made changes to the source code since the last build), then it will be built when this command is executed.
Currently, the build process requires the SOAP backend component to be up and running, so that the artifacts for handling SOAP calls can be automatically generated. To do this,
the easiest way is to first launch the SOAP component with `docker-compose up -d soap-backend`, then either starting the REST component or simply building it (without starting)
with `docker-compose build rest-backend`. Once built, Docker will cache the results, so a rebuild is not necessary until the next source code change.

To access the services outside Docker, one must first check that port 8080 is published in docker-compose.yml. Once it is and the component is running, the endpoints will be
accessible at the same addresses as above, replacing rest-backend with localhost.
