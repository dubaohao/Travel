//package com.example.travel.service.impl;
//
//import com.example.travel.dao.TOrder;
//import com.example.travel.enums.ResultEnum;
//import com.example.travel.exception.TravelException;
//import com.example.travel.service.OrderService;
//import com.example.travel.service.PayService;
//import com.example.travel.utils.JsonUtil;
//import com.example.travel.utils.MathUtil;
//import com.example.travel.utils.WeChatParamUtil;
//import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
//import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
//import com.github.binarywang.wxpay.exception.WxPayException;
//import com.github.binarywang.wxpay.service.WxPayService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class PayServiceImpl implements PayService {
//
//    @Autowired
//    private WxPayService wxPayService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Override
//    public WxPayMpOrderResult create(TOrder tOrder) throws WxPayException{
//        /* 发起支付 */
//        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
//        wxPayUnifiedOrderRequest.setOpenid(tOrder.getWx());
//        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
//        wxPayUnifiedOrderRequest.setNotifyUrl("http://dubaohao.natapp1.cc/pay/notify");
//        wxPayUnifiedOrderRequest.setTotalFee(Integer.valueOf(String.valueOf(tOrder.getMoney().multiply(new BigDecimal(100)).intValue())));
//        wxPayUnifiedOrderRequest.setBody("dubaohao微信官方SDK订单test");
//        wxPayUnifiedOrderRequest.setSpbillCreateIp(WeChatParamUtil.ip());
//
//        wxPayUnifiedOrderRequest.setOutTradeNo(tOrder.getOrderNumber());
//
//        log.info("发起微信支付SDK下单接口, request={}", JsonUtil.toJson(wxPayUnifiedOrderRequest));
//
//
//        /*获取到APPId，timeStamp,nonceStr,packAgeValue,paySign*/
//        WxPayMpOrderResult wxPayMpOrderResult=wxPayService.createOrder(wxPayUnifiedOrderRequest);
//        /*可以获取到prePayId,codeUrl,MwebUrl,TradeRype等等*/
////       WxPayUnifiedOrderResult wxPayUnifiedOrderResult= wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);
//
//        log.info("发起微信SDK支付下单接口, result={}", JsonUtil.toJson(wxPayMpOrderResult));
//
//        return wxPayMpOrderResult;
//    }
//    @Override
//    public PayResponse notify(String notifyData) {
//        //1. 验证签名
//        //2. 支付的状态
//        //3. 支付金额
//        //4. 支付人(下单人 == 支付人)
//
//        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
//        log.info("【微信支付】异步通知, payResponse={}", JsonUtil.toJson(payResponse));
//
//        //查询订单
//        TOrder tOrder = orderService.findOrderNumber(payResponse.getOrderId());
//
//        //判断订单是否存在
//        if (tOrder == null) {
//            log.error("【微信支付】异步通知, 订单不存在, orderId={}", payResponse.getOrderId());
//            throw new TravelException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        //判断金额是否一致(0.10   0.1)
//        if (!MathUtil.equals(payResponse.getOrderAmount(), tOrder.getMoney().doubleValue())) {
//            log.error("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}",
//                    payResponse.getOrderId(),
//                    payResponse.getOrderAmount(),
//                    tOrder.getMoney());
//            throw new TravelException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//        }
//
//        //修改订单的支付状态
//        tOrder.setOrderStatus("已支付");
//        orderService.paid(tOrder);
//
//        return payResponse;
//    }
//
//    /**
//     * 退款
//     * @param tOrder
//     */
//    @Override
//    public RefundResponse refund(TOrder tOrder) {
//        RefundRequest refundRequest = new RefundRequest();
//        refundRequest.setOrderId(tOrder.getOrderNumber());
//        refundRequest.setOrderAmount(tOrder.getMoney().doubleValue());
//        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
//        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
//
//        RefundResponse refundResponse = bestPayService.refund(refundRequest);
//        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));
//
//        return refundResponse;
//    }
//}
