package com.util;

/**
 * @author xsy
 * @create 2019-01-09 13:48
 * @desc 字符串处理工具类
 **/
public class StringUtil {
    public static void main(String[] args) {
        String openid = "1";
        String cartIds = "2";
        String exchangeGoodsIds = "3";
        String userIntegralFlag = "4";
        String userIntegral = "5";
        String couponId = "6";
        String payAmount = "7";
        String formatString = String.format("购物车结算后下单，用户openid=%s；购物车id组cartIds=%s；" +
                        "兑换商品id组exchangeGoodsIds=%s；是否使用积分标志userIntegralFlag=%s；用户使用积分userIntegral=%s；优惠券id=%s；" +
                        "支付金额payAmount=%s", openid, cartIds, exchangeGoodsIds, userIntegralFlag, userIntegral,
                couponId, payAmount);
        System.out.println(formatString);
    }

}
