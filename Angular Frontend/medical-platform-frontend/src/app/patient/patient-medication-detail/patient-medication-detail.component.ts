import { Component, OnInit, Input } from '@angular/core';
import { MedicationTakenService } from 'src/app/services/medication-taken.service';
import { MedicationTaken } from 'src/app/model/medicationTaken';
import { MatSelectChange } from '@angular/material/select';

@Component({
    selector: 'app-patient-medication-detail',
    templateUrl: './patient-medication-detail.component.html',
    styleUrls: ['./patient-medication-detail.component.css'],
    standalone: false
})
export class PatientMedicationDetailComponent implements OnInit {

  medicationsTaken: MedicationTaken[];

  // Timeframe options
  DAYS_24_HOURS = 1;
  DAYS_1_WEEK = 7;
  DAYS_30_DAYS = 30;
  DAYS_ALL_TIME = Infinity;
  timeframeOptions = [
    {title: '24 Hours', value: this.DAYS_24_HOURS},
    {title: '1 Week', value: this.DAYS_1_WEEK},
    {title: '30 Days', value: this.DAYS_30_DAYS},
    {title: 'All time', value: this.DAYS_ALL_TIME}
  ];

  // Bar chart of medication taken
  stackedTimeframe = this.DAYS_1_WEEK;
  stackedHeightPerEntry = 60;
  stackedHeightForScale = 40;
  stackedColumns = [ 'Name', 'Taken', 'Not taken' ];
  stackedData: [string, number, number][];
  stackedOptions = {
    isStacked: 'percent',
    height: 0,
    width: '100%',
    legend: 'right',
    chartArea: { left: 0, top: 30 },
  };

  @Input() patientId: number;

  constructor(private medicationTakenService: MedicationTakenService) { }

  ngOnInit() {
    this.medicationTakenService.getMedicationsTakenForPatient(this.patientId)
      .subscribe(loadedMedicationsTaken => {
        this.medicationsTaken = loadedMedicationsTaken;

        this.generateChart(loadedMedicationsTaken, this.stackedTimeframe);
      });
  }

  generateChart(medicationsTaken: MedicationTaken[], timeframeDays: number) {
    const filteredTaken =
      (Infinity === timeframeDays) // Special case, since you can't construct a date Infinity days ago
      ? medicationsTaken
      : medicationsTaken.filter(medicationTaken => {
        const timeframeLimitDate = new Date();
        timeframeLimitDate.setHours(0, 0, 0, 0); // Get start-of-day
        timeframeLimitDate.setDate(timeframeLimitDate.getDate() - timeframeDays); // Loops month automatically, if needed

        return Date.parse(medicationTaken.date) > timeframeLimitDate.getTime();
      });

    this.stackedData = filteredTaken.reduce((currentTakenList, medicationTaken) => {
      const entryMatchingMedication = currentTakenList.find(entry => entry[0] === medicationTaken.medicationName);
      if (entryMatchingMedication) {
        if (medicationTaken.taken) {
          entryMatchingMedication[1]++;
        } else {
          entryMatchingMedication[2]++;
        }
      } else {
        if (medicationTaken.taken) {
          currentTakenList.push([medicationTaken.medicationName, 1, 0]);
        } else {
          currentTakenList.push([medicationTaken.medicationName, 0, 1]);
        }
      }

      return currentTakenList;
    }, []);

    this.stackedOptions.height = this.stackedHeightForScale + this.stackedHeightPerEntry * this.stackedData.length;
  }

  updateChart(event: MatSelectChange) {
    this.stackedTimeframe = event.value;
    this.generateChart(this.medicationsTaken, this.stackedTimeframe);
  }

}
