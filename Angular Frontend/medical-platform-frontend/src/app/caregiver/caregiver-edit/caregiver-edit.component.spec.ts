import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaregiverEditComponent } from './caregiver-edit.component';

describe('CaregiverEditComponent', () => {
  let component: CaregiverEditComponent;
  let fixture: ComponentFixture<CaregiverEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaregiverEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaregiverEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
