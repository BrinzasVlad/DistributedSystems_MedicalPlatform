import { Component, OnInit } from '@angular/core';
import { Medication } from '../../model/medication';
import { MedicationService } from 'src/app/services/medication.service';

@Component({
    selector: 'app-medications',
    templateUrl: './medications.component.html',
    styleUrls: ['./medications.component.css'],
    standalone: false
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
