package com.example.franciscoandrade.mtastatus.model;

import java.util.List;

/**
 * Created by bobbybah on 2/6/18.
 */

public class MTA_Stations {

    List <result> results;

    public List<result> getResults() {
        return results;
    }

    public class result {
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
