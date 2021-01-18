import { Component, OnInit } from '@angular/core';
import { Caregiver } from 'src/app/model/caregiver';
import { ActivatedRoute, Router } from '@angular/router';
import { CaregiverService } from 'src/app/services/caregiver.service';

@Component({
  selector: 'app-caregiver-detail',
  templateUrl: './caregiver-detail.component.html',
  styleUrls: ['./caregiver-detail.component.css']
})
export class CaregiverDetailComponent implements OnInit {

  caregiver: Caregiver;
  isLoadingData = true;

  constructor(private route: ActivatedRoute, private caregiverService: CaregiverService, private router: Router) { }

  ngOnInit() {
    const caregiverId = this.route.snapshot.params.id;
    this.caregiverService.getCaregiverById(caregiverId)
      .subscribe(loadedCaregiver => {
        this.caregiver = loadedCaregiver;
        this.caregiver.patients.sort((a, b) => a.name.localeCompare(b.name)); // For pretty, orderly looks
        this.isLoadingData = false;
      });
  }

  deleteCurrentCaregiver() {
    this.caregiverService.deleteCaregiverById(this.caregiver.id)
      .subscribe(() => { this.router.navigateByUrl('/caregivers'); });
  }

}
