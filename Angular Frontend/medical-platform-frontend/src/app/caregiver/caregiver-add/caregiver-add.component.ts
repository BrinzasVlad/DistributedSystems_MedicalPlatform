import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CaregiverService } from 'src/app/services/caregiver.service';
import { Router, RouterLink } from '@angular/router';
import { DatePipe, NgIf } from '@angular/common';
import { Caregiver } from 'src/app/model/caregiver';
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
    selector: 'app-caregiver-add',
    templateUrl: './caregiver-add.component.html',
    styleUrls: ['./caregiver-add.component.css'],
    imports: [NgIf, MatProgressSpinner, MatAnchor, RouterLink, MatIcon, MatCard, FormsModule, ReactiveFormsModule, MatFormField, MatInput, MatError, MatDatepickerInput, MatDatepickerToggle, MatSuffix, MatDatepicker, MatSelect, MatOption, MatButton]
})
export class CaregiverAddComponent implements OnInit {

  caregiverForm: UntypedFormGroup;
  maxDate = new Date(); // No caregivers born in the future

  isSendingData = false;

  constructor(private formBuilder: UntypedFormBuilder,
              private caregiverService: CaregiverService,
              private router: Router,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.caregiverForm = this.formBuilder.group({
      name: ['', Validators.required],
      birthDate: [],
      gender: [],
      address: []
    });
  }

  onFormSubmit() {
    this.isSendingData = true;

    const formValue = this.caregiverForm.value;
    const caregiverFromForm: Caregiver = {
      id: undefined,
      name: formValue.name,
      birthDate: this.datePipe.transform(formValue.birthDate, 'yyyy-MM-dd'),
      // birthDate: formValue.birthDate,
      gender: formValue.gender,
      address: formValue.address,
      patients: []
    };

    this.caregiverService.insertCaregiver(caregiverFromForm)
      .subscribe(insertedCaregiver => {
        this.isSendingData = false;
        this.router.navigate(['/caregiver-detail', insertedCaregiver.id]);
      });
  }
}
