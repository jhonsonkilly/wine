package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/11/14.
 */

public class HorlistModel implements Serializable {
    public String code;
    public String message;
    public List<HorData> result;

    public class HorData implements Serializable {
        public String name;
        public String pic;
    }
}
