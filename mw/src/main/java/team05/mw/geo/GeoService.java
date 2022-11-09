package team05.mw.geo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.quarkus.runtime.Startup;
import team05.mw.MwConfig;

@ApplicationScoped
@Startup
public class GeoService {

    private final MwConfig config;
    private ArrayList<Roadhouse> roadhouses;

    public GeoService(MwConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void setup() {
        roadhouses = new ArrayList<>();

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(config.raststaetten().asInputStream(),
                Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String json = textBuilder.toString();

        JSONParser parser = new JSONParser();
        JSONObject root;
        try {
            root = (JSONObject) parser.parse(json);
            JSONArray ja = (JSONArray) root.get("features");
            Iterator<JSONObject> iterator = ja.iterator();
            while (iterator.hasNext()) {
                JSONObject feature = iterator.next();
                roadhouses.add(new Roadhouse(feature));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}