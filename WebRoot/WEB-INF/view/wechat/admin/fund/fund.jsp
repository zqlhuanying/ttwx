<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>公积金</title>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <!--<link href="<%=resourceUrl%>/css/login.css?v=2014030901" rel="stylesheet" type="text/css"/>-->
    <script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
    <script src="<%=resourceUrl %>/script/admin/fund.js?v=20140316" type="text/javascript" charset="UTF-8"></script>
</head>
<body class="easyui-layout" fit="true">
<div region="center" border="false" style="overflow: hidden;">
    <div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
        <fieldset>
            <table class="tableForm">
                <tr>
                    <td>姓名</td>
                    <td colspan="2">
                        <input name="username" style="width: 155px;" value="" />
                    </td>
                    <td>公积金账号</td>
                    <td colspan="2">
                        <input name="fund_account" style="width: 155px;" value="" />
                    </td>
                    </td>
                    <td>
                        <!--<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a>
                        <a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">重置</a>-->
                    </td>
                </tr>
            </table>
        </fieldset>
        <div>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="append();" plain="true" href="javascript:void(0);">添加</a>
            <!--<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteMsgAction();" plain="true" href="javascript:void(0);">刪除</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editMsgAction();" plain="true" href="javascript:void(0);">编辑</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="view();" plain="true" href="javascript:void(0);">查看</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('load');" plain="true" href="javascript:void(0);">刷新</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>-->
        </div>
    </div>
    <table id="datagrid"></table>
</div>

<div id="addFundDialog" style="display: none;overflow: hidden; height: 400px;width: 800px;">
    <!-- 公积金弹出框 -->
    <form id="form-add-fund" method="post" class="fund-form">
        <div class="top-margin control-group">
            <label>姓名<span class="text-danger">*</span></label>
            <input id="name" name="name" type="text" class="form-control login-field" placeholder="输入姓名" value="" />
            <label class="login-field-icon fui-user label-icon" for="name"></label>
        </div>
        <div class="top-margin control-group">
            <label>公积金账号<span class="text-danger">*</span></label>
            <input id="fund_account" name="fund_account" type="text" class="form-control login-field" placeholder="输入公积金账号" value="" />
            <label class="login-field-icon fui-lock label-icon" for="fund_account"></label>
        </div>
        <div class="top-margin control-group">
            <label>公积金金额<span class="text-danger">*</span></label>
            <input id="amount" name="amount" type="text" class="form-control login-field" placeholder="输入公积金金额" value="" />
            <label class="login-field-icon fui-lock label-icon" for="amount"></label>
        </div>
        <div class="row">
            <div class="col-lg-4 text-right">
                <input id="btn-fund" type="submit" class="btn btn-primary" data-loading-text="正在添加..." value="&nbsp;添&nbsp;&nbsp;加&nbsp;" />
            </div>
        </div>
    </form>
</div>

<div id="viewDialog" style="display: none;overflow: hidden;top:50px; min-height:200px; width: 400px;">
    <div id="viewDiv">
        <!-- js加载预览效果 -->
    </div>
</div>


</body>
</html> 