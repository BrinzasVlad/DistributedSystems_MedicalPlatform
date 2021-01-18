import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Activity } from '../model/activity';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  patientURL = 'https://localhost:8080/patient';
  activityURL = 'activities';

  constructor(private http: HttpClient) { }

  getActivitiesForPatient(patientId: number) {
    return this.http.get<Activity[]>(this.patientURL + '/' + patientId + '/' + this.activityURL);
  }

  updateActivity(activity: Activity) {
    return this.http.put<Activity>(
      this.patientURL + '/' + activity.patientId + '/' + this.activityURL,
      activity
    );
  }
}
