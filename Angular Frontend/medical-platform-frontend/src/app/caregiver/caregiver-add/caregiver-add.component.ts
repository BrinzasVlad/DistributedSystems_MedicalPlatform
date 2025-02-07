import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { CaregiverService } from 'src/app/services/caregiver.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Caregiver } from 'src/app/model/caregiver';

@Component({
    selector: 'app-caregiver-add',
    templateUrl: './caregiver-add.component.html',
    styleUrls: ['./caregiver-add.component.css'],
    standalone: false
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
