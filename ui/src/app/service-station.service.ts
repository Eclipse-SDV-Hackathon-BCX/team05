import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http"
import { switchMap } from 'rxjs';


enum Occupancy {
  Full,
  Medium,
  Free
}

interface Service_Station {
  latitude: number
  longditude: number
  status: Occupancy
}

interface Backend_Service_model{
  id: string,
  name: string,
  capacity: number,
  occupancy: number,
  coordinates: {lat: number, lng: number}
}

@Injectable({
  providedIn: 'root'
})
export class ServiceStationService {
  private service_stations: Service_Station[] = [];
  
  constructor(private http: HttpClient) {
  }


  getStatusfromOcupancy(capacity: number, occupancy: number): Occupancy {
    if(occupancy >= capacity) return Occupancy.Full
    else if( occupancy > (capacity/2)) return Occupancy.Medium
    else return Occupancy.Free
  }

  getStations(): Service_Station[]{
    if (this.service_stations.length > 0)
    {return this.service_stations}
    else {
      this.http.get<Backend_Service_model[]>('/api/station/list')
      .subscribe((result: Backend_Service_model[])=>{
        console.log(result)
        this.service_stations = result.map((service_station)=>{
          let station_object: Service_Station = {
            latitude: service_station.coordinates.lat,
            longditude: service_station.coordinates.lng,
            status: this.getStatusfromOcupancy(service_station.capacity, service_station.occupancy)
          }
          return station_object
        })
        console.log(this.service_stations)
        return this.service_stations
      })
    }
    return []
  }
  


}
