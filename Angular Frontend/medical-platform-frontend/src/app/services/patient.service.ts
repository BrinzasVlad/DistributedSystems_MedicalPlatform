import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  patientURL = 'https://localhost:8080/patient';

  constructor(private http: HttpClient) { }

  getAllPatients() {
    return this.http.get<Patient[]>(this.patientURL);
  }

  getPatientById(id: number) {
    return this.http.get<Patient>(this.patientURL + '/' + id);
  }

  insertPatient(patient: Patient) {
    return this.http.post<Patient>(this.patientURL, patient);
  }

  updatePatient(patient: Patient) {
    return this.http.put<Patient>(this.patientURL, patient);
  }

  deletePatientById(id: number) {
    return this.http.delete<void>(this.patientURL + '/' + id);
  }
}
