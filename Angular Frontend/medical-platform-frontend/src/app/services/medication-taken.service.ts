import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MedicationTaken } from '../model/medicationTaken';

@Injectable({
  providedIn: 'root'
})
export class MedicationTakenService {

  patientURL = 'https://localhost:8080/patient';
  medicationTakenURL = 'taken';

  constructor(private http: HttpClient) { }

  getMedicationsTakenForPatient(patientId: number) {
    return this.http.get<MedicationTaken[]>(this.patientURL + '/' + patientId + '/' + this.medicationTakenURL);
  }
}
