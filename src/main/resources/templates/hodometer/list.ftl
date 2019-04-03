<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>行程id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>主题</th>
                            <th>文章</th>
                            <th>攻略</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>状态</th>
                            <th>人数上限</th>
                            <th>人数下限</th>

                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list hodoDTOPage.content as hodometerDTO>
                        <tr>
                            <td>${hodometerDTO.hoId}</td>
                            <td>${hodometerDTO.hoName}</td>
                            <td><img height="100" width="100" src="${hodometerDTO.picture}" alt=""></td>
                            <td>${hodometerDTO.price}</td>
                            <td>${hodometerDTO.category}</td>
                            <td>${hodometerDTO.hoUrl}</td>
                            <td>${hodometerDTO.strategyUrl}</td>
                            <td>${hodometerDTO.createDate}</td>
                            <td>${hodometerDTO.updateDate}</td>
                            <td>${hodometerDTO.status}</td>
                            <td>${hodometerDTO.hoNumUp}</td>
                            <td>${hodometerDTO.hoNumDown}</td>

                            <td><a href="/sell/hodometer/index?hoId=${hodometerDTO.hoId}">修改</a></td>
                            <td>
                                <#if hodometerDTO.status == "在架">
                                    <a href="/sell/hodometer/off_sale?hoId=${hodometerDTO.hoId}">下架</a>
                                <#else>
                                    <a href="/sell/hodometer/on_sale?hoId=${hodometerDTO.hoId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..hodoDTOPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte hodoDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>