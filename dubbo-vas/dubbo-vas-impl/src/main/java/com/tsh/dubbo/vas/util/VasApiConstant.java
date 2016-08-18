package com.tsh.dubbo.vas.util;
/**
 * 话费充值Api常量
 * @version 1.0.0 2016年8月4日<br>
 * @see 
 * @since JDK 1.7.0
 */
public interface VasApiConstant {
	/**
	 * 充值状态
	 * @version 1.0.0 2016年8月4日<br>
	 * @see 
	 * @since JDK 1.7.0
	 */
	public class BillStatus{
		/**
		 * 1-充值中
		 */
		public static final long rechargeIng = 1;
		/**
		 * 2-充值成功
		 */
		public static final long rechargeSuccess = 2;
		/**
		 * 3-充值失败
		 */
		public static final long rechargeFailure = 3;
	}
}

