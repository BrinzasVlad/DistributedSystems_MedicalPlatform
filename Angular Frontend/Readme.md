# Angular Frontend

The Angular frontend component represents the web application through which the medical platform services can be accessed. End users (doctors, caretakers, patients) interact
with the frontend component, which in turn makes calls to the REST component to retrieve or update data.

### Initial Requirements

The initial requirements given for the component were as follows:
- the component must run in-browser and retrieve or update information by interacting with the other components
- the component must consider three types of users: doctors, caretakers and patients
- the component must require users to authenticate via username and password
- the component must forbid users from accessing pages that they do not have permission to access (such as one patient viewing another patient's data)
- doctor users:
  - must be able to perform CRUD (create, read, update, delete) operations on all patients, caretakers and medications
  - must be able to assign a caretaker to a patient
  - must be able to create a medication plan for a patient
  - must be able to view a patient's activity history (if any) and mark abnorbal activities as justified (as well as providing a justification / recommendation)
  - must be able to view a patient's history of taking medication (which medications have been taken as prescribed and which have been missed)
- caretaker users:
  - must be able to view their own page, all patients that they are taking care of, and all relevant medications
  - must be able to view the medication plans for patients that they are taking care of
  - must be able to view a patient's activity history
  - must be able to view a patient's history of taking medication
- patient users:
  - must be able to view their own page
  - must be able to view their own medication plans
  - must be able to view their own activity history
  - must be able to view their own history of taking medication
- for the activity and medication history, the component must allow filtering for a number of time periods (for example, 24 hours, one week, one month and all time)
- for the activity and medication history, the component must also present data in a visual format, such as using graphs or charts

### Implementation

The component is an Angular 8 single-page web application generated through the Angular CLI and running inside an Nginx Docker container.
Each "page" within the application corresponds to an Angular component separated into a `.ts` file describing the behaviour and data acquisition, a `.html` template describing
the look and arrangement of the component's elements and a `.css` file describing styling applied to the view.

Currently, no authentication has been implemented yet - the user has access to all pages and operations as if they were a doctor user.

### Usage

To start the Angular frontend component, use `docker-compose up angular-frontend`. This will automatically start any other requisite components, such as the REST backend.
Once the component is running, it can be accessed under `http://localhost:80` or simply `http://localhost`.
Note that if the Docker container has not been built yet (or if changes have been made to the source code since the last build), then the start-up procedure may take longer,
as Docker will automatically build the application before deployment.
