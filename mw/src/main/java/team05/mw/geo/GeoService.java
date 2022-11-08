package team05.mw.geo;

import io.quarkus.runtime.Startup;
import team05.mw.MwConfig;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
public class GeoService {

    private final MwConfig config;

    public GeoService(MwConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void setup() {
        String json = config.raststaettenJson();
    }

}
