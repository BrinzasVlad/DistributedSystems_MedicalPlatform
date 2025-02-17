import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MedicationService } from 'src/app/services/medication.service';
import { NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor, MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard } from '@angular/material/card';
import { MatFormField, MatError, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

@Component({
    selector: 'app-medication-edit',
    templateUrl: './medication-edit.component.html',
    styleUrls: ['./medication-edit.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, FormsModule, ReactiveFormsModule, MatFormField, MatLabel, MatInput, MatError, MatButton]
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
