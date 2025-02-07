import { Component, OnInit } from '@angular/core';
import { MatNavList, MatListItem } from '@angular/material/list';
import { NgFor } from '@angular/common';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    imports: [MatNavList, NgFor, MatListItem]
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
