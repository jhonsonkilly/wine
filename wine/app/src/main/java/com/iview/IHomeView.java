package com.iview;

import com.androidyuan.frame.base.view.IBaseCommView;
import com.model.BannerModel;
import com.model.HorlistModel;
import com.model.JingXuanModel;
import com.model.LeftClassifyModel;
import com.model.ProductModel;
import com.model.QiangGouModel;

import java.util.List;

/**
 * Created by mac on 2017/11/14.
 */

public interface IHomeView extends IBaseCommView {

    void showBannerList(List<BannerModel.BannerData> list);


    void showHorList(List<HorlistModel.HorData> list);

    void showQiangGouList(List<QiangGouModel.QiangGouData> list);

    void showJingXuanList(List<JingXuanModel.Data> list);

    void showProductList(List<ProductModel.Result> list);
}
