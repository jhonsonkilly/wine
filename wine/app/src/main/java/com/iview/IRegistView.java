package com.iview;

import com.androidyuan.frame.base.view.IBaseCommView;
import com.model.PersonalModel;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.iview</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public interface IRegistView extends IBaseCommView {

    void showResult(String result);

    void showRegist(String mes);

    void showPersonal(PersonalModel.PersonalResult result);

}
