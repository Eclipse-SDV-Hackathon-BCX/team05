package team05.mw.truck;

import team05.mw.ecal.EcalMessage;

import java.util.List;
import java.util.Optional;

public interface TruckService {

    List<Truck> list();

    Optional<Truck> findById(String truckId);

    void updateTruck(EcalMessage ecalMessage);

}
