package com.iview;

import com.androidyuan.frame.base.view.IBaseCommView;
import com.model.LeftClassifyModel;
import com.model.RightClassifyModel;

import java.util.List;

/**
 * Created by mac on 2017/10/29.
 */

public interface ILeftClassifyView extends IBaseCommView {


    void showData(List<LeftClassifyModel.Data> list,boolean isHorList);

    void showRightData(List<RightClassifyModel.Data> list);

}
