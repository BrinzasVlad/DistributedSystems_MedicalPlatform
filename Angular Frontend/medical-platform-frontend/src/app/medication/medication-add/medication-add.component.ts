import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Medication } from 'src/app/model/medication';
import { MedicationService } from 'src/app/services/medication.service';

@Component({
  selector: 'app-medication-add',
  templateUrl: './medication-add.component.html',
  styleUrls: ['./medication-add.component.css']
})
export class MedicationAddComponent implements OnInit {

  medicationForm = this.formBuilder.group({
    name: ['', Validators.required],
    dosage: [''],
    sideEffects: ['']
  });
  isSendingData = false;

  constructor(private formBuilder: FormBuilder, private medicationService: MedicationService, private router: Router) { }

  ngOnInit()
  { }

  onFormSubmit() {
    this.isSendingData = true;

    // FIXME: getRawValue() *works*, but might not be what we want
    //
    // Specifically: getRawValue() gets the values of all fields, including disabled ones.
    // We probably don't ever plan to have disabled fields, and if we did it'd probably
    // be because those are hard-coded / picked some other way / populated from someplace else,
    // case in which getRawValue() would still work since it'd get the field value as-is.
    //
    // The alternative would be to handle the fields individually, and provide defaults
    // for fields left empty (e.g. using the ?? operator).
    // Notably, the compiler lets it go even if you simply skip the ... destructuring and
    // write out every field (even though the values may still be undefined).
    const formValue = this.medicationForm.getRawValue();
    const medicationFromForm: Medication = {
      id: undefined,
      ...formValue
    }

    this.medicationService.insertMedication(medicationFromForm)
      .subscribe(insertedMedication => {
        this.isSendingData = false;
        this.router.navigate(['/medication-detail', insertedMedication.id]);
      });
  }

}
