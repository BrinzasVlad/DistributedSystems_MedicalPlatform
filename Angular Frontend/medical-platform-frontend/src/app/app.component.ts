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
// - timeline chart might or might not be broken; couldn't test without 24-hour-recent data
// - smaller Material issues:
// - - material icon buttons have the icons smaller and less centred now
// - - - FIXED, -ish; edit/delete buttons are not caught in the current iteration, but they... kinda' look good like that?
// - - spacing changed somewhat (tables have less padding, detail pages have too much padding under title)
// - - openable folders under patient-detail are now bold, which looks a bit too strong
// - - textbox in Activity History table still gets solid input when selecting, highlighting unequal top-bottom spacing
// - - having written labels (e.g. 12/16 - 75%) on the bars in the Medication History chart would be nice
// - look at routing module and figure whether we should remove @NgModule from it for standalone-ness
// - look at long list of providers in main.ts and figure whether that is normal or something is wrong
