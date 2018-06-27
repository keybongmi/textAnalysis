
	var tab = 'account_number';

	$('#num').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#pass').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	// 按钮是否可点击
	function checkBtn()
	{
		$(".log-btn").off('click');
		var inp = $.trim($('#num').val());
		var pass = $.trim($('#pass').val());
		if (inp != '' && pass != '') {
			$(".log-btn").removeClass("off");
			//sendBtn(); 
		} else {
			$(".log-btn").addClass("off");
		}
	}

	//检查用户名是不是为空
	function checkAccount(username){
		if (username == '') {
			$('.num-err').removeClass('hide').find("em").text('请输入账户');
			return false;
		} else {
			$('.num-err').addClass('hide');
			return true;
		}
	}

	//检查密码是不是为空
	function checkPass(pass){
		if (pass == '') {
			$('.pass-err').removeClass('hide').text('请输入密码');
			return false;
		} else {
			$('.pass-err').addClass('hide');
			return true;
		}
	}


	// 登录点击事件
	function sendBtn(){

			var inp = $.trim($('#num').val());
			var pass = $.md5($.trim($('#pass').val()));
			if (checkAccount(inp) && checkPass(pass)) {
				var ldata = {userinp:inp,password:pass};
				$.ajax({
			        url: '/textAnalysis/login/loginPost',
			        type: 'post',
			        dataType: 'json',
			        async: true,
			        data: ldata,
			        success:function(data){
				        if (data.result ==  "1") {
				        	document.getElementById("result").innerHTML="<font color='#ff0000'>用户不存在!</font>";
				        	document.getElementById("num").value="";
				        	document.getElementById("pass").value="";
				        	return false;
				        } else if(data.result == "2") {
				        	window.location.href="/textAnalysis/system/main"; 
				            return false;
				        } else if(data.result == "3") {
				        	document.getElementById("result").innerHTML="<font color='#ff0000'>密码不正确!</font>";
				        	document.getElementById("num").value="";
				        	document.getElementById("pass").value="";
				        	$('.pass').val("");
				             return false;
				        } 
			        },
			        error:function(){
			        	alert("登录失败");   
			        }
			    });
			} else {
					return false;
			}
	}

	
	// 登录的回车事件
	$(window).keydown(function(event) {
    	if (event.keyCode == 13) {
    		sendBtn();
    	}
    });

