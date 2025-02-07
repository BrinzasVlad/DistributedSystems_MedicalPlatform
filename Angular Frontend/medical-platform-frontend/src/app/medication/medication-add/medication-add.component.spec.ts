import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationAddComponent } from './medication-add.component';

describe('MedicationAddComponent', () => {
  let component: MedicationAddComponent;
  let fixture: ComponentFixture<MedicationAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [MedicationAddComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
