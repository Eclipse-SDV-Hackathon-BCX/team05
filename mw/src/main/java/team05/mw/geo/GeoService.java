package team05.mw.geo;

import io.quarkus.runtime.Startup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import team05.mw.MwConfig;
import team05.mw.common.Coordinates;
import team05.mw.station.Station;
import team05.mw.station.StationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@ApplicationScoped
@Startup
public class GeoService {

    private final MwConfig config;
    private final StationService stationService;
    private ArrayList<Roadhouse> roadhouses;

    public GeoService(MwConfig config, StationService stationService) {
        this.config = config;
        this.stationService = stationService;
    }

    public Optional<Station> isInRoadhouse(Coordinates coordinates){
        double[] position = new double[]{coordinates.getLng(), coordinates.getLat()};
        String name = null;
        for(Roadhouse roadhouse: roadhouses){
            if(roadhouse.pointInarea(position)){
                name = roadhouse.name;
            }
        }
        return stationService.findByName(name);
    }
    @PostConstruct
    public void setup() {
        roadhouses = new ArrayList<>();
        String json = config.raststaettenJson();

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
