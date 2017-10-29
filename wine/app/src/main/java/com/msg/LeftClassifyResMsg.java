package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.androidyuan.frame.cores.utils.FastJSONHelper;
import com.model.LeftClassifyModel;

/**
 * Created by mac on 2017/10/29.
 */

public class LeftClassifyResMsg extends ResponseMsg<LeftClassifyModel>{


    public LeftClassifyResMsg(int what) {
        super(what);
    }



    @Override
    protected int getSucCode() {
        return 0;
    }

    @Override
    public LeftClassifyModel convertData() {
        LeftClassifyModel model = null;
        return (LeftClassifyModel) FastJSONHelper.parseToObject(getResponse(), LeftClassifyModel.class);
    }
}
