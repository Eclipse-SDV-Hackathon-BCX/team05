package team05.mw.truck;

import team05.mw.ecal.EcalMessage;

import java.util.List;

public interface TruckService {

    List<Truck> list();
    void updateTruck(EcalMessage ecalMessage);
    void detectStation(Truck truck);
    void enterStation(Truck truck);
    void leaveStation(Truck truck);

}
