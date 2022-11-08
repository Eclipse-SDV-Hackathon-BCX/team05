package team05.mw.rp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class EcalMessageMapperTest {

    private final EcalMessageMapper messageMapper;

    EcalMessageMapperTest(EcalMessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Test
    public void testFromBytes() {
        String msg = "{\n" +
                "  \"uuid\": \"a593ff4c-562c-4f95-a5be-a6de91e13776\",\n" +
                "  \"acceleration\": 90,\n" +
                "  \"longitude\": 52.69095,\n" +
                "  \"latitude\": 13.00937\n" +
                "}";
        EcalMessage message = messageMapper.fromBytes(msg.getBytes());
        Assertions.assertNotNull(message);
        Assertions.assertEquals("a593ff4c-562c-4f95-a5be-a6de91e13776", message.getUuid());
    }
}
