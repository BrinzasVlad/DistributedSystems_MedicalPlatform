# Database Component

The database component is a plain MySQL Docker container.
A database dump containing the table structure and some starting demonstration data has been provided and will be loaded when the Docker container first starts.
After this, the data can be modified and the changes will be persisted between Docker-Compose runs in the `medicalplatform_data-volume` volume.

In order to discard accumulated changes and have the container start from the default dump data again on the next startup, stop the Docker-Compose system with
`docker-compose down -v`.
Alternatively, stop the MySQL container if needed, remove it with `docker-compose rm mysql`, then remove the data volume with `docker volume rm medicalplatform_data-volume`.
