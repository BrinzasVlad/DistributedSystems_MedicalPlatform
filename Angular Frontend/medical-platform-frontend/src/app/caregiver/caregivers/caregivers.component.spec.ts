import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaregiversComponent } from './caregivers.component';

describe('CaregiversComponent', () => {
  let component: CaregiversComponent;
  let fixture: ComponentFixture<CaregiversComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [CaregiversComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaregiversComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
