import { Component, OnInit } from '@angular/core';
import { ServiceStationService } from '../service-station.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

  constructor(private stationService: ServiceStationService) {
  }

  ngOnInit(): void {

  }

}
