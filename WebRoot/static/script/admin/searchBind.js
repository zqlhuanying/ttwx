/**
 * 公积金绑定信息查询
 */


var datagrid;

$(function(){

    init();

});


/**
 * 初始化
 */
function init(){

    datagrid = $('#datagrid').datagrid({
        url : domain + '/admin/fund/pageListBind',
        toolbar : '#toolbar',
        iconCls : 'icon-save',
        pagination : true,
        pageSize : 10,
        pageList : [10,15,20],
        fit : true,
        fitColumns : true,
        nowrap : false,
        border : false,
        idField : 'id',
        frozenColumns : [ [ {
            title : 'id',
            field : 'id',
            width : 50,
            checkbox : true
        }] ],
        columns : [[ {
            field : 'wechatUserEntity',
            title : '用户id',
            width : 150,
            formatter : function(value, rowData, rowIndex){
                return value.id;
            }
        }, {
            field : 'fund_account',
            title : '公积金账号',
            width : 150
        }, {
            field : 'phone',
            title : '手机号',
            width : 150
        }, {
            field : 'op',
            title : '操作',
            width : 150,
            formatter : function(value, rowData, rowIndex) {
                var html = '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteBind(\''+rowData.id+'\');" href="javascript:void(0);">刪除</a>';
                return html;
            }
        } ] ],
        onLoadSuccess : function(){
            $.parser.parse();
        }
    });
}


function deleteBind(ids){
    $.ajax({
        url :  domain + '/admin/fund/deleteBind',
        data : 'ids='+ids,
        cache : false,
        dataType : "json",
        success : function(res) {
            if(res && '1' === res.code){
                fjx.showMsg('刪除成功');
                datagrid.datagrid("myReload");
            }else{
                $.messager.alert('提示',res?res.msg:'刪除失败','error');
            }
        }
    });
}

