import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MedicationService } from 'src/app/services/medication.service';

@Component({
  selector: 'app-medication-add',
  templateUrl: './medication-add.component.html',
  styleUrls: ['./medication-add.component.css']
})
export class MedicationAddComponent implements OnInit {

  medicationForm: FormGroup;
  isSendingData = false;

  constructor(private formBuilder: FormBuilder, private medicationService: MedicationService, private router: Router) { }

  ngOnInit() {
    this.medicationForm = this.formBuilder.group({
      name: ['', Validators.required],
      dosage: [],
      sideEffects: []
    });
  }

  onFormSubmit() {
    this.isSendingData = true;

    this.medicationService.insertMedication(this.medicationForm.value)
      .subscribe(insertedMedication => {
        this.isSendingData = false;
        this.router.navigate(['/medication-detail', insertedMedication.id]);
      });
  }

}
