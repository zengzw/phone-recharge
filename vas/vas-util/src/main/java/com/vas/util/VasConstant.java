package com.vas.util;
/**
 * vas常量
 * @version 1.0.0 2016年7月29日<br>
 * @see 
 * @since JDK 1.7.0
 */
public interface VasConstant {

    /**
     * 供应商类型
     *
     * @author zengzw
     * @date 2016年8月9日
     */
    public class SupplierType{

        /**
         * 话费充值
         */

        public static final int  RECHARGE = 1;



        /**
         * 公共事业（水电煤）
         */

        public static final int UTILITIES = 2;



        /**
         * 汽车票
         */

        public static final int BUSTIKETS = 3;


        /**
         * 花车票
         */

        public static final int TRAINTIKETS = 4;

    }



    /**
     * 推送状态
     *
     * @author zengzw
     * @date 2016年8月10日
     */
    public class PushStatus{
        /**
         *  失败
         */

        public static final int ERROR = 0;


        /**
        * 成功
         */

        public static final int SUCESS = 1;


        /**
         * 未推送
         */

        public static final  int NOTSEND = 2;

    }



    /**
     * 方法调用状态
     * @see 
     * @since JDK 1.7.0
     */
    public class MethodCallState{
        /**
         * 成功
         */
        public static final int success = 1;
        /**
         * 失败
         */
        public static final int failure = 2;
    }



    /**
     * 虚拟服务主题名称
     * @see 
     * @since JDK 1.7.0
     */
    public class VirtualServiceTopic{
        /**
         * 话费充值发送的主题
         */
        public static final String vasRechargeTopic = "vas_recharge_topic";
        /**
         * 话费充值接收订单的主题
         */
        public static final String ordVirRechargeTopic = "ord_vir_recharge_topic";
    }



    /**
     * 运营商
     * @see 
     * @since JDK 1.7.0
     */
    public class Operators{
        /**
         * 中国移动
         */
        public static final int chinaMobile = 1;
        /**
         * 中国联通
         */
        public static final int chinaUnicom = 2;
        /**
         * 中国电信
         */
        public static final int chinaTelecom = 3;
    }



    /**
     * 操作行为
     * @see 
     * @since JDK 1.7.0
     */
    public class OpAction{
        /**
         * 查询
         */
        public static final int search = 1;
        /**
         * 充值
         */
        public static final int recharge = 2;
        /**
         * 回调
         */
        public static final int callback = 3;
    }

    
    /**
     * 供应商接口代码
     *
     * @author zengzw
     * @date 2016年8月12日
     */
    public class SupplierInterface{
        /**
         * 高阳话费充值
         */
        public static final String GYPAY = "1000000000000GYPAY";
        
        /**
         * 欧飞话费充值
         */
        public static final String OFPAY = "1000000000000OFPAY";
        
    }

}

