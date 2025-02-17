import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [RouterOutlet]
})
export class AppComponent {
  title = 'Medical Platform';
}

// Known TODOs:
// - fix Angular Material v15 appearance changes; in extra particular:
// - - fix form input labels no longer minimizing to the top
// - - fix input fields having solid background
// - smaller Material issues:
// - - material icon buttons have the icons smaller and less centred now
// - - - FIXED, -ish; edit/delete buttons are not caught in the current iteration, but they... kinda' look good like that?
// - - spacing changed somewhat (tables have less padding, detail pages have too much padding under title)
// - - openable folders under patient-detail are now bold, which looks a bit too strong
// - - Activity History table in patient-detail is now a bit too tight, leading to ugly word-wrap
// - - duration column in Activity History table in patient-detail has no data (why?)
// - - Medication History section in patient-detail lacks proper messaging for when no data exists
// - - new version of slider toggles / checkboxes looks too strong; older softer version was better
// - - form input solid background also applies to textbox in Medication History table
// - - having written labels (e.g. 12/16 - 75%) on the bars in the Medication History chart would be nice
// - look at routing module and figure whether we should remove @NgModule from it for standalone-ness
// - look at long list of providers in main.ts and figure whether that is normal or something is wrong
// - fix Column Chart in patient-activities-detail so it's not horizontally squished
