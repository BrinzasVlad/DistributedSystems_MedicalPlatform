import { Component, OnInit } from '@angular/core';
import { Medication } from '../../model/medication';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MedicationService } from 'src/app/services/medication.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard, MatCardHeader, MatCardTitle, MatCardContent, MatCardActions } from '@angular/material/card';

@Component({
    selector: 'app-medication-detail',
    templateUrl: './medication-detail.component.html',
    styleUrls: ['./medication-detail.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, MatCardHeader, MatCardTitle, MatCardContent, MatCardActions]
})
export class MedicationDetailComponent implements OnInit {

  medication: Medication;
  isLoadingData = false;

  constructor(private route: ActivatedRoute, private medicationService: MedicationService, private router: Router) { }

  ngOnInit() {
    const medicationId = this.route.snapshot.params.id;
    this.medicationService.getMedicationById(medicationId)
      .subscribe(loadedMedication => {
        this.medication = loadedMedication;
        this.isLoadingData = false;
      });
  }

  deleteCurrentMedication() {
    this.medicationService.deleteMedicationById(this.medication.id)
      .subscribe(() => { this.router.navigateByUrl('/medications'); });
  }

}
