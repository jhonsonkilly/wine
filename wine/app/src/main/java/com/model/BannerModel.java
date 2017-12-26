package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/11/14.
 */

public class BannerModel implements Serializable {
    public String code;
    public String message;
    public List<BannerData> result;

    public class BannerData implements Serializable {
        public String id;
        public String finishTime;
        public String guid;
        public String img;
    }
}
