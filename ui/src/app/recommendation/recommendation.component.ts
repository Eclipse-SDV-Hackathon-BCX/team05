import { Component, OnInit } from '@angular/core';
import { Backend_Service_model, Service_Station, ServiceStationService } from '../service-station.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

  recommendedStation?: Backend_Service_model;

  stationData = [
    '11km | 00:05',
    '23km | 00:12',
    '49km | 00:30'
  ];

  constructor(private stationService: ServiceStationService) {
  }

  ngOnInit(): void {
    setTimeout(() => this.updateStation(), 5000);
  }

  updateStation() {
    this.stationService.getStations().subscribe(
      stations => {
        let newStation = stations.filter(station => {
          return station.capacity >= station.occupancy;
        }).pop();
        if (this.recommendedStation?.id != newStation?.id) {

        }
        this.recommendedStation = newStation;
      }
    );
  }

  getParkingText(): string {
    let currStationId: number = parseInt((this.recommendedStation?.id.substr(3, 1) || '4'));

    return this.stationData[currStationId];
  }

  getParkingStyle() {

  }

}
