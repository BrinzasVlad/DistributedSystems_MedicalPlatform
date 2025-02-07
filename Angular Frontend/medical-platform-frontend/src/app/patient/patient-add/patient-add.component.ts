import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { DatePipe, NgIf } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor, MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard } from '@angular/material/card';
import { MatFormField, MatError, MatSuffix } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatDatepickerInput, MatDatepickerToggle, MatDatepicker } from '@angular/material/datepicker';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';

@Component({
    selector: 'app-patient-add',
    templateUrl: './patient-add.component.html',
    styleUrls: ['./patient-add.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, FormsModule, ReactiveFormsModule, MatFormField, MatInput, MatError, MatDatepickerInput, MatDatepickerToggle, MatSuffix, MatDatepicker, MatSelect, MatOption, MatButton]
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
