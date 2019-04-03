package com.example.travel.controller;

import com.example.travel.configWeChatSDK.MyConfig;
import com.example.travel.dao.TOrder;
import com.example.travel.enums.ResultEnum;
import com.example.travel.exception.TravelException;
import com.example.travel.service.OrderService;
import com.example.travel.utils.JsonUtil;
import com.example.travel.utils.WeChatParamUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MyConfig myConfig;

    @Autowired
    private WxPayService wxPayService;

    /*
     * @param transactionId 微信订单号
     * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
     */
//    @ApiOperation(value = "查询订单")
    @GetMapping("/queryOrder")
    public WxPayOrderQueryResult queryOrder(@RequestParam(required = false) String transactionId,
                                            @RequestParam(required = false) String outTradeNo)
            throws WxPayException {
        WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(transactionId, outTradeNo);
        return wxPayOrderQueryResult;
    }

    /*
     * @param outTradeNo 商户系统内部的订单号
     */
//    @ApiOperation(value = "关闭订单")
    @GetMapping("/closeOrder/{outTradeNo}")
    public WxPayOrderCloseResult closeOrder(@PathVariable String outTradeNo) throws WxPayException {
        return wxPayService.closeOrder(outTradeNo);
    }

    /**
     * 微信统一下单支付
     *
     * @param orderNumber,returnUrl
     */
    @GetMapping("/createOrder")
    public ModelAndView createOrder(@RequestParam("orderNumber") String orderNumber,
                                    @RequestParam("returnUrl") String returnUrl
    ) throws WxPayException {
        /**1.查询订单*/
        TOrder tOrder = orderService.findOrderNumber(orderNumber);
        System.out.println(tOrder);
        if (tOrder == null) {
            throw new TravelException(ResultEnum.ORDER_NOT_EXIST);
        }
        /* 发起支付 */
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setOpenid(tOrder.getWx());
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setNotifyUrl("http://dubaohao.natapp1.cc/pay/notify");
        wxPayUnifiedOrderRequest.setTotalFee(Integer.valueOf(String.valueOf(tOrder.getMoney().multiply(new BigDecimal(100)).intValue())));
        wxPayUnifiedOrderRequest.setBody("dubaohao微信官方SDK订单test");
        wxPayUnifiedOrderRequest.setSpbillCreateIp(WeChatParamUtil.ip());

        wxPayUnifiedOrderRequest.setOutTradeNo(tOrder.getOrderNumber());

        log.info("发起微信支付SDK下单接口, request={}", JsonUtil.toJson(wxPayUnifiedOrderRequest));


        /*获取到APPId，timeStamp,nonceStr,packAgeValue,paySign*/
        WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        /*可以获取到prePayId,codeUrl,MwebUrl,TradeRype等等*/
//       WxPayUnifiedOrderResult wxPayUnifiedOrderResult= wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);

        log.info("发起微信SDK支付下单接口, result={}", JsonUtil.toJson(wxPayMpOrderResult));

        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", wxPayMpOrderResult.getAppId());
        map.put("timeStamp", wxPayMpOrderResult.getTimeStamp());
        map.put("nonceStr", wxPayMpOrderResult.getNonceStr());
        map.put("packAgeValue", wxPayMpOrderResult.getPackageValue());
        map.put("paySign", wxPayMpOrderResult.getPaySign());
        map.put("returnUrl", returnUrl);
        /* log.info("发起微信支付下单接口, map={}", JsonUtil.toJson(map)); */

        return new ModelAndView("pay/createSDK", map);
    }


    /**
     * 微信支付异步通知
     *
     * @param xmlData
     */

    @PostMapping("/notify")
    public ModelAndView notifys(@RequestBody String xmlData) throws WxPayException {

        log.info("支付回调通知");
//      log.info("回调通知！！！！！！xmlData={}",xmlData);

        WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
//      log.info("回调通知！！！！！！notifyResult={}",notifyResult);

        TOrder tOrder = orderService.findOrderNumber(notifyResult.getOutTradeNo());
        //判断订单是否存在
        if (tOrder == null) {
            log.error("【微信支付】异步通知, 订单不存在, orderId={}", notifyResult.getOutTradeNo());
            throw new TravelException(ResultEnum.ORDER_NOT_EXIST);
        }

//      return WxPayNotifyResponse.success("成功");//总是回调好多次，xmldata不存在return_msg字段
        /*更改订单状态*/
        tOrder.setOrderStatus("已支付");
        orderService.paid(tOrder);
        return new ModelAndView("pay/success");
    }

    /**
     * 微信退款
     *
     * @param orderNumber,returnUrl
     */
    @GetMapping("/refund")
    public WxPayRefundResult refund(@RequestParam("orderNumber") String orderNumber,
                                    @RequestParam("returnUrl") String returnUrl) throws WxPayException {
        /**1.查询订单*/
        TOrder tOrder = orderService.findOrderNumber(orderNumber);

        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setOutRefundNo(tOrder.getOrderNumber());
        wxPayRefundRequest.setRefundFee(Integer.valueOf(String.valueOf(tOrder.getMoney().multiply(new BigDecimal(100)).intValue())));
        wxPayRefundRequest.setOutTradeNo(tOrder.getOrderNumber());
        wxPayRefundRequest.setTotalFee(Integer.valueOf(String.valueOf(tOrder.getMoney().multiply(new BigDecimal(100)).intValue())));
        log.info("退款信息：{}", wxPayService.refund(wxPayRefundRequest));
        return wxPayService.refund(wxPayRefundRequest);
    }

    /**
     * 微信退款异步通知
     *
     * @param xmlData
     */
    @PostMapping("/notify/refund")
    public ModelAndView notifysRefund(@RequestBody String xmlData) throws WxPayException {
//    public String parseOrderNotifyResult(@RequestBody String xmlData)throws WxPayException{
        log.info("退款回调通知！！！！！！xmlData={}", xmlData);
        WxPayRefundNotifyResult notifyResult = wxPayService.parseRefundNotifyResult(xmlData);
        if (!notifyResult.getReturnMsg().equals("SUCCESS")) {
            throw new TravelException(ResultEnum.WECHAT_PAY_REFUND_NOTIFY_FAILED);
        }
        log.info("退款回调通知！！！！！！notifyResult={}", notifyResult);
//        return WxPayNotifyResponse.success("成功");
        return new ModelAndView("pay/success");
    }


    /**
     * 微信支付生成二维码链接
     *
     * @param orderNumber,returnUrl
     */


}
