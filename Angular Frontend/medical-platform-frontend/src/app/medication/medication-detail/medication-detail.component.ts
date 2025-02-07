import { Component, OnInit } from '@angular/core';
import { Medication } from '../../model/medication';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicationService } from 'src/app/services/medication.service';

@Component({
    selector: 'app-medication-detail',
    templateUrl: './medication-detail.component.html',
    styleUrls: ['./medication-detail.component.css'],
    standalone: false
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
