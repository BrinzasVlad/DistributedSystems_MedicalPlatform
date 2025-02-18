import { Component, OnInit, Input } from '@angular/core';
import { ActivityService } from 'src/app/services/activity.service';
import { Activity } from 'src/app/model/activity';
import { PageEvent, MatPaginator } from '@angular/material/paginator';
import { MatSelectChange, MatSelect } from '@angular/material/select';
import { NgIf, NgFor, NgClass, DatePipe } from '@angular/common';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { GoogleChartsModule } from 'angular-google-charts';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow } from '@angular/material/table';
import { MatSlideToggle } from '@angular/material/slide-toggle';
import { MatInput } from '@angular/material/input';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';

@Component({
    selector: 'app-patient-activities-detail',
    templateUrl: './patient-activities-detail.component.html',
    styleUrls: ['./patient-activities-detail.component.css'],
    imports: [NgIf, MatProgressSpinner, GoogleChartsModule, MatFormField, MatLabel, MatSelect, FormsModule, NgFor, MatOption, MatTable, MatColumnDef, MatHeaderCellDef, MatHeaderCell, MatCellDef, MatCell, MatSlideToggle, MatInput, CdkTextareaAutosize, MatHeaderRowDef, MatHeaderRow, MatRowDef, MatRow, NgClass, MatPaginator, DatePipe]
})
export class PatientActivitiesDetailComponent implements OnInit {

  @Input() patientId: number;
  patientActivities: Activity[];

  // Activity timeline
  timelineHeightPerActivity = 60;
  timelineHeightForScale = 40;
  timelineData: [string, number, number][];
  timelineOptions = {
    height: 0,
    hAxis: {
      minValue: undefined,
      maxValue: undefined // Dummy values, get replaced in onInit
    }
  };

  // Timeframe options
  MILLIS_24_HOURS = 24 * 60 * 60 * 1000;
  MILLIS_1_WEEK = 7 * 24 * 60 * 60 * 1000;
  MILLIS_30_DAYS = 30 * 24 * 60 * 60 * 1000;
  MILLIS_ALL_TIME = Infinity;
  timeframeOptions = [
    {title: '24 Hours', value: this.MILLIS_24_HOURS},
    {title: '1 Week', value: this.MILLIS_1_WEEK},
    {title: '30 Days', value: this.MILLIS_30_DAYS},
    {title: 'All time', value: this.MILLIS_ALL_TIME}
  ];

  // Activity distribution data
  columnTimeframe = this.MILLIS_1_WEEK;
  columnWidthPerEntry = 110;
  columnWidthForScale = 40;
  columnData: [string, number][];
  columnOptions = {
    legend: { position: 'none' },
    width: 0,
    height: 300,
    chartArea: { left: 30, top: 30, width: '100%' },
  };

  // Activity justified/abnormal/normal breakdown
  pieTimeframe = this.MILLIS_1_WEEK;
  pieData: [string, number][];
  pieEntriesTotal: number;
  pieOptions = {
    pieHole: 0.35,
    pieSliceText: 'label',
    legend: { position: 'left' },
    height: 320,
    chartArea: {left: 0, top: 20, height: '90%'},
    colors: ['blue', 'orange', 'red'],
  };

  // Main activity table
  tableColumns = ['name', 'start', 'end', 'duration', 'abnormal', 'justified', 'recommendation'];
  tablePagedData: Activity[];
  tablePageSizes = [5, 10, 20];
  tableDefaultPageSize = 10;


  constructor(private activityService: ActivityService) { }

  ngOnInit() {
    // Set timeline thresholds for 24h exactly here because it can't be prettily done inline
    this.timelineOptions.hAxis.maxValue = new Date();
    const yesterday = new Date(); yesterday.setDate(yesterday.getDate() - 1);
    this.timelineOptions.hAxis.minValue = yesterday;

    this.activityService.getActivitiesForPatient(this.patientId)
      .subscribe(loadedActivities => {
        // We have the data, now we want to fill in all of the chart data
        this.patientActivities = loadedActivities;

        this.generateTimeline(this.patientActivities);
        this.generateActivityDistribution(this.patientActivities, this.columnTimeframe);
        this.generateActivityBreakdown(this.patientActivities, this.pieTimeframe);
        this.tablePagedData = this.patientActivities.slice(0, this.tableDefaultPageSize);
      });
  }

  computeDuration(activity: Activity): number {
    // FIXME: this should by all means be in Activity or a related helper
    // I'd implement it as a get property, but because of how the Activity[] array is populated,
    // they don't actually get the getter methods, I get the feeling. So the compiler says nothing
    // is wrong, but the getter doesn't actually return anything.
    return Date.parse(activity.end) - Date.parse(activity.start);
  }

  formatDuration(durationInMilliseconds: number): string {
    const hours = Math.floor(durationInMilliseconds / 3600000);
    const minutes = Math.floor((durationInMilliseconds % 3600000) / 60000);
    
    if (hours > 0) {
        return `${hours}h ${minutes}m`;
    } else {
        return `${minutes}m`;
    }
  }

  generateTimeline(activities: Activity[]) {
    // Select only relevant activities; filter activities to last 24 hours and truncate
    // FIXME: use UTC dates, since else we have timezone issues!
    const filteredActivities = activities.filter(activity => {
      const millisTimeDifferenceEnd = Date.now() - Date.parse(activity.end);
      return millisTimeDifferenceEnd < this.MILLIS_24_HOURS;
    }).map(activity => {
        const copiedActivity: Activity = JSON.parse(JSON.stringify(activity)); // Clone; TODO: make sure it's safe
        const millisTimeDifferenceStart = Date.now() - Date.parse(copiedActivity.start);

        // Truncate if needed
        if (millisTimeDifferenceStart > this.MILLIS_24_HOURS) {
          copiedActivity.start = new Date(Date.now() - this.MILLIS_24_HOURS).toISOString();
          // TODO: switch to using dates
        }

        return copiedActivity;
    });

    const numberOfActivities = filteredActivities.reduce((listOfNames, activity) => {
      if (!listOfNames.includes(activity.name)) { listOfNames.push(activity.name); }
      return listOfNames;
    }, []).reduce((count, _) => count + 1, 0);
    this.timelineOptions.height = this.timelineHeightForScale + this.timelineHeightPerActivity * numberOfActivities;

    this.timelineData = filteredActivities.map(activity => {
      return [activity.name, Date.parse(activity.start), Date.parse(activity.end)];
    });
  }

  generateActivityDistribution(activities: Activity[], timeframeMillis: number) {
    const filteredActivities = activities.filter(activity => {
      const millisSinceActivity = Date.now() - Date.parse(activity.end);
      return millisSinceActivity < timeframeMillis;
    });

    this.columnData = [];
    filteredActivities.forEach(activity => {
      const columnDataEntry = this.columnData.find(entry => entry[0] === activity.name);
      if (columnDataEntry) {
        columnDataEntry[1]++;
      } else {
        this.columnData.push([activity.name, 1]);
      }
    });

    // Sort alphabetically by name, to be pretty
    this.columnData.sort((first, second) => first[0].localeCompare(second[0]));
    
    this.columnOptions = {
      ...this.columnOptions,
      width: this.columnWidthForScale + this.columnWidthPerEntry * this.columnData.length
    };
    // this.columnOptions.width = this.columnWidthForScale + this.columnWidthPerEntry * this.columnData.length;
  }

  generateActivityBreakdown(activities: Activity[], timeframeMillis: number) {
    const filteredActivities = activities.filter(activity => {
      const millisSinceActivity = Date.now() - Date.parse(activity.end);
      return millisSinceActivity < timeframeMillis;
    });

    const breakdown = filteredActivities.reduce((currentBreakdown, activity, index, array) => {
      if (!activity.abnormal) {
        currentBreakdown.normal++;
      } else if (activity.justified) {
        currentBreakdown.justified++;
      } else {
        currentBreakdown.abnormal++;
      }
      return currentBreakdown;
    }, {normal: 0, justified: 0, abnormal: 0});

    this.pieData = [
      ['Normal', breakdown.normal],
      ['Justified', breakdown.justified],
      ['Abnormal', breakdown.abnormal]
    ];
    this.pieEntriesTotal = breakdown.normal + breakdown.justified + breakdown.abnormal;
  }

  updateActivityDistribution(event: MatSelectChange) {
    this.columnTimeframe = event.value;
    this.generateActivityDistribution(this.patientActivities, this.columnTimeframe);
  }

  updateActivityBreakdown(event: MatSelectChange) {
    this.pieTimeframe = event.value;
    this.generateActivityBreakdown(this.patientActivities, this.pieTimeframe);
  }

  paginateTableData(event: PageEvent) {
    const tablePageLow = event.pageIndex * event.pageSize;
    const tablePageHigh = tablePageLow + event.pageSize;
    this.tablePagedData = this.patientActivities.slice(tablePageLow, tablePageHigh);
  }

  updateActivity(activity: Activity) {
    // TODO: This activity has been changed and must be updated on the backend!
    this.activityService.updateActivity(activity)
      .subscribe();
  }

}
