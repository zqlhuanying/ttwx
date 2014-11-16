<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>扩展接口配置</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<script src="<%=resourceUrl %>/script/admin/busiapp.js?v=20141112" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
			<fieldset>
				<table class="tableForm">
					<tr>
						<td>关键字</td>
						<td colspan="2">
							<input name="key_word" style="width: 155px;" value="" />
						</td>
						<td>编辑时间</td>
						<td colspan="2">
							<!-- <input name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />-<input name="paramMap.end_time" class="easyui-datetimebox" editable="false" style="width: 150px;" /> -->
							<input class="Wdate" onClick="WdatePicker()" name="start_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" style="width: 148px;" />
							-
							<input class="Wdate" onClick="WdatePicker()" name="end_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" style="width: 148px;" />
							
						</td>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">重置</a>
						</td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="append();" plain="true" href="javascript:void(0);">添加</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteMsgAction();" plain="true" href="javascript:void(0);">刪除</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editMsgAction();" plain="true" href="javascript:void(0);">编辑</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="view();" plain="true" href="javascript:void(0);">查看</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('load');" plain="true" href="javascript:void(0);">刷新</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid">
			<!-- js加载 -->
		</table>
	</div>

</body>
</html> 