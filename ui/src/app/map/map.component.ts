import { AfterViewInit, Component, ViewEncapsulation } from '@angular/core';
import * as omnivore from '@mapbox/leaflet-omnivore';
import * as L from 'leaflet';
import { Backend_Service_model, Occupancy, ServiceStationService } from '../service-station.service';
import { Message, TruckPositionService } from '../truck-position.service';

interface UI_Station {
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

  private map: any;
  private received: Message[] = [];

  private stations: UI_Station[] = [];

  private timeMarker = L.icon({
    iconUrl: 'assets/time_black.svg',
    iconSize: [80, 90], // size of the icon
    iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
    popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
  })

  private stationMarkerFree = L.icon({
    iconUrl: 'assets/fuel_green.svg',
    iconSize: [80, 90], // size of the icon
    iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
    popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
  })

  private stationMarkerFull = L.icon({
    iconUrl: 'assets/Fuel_red.svg',
    iconSize: [80, 90], // size of the icon
    iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
    popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
  })

  private currentPos: any;

  constructor(private stationService: ServiceStationService, private truckService: TruckPositionService) {
    truckService.messages.subscribe(msg => {
      this.received.push(msg);
      if(!this.map) return;
      // console.log("Response from websocket: " + msg);
      if(this.currentPos) this.currentPos.remove();
      this.currentPos = L.circle([msg.lat, msg.lng], 500, {
        color: 'blue',
        fillColor: 'blue',
        fillOpacity: 1        
      });
      this.currentPos.bringToFront();
      this.currentPos.addTo(this.map);
    });
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: [52.693, 13.05],
      zoom: 10,
      zoomControl: true,
      attributionControl: false,
      dragging: true
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 10,
      minZoom: 12,

      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    L.marker([52.708480, 13.0232002], { icon: this.timeMarker }).addTo(this.map);

    tiles.addTo(this.map);
    omnivore.kml('assets/LKW-Strecke.kml').addTo(this.map);
  }

  private addStations(stations: Backend_Service_model[]): void {
    stations.forEach(station => {
      const icon = this.getIconFromOccupancy(station.capacity, station.occupancy);
      let marker = L.marker([station.coordinates.lng, station.coordinates.lat], { icon })
        .addTo(this.map)
        .bindPopup(station.name)
      this.stations.push({ station, marker });
    })
  }

  private getIconFromOccupancy(capacity: number, occupancy: number): L.Icon {
    return this.stationService.getStatusfromOcupancy(capacity, occupancy) === Occupancy.Free
      ? this.stationMarkerFree : this.stationMarkerFull;
  }

  private updateStationOccupancy(): void {
    this.stationService.getStations().subscribe(
      updatedStations => {
        this.stations.forEach(currentStation => {
          let updatedStation = (updatedStations.find(single => currentStation.station.id === single.id));
          currentStation.marker.setIcon(this.getIconFromOccupancy(updatedStation?.capacity || 0, updatedStation?.occupancy || 0));
        });
      }
    );
  }

  ngAfterViewInit(): void {
    this.initMap();
    this.stationService.getStations().subscribe(
      stations => this.addStations(stations)
    )

    setInterval(() => { this.updateStationOccupancy() }, 5000);
  }

}
