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
                    <form role="form" method="post" action="/sell/hodometer/save">
                        <div class="form-group col-md-6">
                            <label>名称</label>
                            <input name="hoName" type="text" class="form-control" value="${(hodometer.hoName)!''}"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label>价格</label>
                            <input name="price" type="text" class="form-control" value="${(hodometer.price)!''}"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label>人数上限</label>
                            <input name="hoNumUp" type="number" class="form-control" value="${(hodometer.hoNumUp)!''}"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label>人数下限</label>
                            <input name="hoNumDown" type="number" class="form-control" value="${(hodometer.hoNumDown)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>文章</label>
                            <input name="hoUrl" type="text" class="form-control" value="${(hodometer.hoUrl)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>攻略</label>
                            <input name="strategyUrl" type="text" class="form-control" value="${(hodometer.strategyUrl)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(hodometer.picture)!''}" alt="">
                            <br/>
                            <input name="picture" type="text" class="form-control" value="${(hodometer.picture)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="category" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.category}"
                                            <#if (hodometer.category)?? && hodometer.category == category.category>
                                                selected
                                            </#if>
                                        >${category.category}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="number" name="HoId" value="${(hodometer.HoId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>