package net.somta.juggle.console.interfaces.dto;

public class OrderDTO {
    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付二维码
     */
    private String qrCode;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
