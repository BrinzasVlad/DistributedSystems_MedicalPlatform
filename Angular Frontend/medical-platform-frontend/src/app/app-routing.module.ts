import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicationsComponent } from './medication/medications/medications.component';
import { MedicationDetailComponent } from './medication/medication-detail/medication-detail.component';
import { HomeComponent } from './home/home.component';
import { MedicationAddComponent } from './medication/medication-add/medication-add.component';
import { MedicationEditComponent } from './medication/medication-edit/medication-edit.component';
import { PatientsComponent } from './patient/patients/patients.component';
import { PatientDetailComponent } from './patient/patient-detail/patient-detail.component';
import { PatientEditComponent } from './patient/patient-edit/patient-edit.component';
import { PatientAddComponent } from './patient/patient-add/patient-add.component';
import { CaregiversComponent } from './caregiver/caregivers/caregivers.component';
import { CaregiverDetailComponent } from './caregiver/caregiver-detail/caregiver-detail.component';
import { CaregiverAddComponent } from './caregiver/caregiver-add/caregiver-add.component';
import { CaregiverEditComponent } from './caregiver/caregiver-edit/caregiver-edit.component';


const routes: Routes = [
  // Home page
  {
    path: '',
    component: HomeComponent,
    data: { title: 'Medical Platform Home' }
  },

  // Medication routes
  {
    path: 'medications',
    component: MedicationsComponent,
    data: { title: 'List of Medications' }
  },
  {
    path: 'medication-detail/:id',
    component: MedicationDetailComponent,
    data: { title: 'Medication Details' }
  },
  {
    path: 'medication-add',
    component: MedicationAddComponent,
    data: { title: 'Add Medication' }
  },
  {
    path: 'medication-edit/:id',
    component: MedicationEditComponent,
    data: { title: 'Editing Medication'}
  },

  // Patient routes
  {
    path: 'patients',
    component: PatientsComponent,
    data: { title: 'List of Patients' }
  },
  {
    path: 'patient-detail/:id',
    component: PatientDetailComponent,
    data: { title: 'Patient Details' }
  },
  {
    path: 'patient-add',
    component: PatientAddComponent,
    data: { title: 'Add Patient' }
  },
  {
    path: 'patient-edit/:id',
    component: PatientEditComponent,
    data: { title: 'Editing Patient' }
  },

  // Caregiver routes
  {
    path: 'caregivers',
    component: CaregiversComponent,
    data: { title: 'List of Caregivers' }
  },
  {
    path: 'caregiver-detail/:id',
    component: CaregiverDetailComponent,
    data: { title: 'Caregiver Details' }
  },
  {
    path: 'caregiver-add',
    component: CaregiverAddComponent,
    data: { title: 'Add Caregiver' }
  },
  {
    path: 'caregiver-edit/:id',
    component: CaregiverEditComponent,
    data: { title: 'Editing Caregiver' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
