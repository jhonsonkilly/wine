package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/11/22.
 */

public class ProductModel implements Serializable {
    public String code;
    public String message;
    public List<Result> result;

    public static class Result implements Serializable {
        public String name;
        public String price;
        public String guid;
        public String image;
        public String salenum;
        public String proGuid;


    }
}
