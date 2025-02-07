import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaregiverAddComponent } from './caregiver-add.component';

describe('CaregiverAddComponent', () => {
  let component: CaregiverAddComponent;
  let fixture: ComponentFixture<CaregiverAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [CaregiverAddComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaregiverAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
