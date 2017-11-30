package com.msg;

import android.text.TextUtils;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.msg</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class RegistReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return Urls.m_register;
    }

    public RegistReqMsg(String phone, String vertify, String yaoqing) {
        super();
        Map<String, String> map = new HashMap<>();

        map.put("mobile", phone);
        map.put("verifyCode", vertify);
        if (!TextUtils.isEmpty(yaoqing)) {
            map.put("inviteCode", yaoqing);
        }
        put("p", map);
    }
}
