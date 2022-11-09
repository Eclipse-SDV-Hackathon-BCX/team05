import { Component, OnInit } from '@angular/core';
import { Backend_Service_model, Service_Station, ServiceStationService } from '../service-station.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

  recommendedStation?: Backend_Service_model;

  constructor(private stationService: ServiceStationService) {
  }

  ngOnInit(): void {
    setTimeout(() => this.updateStation(), 5000);
  }

  updateStation() {
    this.stationService.getStations().subscribe(
      stations => {
        this.recommendedStation = stations.filter(station => {
          return station.capacity >= station.occupancy;
        }).pop()
      }
    )
  }

}
