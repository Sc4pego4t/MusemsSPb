package com.nixbyte.project.utils.responseType;

/**
 * Created by scapegoat on 10/04/2018.
 */

public class IntAndArrayResponse {
    private String rate;
    private String[] reviews;
    private String liked;

    public String getLiked() {
        return liked;
    }

    public String getRate() {
        return rate;
    }

    public String[] getReviews() {
        return reviews;
    }
}
