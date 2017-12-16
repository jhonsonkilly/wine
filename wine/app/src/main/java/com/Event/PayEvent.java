package com.Event;

import java.util.Map;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.Event</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class PayEvent {

    public Map<String, String> map;

    public String payResult;
    //区分种类
    public String type;

    public boolean isParams() {
        return type.equals(PayType.PARAMS);
    }

    public boolean isResult(){

        return type.equals(PayType.RESULT);
    }

    public static class PayType {

        public static final String PARAMS = "params";
        public static final String RESULT = "result";
    }
}
