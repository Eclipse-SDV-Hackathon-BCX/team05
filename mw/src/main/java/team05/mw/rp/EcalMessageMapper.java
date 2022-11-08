package team05.mw.rp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class EcalMessageMapper {

    private final ObjectMapper objectMapper;

    public EcalMessageMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EcalMessage fromBytes(byte[] payload) {
        EcalMessage msg = null;
        try {
            msg = objectMapper.readValue(payload, EcalMessage.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return msg;
    }

}
