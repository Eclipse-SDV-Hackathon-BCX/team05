import { AfterViewInit, Component, ViewEncapsulation } from '@angular/core';
import * as L from 'leaflet';
import { ServiceStationService, Backend_Service_model } from '../service-station.service';

interface UI_Station{
  station: Backend_Service_model,
  marker: L.Marker
}

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MapComponent implements AfterViewInit {

  private map:any;

  private stations:UI_Station[] = [];

  private timeMarker = L.icon({
    iconUrl: 'assets/time_black.svg',
    iconSize:     [80, 90], // size of the icon
    iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
  })

  private stationMarker = L.icon({
    iconUrl: 'assets/fuel_green.svg',
    iconSize:     [80, 90], // size of the icon
    iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
  })

  constructor(private stationService: ServiceStationService) {
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: [ 52.693, 13.05 ],
      zoom: 10,
      zoomControl: true,
      attributionControl: false,
      dragging: true
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 10,
      minZoom: 12.2,

      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    L.marker([52.708480, 13.0232002], {icon: this.timeMarker}).addTo(this.map);

    tiles.addTo(this.map);
  }

  addStations(stations:Backend_Service_model[]):void {
    stations.forEach(station => {
      let marker = L.marker([station.coordinates.lng, station.coordinates.lat], {icon: this.stationMarker})
        .addTo(this.map)
        .bindPopup(station.name)
        this.stations.push({station, marker});

    })
  }

  private updateStationOccupancy():void {
    this.stations.forEach(stat => {
   //   console.log(stat);
   //   let status = this.stationService.getStatusfromOcupancy(stat.station.capacity, s.occupancy)
      stat.marker.setIcon(this.timeMarker);
    });
  }

  ngAfterViewInit(): void {
    this.initMap();
    this.stationService.getStations().subscribe(
      stations => this.addStations(stations)

    )

    setTimeout(() => { this.updateStationOccupancy() }, 5000);
  }

}
