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
                        <b>团单详情</b>
                        <thead>
                        <tr>
                            <th>团单id</th>
                            <th>团单号</th>
                            <th>作者Id</th>
                            <th>支付人数</th>
                            <th>人数上限</th>
                            <th>报名人数</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <th>出发时间</th>
                            <th>二维码</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${GroupDetail.groupId}</td>
                            <td>${GroupDetail.groupNumber}</td>
                            <td>${GroupDetail.TId}</td>
                            <td>${GroupDetail.payNum}</td>
                            <td>${GroupDetail.TNumUp}</td>
                            <td>${GroupDetail.TNum}</td>
                            <td>${GroupDetail.createDate}</td>
                            <td>${GroupDetail.updateDate}</td>
                            <td>${GroupDetail.goTime}</td>
                            <td>${GroupDetail.verificationCode}</td>
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
                        <#--<#list GroupDetail.content as orderDetail>-->
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
                <#if GroupDetail.status == "新行程">
                    <a href="/sell/order/finish?orderId=${GroupDetail.groupNumber}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/order/cancel?orderId=${GroupDetail.groupNumber}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>