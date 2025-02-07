import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientMedicationDetailComponent } from './patient-medication-detail.component';

describe('PatientMedicationDetailComponent', () => {
  let component: PatientMedicationDetailComponent;
  let fixture: ComponentFixture<PatientMedicationDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [PatientMedicationDetailComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientMedicationDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
