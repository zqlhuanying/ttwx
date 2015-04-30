/**
 * 公积金管理
 */

var addFundDialog;
var viewDialog;
var addFundForm;
var datagrid;

$(function(){

    init();

    $("#form-add-fund").submit(function(){
        $(this).ajaxSubmit({
            url : domain + '/admin/fund/save',
            dataType : 'json',
            data :{
                'name' : $("#name").val(),
                'fund_account' : $("#fund_account").val(),
                'amount' : $("#amount").val()
            },
            beforeSubmit : validForm,
            success : function(res) {
                if(res && '1' == res.code){
                    clearData();
                    addFundDialog.dialog("close");
                    fjx.showMsg('设置成功');
                    datagrid.datagrid("myReload");
                }else{
                    $.messager.alert('提示',	res?res.msg:'设置失败！','error');
                }
            }
        });
        return false;
    });

});


/**
 * 初始化
 */
function init(){
    //添加公积金账号表单
    addFundForm = $('#form-add-fund').form();

    //添加公积金账号dialog
    addFundDialog = $('#addFundDialog').show().dialog({
        modal : true,
        title : '添加公积金账号'
    }).dialog('close');

    //公积金金额预览dialog
    viewDialog = $("#viewDialog").show().dialog({
        modal : true
    }).dialog('close');

    datagrid = $('#datagrid').datagrid({
        url : domain + '/admin/fund/pageListFund',
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
        }/*{
            field : 'fund_account',
            hidden : true
        },{
            field : 'material_id',
            hidden : true
        },{
            field : 'app_id',
            hidden : true
        },{
            field : 'action_type',
            hidden : true
        },{
            field : 'resp_type',
            hidden : true
        },{
            field : 'key_word',
            title : '关键字',
            width : 200
        } */] ],
        columns : [[ /*{
            field : 'name',
            title : '姓名',
            width : 150
        },*/ {
            field : 'fund_account',
            title : '公积金账号',
            width : 150
        }, {
            field : 'amount',
            title : '金额',
            width : 150
        }, {
            field : 'op',
            title : '操作',
            width : 150,
            formatter : function(value, rowData, rowIndex) {
                //var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''+rowData.id+'\');" href="javascript:void(0);">查看</a>';
                //html += '<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMsgAction(\''+rowData.id+'\');" href="javascript:void(0);">修改</a>';
                var html = '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteFund(\''+rowData.id+'\');" href="javascript:void(0);">刪除</a>';
                return html;
            }
        } ] ],
        onLoadSuccess : function(){
            $.parser.parse();
        }
    });
}


function searchDatagrid(){
    var start_time = $('#toolbar input[name="start_time"]').val();
    var end_time = $('#toolbar input[name="end_time"]').val();
    datagrid.datagrid('load', {
        "key_word" : $('#toolbar input[name="key_word"]').val(),
        "start_time" : start_time,
        "end_time" : end_time
    });
}

function clearDatagrid() {
    $('#toolbar input').val('');
    datagrid.datagrid('load', {});
}

/**
 * 打开添加公积金dialog
 */
function append() {
    clearData();
    addFundDialog.dialog('open');
}

/**
 * 提交設置
 */
/*function submitAddFundForm(){
    addFundForm.form('submit', {
        url : domain + '/admin/fund/save',
        data :{
            'name' : $("#name").val(),
            'fund_account' : $("#fund_account").val(),
            'amount' : $("#amount").val()
        },
        ajax : true,
        success : function(data) {
            fjx.closeProgress();
            var res = $.evalJSON(data);
            if(res && '1' == res.code){
                clearData();
                addFundDialog.dialog("close");
                fjx.showMsg('设置成功');
                datagrid.datagrid("myReload");
            }else{
                $.messager.alert('提示',	res?res.msg:'设置失败！','error');
            }
        },
        beforeSubmit : validForm,
        onSubmit : function(){
            $.messager.progress({
                text : '数据提交中....',
                interval : 100
            });
        }
    });
}*/
/**
 * 清除数据
 */
function clearData(){
    $("#name").val("");
    $("#fund_account").val("");
    $("#amount").val("");
}

function validForm(){
    var name = $("#name").val();
    if(!name || name == ''){
        $.messager.alert('提示','请输入姓名');
        $("#name").focus();
        return false;
    }
    var fund_account = $("#fund_account").val();
    if(!fund_account || fund_account == ''){
        $.messager.alert('提示','请输入公积金账号');
        $("#fund_account").focus();
        return false;
    }
    var amount = $("#amount").val();
    var amountPattern = "^([0-9]{1,5})(\\.(\\d){1,2})?$";
    if(!amount || amount == ''){
        $.messager.alert('提示','请输入公积金金额');
        $("#amount").focus();
        return false;
    }
    if(!amount.match(amountPattern)){
        $.messager.alert('提示','请输入正确的公积金金额');
        $("#amount").focus();
        return false;
    }
    return true;
}


function deleteFund(ids){
    $.ajax({
        url :  domain + '/admin/fund/deleteFund',
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

function editMsgAction(id){
    var row = undefined;
    if(!id || id == ''){	//id为空，则编辑当前选中的行
        var rows = datagrid.datagrid('getSelections');
        if(!rows || rows.length<1){
            fjx.showMsg('请选择要编辑的关键字');
            return false;
        }else if (rows.length > 1 ) {
            var keywords = [];
            for ( var i = 0; i < rows.length; i++) {
                keywords.push(rows[i].key_word);
            }
            fjx.showMsg('只能选择一个关键字进行编辑！你已经选择了【' + keywords.join(',') + '】' + rows.length + '个关键字');
            return false;
        } else if (rows.length == 1) {
            row = rows[0];
        }
    }
    if(!row){
        datagrid.datagrid('unselectAll');
        row = datagrid.datagrid('selectRecord',id).datagrid("getSelected");
    }
    msgActionForm.form("clear");
    msgActionForm.form('load', {
        "editType" : "edit",
        "id" : row.id,
        "key_word" : row.key_word
    });
    var tabIndex;
    if(row.action_type == 'material'){//数据源从素材读取
        var json = $.xml2json(row.xml_data);
        var msgType = json.MsgType;
        if(msgType == "text"){		//文本消息
            tabIndex = 0;
            $("#replyText").val(json.Content);
        }else if(msgType == "news"){	//图文消息
            tabIndex = 4;
        }

    }else if(row.action_type == 'api'){
        tabIndex = 5;
        if (row.app_id) {
            busiapi_combobox.combobox("select",row.app_id);
        }
    }
    $("#edit_tabs").tabs("select",tabIndex);
    $("#msgKeyWord").attr("readonly","readonly");
    keywordDialog.dialog('open');
}
/**
 * 预览
 * @param {} id
 * @return {Boolean}
 */
function view (id){
    var row = undefined;
    if(!id || id == ''){	//id为空，则预览当前选中的行
        var rows = datagrid.datagrid('getSelections');
        if(!rows || rows.length<1){
            $.messager.show({
                msg : '请选择要查看的关键字',
                title : '提示'
            });
            return false;
        }else if (rows.length > 1 ) {
            var keywords = [];
            for ( var i = 0; i < rows.length; i++) {
                keywords.push(rows[i].key_word);
            }
            $.messager.show({
                msg : '一次只能查看一个关键字的回复消息！你已经选择了【' + keywords.join(',') + '】' + rows.length + '个关键字',
                title : '提示'
            });
            return false;
        } else if (rows.length == 1) {
            row = rows[0];
        }
    }
    if(!row){
        datagrid.datagrid('unselectAll');
        row = datagrid.datagrid('selectRecord',id).datagrid("getSelected");
    }
    var viewHtml = "";		//预览效果HTML
    var title = "";
    if(row.action_type == 'material'){//数据源从素材读取
        var json = $.xml2json(row.xml_data);
        var msgType = json.MsgType;
        if(msgType == "text"){		//文本消息
            viewHtml = json.Content;
        }else if(msgType == "news"){	//图文消息
            viewHtml = xml2NewsHtml(row.xml_data,row.in_time,row.material_id);	//(wechat.js)
        }
    }else if(row.action_type == 'api'){
        if (row.app_name) {
            viewHtml = "从接口【"+row.app_name+"】中获得响应数据";
        }else{
            viewHtml = "插件已经被删除，请重新配置";
        }
    }
    $("#viewDiv").html(viewHtml);

    viewDialog.dialog({
        title:"用户发送文字消息【"+row.key_word+"】将收到以下回复"
    }).dialog("open");

}
