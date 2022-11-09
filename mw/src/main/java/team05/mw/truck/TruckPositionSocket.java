package team05.mw.truck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;
import team05.mw.common.Coordinates;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@ServerEndpoint("/truck/{truckId}/position")
@Slf4j
public class TruckPositionSocket {

    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    public TruckPositionSocket(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("truckId") String truckId) {
        sessions.put(truckId, session);
        log.info("truck #{} position tracking stopped", truckId);
    }

    @OnClose
    public void onClose(Session session, @PathParam("truckId") String truckId) {
        sessions.remove(truckId);
        log.info("truck #{} position tracking stopped", truckId);
    }

    @OnError
    public void onError(Session session, @PathParam("truckId") String truckId, Throwable throwable) {
        sessions.remove(truckId);
        log.info("truck #{} position tracking stopped with error", truckId);
    }

    @ConsumeEvent(value = "truck-position-update")
    public void onPositionUpdate(Truck truck) {
        if (truck == null) {
            return;
        }
        Session session = sessions.get(truck.getId());
        if (session == null) {
            return;
        }
        session.getAsyncRemote()
                .sendObject(encodePosition(truck.getPosition()), result -> {
                    if (result.getException() != null) {
                        log.error("unable to send message: " + result.getException());
                    }
                });
    }

    private String encodePosition(Coordinates coordinates) {
        try {
            return objectMapper.writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
