package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/10/29.
 */

public class LeftClassifyModel implements Serializable {
   public String code;
    public String message;
    public List<Data> result;

    public class  Data implements Serializable{
      public String name;

        public String id;
        public String guid;




    }
}
