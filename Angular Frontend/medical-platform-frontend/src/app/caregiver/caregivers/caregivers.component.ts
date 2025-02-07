import { Component, OnInit } from '@angular/core';
import { Caregiver } from '../../model/caregiver';
import { CaregiverService } from '../../services/caregiver.service';

@Component({
    selector: 'app-caregivers',
    templateUrl: './caregivers.component.html',
    styleUrls: ['./caregivers.component.css'],
    standalone: false
})
export class CaregiversComponent implements OnInit {

  caregivers: Caregiver[];
  displayedColumns = ['id', 'name', 'birthDate', 'gender', 'address'];
  isLoadingData = true;

  constructor(private caregiverService: CaregiverService) { }

  ngOnInit() {
    this.caregiverService.getAllCaregivers()
      .subscribe(loadedCaregivers => {
        this.caregivers = loadedCaregivers;
      });

    this.isLoadingData = false;
  }

}
