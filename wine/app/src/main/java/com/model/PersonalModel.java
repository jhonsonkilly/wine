package com.model;

import java.io.Serializable;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.model</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class PersonalModel implements Serializable {
    public String code;
    public String message;
    public PersonalResult result;

    public static class PersonalResult {
        public String id;
        public String guid;
        public String wine;
        public String img;
        public int grade;
        public String nick;
        public String phone;
        public String sex;


    }
}
