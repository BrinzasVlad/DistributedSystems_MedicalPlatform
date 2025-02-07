import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PatientService } from '../../services/patient.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard, MatCardHeader, MatCardTitle, MatCardContent, MatCardActions } from '@angular/material/card';
import { MatExpansionPanel, MatExpansionPanelHeader, MatExpansionPanelTitle } from '@angular/material/expansion';
import { PatientActivitiesDetailComponent } from '../patient-activities-detail/patient-activities-detail.component';
import { PatientMedicationDetailComponent } from '../patient-medication-detail/patient-medication-detail.component';

@Component({
    selector: 'app-patient-detail',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, MatCardHeader, MatCardTitle, MatCardContent, MatExpansionPanel, MatExpansionPanelHeader, MatExpansionPanelTitle, PatientActivitiesDetailComponent, PatientMedicationDetailComponent, MatCardActions]
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
