# SOAP Backend Component

The SOAP backend component represents a second server that handles only data about what activities the patients have performed and which medications of the ones prescribed
to them they have taken.

### Initial Requirements

The initial requirements given for this component were as follows:
- the component must be a standalone application, with its own connection to the database
- the component must retrieve from the database, upon request, data about patient activities and medications taken
- the component must expose a SOAP endpoint for each of its services (activities and medications taken)

### Implementation

The component is a Spring Boot application built in contract-last style, meaning that the WSDL is generated from the Java classes constructed. Interaction with the database is
implemented using Hibernate. In order to ensure that the application does not start until the database container is ready, a simple shell script is run on startup which checks
for proper initialisation.

### Future Objectives

There are a number of changes that could be made to the component:
- it could be refactored into a contract-first style application, in order to reduce the variability of the WSDL (to produce a more stable interface for the services)
- interaction with the patient sensor component could be moved from the REST backend component to this one, since it relates to patient activities

### Usage

To start the SOAP backend component, use `docker-compose up soap-backend`. This will automatically start any other requisite components, such as the database.
Once the component is running, the SOAP endpoints it provides can be accessed at `http://soap-backend:8099/soap_activity_service` and respectively
`http://soap-backend:8099/soap_intake_taken_service` from within the Docker network.

To access the services outside Docker, one must first check that port 8099 is published in `docker-compose.yml`. Once it is and the component is running, the endpoints will be
accessible at the same addresses as above, replacing `soap-backend` with `localhost`.
