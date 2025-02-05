import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicationService } from 'src/app/services/medication.service';

@Component({
  selector: 'app-medication-edit',
  templateUrl: './medication-edit.component.html',
  styleUrls: ['./medication-edit.component.css']
})
export class MedicationEditComponent implements OnInit {

  medicationForm: UntypedFormGroup;
  isProcessing = true;

  constructor(private formBuilder: UntypedFormBuilder,
              private medicationService: MedicationService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.medicationForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      dosage: [],
      sideEffects: []
    });

    const medicationId = this.route.snapshot.params.id;
    this.medicationService.getMedicationById(medicationId)
      .subscribe(loadedMedication => {
        this.medicationForm.setValue(loadedMedication);
        this.isProcessing = false;
      });
  }

  onFormSubmit() {
    this.isProcessing = true;

    this.medicationService.updateMedication(this.medicationForm.value)
      .subscribe(updatedMedication => {
        this.isProcessing = false;
        this.router.navigate(['/medication-detail', updatedMedication.id]);
      });
  }

}
