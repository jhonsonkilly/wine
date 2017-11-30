package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/11/16.
 */

public class JingXuanModel implements Serializable {
    public String code;
    public String message;
    public List<Data> result;

    public static class Data implements Serializable {
        public String img;
        public List<Goods> goods;

        public static class Goods implements Serializable {
            public String name;
            public String remark;
            public String image;
            public String price;
            public String guid;


        }
    }
}
