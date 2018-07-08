
//通过obj来判断是进行结束操作还是进行下一步操作
function check(obj){
	
	/*收集数据传送到后台*/
	var file;
	var formData = new FormData();
	
	formData.append("choice",obj);
	//项目名称
	var projectName = document.getElementById("projectName").value;
	formData.append("projectName",projectName);
	
	//项目简介
	var projectInstruction = document.getElementById("projectInstruction").value;
	formData.append("projectInstruction",projectInstruction);

	//判断分词工具的选择情况
	var smartChineseAnalyzer = $("#smartChineseAnalyzer").prop('checked');
	formData.append("smartChineseAnalyzer",smartChineseAnalyzer);
	
	var IK = $("#IK").prop('checked');
	formData.append("IK",IK);
	
	var ansj = $("#ansj").prop('checked');
	formData.append("ansj",ansj);
	
	var jieba = $("#jieba").prop('checked');
	formData.append("jieba",jieba);
	
	var hanlp = $("#hanlp").prop('checked');
	formData.append("hanlp",hanlp);
	
	//如果项目名不为空，而且文件也不为空则允许上传
	if (checkProjectName(projectName) && checkFile()){
		//存储的是一段文本
		if(document.getElementById("File1").value==""){
			
			if(obj==1){
				alert("在只提交一段文本的情况下不能够选择分类算法和聚类算法！现在为您进行分词操作。");
			}
			
			formData.append("files",$("#File2").val());
			$.ajax({
		        url: '/textAnalysis/project/createProjectText',
		        type: 'post',
		        data: formData,
		        processData: false,// 告诉jQuery不要去处理发送的数据
		        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
		        dataType:"json",
		        async: false,
		        success:function(data){
			        if (data.saveResult ==  "success") {
			        	alert("文档创建成功，请耐心等待数据分析结果");
			        	window.location.href="/textAnalysis/system/createProject";
			        	return true;
			        	
			        } else {
			        	alert("失败"); 
			            return false;
			        } 
		        },
		        error:function(){
		        	alert("失败");   
		        }
		    });
		}
		else{
			var docNode = document.getElementById("File1").files;
			//存储的是txt文件和rar文件
			for(var i = 0; i<docNode.length;i++){
				formData.append('files[]',document.getElementById("File1").files[i]);
			}
			$.ajax({
		        url: '/textAnalysis/project/createProjectFile',
		        type: 'post',
		        data: formData,
		        processData: false,// 告诉jQuery不要去处理发送的数据
		        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
		        dataType:"json",
		        async: false,
		        success:function(data){
		        	
			        if (data.saveResult ==  "success") {
			        	if(data.result=="1"){
			        		window.location.href="/textAnalysis/project/ProjectMoreFunction?projectId="+data.projectId; 
			        		return true;
			        	}
			        	else{
			        		alert("文档创建成功，请耐心等待数据分析结果");
			        		window.location.href="/textAnalysis/system/createProject";
			        		return true;
			        	}
			        	
			        } else {
			        	alert("失败"); 
			            return false;
			        } 
		        },
		        error:function(){
		        	alert("失败");   
		        }
		    });
		}
		
	}
	else {
			return false;
	}
}

function ifItIsTxtOrRar(obj){

	    textExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(textExt=='.zip' || textExt=='.rar'){
	    	return true;
	    }
	    else{
	        alert("请上传后缀名为rar、zip类型的压缩文件!");
	        var file = document.getElementById("File1");
	        file.outerHTML=file.outerHTML;  
	        return false;
	    }
	    
}

function Finish(){

	var formData = new FormData();
	var chooseNode;
	
	var radionum = document.getElementsByName("optionsRadiosInline");
	for(var i=0;i<radionum.length;i++){
		if(radionum[i].checked)
			chooseNode = radionum[i].value;
	}
	
	if(chooseNode == "option1")
		formData.append("chooseNode",false);
	else
		formData.append("chooseNode",true);
	
	
	var KNN = $("#KNN").prop('checked');
	formData.append("KNN",KNN);
	
	var KNN_knowledge = $("#KNN_knowledge").prop('checked');
	formData.append("KNN_knowledge",KNN_knowledge);
	
	var SVM = $("#SVM").prop('checked');
	formData.append("SVM",SVM);
	
	//如果SVM算法被选择,则赋输入值
	if(SVM){
		//如果SVM_c有输入值
		if($("#SVM_c").val()){
			formData.append("SVM_c",$("#SVM_c").val());
		}
		else{
			formData.append("SVM_c","200");
		}
			
		if($("#SVM_toler").val()){
			formData.append("SVM_toler",$("#SVM_toler").val());
		}
		else{
			formData.append("SVM_toler","0.0001");
		}
		
		if($("#SVM_max").val()){
			formData.append("SVM_max",$("#SVM_max").val());
		}
		else{
			formData.append("SVM_max","1000");
		}
		
		if($("#SVM_select").val()=="linear"){
			formData.append("SVM_select","linear_"+$("#SVM_test").val());
		}
		else if($("#SVM_select").val()=="rbf"){
			formData.append("SVM_select","rbf_"+$("#SVM_test").val());
		}
		else if($("#SVM_select").val()=="poly"){
			formData.append("SVM_select","poly_"+$("#SVM_test").val());
		}
		else{
			formData.append("SVM_select","0");
		}
	}//如果SVM算法没被选择,则全部都赋值为0
	else{
		formData.append("SVM_c","0");
		formData.append("SVM_toler","0");
		formData.append("SVM_max","0");
		formData.append("SVM_select","0");
	}
	
	var SVM_knowledge = $("#SVM_knowledge").prop('checked');
	formData.append("SVM_knowledge",SVM_knowledge);
	
	var Linear_programming = $("#Linear_programming").prop('checked');
	formData.append("Linear_programming",Linear_programming);
	
	if(Linear_programming){
		//如果Linear_c有输入值,则赋值
		if($("#Linear_c").val()){
			formData.append("Linear_c",$("#Linear_c").val());
		}
		else{
			formData.append("Linear_c","200");
		}
		
		if($("Linear_toler").val()){
			formData.append("Linear_toler",$("#Linear_toler").val());
		}
		else{
			formData.append("Linear_toler","0.0001");
		}
		
		if($("Linear_max").val()){
			formData.append("Linear_max",$("#Linear_max").val());
		}
		else{
			formData.append("Linear_max","1000");
		}
	}
	else{
		formData.append("Linear_c","0");
		formData.append("Linear_toler","0");
		formData.append("Linear_max","0");
	}
	
	var Deep_learning  =$("#Deep_learning").prop('checked');
	formData.append("Deep_learning",Deep_learning);
	
	var Kmeans = $("#Kmeans").prop('checked');
	formData.append("Kmeans",Kmeans);
	
	//如果Kmeans分类算法被选择,则赋输入值
	if(Kmeans){
		//如果Kmeans_class有输入值,则赋值
		if($("#Kmeans_class").val()){
			formData.append("Kmeans_class",$("#Kmeans_class").val());
		}
		else{
			formData.append("Kmeans_class","3");
		}
		
	}//如果Kmeans算法没被选择，则设置为0
	else{
		formData.append("Kmeans_class","0");
	}
	
	var Kmeans_knowledge = $("#Kmeans_knowledge").prop('checked');
	formData.append("Kmeans_knowledge",Kmeans_knowledge);
	
	//如果Kmeans分类算法被选择,则赋输入值
	if(Kmeans_knowledge){
		//如果Kmeans_class有输入值,则赋值
		if($("#Kmeans_knowledge_class").val()){
			formData.append("Kmeans_knowledge_class",$("#Kmeans_knowledge_class").val());
		}
		else{
			formData.append("Kmeans_knowledge_class","3");
		}
		
	}//如果Kmeans算法没被选择，则设置为0
	else{
		formData.append("Kmeans_knowledge_class","0");
	}
	
	
	var projectId= $("#field＿name").val();
	formData.append("projectId",projectId);
	
	 console.log(formData);
	
	$.ajax({
		
		url:'/textAnalysis/project/createProjectMoreFunction',
        type: 'post',
        data: formData,
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        dataType:"json",
        async: false,
        success:function(data){
        	if(data.saveResult == "success"){
        		alert("保存成功"); 
        		window.location.href="/textAnalysis/system/createProject";
        		return true;
        	}
        	else{
        		alert("失败"); 
        		return false;
        	}
        },
        error:function(){
        	alert("失败");
        	return false;
        }
	});
}

//选中Kmeans的时候输入参数
function Kmeans(){
	//Kmeans处于选中模块
	if($("#Kmeans").prop('checked')){
		alert("请按照要求填写Kmeans参数");
		$("#Kmeans_value").show();
	}
	else{
		$("#Kmeans_value").hide();
	}
}

//选中Kmeans_knowledge的时候输入参数
function Kmeans_knowledge(){
	//Kmeans_knowledge处于选中模块
	if($("#Kmeans_knowledge").prop('checked')){
		alert("请按照要求填写Kmeans参数");
		$("#Kmeans_knowledge_value").show();
	}
	else{
		$("#Kmeans_knowledge_value").hide();
	}
}

//选中SVM的时候输入参数
function SVM(){
	//SVM处于选中模块
	if($("#SVM").prop('checked')){
		alert("请按照要求填写SVM参数");
		$("#SVM_test").remove('placeholder');
		$("#SVM_test").hide();
		$("#SVM_select").show();
		$("#SVM_value").show();
	}
	else{
		$("#SVM_value").hide();
	}
}

//选中Linear_programming的时候输入参数
function Linear_programming(){
	//Linear_programming处于选中模块
	if($("#Linear_programming").prop('checked')){
		alert("请按照要求填写参数");
		$("#Linear_value").show();
	}
	else{
		$("#Linear_value").hide();
	}
}

$("#SVM_select").change(function(){
	$("#SVM_select").hide();
	$("#SVM_test").attr('placeholder',$("#SVM_select").val());
	$("#SVM_test").show();
});

$("#reset").click(function(){
	$("#SVM_test").val("");
	$("#SVM_test").hide();
	$("#SVM_select").val("");
	$("#SVM_select").show();
});

function checkProjectName(projectName){
	//判断项目名称是不是为空
	if(projectName==""){
		alert("请输入项目名称");
		return false;
	}
	return true;
}

function checkFile(){
	//判断上传数据是不是为空
	if(document.getElementById("File1").value==""){
		alert("请上传数据");
		return false;
	}
	
	return true;
}