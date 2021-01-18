import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  links = [
    {name: 'Medications', url: '/medications'},
    {name: 'Patients', url: '/patients'},
    {name: 'Caregivers', url: '/caregivers'}
  ];

  constructor() { }

  ngOnInit() {
  }

}
