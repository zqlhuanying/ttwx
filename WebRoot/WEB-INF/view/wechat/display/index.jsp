<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta property="qc:admins" content="422531167706316110063757" />
<jsp:include page="/WEB-INF/view/common/inc/meta.jsp"></jsp:include>
<link href="<%=resourceUrl%>/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>

	<div class="jumbotron masthead">
		<div class="container text-center">
			<h2><%=appName %></h2>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
	
</body>
</html>