package team05.mw.rp;

import java.util.List;

public interface TruckService {

    List<Truck> list();
    void updateTruck(EcalMessage ecalMessage);
    void detectStation(Truck truck);
    void enterStation(Truck truck);
    void leaveStation(Truck truck);

}
