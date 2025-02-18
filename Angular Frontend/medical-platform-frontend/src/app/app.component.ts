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
// - - openable folders under patient-detail are now bold, which looks a bit too strong
// - - having written labels (e.g. 12/16 - 75%) on the bars in the Medication History chart would be nice
// - look at long list of providers in main.ts and figure whether that is normal or something is wrong
