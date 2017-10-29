package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/10/29.
 */

public class RightClassifyModel implements Serializable {
    public String code;

    public String message;
    public List<Data> result;

    public class Data implements Serializable {
        public List<Products> products;
        public String name;

        public String pic;

        public class Products implements Serializable {
            public String name;
            public String image;
        }
    }

}
