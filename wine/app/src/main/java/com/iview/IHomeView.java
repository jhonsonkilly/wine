package com.iview;

import com.androidyuan.frame.base.view.IBaseCommView;
import com.model.BannerModel;
import com.model.LeftClassifyModel;

import java.util.List;

/**
 * Created by mac on 2017/11/14.
 */

public interface IHomeView extends IBaseCommView {

    void showBannerList(List<BannerModel.BannerData> list);
}
