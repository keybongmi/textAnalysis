
$html = "";
var userId = $("#userId").val();

$("#userName_button").click(function(){
	$html = "";
	$html = $("<label id =\"test\" style=\"width:18%\" for=\"userName\">userName</label>" + 
	"<div class=\"form-group\"><input id=\"userName\"  readonly=\"readonly\" value= "+$("#userName").val()+"></input></div>"+
	"<div class=\"form-group\"><input id=\"userName_Change\"></input></div>");
			
	
	$("#panel_model").append($html);
	
});

$("#password_button").click(function(){
	$html = "";
	$html = $("<label id =\"test\" style=\"width:18%\">password</label>" + 
	"<div class=\"form-group\"><input id=\"original_password\" type=\"password\" placeholder=\"请输入原密码\"></input></div>"+
	"<div class=\"form-group\"><input id=\"password1\" type=\"password\" placeholder=\"请输入密码\"></input></div>"+
	"<div class=\"form-group\"><input id=\"password2\" type=\"password\" placeholder=\"请确认密码\"></input></div>");
		
	
	$("#panel_model").append($html);
	
});

$("#phoneNumber_button").click(function(){
	$html = "";
	$html = $("<label id =\"test\" style=\"width:18%\">phoneNumber</label>" + 
	"<div class=\"form-group\"><input id=\"phone_number\"  readonly=\"readonly\" placeholder= "+$("#phoneNumber").val()+"></input></div>"+
	"<div class=\"form-group\"><input id=\"phone_number_change\"></input></div>");
			
	
	$("#panel_model").append($html);
	
});

$("#mail_button").click(function(){
	$html = "";
	$html = $("<label id =\"test\" style=\"width:18%\">mail</label>" + 
	"<div class=\"form-group\"><input  id=\"mail\" readonly=\"readonly\" placeholder= "+$("#mail").val()+"></input></div>"+
	"<div class=\"form-group\"><input id=\"mail_change\"></input></div>");
			
	
	$("#panel_model").append($html);
	
});

$("#dismiss").click(function(){
	$html.remove();
})

$("#save_change").click(function(){
	
	//获取label的值，来确定要修改的标签
	var test = document.getElementById("test").innerText;
	
	//修改userName,用户名要大于5,小于15
	if(test == 'userName'){
		var userName_Change = $("#userName_Change").val();
		if(userName_Change.length<2||userName_Change.length>15){
			alert("修改userName,用户名要大于2,小于15");
			return false;
		}
		else{
			var ldata = {userId:userId,userName:userName_Change};
			$.ajax({
		        url: '/textAnalysis/user/changeUserName',
		        type: 'post',
		        dataType: 'json',
		        async: true,
		        data: ldata,
		        success:function(data){
			        if (data.result ==  "sucess") {
			        	$("#userName").val() = userName_Change;
			        	alert("修改成功");
			        	return true;
			        } else if(data.result == "fail") {
			        	alert("修改失败");  
			            return false;
			        }
		        },
		        error:function(){
		        	alert("修改失败");   
		        }
		    });
		}
			
	}
	//修改password
	else if(test == 'password'){
		var password = document.getElementById("password").value;
		
		var password1 = document.getElementById("password1").value;
		var password2 = document.getElementById("password2").value;
		alert($.md5($.trim(password)));
		if(!check_repsw(password1,password2)){
			alert("请保持两个密码一致");
			return false;
		}
			
		if(!check_psw(password1)){
			alert("密码不符合长度要求!密码长度要大于5，小于21");
			return false;
		}
			
		var ldata = {userId:userId,password:password,password2:$.md5($.trim(password2))};
		$.ajax({
	        url: '/textAnalysis/user/changePassword',
	        type: 'post',
	        dataType: 'json',
	        async: true,
	        data: ldata,
	        success:function(data){
	        	//修改成功
		        if (data.result ==  "sucess") {
		        	$("#password").val() = $.md5(password1);
		        	alert("修改成功");
		        	return true;
		        }//修改失败
		        else if(data.result == "fail") {
		        	alert("修改失败");  
		            return false;
		        }
		        //原密码不正确
		        else if(data.result == "error") {
		        	alert("原密码不正确");  
		            return false;
		        }
	        },
	        error:function(){
	        	alert("修改失败");   
	        }
	    });
	}	
	//修改phoneNumber
	else if(test == 'phoneNumber'){
		var phone_number_change = $("#phone_number_change").val();
		if(!check_tel(phone_number_change)){
			alert("请输入正确的手机号");
			return false;
		}
		else{
			var ldata = {userId:userId,phoneNumber:phone_number_change};
			$.ajax({
		        url: '/textAnalysis/user/changePhoneNumber',
		        type: 'post',
		        dataType: 'json',
		        async: true,
		        data: ldata,
		        success:function(data){
		        	//修改成功
			        if (data.result ==  "sucess") {
			        	$("#phoneNumber").val() = phone_number_change;
			        	return true;
			        }//修改失败
			        else if(data.result == "fail") {
			        	alert("修改失败");  
			            return false;
			        }
			        else if(data.result == "error") {
			        	alert("手机已使用");  
			            return false;
			        }
		        },
		        error:function(){
		        	alert("修改失败");   
		        }
		    });
		}
			
	}
	//修改mail
	else if(test == 'mail'){
		var mail_change = $("#mail_change").val();
		if(!check_mail(mail_change)){
			alert("邮箱地址格式错误或含有非法字符");
			return false;
		}
		else{
			var ldata = {userId:userId,mail:mail_change};
			$.ajax({
		        url: '/textAnalysis/user/changeMail',
		        type: 'post',
		        dataType: 'json',
		        async: true,
		        data: ldata,
		        success:function(data){
		        	//修改成功
			        if (data.result ==  "sucess") {
			        	$("#mail").val() = mail_change;
			        	return true;
			        }//修改失败
			        else if(data.result == "fail") {
			        	alert("修改失败");  
			            return false;
			        }
			        else if(data.result == "error") {
			        	alert("邮箱已使用");  
			            return false;
			        }
		        },
		        error:function(){
		        	alert("修改失败");   
		        }
		    });
		}
	}
	
	$html.remove();
	location.reload(true);
});

$("#close").click(function(){
	$html.remove();
});


//判断密码输入是否合法，一个是长度，一个是没有特殊字符
function check_psw(obj){
	var checkpsw=obj;
	 if(checkpsw.length > 5 && checkpsw.length<21){
		 return true;
	 }else {
		 return false;
	 }
}


//判断两次密码输入是否一致
function check_repsw(obj1,obj2){
	 var objvalue1=obj1;
	 var objvalue2=obj2;
	 if(objvalue1==objvalue2){
		 return true
	 }else {
	     return false;
	 }
 }

 
//判断邮箱地址格式是否合法
function check_mail(obj){
	 var strm=obj;
	 var regm = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;//验证Mail的正则表达式,^[a-zA-Z0-9_-]:开头必须为字母,下划线,数字,
	 if (!strm.match(regm))
	 {
	  return false;
	 } 
	 return true;
}

//判断手机号码格式是否合法
function check_tel(obj){
	 var objvalue=obj;
	 var regx=/^(?:13\d|15\d|18[123456789])-?\d{5}(\d{3}|\*{3})$/;
	 if(regx.test(objvalue)){
		 return true;
	 }else{
		 document.getElementByIdx_x("checktel").innerHTML="<font color='#ff0000'>请输入正确的手机号!</font>";
		 return false;
	 }
}
