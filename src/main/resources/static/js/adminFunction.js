function changePath(){
//	var path = $('#changePath').val();
	var path = document.getElementById("changePath").value;
	
	if(path == ""){
		alert("新地址不能为空");
		return false;
	}
	else{
		$.ajax({
			url:"/textAnalysis/project/changePath?path="+path,
		
			success:function(data){
			
				if(data.result == "success"){
					alert("修改成功");
					document.getElementById("originalPath").value = path;
					document.getElementById("changePath").value = "";
				}
					
				else
					alert("修改失败");
			},
			error:function(data){
				alert("修改失败");
			}
		});
	}	
}