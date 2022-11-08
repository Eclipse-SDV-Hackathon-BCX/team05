import { AfterViewInit, Component, ViewEncapsulation } from '@angular/core';
import * as L from 'leaflet';
import { ServiceStationService } from '../service-station.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MapComponent implements AfterViewInit {

  private map:any;

  private initMap(): void {
    this.map = L.map('map', {
      center: [ 52.68, 13.02 ],
      zoom: 13,
      zoomControl: false,
      attributionControl: false,
      dragging: false
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 13,
      minZoom: 13,

      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    var timeMarker = L.icon({
      iconUrl: 'assets/time_black.svg',
  
      iconSize:     [80, 90], // size of the icon
      iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
      popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    })

    L.marker([52.708480, 13.0232002], {icon: timeMarker}).addTo(this.map);

    L.marker([52.6651948, 13.0032002]).addTo(this.map)
      .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
      .openPopup();

    tiles.addTo(this.map);
  }

  constructor(private stationService: ServiceStationService) { 

  }

  ngAfterViewInit(): void {
    this.initMap();
    this.stationService.getStations()
  }

}
