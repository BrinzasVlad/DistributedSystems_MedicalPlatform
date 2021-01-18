import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {

  patient: Patient;
  isLoadingData = true;

  constructor(private route: ActivatedRoute, private patientService: PatientService, private router: Router) { }

  ngOnInit() {
    const patientId = this.route.snapshot.params.id;
    this.patientService.getPatientById(patientId)
      .subscribe(loadedPatient => {
        this.patient = loadedPatient;
        this.isLoadingData = false;
      });
  }

  deleteCurrentPatient() {
    this.patientService.deletePatientById(this.patient.id)
      .subscribe(() => { this.router.navigateByUrl('/patients'); });
  }

}
