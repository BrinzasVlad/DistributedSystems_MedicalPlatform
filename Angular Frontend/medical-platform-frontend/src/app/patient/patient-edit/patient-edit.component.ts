import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PatientService } from '../../services/patient.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Patient } from '../../model/patient';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {

  patientId: number;

  patientForm: FormGroup;
  maxDate = new Date(); // No patients born in the future

  isProcessing = true;

  constructor(private formBuilder: FormBuilder,
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
