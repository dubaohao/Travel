<script>
    function onBridgeReady(){
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":"${payResponse.appId}",     //���ں����ƣ����̻�����
                    "timeStamp":"${payResponse.timeStamp}",         //ʱ�������1970������������
                    "nonceStr":"${payResponse.nonceStr}", //�����
                    "package":"${payResponse.packAge}",
                    "signType":"MD5",         //΢��ǩ����ʽ��
                    "paySign":"${payResponse.paySign}" //΢��ǩ��
                },
                function(res){
//                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {

//                    }     // ʹ�����Ϸ�ʽ�ж�ǰ�˷���,΢���Ŷ�֣����ʾ��res.err_msg�����û�֧���ɹ��󷵻�    ok����������֤�����Կɿ���
                    location.href = "${returnUrl}";
                }
        );
    }
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>