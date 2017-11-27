package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/11/16.
 */

public class QiangGouModel implements Serializable {
    public String code;
    public String message;
    public List<QiangGouData> result;

    public static class QiangGouData implements Serializable {
        public String endTime;
        public Goods goods;

        public static class Goods implements Serializable {
            public String name;
            public String id;
            public String image;

            public String price;
        }
    }
}
