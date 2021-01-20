# Sensor Component

The sensor component represents a device that is physically installed at a patient's residence and records their activities with the purpose of identifying possible issues
with their health or medication plan and providing doctors and caregivers additional information to make decisions with. These data are then sent to the server for centralisation.

### Initial Requirements

The requirements given for this component were as given:
* the component must read the patient's activities from a file (serving as a substitute for an actual sensor)
* using a RabbitMQ message queue, the component must send the activities one by one to the REST Backend component
* the activities must be persisted in the central database
* at some point before, certain activities must, if their duration exceeds a given threshold, be marked as abnormal (for instance, more than 12 hours of sleep)

### Implementation

The component is a simple Spring Boot application running in an OpenJDK 8 container. The activities are extracted from the file and sent to the RabbitMQ container. The server
will then receive these messages, check them for anomalies, then persist them. Currently, the validation logic (which activities must be checked and what the abnormal duration
thresholds are) is hardcoded into the server. Future versions of the software should address this issue, for example by reading the conditions from a configuration file.

### Usage

To use a sensor instance, the user should first specify the ID of the patient to which the sensor is associated by setting the `sender.patient-id` variable. This can be set
directly from Docker-Compose by editing its value in the `docker-compose.yml` file. A second option is to specify the ID in `application.properties`.

Once the patient's ID has been set, the component can be started with `docker-compose up patient-activity-sensor`.
