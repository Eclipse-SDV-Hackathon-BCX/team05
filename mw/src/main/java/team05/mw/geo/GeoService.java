package team05.mw.geo;

import io.quarkus.runtime.Startup;
import team05.mw.MwConfig;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
@Startup
public class GeoService {

    private final MwConfig config;

    public GeoService(MwConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void setup() {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (config.raststaetten().asInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String json = textBuilder.toString();
    }

}
