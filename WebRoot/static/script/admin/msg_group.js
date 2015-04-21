var msgActionForm;

$(function(){
    msgActionForm = $("#msgActionForm");
})

/**
 * 提交响应规则設置
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType){
    msgActionForm.form('submit', {
        url : domain + '/admin/group/send',
        ajax : true,
        success : function(data) {
            fjx.closeProgress();
            var res = $.evalJSON(data);
            if(res && '1' == res.code){
                fjx.showMsg('群发成功');
            }else{
                $.messager.alert('提示',res ? res.msg : '群发失败！','error');
            }
        },
        onSubmit : function(){
            //$("#materiaMsgType").val(respType);		//响应消息类型
            //$("#msgReqType").val(req_type);
            if(respType === 'text'){
                $("#msgActionType").val("material");    //区别是从素材库还是第三方应用读取数据
                $("#req_send_type").val("text");
                $("#msg_group_type").val("content");
                var txtContent = $.trim($("#replyText").val());
                if(!txtContent || "" == txtContent){
                    $.messager.alert('提示', '请输入要群发的内容', 'warning');
                    return false;
                }
                $("#txtContent").val(txtContent);
            }
            if(respType === 'news'){
                $("#msgActionType").val("material");
                $("#req_send_type").val("mpnews");
                $("#msg_group_type").val("media_id");
                var newsId = $("#newsId").val();
                if(!newsId || newsId == ''){
                    $.messager.alert('提示', '请选择素材', 'warning');
                    return false;
                }
                $("#msgMaterialId").val(newsId);
            }
            $.messager.progress({
                text : '数据提交中....',
                interval : 100
            });
        }
    });
}