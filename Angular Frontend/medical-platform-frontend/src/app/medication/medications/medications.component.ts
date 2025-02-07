import { Component, OnInit } from '@angular/core';
import { Medication } from '../../model/medication';
import { MedicationService } from 'src/app/services/medication.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow } from '@angular/material/table';

@Component({
    selector: 'app-medications',
    templateUrl: './medications.component.html',
    styleUrls: ['./medications.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow]
})
export class MedicationsComponent implements OnInit {

  medications: Medication[] = [];
  displayedColumns: string[] = ['name', 'dosage', 'sideEffects'];
  isLoadingData = true;

  constructor(private medicationService: MedicationService) { }

  ngOnInit() {
    this.medicationService.getAllMedications()
      .subscribe(loadedMedications => {
        this.medications = loadedMedications;
      });

    this.isLoadingData = false;
  }

}
