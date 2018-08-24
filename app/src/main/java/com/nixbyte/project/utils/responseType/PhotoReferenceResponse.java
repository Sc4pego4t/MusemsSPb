package com.nixbyte.project.utils.responseType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scapegoat on 20/05/2018.
 */

public class PhotoReferenceResponse {
//    json:
//    {
//    results[
//      {
//          photos[
//              {
//                  photo_reference
//              }
//          ]
//      }
//    ]

    public List<Results> results;

    public String getPhotoReference(){
        return results.get(0).photos.get(0).photo_reference;
    }

    class Results {
        List<Photos> photos;

    }

    class Photos {
        String photo_reference;
    }
}
