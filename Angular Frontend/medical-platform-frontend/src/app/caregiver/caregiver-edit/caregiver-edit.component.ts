import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CaregiverService } from 'src/app/services/caregiver.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DatePipe, NgIf, NgFor } from '@angular/common';
import { Caregiver } from 'src/app/model/caregiver';
import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/services/patient.service';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatAnchor, MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatCard } from '@angular/material/card';
import { MatFormField, MatError, MatSuffix, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatDatepickerInput, MatDatepickerToggle, MatDatepicker } from '@angular/material/datepicker';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';
import { MatSelectionList, MatListOption } from '@angular/material/list';

@Component({
    selector: 'app-caregiver-edit',
    templateUrl: './caregiver-edit.component.html',
    styleUrls: ['./caregiver-edit.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, FormsModule, ReactiveFormsModule, MatFormField, MatLabel, MatInput, MatError, MatDatepickerInput, MatDatepickerToggle, MatSuffix, MatDatepicker, MatSelect, MatOption, MatSelectionList, NgFor, MatListOption, MatButton]
})
export class CaregiverEditComponent implements OnInit {

  caregiverForm: UntypedFormGroup;
  maxDate = new Date(); // No caregivers born in the future

  allPatients: Patient[];
  selectedPatients: Patient[];

  isProcessing = true; // Actually a lie, because we set it to false in two places at once

  constructor(private formBuilder: UntypedFormBuilder,
              private patientService: PatientService,
              private caregiverService: CaregiverService,
              private route: ActivatedRoute,
              private router: Router,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.patientService.getAllPatients()
      .subscribe(loadedPatients => {
        this.allPatients = loadedPatients;
        this.isProcessing = false;

        this.caregiverService.getCaregiverById(caregiverId)
          .subscribe(loadedCaregiver => {
            this.caregiverForm.setValue(loadedCaregiver);
            this.selectedPatients = loadedCaregiver.patients;
            this.isProcessing = false;
          });
      });

    this.caregiverForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      birthDate: [],
      gender: [],
      address: [],
      patients: []
    });

    const caregiverId = this.route.snapshot.params.id;
  }

  onFormSubmit() {
    this.isProcessing = true;

    const formValue = this.caregiverForm.value;
    const caregiverFromForm: Caregiver = {
      id: formValue.id,
      name: formValue.name,
      birthDate: this.datePipe.transform(formValue.birthDate, 'yyyy-MM-dd'),
      // birthDate: formValue.birthDate,
      gender: formValue.gender,
      address: formValue.address,
      patients: this.selectedPatients
    };

    this.caregiverService.updateCaregiver(caregiverFromForm)
      .subscribe(updatedCaregiver => {
        this.isProcessing = false;
        this.router.navigate(['/caregiver-detail', updatedCaregiver.id]);
      });
  }

  compareFormPatients(a: Patient, b: Patient) { return a.id === b.id; }

}
