package team05.mw.rp;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class EcalMessageProcessor {

    private final TruckService truckService;

    public EcalMessageProcessor(TruckService truckService) {
        this.truckService = truckService;
    }

    public void process(EcalMessage message) {
        truckService.updateTruck(message);
        if (detectStop(message)) {
            return;
        }
        if (detectStart(message)) {
            return;
        }
    }

    private boolean detectStart(EcalMessage message) {
        return false;
    }

    private boolean detectStop(EcalMessage message) {
        return false;
    }

}
