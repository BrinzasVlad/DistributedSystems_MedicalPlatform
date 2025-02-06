import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MedicationsComponent } from './medication/medications/medications.component';
import { MedicationDetailComponent } from './medication/medication-detail/medication-detail.component';
import { MedicationAddComponent } from './medication/medication-add/medication-add.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';

// import { GoogleChartsModule } from 'angular-google-charts';

import { MatLegacyButtonModule as MatButtonModule } from '@angular/material/legacy-button';
import { MatLegacyCardModule as MatCardModule } from '@angular/material/legacy-card';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatLegacyFormFieldModule as MatFormFieldModule } from '@angular/material/legacy-form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatLegacyInputModule as MatInputModule } from '@angular/material/legacy-input';
import { MatLegacyListModule as MatListModule } from '@angular/material/legacy-list';
import { MatLegacyPaginatorModule as MatPaginatorModule } from '@angular/material/legacy-paginator';
import { MatLegacyProgressSpinnerModule as MatProgressSpinnerModule } from '@angular/material/legacy-progress-spinner';
import { MatLegacySelectModule as MatSelectModule } from '@angular/material/legacy-select';
import { MatLegacySlideToggleModule as MatSlideToggleModule } from '@angular/material/legacy-slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { MatLegacyTableModule as MatTableModule } from '@angular/material/legacy-table';
import { HttpClientModule } from '@angular/common/http';
import { MedicationEditComponent } from './medication/medication-edit/medication-edit.component';
import { PatientsComponent } from './patient/patients/patients.component';
import { PatientDetailComponent } from './patient/patient-detail/patient-detail.component';
import { PatientEditComponent } from './patient/patient-edit/patient-edit.component';
import { PatientAddComponent } from './patient/patient-add/patient-add.component';
import { DatePipe } from '@angular/common';
import { CaregiversComponent } from './caregiver/caregivers/caregivers.component';
import { CaregiverDetailComponent } from './caregiver/caregiver-detail/caregiver-detail.component';
import { CaregiverAddComponent } from './caregiver/caregiver-add/caregiver-add.component';
import { CaregiverEditComponent } from './caregiver/caregiver-edit/caregiver-edit.component';
import { PatientActivitiesDetailComponent } from './patient/patient-activities-detail/patient-activities-detail.component';
import { PatientMedicationDetailComponent } from './patient/patient-medication-detail/patient-medication-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    MedicationsComponent,
    MedicationDetailComponent,
    MedicationAddComponent,
    HomeComponent,
    MedicationEditComponent,
    PatientsComponent,
    PatientDetailComponent,
    PatientEditComponent,
    PatientAddComponent,
    CaregiversComponent,
    CaregiverDetailComponent,
    CaregiverAddComponent,
    CaregiverEditComponent,
    PatientActivitiesDetailComponent,
    PatientMedicationDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatGridListModule,
    MatListModule,
    MatSlideToggleModule,
    MatExpansionModule,
    // GoogleChartsModule.forRoot()
  ],
  providers: [
    DatePipe,
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
