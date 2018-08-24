package com.nixbyte.project.utils.responseType;

import java.util.List;

/**
 * Created by scapegoat on 18/05/2018.
 */

public class RouteResponse {
    public List<Route> routes;

    public String getPoints() {
        return this.routes.get(0).overview_polyline.points;
    }

    class Route {
        OverviewPolyline overview_polyline;
    }

    class OverviewPolyline {
        String points;
    }
}
