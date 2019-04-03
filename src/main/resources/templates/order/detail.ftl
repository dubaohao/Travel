<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">

                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <b>订单详情</b>
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单号</th>
                            <th>人数</th>
                            <th>费用</th>
                            <th>姓名</th>
                            <th>电话</th>
                            <th>支付状态</th>
                            <th>订单状态</th>
                            <th>创建时间</th>
                            <th>出发时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${OrderDetail.orderId}</td>
                            <td>${OrderDetail.orderNumber}</td>
                            <td>${OrderDetail.orderNum}</td>
                            <td>${OrderDetail.money}</td>
                            <td>${OrderDetail.name}</td>
                            <td>${OrderDetail.tel}</td>
                            <td>${OrderDetail.orderStatus}</td>
                            <td>${OrderDetail.orderProgress}</td>
                            <td>${OrderDetail.createDate}</td>
                            <td>${OrderDetail.updateDate}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <b>行程详情</b>
                        <thead>
                        <tr>
                            <th>行程Id</th>
                            <th>形成名称</th>
                            <th>图片</th>
                            <th>价格</th>
                            <th>人数上限</th>
                            <th>下限</th>
                            <th>更新时间</th>
                            <th>文章地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#--<#list OrderDetail.content as orderDetail>-->
                        <tr>
                            <td>${Hodometer.hoId}</td>
                            <td>${Hodometer.hoName}</td>
                            <td><img height="100" width="100" src="${(Hodometer.picture)!''}" alt=""></td>
                            <td>${Hodometer.price}</td>
                            <td>${Hodometer.hoNumUp}</td>
                            <td>${Hodometer.hoNumDown}</td>
                            <td>${Hodometer.updateDate}</td>
                            <td>${Hodometer.strategyUrl}</td>
                        </tr>
                        <#--</#list>-->
                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column">
                <#--<#if orderDTO.getOrderStatusEnum().message == "新订单">-->
                    <a href="/sell/order/finish?orderId=${OrderDetail.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/order/cancel?orderId=${OrderDetail.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                <#--</#if>-->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>