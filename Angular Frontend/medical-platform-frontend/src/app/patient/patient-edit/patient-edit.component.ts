import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PatientService } from '../../services/patient.service';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { DatePipe, NgIf } from '@angular/common';
import { Patient } from '../../model/patient';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor, MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard } from '@angular/material/card';
import { MatFormField, MatError, MatSuffix, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatDatepickerInput, MatDatepickerToggle, MatDatepicker } from '@angular/material/datepicker';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';

@Component({
    selector: 'app-patient-edit',
    templateUrl: './patient-edit.component.html',
    styleUrls: ['./patient-edit.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, FormsModule, ReactiveFormsModule, MatFormField, MatLabel, MatInput, MatError, MatDatepickerInput, MatDatepickerToggle, MatSuffix, MatDatepicker, MatSelect, MatOption, MatButton]
})
export class PatientEditComponent implements OnInit {

  patientId: number;

  patientForm: UntypedFormGroup;
  maxDate = new Date(); // No patients born in the future

  isProcessing = true;

  constructor(private formBuilder: UntypedFormBuilder,
              private patientService: PatientService,
              private route: ActivatedRoute,
              private router: Router,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.patientForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      birthDate: [],
      gender: [],
      address: []
    });

    this.patientId = this.route.snapshot.params.id;
    this.patientService.getPatientById(this.patientId)
      .subscribe(loadedPatient => {
        this.patientForm.setValue(loadedPatient);
        this.isProcessing = false;
      });
  }

  onFormSubmit() {
    this.isProcessing = true;

    const formValue = this.patientForm.value;
    const patientFromForm: Patient = {
      id: formValue.id,
      name: formValue.name,
      birthDate: this.datePipe.transform(formValue.birthDate, 'yyyy-MM-dd'),
      // birthDate: formValue.birthDate,
      gender: formValue.gender,
      address: formValue.address
    };

    this.patientService.updatePatient(patientFromForm)
      .subscribe(updatedPatient => {
        this.isProcessing = false;
        this.router.navigate(['/patient-detail', updatedPatient.id]);
      });
  }

}
