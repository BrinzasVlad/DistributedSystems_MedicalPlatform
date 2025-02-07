import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow } from '@angular/material/table';

@Component({
    selector: 'app-patients',
    templateUrl: './patients.component.html',
    styleUrls: ['./patients.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow]
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
