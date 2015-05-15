<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <jsp:include page="/WEB-INF/view/common/inc/meta.jsp">
        <jsp:param value="绑定" name="title"/>
    </jsp:include>
    <style type="text/css">

        .label-icon{
            margin-top: 40px;
        }

    </style>
</head>
<body>
<!-- container -->
<div class="container">
    <ol class="breadcrumb">
        <!--<li><a href="<%=domain %>">公积金账户绑定</a></li>-->
        <li class="active">公积金账户绑定</li>
    </ol>
    <div class="row">
        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">绑定你的账号</h3>
                        <hr>
                        <form id="form-login" method="post" class="login-form">
                            <div class="top-margin control-group">
                                <label>手机号<span class="text-danger">*</span></label>
                                <input id="phone" name="phone" type="text" class="form-control login-field" placeholder="输入手机号" value="" />
                                <input id="id" name="id" type="hidden" value="<%=request.getParameter("id")%>">
                                <label class="login-field-icon fui-user label-icon" for="phone"></label>
                            </div>
                            <div class="top-margin control-group">
                                <label>公积金账号<span class="text-danger">*</span></label>
                                <input id="fund_account" name="fund_account" type="text" class="form-control login-field" placeholder="输入公积金账号" value="" />
                                <label class="login-field-icon fui-lock label-icon" for="fund_account"></label>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-lg-4 text-right">
                                    <input id="btn-login" type="submit" class="btn btn-primary" data-loading-text="正在绑定..." value="&nbsp;绑&nbsp;&nbsp;定&nbsp;" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </article>
        <!-- /Article -->
    </div>
</div>	<!-- /container -->


<jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
<script src="<%=resourceUrl%>/script/display/bind.js" type="text/javascript"></script>
</body>
</html>