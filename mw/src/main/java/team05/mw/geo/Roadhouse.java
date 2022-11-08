package team05.mw.geo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Roadhouse {
    double[][] area;
    String name;

    Roadhouse(JSONObject feature){
        JSONObject geometry = (JSONObject) feature.get("geometry");
        JSONArray coordinates = (JSONArray) geometry.get("coordinates");
        JSONArray coordinate = (JSONArray) coordinates.get(0);
        area = new double[coordinate.size()][2];
        for(int j=0;j<coordinate.size();j++){
            JSONArray point = (JSONArray) coordinate.get(j);
            double x = (double) point.get(0);
            double y = (double) point.get(1);
            double[] a = new double[]{x,y};
            area[j] = a;
        }
        name = ((JSONObject) feature.get("properties")).get("name").toString();
    }

    public boolean pointInarea(double[] point) {
        boolean odd = false;
        for (int i = 0, j = area.length - 1; i < area.length; i++) {
            if (((area[i][1] > point[1]) != (area[j][1] > point[1]))
                    && (point[0] < (area[j][0] - area[i][0]) * (point[1] - area[i][1])
                            / (area[j][1] - area[i][1]) + area[i][0])) {
                odd = !odd;
            }
            j = i;
        }
        return odd;
    }
}
