<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<html>
<head>
    <title>微信群发消息管理</title>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <link href="<%=resourceUrl%>/css/group.css" rel="stylesheet" type="text/css"/>
    <link href="<%=resourceUrl%>/css/material.css" rel="stylesheet" type="text/css"/>
    <script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/script/admin/msg_group.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<!--<div id="edit_tabs" class="easyui-tabs" data-options="fit:true" style="height:390px">
    <div title="<span class='tt-inner tab_text'><i class='icon_msg_sender'></i>文字</span>" style="padding:10px">
        <div style="width: 510px">
            <div id="js_textArea">
                <textarea class="textarea field" id="replyText" name="replyText"></textarea>
            </div>
            <div class="editor_toolbar">
                <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch"></a>
                <span style="margin-left: 342px;">还可以输入<em>600</em>字</span>
            </div>
            <div class="clear"></div>
            <div id="txt_btn" style="float: left;">
                         <span class="btn btn_input btn_primary">
                            <button onclick="submitMsgActionForm('text')">保存</button>
                         </span>
            </div>
        </div>
    </div>
    <div title="<span class='tt-inner tab_appmsg'><i class='icon_msg_sender'></i>图文</span>" style="padding:10px">
        <input class="field" type="hidden" id="newsId" value="">

        <div style="width: 100%;">
            图文预览
            <div class="clear"></div>
            <div style="float: left;">
                <div id="preview_news"
                     style="min-height:300px;max-height:500px; width:350px;float: left; border: solid 1px #E0ECFF;">-->
                    <!-- js加载 预览效果 -->
                <!--</div>
                <div style="height:35px; width:410px; float: left;margin-top: 270px; ">
                    <span class="btn btn_input btn_default">
                        <input type="hidden" id="newsId" name="newsId" style="width:250px;">
                        <button onclick="openMaterialDialog();">选择</button>
                     </span>
                     <span class="btn btn_input btn_primary">
                        <button onclick="submitMsgActionForm('news');">保存</button>
                     </span>
                </div>
            </div>
        </div>
    </div>-->

    <jsp:include page="/WEB-INF/view/wechat/admin/menu/action_inc.jsp">
        <jsp:param name="tag_image" value="hide"/>
        <jsp:param name="tag_voice" value="hide"/>
        <jsp:param name="tag_video" value="hide"/>
        <jsp:param name="tag_exp" value="hide"/>
        <jsp:param name="btn_return" value="hide"/>
        <jsp:param name="group_type" value="group"/>
        <jsp:param name="btn_submit_text" value="群发"/>
    </jsp:include>
</div>


</body>
</html>
