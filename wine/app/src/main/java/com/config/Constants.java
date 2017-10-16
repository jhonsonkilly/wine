package com.config;

import android.os.Environment;



import java.io.File;

public class Constants {
    //    public static final String BASEURL = "http://dsapp.dev.odianyun.local";
    public static final String BASEURL = "";
//    public static final String BASEURL = "http://p2ptest.oudianyun.com";

    public static final String AREA_CODE = "areaCode";
    public static final String AREA_CODE_ADDRESS = "areaCode_address";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String AREA_NAME = "area_name";

    //阿里百川
    public static final String APP_KEY = "app_key";
    public static final String BC_USER_ID = "bc_userId";
    public static final String RECEIVER_ID = "bc_receiveId";
    public static final String BC_PASS = "bc_password";

    //商品详情中的商品推荐接口---德生
    public static final String RECOMMENDPRODUT = BASEURL + "/api/search/brandRecommendProduct";//track
    public static final String ISSUE_FIRSTLOGIN_COUPON = BASEURL + "/api/promotion/coupon/issueFirstLoginCoupon";//首次登陆送券
    public static final String UPLOADING_PHOTOS = BASEURL + "/api/fileUpload/putObjectWithForm.do";//单张图片上传
    public static final String AFTERSALE_CAUSE_LIST = BASEURL + "/api/my/orderAfterSale/afterSaleCauseList";//获取退款原因列表
    public static final String INIT_APPLY_REFUND = BASEURL + "/api/my/orderAfterSale/initApplyRefund";//初始化申请退款
    public static final String INIT_RETURN_PRODUCT = BASEURL + "/api/my/orderAfterSale/initReturnProduct";//初始化申请退货
    public static final String AFTERSALELIST = BASEURL + "/api/my/orderAfterSale/afterSaleList";// 获取退货款详情列表
    public static final String RETURN_UPDATA = BASEURL + "/api/my/orderAfterSale/updateReturnProduct";//修改申请
    public static final String RETURN_PRODUCT = BASEURL + "/api/my/orderAfterSale/applyReturnProduct";//申请退货
    public static final String RETURN_REFUND = BASEURL + "/api/my/orderAfterSale/applyRefund";//申请退款
    public static final String SERVICE_AGREEMENT = BASEURL + "/api/static/pages/registerAgreement.html";//服务协议
    public static final String AFTER_SALE_TYPE = BASEURL + "/api/my/orderAfterSale/afterSaleType";//售后类型列表
    public static final String AFTER_SALE_CHANGE_PRODUCT = BASEURL + "/api/my/orderAfterSale/afterSaleProduct";//换货商品


    public static final String APP_UPGRADE = BASEURL + "/api/app/upgrade";//从检测是否需要版本更新

    /**
     * 照片存储地址
     */
    public static final String IMAGEPATH = Environment.getExternalStorageDirectory() + File.separator + "ody"
            + File.separator + "p2p" + File.separator;

    /**
     * 用户头像地址
     */
    public static final String USER_IMAGEURL = "user_image_path";
    /**
     * 用户本地头像地址
     */
    public static final String USER_IMAGE = "user_image";
    //首次登陆送券的tag
    public static final String TO_SEND_STAMPS_TAG = "To_send_stamps_tag";
    //是否是第一次打开app
    public static final String IS_FRIST_OPEAN_APP = "is_first_opean_app";

    //登录时的ut
    public static final String USER_LOGIN_UT = "token";
    //用户头像
    public static final String HEAD_PIC_URL = "headPicUrl";
    //昵称
    public static final String NICK_NAME = "nickname";

    public static final String DISTRIBUTOR_ID = "distributorId";

    //是否登录的状态值
    public static final String LOGIN_STATE = "loginState";
    //悠点卡是否开卡
    public static final String IS_YCARD = "y_card";
    //登录时保存的手机号
    public static final String LOGIN_MOBILE_PHONE = "loginPhone";
    //登录时保存的手机号
    public static final String LOGIN_USER_ID = "loginUserId";
    //注册第一步存下的手机号
    public static final String REGISTER_MOBILE_PHONE = "registerPhone";
    //忘记密码第一步存下的手机号
    public static final String FORGET_MOBILE_PHONE = "forgetPhone";
    //没有注册直接短信登录时,相当于注册流程,此时存下的手机号
    public static final String SMS_REGISTER_MOBILE_PHONE = "smsRegisterPhone";
    //店铺id
    public static final String SHARE_CODE = "shareCode";

    public static final String INVITE_CODE = "inviteCode";
    //当前用户是否是分销商
    public static final String USER_IS_DISTRIBUTOR = "USER_IS_DISTRIBUTOR";

    //商品id
    public static final String SP_ID = "sp_id";
    public static final String H5_URL = "h5_url";
    public static final String BUY_TYPE = "isQuickpurchase";
    public static final String PRODUCT_JUMP_TYPE = "product_jump_type";
    //美颜效果的开启状态

    //搜索
    public static final String SEARCH_KEY = "keyword";
    public static final String NAVCATEGORY_NAME = "navCategoryNames";
    public static final String NAVCATEGORY_ID = "navCategoryIds";
    public static final String PRODUCT_JSON = "productJson";

    //pay
    public static final String ORDER_ID = "order_id";
    public static final String USER_ID = "user_id";
    public static final String ORDER_MONEY = "order_money";
    public static final String ORDER_DELIVERYfEE = "order_deliveryFee";
    public static final String MERCHANT_ID = "merchantId";
    public static final String CART_NUMBER = "num";

    public static final String PAY_LIST = "pay_list";


    //订单
    public static final String ADDRESS_ID = "address_id";

    //主界面
    public static final String GO_MAIN = "choose_item";
    //
    public static final String PULLURL = "pullUrl";
    //直播界面点击头像弹出的弹窗里的关注数和粉丝数
    //直播界面点击头像弹出的弹窗里的发布数量

    //消息的数量
    public static final String MSG_COUNT = "msg_count";
    //去凑单的活动id
    public static final String PRO_ID = "promotionid";

    /**
     * 登录注册,忘记密码,修改手机号,短信登录
     */
    //校验手机号是否已经注册
    public static final String IS_REPEAT_PHONE = BASEURL + "/ouser-web/mobileRegister/isRepeatPhoneForm.do";
    //手机号换绑
    public static final String SEND_SMS_CAPTCHA_TO_BINDED_MOBILE = BASEURL + "/ouser-web/mobileRegister/sendSmsCaptchaToBindedMobile.do";
    //获取验证码
    public static final String GET_CAPTCHA = BASEURL + "/ouser-web/mobileRegister/sendCaptchasForm.do";
    //图形验证码
    public static final String CHECK_IMAGGE = BASEURL + "/ouser-web/mobileRegister/checkImageForm.do";
    //注册时校验验证码
    public static final String CHECK_CAPTCHA = BASEURL + "/ouser-web/mobileRegister/checkCaptchasForm.do";
    //注册(第二步)
    public static final String REGISTER_SECOND = BASEURL + "/ouser-web/memberRegisterForm.do";
    public static final String BUNDLE_ALIAS = BASEURL + "/api/social/vl/device/bundleAlias";//绑定设备

    //登录
    public static final String LOGIN = BASEURL + "/ouser-web/mobileLogin/loginForm.do";
    public static final String UNBUNDLE_ALIAS = BASEURL + "/api/social/vl/device/unbundleAlias";//解绑设备
    //退出登录
    public static final String EXIT_ACCOUNT = BASEURL + "/ouser-web/mobileLogin/exit.do";
    //修改手机号界面二---绑定手机号
    public static final String BIND_PHONE = BASEURL + "/ouser-web/mobileRegister/checkMobileAndModifyMobileForm.do";
    //修改手机号界面二---首次联合登录成功后,要绑定手机号
    public static final String UNION_BIND_PHONE = BASEURL + "/api/my/user/bindMobile";
    //修改密码-----接口联调响应码200,但是没有数据返回,且实际密码修改也确实不成功
    public static final String MODIFY_PSD = BASEURL + "/ouser-web/mobileRegister/modifyPasswordForm.do";
    //获取第三方登录的logo------------暂无接口
    public static final String THIRD_LOGIN_LOGO = BASEURL + "/api/login/thirdLogo.do";
    //修改密码
    public static final String MODIFY_PSD_CONFIRM = BASEURL + "/ouser-web/mobileRegister/modifyPasswordOnlineForm.do";
    //联合登录
    public static final String UNION_LOGIN = BASEURL + "/ouser-web/unionLogin/login.do";
    //德升  首次联合登录成功后  添加密码
    public static final String UNION_ADD_PSD = BASEURL + "/ouser-web/unionLogin/bindUserPassword.do";
    //联合登录成功后接口返回的id
    public static final String UNION_ID = "id";
    //联合登录成功后接口返回的ut  不一定能当作登录成功的ut,因为当用户在联合登录成功后的绑定手机界面直接返回的话,算是没有登录成功的
    public static final String UNION_UT = "ut";

    public static final String BIND_THIRD_PLATFORM = BASEURL + "/ouser-web/unionLogin/bindThirdPlatform.do";

    /**
     * 搜索
     */
    //搜索  热搜词
    public static final String SEARCH_HOT_WORD = BASEURL + "/api/search/searchHotWord";
    //德升 默认搜索词,积分规则,用户注册协议都是广告配置,路径变为下面的 参数加上  params.put("platform", "3");//获取专题列表
    public static final String AD_LIST_NEW = BASEURL + "/api/dolphin/list";
    //根据输入的搜索关键字获取包含该关键字的list--------暂无法联调
    public static final String SEARCH_KEYWORD_LIST = BASEURL + "/api/search/auto";
    //搜索  历史
    public static final String SEARCH_HISTORY = BASEURL + "/api/search/searchHistoryList";
    //搜索  清除搜索历史
    public static final String CLEAN_SEARCH_HISTORY = BASEURL + "/api/search/cleanSearchHistory";


    /**
     * 购物车
     */
    public static final String SHOP_CART = BASEURL + "/api/cart/list";// 购物车列表
    public static final String EDIT_CART_NUM = BASEURL + "/api/cart/editItemNum";//编辑数量
    public static final String DELEDCT_PRODUCT = BASEURL + "/api/cart/removeItem";//删除
    public static final String DELEDCT_CLEAR = BASEURL + "/api/cart/clear";//清空购物车
    public static final String DELEDCT_CLEARFAILURE = BASEURL + "/api/cart/clearFailure";//清除失效品
    public static final String SHOPCART_RECOMMEND = BASEURL + "/api/read/product/recommendProductList";//购物车猜你喜欢

    public static final String CHECKED_ITEM_PRODUCT = BASEURL + "/api/cart/editItemCheck";//选中
    public static final String PROPERTY_PRODUCT = BASEURL + "/api/product/serialProducts.do";//系列属性
    public static final String CART_GIFDETAIL = BASEURL + "/api/cart/minSkuDetail";//赠品的系列属性

    public static final String CART_UPDATEGIFT = BASEURL + "/api/cart/updateGift";//更换赠品
    public static final String CART_UPDATEPRODUCT = BASEURL + "/api/cart/updateProduct";//更换商品的系列属性
    public static final String PROPERTY_RECOMMED = BASEURL + "/api/recommend.do";//猜你喜欢
    public static final String PROPERTY_GET_COUPON = BASEURL + "/api/getCoupon.do";//领券
    public static final String ORDER_INFO = BASEURL + "/api/my/order/detail";// 订单详情
    public static final String PREPARE_CHECKOUT = BASEURL + "/api/cart/prepareCheckout";//更换赠品

    public static final String AFTERSALEDETAILS = BASEURL + "/api/my/orderAfterSale/afterSaleDetails";// 订单详情
    public static final String CANCEL_RETURNP_RODUCT = BASEURL + "/api/my/orderAfterSale/cancelReturnProduct";// 撤销退货申请
    public static final String SAVE_COURIERNO = BASEURL + "/api/my/orderAfterSale/saveCourierNo";// 录入退货的运单号
    public static final String CANCEL_ORDER = BASEURL + "/api/my/order/cancel";// 取消订单
    public static final String ORDER_LOGSITICS = BASEURL + "/api/my/order/newOrderMessage";// 物流
    public static final String USER_SUMMARY = BASEURL + "/api/my/order/summary";// 用户信息角标

    //    public static final String USER_INFO = BASEURL + "/ouser-web/user/getUserForm.do";// 用户信息
    public static final String USER_INFO = BASEURL + "/api/my/user/info";// 用户注册信息(以后用这个)

    public static final String DELETE_SELECTED = BASEURL + "/api/cart/removeItemBatch";// 批量删除购物车商品
    public static final String ADDALL_FAVORITE = BASEURL + "/api/cart/batchFavorite";// 购物车批量收藏
    public static final String CART_EXT = BASEURL + "/api/cart/ext";// 购物车去凑单
    public static final String PROMOTION_DETAIL = BASEURL + "/api/product/promotionDetail";// 购物车去凑单

    public static final String CONFIRM_RECEIVE = BASEURL + "/api/my/order/confirmReceived";//确认收货
    public static final String SUBMIT_ORDER = BASEURL + "/api/checkout/submitOrder";//提交订单
    public static final String INIT_ORDER = BASEURL + "/api/checkout/initOrder";
    public static final String SHOW_ORDER = BASEURL + "/api/checkout/showOrder";
    public static final String OEDER_LIST = BASEURL + "/api/my/order/list";//订单页;
    public static final String QUICK_PURCHASE = BASEURL + "/api/checkout/quickPurchase";//快付
    public static final String SAVE_ADDRESS = BASEURL + "/api/checkout/saveReceiver";//结算页保存地址
    public static final String COUPON_LIST = BASEURL + "/api/my/coupon";//优惠券列表
    public static final String COUPON_COUNT = BASEURL + "/api/my/coupon/count";//优惠券数量
    public static final String USE_COUPON = BASEURL + "/api/checkout/getCoupons";//使用优惠券列表
    public static final String SAVE_COUPON = BASEURL + "/api/checkout/saveCoupon";//保存优惠券
    public static final String BIND_COUPON = BASEURL + "/api/my/coupon/bindCoupon";//绑定优惠券
    public static final String IS_AFTER_SALE = BASEURL + "/api/my/orderAfterSale/isAfterSale";//判断是否可以售后
    public static final String SAVE_BROKERAGE = BASEURL + "/api/checkout/saveBrokerage";//保存佣金
    public static final String SAVE_POINTS = BASEURL + "/api/checkout/savePoints";//保存积分
    public static final String SAVE_PAYMENT = BASEURL + "/api/checkout/savePayment";//保存支付方式
    public static final String DELETE_ORDER = BASEURL + "/api/my/order/delete";//删除订单
    public static final String SAVE_REMARK = BASEURL + "/api/checkout/saveRemark";//保存订单商家备注
    public static final String AFTERSALE_LOGISTICS = BASEURL + "/api/my/orderAfterSale/delivery";//换货后，卖家发货物流信息
    public static final String AFTERSALE_CONFIRM_REEIVE = BASEURL + "/api/my/orderAfterSale/confirm";//换货确认收货
    public static final String LOGISTC_COMPANY_LIST = BASEURL + "/api/my/orderAfterSale/logistics";//物流公司列表
    public static final String GIFT_CARD_LIST = BASEURL + "/api/my/giftCard";//礼金卡列表
    public static final String BIND_GIFTCARD = BASEURL + "/api/my/bindGiftCard";//绑定礼金卡
    public static final String GET_GIFTCARD = BASEURL + "/api/checkout/getGiftCards";//使用礼金卡列表
    public static final String SAVE_GIFECARD = BASEURL + "/api/checkout/saveGiftCardSelect";//保存礼金卡
    public static final String GIFTCARD_CONSUMER_DETAIL = BASEURL + "/api/my/giftCardDetail";//礼金卡交易明细
    public static final String SUBMIT_ADDTIONAL_EVALUATE = BASEURL + "/api/social/my/comment/addSave";//追加评论提交
    public static final String INIT_ADDTIONAL_EVALUATE = BASEURL + "/api/social/my/comment/addInit";//初始化追加评论
    public static final String SAVE_DELIVERY_MODE = BASEURL + "/api/checkout/saveDeliveryMode";//保存配送方式
    public static final String STORE_LIST = BASEURL + "/api/read/merchant/queryBaseMerchantList";//查询门店列表

    public static final String SHOP_ORDER_LOGISTICS = BASEURL + "/api/seller/order/logistics";//店铺订单查看物流
    public static final String INVOICE_INFO = BASEURL + "/api/my/showVATInvoice";//查询增值税发票信息
    public static final String SAVE_EDIAN = BASEURL + "/api/checkout/saveECard";//保存伊点卡
    public static final String SAVE_UDIAN = BASEURL + "/api/checkout/saveUCard";//保存悠点卡


    /**
     * 直播
     */
    //热门里的信息·
    public static final String RE_MEN = BASEURL + "/api/social/vl/videolive/listdetail";//listdetail有获取热度信息,而且是按照热度排行的
    //关注的直播列表
    public static final String ATTENTION_VIDEO_LIST = BASEURL + "/api/social/vl/videolive/followvideos";//listdetail有获取热度信息,而且是按照热度排行的
    //进入聊天室的时候要先登录
    public static final String ENTER_CHATROOM = BASEURL + "/api/social/vl/imuser/info";
    //进入聊天室的时候要先登录
    public static final String ENTER_VIDEOLIVE = BASEURL + "/api/social/vl/videolive/enter";
    //关闭直播室--主播视角
    public static final String CLOSE_VIDEOLIVE = BASEURL + "/api/social/vl/videolive/close";
    //退出直播室---观众视角
    public static final String QUIT_VIDEOLIVE = BASEURL + "/api/social/vl//fan/quit";
    //直播界面点击顶部的关注按钮
    public static final String ADD_FOLLOW = BASEURL + "/api/social/vl/fan/follow";
    //获取直播界面是否关注了该主播
    public static final String GET_FOLLOW_STATUS = BASEURL + "/api/social/vl/fan/isfollow";
    //直播间里点击粉丝热度跳转到的粉丝排行榜界面
    public static final String FANS_RANKING_VIDEO = BASEURL + "/api/social/vl/fan/pointlist";
    //直播间里点击粉丝热度跳转到的粉丝排行榜界面
    public static final String USER_SUMPOINT = BASEURL + "/api/social/vl/fan/usersumpoint";
    //粉丝热度排行榜
    public static final String FANS_RANKING = BASEURL + "/api/social/vl/fan/list";
    //粉丝热度排行榜2
    public static final String FANS_RANKING2 = BASEURL + "/api/video/fansRanking2.do";
    //播发界面里的粉丝头像列表
    public static final String FANS_PHOTOS = BASEURL + "/api/video/fansPhotos.do";
    //获取直播间详情
    public static final String LIVE_BROADCAST_DETAIL = BASEURL + "/api/social/vl/videolive/detail";
    //直播详情页---获取热度最高的N个粉丝头像
    public static final String LIVE_HOT_FANS_PHOTO = BASEURL + "/api/social/vl/fan/vlpointlist ";
    //粉丝列表
    public static final String FANS_LIST = BASEURL + "/api/social/vl/fan/list";
    //主播推荐商品列表、观众视角
    public static final String RECOMMED_PRODUT = BASEURL + "/api/social/vl/mp/list";
    //主播推荐商品列表、主播视角
    public static final String RECOMMED_PRODUTUPDATE = BASEURL + "/api/social/vl/mp/update";

    //收藏列表
    public static final String USER_GOODS = BASEURL + "/api/my/favorite/goods";
    //文章收藏列表
    public static final String USER_ARTICLELIST = BASEURL + "/api/my/favorite/articleList";
    //足迹列表
    public static final String HISTORY_LIST = BASEURL + "/api/my/foot/list";
    //添加足迹
    public static final String HISTORY_ADD = BASEURL + "/api/my/foot/update";
    //同步足迹
    public static final String HISTORY_SYN = BASEURL + "/api/my/foot/syn";
    //删除足迹
    public static final String HISTORY_DELETE = BASEURL + "/api/my/foot/delete";

    //直播界面点击头像弹出的弹窗里的基本信息
    public static final String BASE_INFO = BASEURL + "/api/social/vl/user/info";
    //美颜效果的开启状态
    public static final String MEIYAN_STATUS = "meiyan";
    //直播界面点击头像弹出的弹窗里的关注数和粉丝数
    public static final String FANS_ATTENTION_NUM = BASEURL + "/api/social/vl/fan/count";
    //直播界面点击头像弹出的弹窗里的发布数量
    public static final String RELEASE_NUM = BASEURL + "/api/social/vl/videolive/mylist";

    public static final String USER_CLEAR = BASEURL + "/api/my/favorite/clear";
    //进创建直播的时候要先发送请求，获取上次直播信息
    public static final String LAST_VIDEOLIVE = BASEURL + "/api/social/vl/videolive/lastlive";


    /*
    * 创建直播室，上传头像，搜索，收藏夹，已购买
    * */
    public static final String CREATE_LIVE = BASEURL + "/api/social/vl/videolive/create";//创建直播接口
    public static final String PUT_OBJECT_WITH_FORM = BASEURL + "/api/fileUpload/putObjectWithForm.do";//上传图片的接口
    public static final String ADDRESS = BASEURL + "/api/location/list/";//获取地址的接口

    public static final String FAVORITE_SHOP = BASEURL + "/api/my/favorite/goods";//收藏夹列表

    public static final String PURCHASED_SHOP = BASEURL + "/api/my/purchased/goodsList";//已购买列表
    public static final String UPDATE_SHOP = BASEURL + "/api/social/vl/mp/update";//更新推荐的商品列表
    /*地址管理*/
    public static final String ADDRESS_LIST = BASEURL + "/ouser-web/address/getAllAddressForm.do";//获取地址的列表
    public static final String DELETE_ADDRESS = BASEURL + "/ouser-web/address/deleteAddressForm.do";//删除收货地址
    public static final String EDIT_ADDRESS = BASEURL + "/ouser-web/address/updateAddressForm.do";//编辑收货地址
    public static final String ADD_ADDRESS = BASEURL + "/ouser-web/address/addAddressForm.do";//添加收货地址
    public static final String DELIVERY_TIME = BASEURL + "/api/product/deliveryTime";//添加收货地址


    //搜索结果
    public static final String GET_SEARCH_LIST = BASEURL + "/api/search/searchList";
    //添加商品到购物车
    public static final String ADD_TO_CART = BASEURL + "/api/cart/addItem";
    public static final String SAVE_INVOICE = BASEURL + "/api/checkout/saveOrderInvoice";

    //---------------------------------------------商品详情
    // 获取全部分类数据
    public static final String CATEGORY_LIST = BASEURL + "/api/category/list";
    //商品详情
    public static final String PRODUCT_INFO = BASEURL + "/api/product/baseInfo";
    public static final String PRODUCT_INFO_ADDRESS_ID = "product_address_id";

    //店铺
    //店铺基本信息
    public static final String GET_SHOP_BASE_INFO = BASEURL + "/back-merchant-web/shop/baseInfo.do";
    //店铺活动
    public static final String GET_SHOP_ACTIVITY = BASEURL + "/api/promotion/merchantPromotionList";

    //商品运费
    public static final String PRODUCT_FREIGHT = BASEURL + "/api/product/distributions";
    //购物车数量
    public static final String PRODUCT_CARTCOUNT = BASEURL + "/api/cart/count";

    //推荐
//    public static final String PRODUCT_RECOMMMEND = BASEURL + "/api/product/hotlist";
    public static final String PRODUCT_RECOMMMEND = BASEURL + "/api/recommend.do";
    //收货人地址
    public static final String PRODUCT_THIRDADDRESS = BASEURL + "/api/product/thirdAddress.do";
    //评价
//    public static final String PRODUCT_APPRAISE = "http://p2p.odianyun.com/api/product/appraise.do";
    public static final String PRODUCT_APPRAISE = BASEURL + "/api/social/mpComment/get";

    //满减
    public static final String PRODUCT_Minu = BASEURL + "/api/category/minu.do";//活动
    //领券
    public static final String PRODUCT_GETCOUPON = BASEURL + "/api/getCoupon.do";
    //系列属性
    public static final String PRODUCT_SERIALPRRODUCES = BASEURL + "/api/product/serialProducts";
    //关联商品
    public static final String PRODUCT_ASSOCIATEPRODUCTS = BASEURL + "/api/product/associateProducts";
    /*取消收藏*/
    public static final String CLEAN_ALL_SHOU_CANG = BASEURL + "/api/my/favorite/clear";
    /*添加收藏*/
    public static final String SHOU_CANG = BASEURL + "/api/my/favorite/add";
    /*添加到购物车*/
    public static final String ADD_SHOPPING = BASEURL + "/api/cart/addItem";
    /*商品规格*/
    public static final String SHOPPING_GUIGE = BASEURL + "/api/product/spec";
    //商品促销
    public static final String PRODUCT_PROMOTIONINFO = BASEURL + "/api/product/promotionInfo";
    //商品实时价格
    public static final String PRODUCT_CURRENT_PRICE = BASEURL + "/api/realTime/getPriceStockList";
    //到货通知
    public static final String ATTENTION_NOTIFACTION = BASEURL + "/api/attention/attentionMerchantProduct";

    //    # 5、获取关注列表
    public static final String PEOPLE_LIST = BASEURL + "/api/social/vl/follow/list";
    //粉丝列表
    public static final String FANS_INFO = BASEURL + "/api/social/vl/fan/list";
    //关注
    public static final String FANS_FOLLOW = BASEURL + "/api/social/vl/fan/follow";
    //不关注
    public static final String FANS_ISNOTFOLLOW = BASEURL + "/api/social/vl/fan/cancel";
    //关闭直播的信息
    public static final String VIDEO_CLOSE = BASEURL + "/api/social/vl/videolive/close";
    //关闭直播的信息
    public static final String SHARE = BASEURL + "/api/social/vl/fan/share";
    //关闭直播的信息
    public static final String ADDGOODS = BASEURL + "/api/social/vl/videolive/addGoods";

    /*售后服务*/
    public static final String SHOUHOU_SERVICE = BASEURL + "/api/static/pages/returnAgreement.html";
    //设置
    //获取用户信息
    public static final String GET_USER_INFO = BASEURL + "/api/owner/my/getUserInfo";
    //获取用户信息
    public static final String UPLOAD_SINGLE_PHOTO = BASEURL + "/api/serving/fileUpload/putObjectWithForm.do";


    //支付
    public static final String GET_PAYWAY = BASEURL + "/api/payment/list";
    public static final String GET_PAYTYPE = BASEURL + "/api/checkout/getPayGateway";
    public static final String GET_PAYINFO = BASEURL + "/opay-web/createPay.do";
    public static final String GET_PAYINFO_PROMOTION = BASEURL + "/api/cashier/createPay";

    //直播个人主页
    public static final String USER_HOMEPAGE = BASEURL + "/api/social/vl/user/homepage";
    public static final String VEDIO_HISTORY = BASEURL + "/api/social/vl/videolive/mylist";

    public static final String LOSINGTAP = "this_finish";

    public static final String INIT_DATA = BASEURL + "/api/app/init";

    public static final String GET_ALIBC = BASEURL + "/search-backend-web/getTaoBaoOpenIM.json";

    public static final String SECONDKILL_LIST = BASEURL + "/api/promotion/secondkill/list";//获取秒杀列表

    public static final String MESSAGE_MSGLIST = BASEURL + "/api/social/vl/message/getMsgList";//消息列表
    public static final String MESSAGE_MSGSUMMARY = BASEURL + "/api/social/vl/message/getMsgSummary";//消息中心
    public static final String FEEDBACK_CREATE_COMPLAIN = BASEURL + "/api/social/live/complain/create";//反馈中心
    public static final String FEEDBACK_COMPLAIN_LIST = BASEURL + "/api/social/live/complain/list";//反馈列表
    public static final String FEEDBACK_COMPLAIN_DETAIL = BASEURL + "/api/social/live/complain/detail";//反馈详情
    public static final String DOLPHIN_ADLIST = BASEURL + "/view/h5/helpList";//帮助列表

    public static final String HOT_LIST = BASEURL + "/api/product/hotlist";//获取热门商品列表
    public static final String HEAD_LIST = BASEURL + "/cms/view/h5/headlinesList";//获取商品头条
    public static final String SHOP_INFO = BASEURL + "/api/seller/distributor/currDistributor";//获取店铺信息
    public static final String SHARE_INFO = BASEURL + "/api/share/shareInfo";//获取分享信息
    public static final String SHARE_IMG = BASEURL + "/api/share/shareImg";//获取多图分享信息
    public static final String SHARE_ARTICLE = BASEURL + "/api/social/live/article/share";//获取多图分享信息

    public static final String STORE_HOT_KEY = BASEURL + "/back-merchant-web/api/merchant/trendingKeywords.do";//店铺进入搜索，获取热门关键字


    public static final String INIT_EVALUATE = BASEURL + "/api/social/my/comment/init";//初始化商品评价
    public static final String COMMIT_EVALUATE = BASEURL + "/api/social/my/comment/save";//提交商品评价
    public static final String RECOMMEND_LIST = BASEURL + "/api/product/recoList";//获取商家推荐商品
    public static final String CHAT_ID = BASEURL + "/admin-web/getCustomerSiteInfo.json";//获取商家客服
    public static final String CART_NUM = BASEURL + "/api/cart/count";//获取购物车总数量
    public static final String GET_SYSTEM_TIME = BASEURL + "/api/realTime/getTimestamp";//获取购物车总数量
    public static final String GET_COLLECTION = BASEURL + "/api/my/favorite/checkFavorite";//是否收藏

    //登录
    public static final String GET_ALIAS = BASEURL + "/api/social/vl/message/getAlias";

    public static final String TRACK_UPLOAD = BASEURL + "/obi-track/info.do";//track
    public static final java.lang.String SEARCH_ORDER_LIST = BASEURL + "/api/my/order/search";

    public static final String ARC_COLLECT = BASEURL + "/api/my/favorite/add";//文章收藏

    public static final String GET_LUCKYLIST = BASEURL + "/api/promotion/freeorder/luckyList";//免单

    public static final String SCAN_CODE = BASEURL + "/api/scan/scanCode";//扫码购

    public static final String POINT_CARD_SEARCH = BASEURL + "/api/my/wallet/eInfo";//扫码购

    public static final String IS_NEW_USER = BASEURL + "/ouser-web/user/getUserExtByConditionsForm.do";//是否是新下单用户

    public static final String FIRST_LOGIN_COUPON = BASEURL + "/api/my/coupon";
    public static final String MY_WALLET = BASEURL + "/api/my/wallet/summary";//我的钱包
    public static final String LYF_SUPPORT_PAYMENT = BASEURL + "/api/cashier/lyfSupportPayment";//获取是否支持悠点卡、伊点卡支付权限

    public static final String YBALANCE = BASEURL + "/api/my/wallet/YBalance";//查询悠点卡余额

    public static final String LYF_ACCOUNT = BASEURL + "/api/my/point/account";//获取当前用户积分账户信息

    public static final String LYF_ACCOUNT_DETAILS_LIST = BASEURL + "/api/my/point/list";//获取当前用户的积分列表明细

    public static final String YRECORDLIST = BASEURL + "/api/my/wallet/YRecordList";
    public static final String GETCOUPONSHAREINFO = BASEURL + "/api/promotion/coupon/share";
    public static final String PAYBYCARD = BASEURL + "/api/cashier/payByCard";
    public static java.lang.String ECardList = BASEURL + "/api/my/wallet/ECardList";
    public static java.lang.String LyfCouponList = BASEURL + "/api/my/coupon";
    public static String BindECard = BASEURL + "/api/my/wallet/bindECard";

    public static final String EXCHANG_RULE = BASEURL + "/api/my/wallet/exchangeRule";//查询积分兑换伊豆规则

    public static final String EXCHANG = BASEURL + "/api/my/wallet/exchange";//兑换伊豆

        public static final String GET_SHOP_CATEGORY_ID=BASEURL+"/back-product-web/merchantProductAppAction/getMerchantAPPCateTree.do";//获取店铺分类ID
//    public static final String GET_SHOP_CATEGORY_ID = BASEURL + "/back-product-web/merchantProductAppAction/getCategoryTreeApp.do";//获取店铺分类ID

    public static final String GET_SHOP_CATEGORY_TREE = BASEURL + "/back-product-web/merchantProductAppAction/getCategoryTreeApp.do";//获取店铺类目树


    public static final String QIANGGOU = BASEURL  + "/api/promotion/secondkill/lyfTimeList";// 抢购

    public static final String QIANGGOUBUTTOM = BASEURL + "/api/promotion/secondkill/lyfKillList";// 抢购数据

    public static final String QIANGGOUADDCART = BASEURL + "/api/cart/addItem";// 加入购物车数据

    public static final String SAVENOTE = BASEURL + "/api/promotion/secondkill/saveNotice";// 加入提醒

    public static final String CANCELNOTE = BASEURL + "/api/promotion/secondkill/cancelNotice";// 取消提醒
}
