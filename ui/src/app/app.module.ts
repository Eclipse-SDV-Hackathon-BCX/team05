import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IconModule, UIShellModule } from 'carbon-components-angular';

import { AppComponent } from './app.component';
import { BreakComponent } from './break/break.component';
import { DestinationComponent } from './destination/destination.component';
import { MapComponent } from './map/map.component';
import { PanelComponent } from './panel/panel.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { IconComponent } from './common/icon/icon.component';


@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    PanelComponent,
    DestinationComponent,
    RecommendationComponent,
    BreakComponent,
    IconComponent,
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
