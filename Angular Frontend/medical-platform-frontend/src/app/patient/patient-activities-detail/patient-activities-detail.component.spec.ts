import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientActivitiesDetailComponent } from './patient-activities-detail.component';

describe('PatientActivitiesDetailComponent', () => {
  let component: PatientActivitiesDetailComponent;
  let fixture: ComponentFixture<PatientActivitiesDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientActivitiesDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientActivitiesDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
