package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

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
public class AliPayReqMsg extends RequestMsg {
    @Override
    public String getUrl() {

        return Urls.m_AliPayMes;
    }

    public AliPayReqMsg(Map<String,String> map) {

        super();
        params.putAll(map);

    }
}
