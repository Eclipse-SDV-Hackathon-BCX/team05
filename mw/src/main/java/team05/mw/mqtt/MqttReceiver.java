package team05.mw.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import team05.mw.ecal.EcalMessage;
import team05.mw.ecal.EcalMessageMapper;
import team05.mw.ecal.EcalMessageProcessor;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
@Slf4j
@Startup
public class MqttReceiver {

    private final ObjectMapper objectMapper;
    private final EcalMessageMapper messageMapper;
    private final EcalMessageProcessor messageProcessor;

    public MqttReceiver(ObjectMapper objectMapper, EcalMessageMapper messageMapper, EcalMessageProcessor messageProcessor) {
        this.objectMapper = objectMapper;
        this.messageMapper = messageMapper;
        this.messageProcessor = messageProcessor;
    }

    @Incoming("ecal")
    public CompletionStage<Void> consume(Message<byte[]> ecalMessage) {
//        log.info("received message: {}", ecalMessage.getMetadata());
        EcalMessage message = messageMapper.fromBytes(ecalMessage.getPayload());
//        log.info("{}", messageToJson(message));
        messageProcessor.process(message);
        return ecalMessage.ack();
    }

    private String messageToJson(EcalMessage ecalMessage) {
        try {
            return objectMapper.writeValueAsString(ecalMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

