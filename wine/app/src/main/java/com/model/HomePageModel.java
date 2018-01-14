package com.model;

import java.util.List;

/**
 * Created by mac on 18/1/13.
 */

public class HomePageModel {

    public List<String> rotate;
    public List<Cates> cates;

    public static class Cates {
        public String id;
        public String name;
        public String cover;
    }
}
