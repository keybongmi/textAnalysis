var checkpsw;                                  //全局变量，用来密码相同检测
var status = true;

//判断密码输入是否合法，一个是长度，一个是没有特殊字符
function check_psw(obj){
	 checkpsw=obj.value;
	 if(checkpsw.length > 5 && checkpsw.length<21){
		 return true;
	 }else {
		 document.getElementById("result").innerHTML="<font color='#ff0000'>密码不符合长度要求!</font>";
		 return false;
	 }
}


//判断两次密码输入是否一致
function check_repsw(obj1,obj2){
	 var objvalue1=obj1.value;
	 var objvalue2=obj2.value;
	 
	 if(objvalue1==objvalue2){
		 return true
	 }else {
	     document.getElementById("result").innerHTML="<font color='#ff0000'>两次输入的密码不一样!</font>";
	     return false;
	 }
 }

 
//判断邮箱地址格式是否合法
function check_mail(obj){
	 var strm=obj.value;
	 var regm = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;//验证Mail的正则表达式,^[a-zA-Z0-9_-]:开头必须为字母,下划线,数字,
	 if (!strm.match(regm))
	 {
	  document.getElementById("result").innerHTML="<font color='#ff0000'>邮箱地址格式错误或含有非法字符!</font>";
	  status = false;
	  return false;
	 } 
	 return true;
}


//判断手机号码格式是否合法
function check_box(){
	if($('.icon-ok-sign').hasClass('boxcol')){
		return true;
	}
	else{
		document.getElementById("result").innerHTML="<font color='#ff0000'>请同意协定!</font>";
		return false;
	}
	
}

//判断手机号码格式是否合法
function check_tel(obj){
	 var objvalue=obj.value;
	 var regx=/^(?:13\d|15\d|18[123456789])-?\d{5}(\d{3}|\*{3})$/;
	 if(regx.test(objvalue)){
		 return true;
	 }else{
		 document.getElementByIdx_x("checktel").innerHTML="<font color='#ff0000'>请输入正确的手机号!</font>";
		 return false;
	 }
}


//点击按钮启动检测
function check(){
	
	//telphone = document.getElementById("tel");
	mail = document.getElementById("mail");
	mima1 = document.getElementById("password");
	mima2 = document.getElementById("password2");
	secury_mima = document.getElementById("password");
	
	//check_tel(telphone);//检测电话
	
	if(!check_mail(mail))
		return false;//检测邮箱
	
	if(!check_psw(secury_mima))
		return false;//检测密码是否合法
	
	if(!check_repsw(mima1,mima2))
		return false;//检测两次密码是否一致
	
	if(!check_box())
		return false;
	
	var variable = mail.value;
	var password = $.md5(mima1.value);

	var ldata = {userinp:variable,password:password};
	
	$.ajax({
        url: '/textAnalysis/login/check',
        type: 'post',
        dataType: 'json',
        async: true,
        data: ldata,
        success:function(data){
	        if (data.result ==  "1") {
	        	document.getElementById("result").innerHTML="<font color='#ff0000'>用户已被注册!</font>";
	        	return false;
	        } else {
	        	window.location.href="/textAnalysis/system/main"; 
	            return false;
	        }  
        },
        error:function(){
        	alert("登录失败");   
        }
    });
}