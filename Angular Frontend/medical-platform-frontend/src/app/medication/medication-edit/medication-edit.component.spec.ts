import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationEditComponent } from './medication-edit.component';

describe('MedicationEditComponent', () => {
  let component: MedicationEditComponent;
  let fixture: ComponentFixture<MedicationEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [MedicationEditComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
