package team05.mw.rp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
@Slf4j
@Startup
public class MqttReceiver {

    private final ObjectMapper objectMapper;

    public MqttReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Incoming("ecal")
    public CompletionStage<Void> consume(Message<EcalMessage> ecalMessage) {
        log.info("received message: {}", ecalMessage.getMetadata());
        log.info("{}", messageToJson(ecalMessage));
        return ecalMessage.ack();
    }

    private String messageToJson(Message<EcalMessage> ecalMessage) {
        try {
            return objectMapper.writeValueAsString(ecalMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

