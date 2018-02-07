package com.example.franciscoandrade.mtastatus.model;

import java.util.List;

/**
 * Created by bobbybah on 2/6/18.
 */

public class MTA_Stations {

    List <Results> result;

    public List<Results> getResult() {
        return result;
    }

    public class Results {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


}
