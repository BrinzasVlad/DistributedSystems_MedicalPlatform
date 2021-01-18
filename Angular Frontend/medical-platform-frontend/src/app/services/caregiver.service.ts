import { Injectable } from '@angular/core';
import { Caregiver } from '../model/caregiver';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CaregiverService {

  caregiverURL = 'https://localhost:8080/caregiver';

  constructor(private http: HttpClient) { }

  getAllCaregivers() {
    return this.http.get<Caregiver[]>(this.caregiverURL);
  }

  getCaregiverById(id: number) {
    return this.http.get<Caregiver>(this.caregiverURL + '/' + id);
  }

  insertCaregiver(caregiver: Caregiver) {
    return this.http.post<Caregiver>(this.caregiverURL, caregiver);
  }

  updateCaregiver(caregiver: Caregiver) {
    return this.http.put<Caregiver>(this.caregiverURL, caregiver);
  }

  deleteCaregiverById(id: number) {
    return this.http.delete<void>(this.caregiverURL + '/' + id);
  }
}
