<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>公积金</title>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
    <script src="<%=resourceUrl %>/script/admin/searchBind.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body class="easyui-layout" fit="true">
<div region="center" border="false" style="overflow: hidden;">
    <table id="datagrid"></table>
</div>



</body>
</html> 