import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';

@Component({
    selector: 'app-patients',
    templateUrl: './patients.component.html',
    styleUrls: ['./patients.component.css'],
    standalone: false
})
export class PatientsComponent implements OnInit {

  patients: Patient[];
  displayedColumns = ['id', 'name', 'birthDate', 'gender', 'address'];
  isLoadingData = true;

  constructor(private patientService: PatientService) { }

  ngOnInit() {
    this.patientService.getAllPatients()
      .subscribe(loadedPatients => {
        this.patients = loadedPatients;
      });

    this.isLoadingData = false;
  }

}
