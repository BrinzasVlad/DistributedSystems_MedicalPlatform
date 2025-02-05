import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {

  patientForm: UntypedFormGroup;
  maxDate = new Date(); // No patients born in the future

  isSendingData = false;

  constructor(private formBuilder: UntypedFormBuilder,
              private patientService: PatientService,
              private router: Router,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.patientForm = this.formBuilder.group({
      name: ['', Validators.required],
      birthDate: [],
      gender: [],
      address: []
    });
  }

  onFormSubmit() {
    this.isSendingData = true;

    const formValue = this.patientForm.value;
    const patientFromForm: Patient = {
      id: undefined,
      name: formValue.name,
      birthDate: this.datePipe.transform(formValue.birthDate, 'yyyy-MM-dd'),
      // birthDate: formValue.birthDate,
      gender: formValue.gender,
      address: formValue.address
    };

    this.patientService.insertPatient(patientFromForm)
      .subscribe(insertedPatient => {
        this.isSendingData = false;
        this.router.navigate(['/patient-detail', insertedPatient.id]);
      });
  }

}
