package com.rrg.gz.utils;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/16
 */
public class ConstantUtil {

    /**
     * 订单状态
     */
    public enum OrderStatus {

        STATUS_ONE("1", "下单"),
        STATUS_TWO("2", "支付完成"),
        STATUS_THREE("3", "配送中"),
        STATUS_FORE("4", "确认收货");

        OrderStatus(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println(OrderStatus.STATUS_FORE.key);
    }
}
