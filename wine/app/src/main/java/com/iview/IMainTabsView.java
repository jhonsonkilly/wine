package com.iview;

import com.androidyuan.frame.base.view.IBaseCommView;
import com.model.PersonalModel;

/**
 * Created by mac on 18/1/10.
 */

public interface IMainTabsView extends IBaseCommView {

    void setData(PersonalModel.PersonalResult model);
}
