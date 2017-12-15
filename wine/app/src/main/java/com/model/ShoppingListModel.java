package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.model</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class ShoppingListModel implements Serializable {
    public String code;
    public List<ShoppingResult> result;
    public String message;

    public static class ShoppingResult implements Serializable {
        public String id;
        public String goodGuid;
        public String goodName;
        public int quantity;
        public String image;
        public String price;
        public boolean isChoose = true;

        public String totalPrice;

        public boolean isChoose() {
            return isChoose;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }
    }

}
