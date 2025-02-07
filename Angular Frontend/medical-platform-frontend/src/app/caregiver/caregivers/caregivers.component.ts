import { Component, OnInit } from '@angular/core';
import { Caregiver } from '../../model/caregiver';
import { CaregiverService } from '../../services/caregiver.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow } from '@angular/material/table';

@Component({
    selector: 'app-caregivers',
    templateUrl: './caregivers.component.html',
    styleUrls: ['./caregivers.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow]
})
export class CaregiversComponent implements OnInit {

  caregivers: Caregiver[];
  displayedColumns = ['id', 'name', 'birthDate', 'gender', 'address'];
  isLoadingData = true;

  constructor(private caregiverService: CaregiverService) { }

  ngOnInit() {
    this.caregiverService.getAllCaregivers()
      .subscribe(loadedCaregivers => {
        this.caregivers = loadedCaregivers;
      });

    this.isLoadingData = false;
  }

}
