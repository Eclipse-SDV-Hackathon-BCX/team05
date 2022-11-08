import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ButtonModule } from 'carbon-components-angular';

import { AppComponent } from './app.component';
import { BreakComponent } from './break/break.component';
import { BadgeComponent } from './common/badge/badge.component';
import { IconComponent } from './common/icon/icon.component';
import { DestinationComponent } from './destination/destination.component';
import { MapComponent } from './map/map.component';
import { PanelComponent } from './panel/panel.component';
import { RecommendationComponent } from './recommendation/recommendation.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    PanelComponent,
    DestinationComponent,
    RecommendationComponent,
    BreakComponent,
    IconComponent,
    BadgeComponent
  ],
  imports: [
    BrowserModule, ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
