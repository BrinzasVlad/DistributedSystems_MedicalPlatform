import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Medication } from '../model/medication';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {

  medicationURL = 'https://localhost:8080/medication';

  constructor(private http: HttpClient) { }

  getAllMedications() {
    return this.http.get<Medication[]>(this.medicationURL);
  }

  getMedicationById(id: number) {
    return this.http.get<Medication>(this.medicationURL + '/' + id);
  }

  insertMedication(medication: Medication) {
    return this.http.post<Medication>(this.medicationURL, medication);
  }

  updateMedication(medication: Medication) {
    return this.http.put<Medication>(this.medicationURL, medication);
  }

  deleteMedicationById(id: number) {
    return this.http.delete<void>(this.medicationURL + '/' + id);
  }
}
