import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export enum Occupancy {
  Full,
  Medium,
  Free
}

export interface Service_Station {
  latitude: number
  longditude: number
  status: Occupancy
}

export interface Backend_Service_model{
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
    else return Occupancy.Free
  }

  getStations(): Observable<Backend_Service_model[]>{
      return this.http.get<Backend_Service_model[]>('/api/station/list')
  }

}
