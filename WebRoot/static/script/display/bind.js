
/**
 * 用户手机号/公积金账号绑定
 */

$(function(){
	
	if(window.parent != window){
		window.parent.location = window.location;
	}
	
	$("#form-login").submit(function(){
		$(this).ajaxSubmit({
			url : domain + "/admin/extmenu/bind",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
		   			window.location.href = domain + "/admin/extmenu/bindok";
				}else{
					app.alert(res.msg?res.msg:'绑定失败',{
						ok: function () {
							$("#btn-login").button('reset');
						}
					});
				}
	        } 
	    });
	    return false;
	});

});


function validForm(){
    var phonePattern = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	var phone = $("#phone").val();
	if(!phone || phone == ''){
		app.alert("请输入手机号");
		$("#phone").focus();
		return false;
	}
    if(!phone.match(phonePattern)){
        app.alert("手机格式不对");
        $("#phone").focus();
        return false;
    }
	var fund_account = $("#fund_account").val();
	if(!fund_account || fund_account == ''){
		app.alert("请输入公积金账号");
		$("#fund_account").focus();
		return false;
	}
    /*if(fund_account.length != 12){
        app.alert("公积金账号错误");
        $("#fund_account").focus();
        return false;
    }*/
	$("#btn-login").button('loading');
	return true;
}


