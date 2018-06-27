/*第一个div word_segmentation实现三级联动所需的数据*/
var tools = $("#segmentation＿name").val();//从前台获取数据
var theProjectsObj = JSON.parse(tools); //数据转为json格式
var temp = theProjectsObj[0];//得到的json形式为[{}]，所以要想获得json格式那么就必须获得第零个元素。

/*第二个div feature exatration实现三级联动*/
var extraction＿name = $("#extraction＿name").val();
var theExtractioName = JSON.parse(extraction＿name); 
var temp1 = theExtractioName[0];

/*第三个div clustering实现三级联动*/
var cluster_value = $("#clustering＿name").val();
var cluster_json =  JSON.parse(cluster_value);
var cluster_methods = cluster_json[0];
var cluster_projectNames = cluster_methods['Kmeans'][0];


/*第四个div classification实现三级联动*/
var classification = $("#classification＿name").val();
var classification_json = JSON.parse(classification);
var classification_Name = classification_json[0];

/*第五个div sentiment*/
var sentiment = $("#analysis＿name").val();
var sentiment_json = JSON.parse(sentiment);
var sentiment_Name = sentiment_json[0];

/*第六个div text statistic*/
var statistics = $("#statistics＿name").val();
var statistics_json = JSON.parse(statistics);
var statistics_Name = statistics_json[0];

//用户点击下载的时候会将content里面的内容
var content = "";

$(document).ready(function () {
	
	var i = 1;//i的作用就是实现select的三级联动，并且以第一级的第一个项目未基准
	var j = 1;//j的作用就是实现三级联动的第二层级的限制
	var show = 1;//实现三级联动的第三层级限制
	for(var key1 in temp){
		$("#select_id_segmentation1").append("<option value='"+key1+"'>"+key1+"</option>");//添加第一层select的工具名
		var toolsName = temp[key1][0];//0的作用相当于去括号
		if(i == 1){
			for(var key2 in toolsName){
				$("#select_id_segmentation2").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层select的文件名
				if(j == 1){
					var projectName = toolsName[key2][0];
					for(var key3 in projectName){
						$("#select_id_segmentation3").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层select的文件名
						var addPara = "";
						content ="";//content在添加内容之前必须要先清空才可以。
						
						if(show == 1){
							var value = projectName[key3];
							for(var key4 = 0;key4<value.length;key4++){//网页中添加显示的文本
								addPara = addPara+ "<span class =\"text text-word text-d\">"+value[key4]+"</span>";
								content = content+value[key4]+"\n";//往content中添加内容，同时加入换行符
							}
							$("#span_span").html(addPara);
						}
						show++;
					}
				}
				
				j++;
			}
		}
		i++;
	}
});

$("#1").click(function(){
	
    // 先清空第二级的select和第三级的select
	$("#select_id_segmentation1").empty();
    $("#select_id_segmentation2").empty();
    $("#select_id_segmentation3").empty();
	
	var i = 1;//i的作用就是实现select的三级联动，并且以第一级的第一个项目未基准
	var j = 1;//j的作用就是实现三级联动的第二层级的限制
	var show = 1;//实现三级联动的第三层级限制
	for(var key1 in temp){
		$("#select_id_segmentation1").append("<option value='"+key1+"'>"+key1+"</option>");//添加第一层select的工具名
		var toolsName = temp[key1][0];//0的作用相当于去括号
		if(i == 1){
			for(var key2 in toolsName){
				$("#select_id_segmentation2").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层select的文件名
				if(j == 1){
					var projectName = toolsName[key2][0];
					for(var key3 in projectName){
						$("#select_id_segmentation3").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层select的文件名
						var addPara = "";
						content ="";//content在添加内容之前必须要先清空才可以。
						
						if(show == 1){
							var value = projectName[key3];
							for(var key4 = 0;key4<value.length;key4++){//网页中添加显示的文本
								addPara = addPara+ "<span class =\"text text-word text-d\">"+value[key4]+"</span>";
								content = content+value[key4]+"\n";//往content中添加内容，同时加入换行符
							}
							$("#span_span").html(addPara);
						}
						show++;
					}
				}
				
				j++;
			}
		}
		i++;
	}
		
});

$("#select_id_segmentation1").change(function(){
    // 先清空第二级的select和第三级的select
     $("#select_id_segmentation2").empty();
     $("#select_id_segmentation3").empty();
	 //获取第一级的选中Value
	 var checkValue=$("#select_id_segmentation1").val();
	 var j = 1;
	 var toolsName = temp[checkValue][0];//0的作用相当于去括号
	 for(var key2 in toolsName){
		$("#select_id_segmentation2").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层的文件名
		var projectName = toolsName[key2][0];
		if(j == 1){
			for(var key3 in projectName){
				$("#select_id_segmentation3").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层的文件名
			}
	     }
		 j++;
	  }
	 
});


$("#select_id_segmentation2").change(function(){
	
	//获取第一级的选中Value
	var checkValue1=$("#select_id_segmentation1").val();
	//获取第二级的选中Value
	var checkValue2=$("#select_id_segmentation2").val();
	 
	var toolsName = temp[checkValue1][0];//0的作用相当于去json中的括号
	var projectName = toolsName[checkValue2][0];
    // 清空第三个select
    $("#select_id_segmentation3").empty();

    // 实际的应用中，这里的option一般都是用循环生成多个了
     for(var key3 in projectName){
		$("#select_id_segmentation3").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层的文件名
	 }
});

$("#select_id_segmentation3").change(function(){
	var tools = $("#select_id_segmentation1").val();
	var projects = $("#select_id_segmentation2").val();
	var files = $("#select_id_segmentation3").val()
	var value = temp[tools][0][projects][0][files];
	var addPara = "";
	content ="";
	for(var key4=0;key4<value.length;key4++){
		addPara = addPara+ "<span class =\"text text-word text-d\">"+value[key4]+"</span>";
		content = content+ value[key4] + "\n";
	}
	$("#span_span").html(addPara);
});


function downloadFile(){
	
	var fileName = $("#select_id_segmentation3").val();
    var aLink = $("#test_segmentation");
    var blob = new Blob([content],{endings :"native"});
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", false, false);//initEvent
    aLink.download = fileName;
    $("#test_segmentation").attr("download",fileName);
    //aLink.href = URL.createObjectURL(blob);
    $("#test_segmentation").attr("href",URL.createObjectURL(blob));
    alert(aLink.href);
    aLink.dispatchEvent(evt);//自动执行事件
    //$("#test_segmentation").trigger("click");
    
}


/***************************************************
 *                                                 *
 *    feature 模块开始                                *
 *                                                 *
 * *************************************************/

/*选择第二个模块，也就是feature extraction模块*/
$("#2").click(function (){
	$("#select_id_extraction1").empty();
	$("#select_id_extraction2").empty();
	$("#extraction-pills").addClass(" active in");//div显示
	$("#morris-bar-chart").empty();//清除morris-bar-chart中的上一个表格
	var j = 1;
	var data_chart=[];//用来存储要显示在表中的数据
	content = "";
	for(var key2 in temp1){
		$("#select_id_extraction1").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层的文件夹名
		var projectName = temp1[key2][0];
		if(j == 1){
			
			var addPara = "<tbody>";
			var show = 1;
			for(var key3 in projectName){
				
				$("#select_id_extraction2").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层的文件名
				var addPara = "<tbody>";
				//var data = new formData();
				if(show == 1){
					var value = projectName[key3];
					for(var key4=0;key4<value.length;key4++){//网页中添加显示的table文本
						
						var strs= new Array(); //定义一数组 
						strs=value[key4].split("\t"); //字符分割 
						
						addPara = addPara+ "<tr class=\"odd gradeX\"><td>"+strs[0]+"</td><td>"+strs[1]+"</td></tr>";
						
						//如果有占比的话，就显示
						if(strs.length == 3){
							data_chart.push(new ObjStory(strs[0],strs[2]));
						}
							
						content = content+value[key4]+"\n";
					}
					addPara = addPara + "</tbody>";
					$("#table1").html(addPara);
				}
				show++;
			}
	     }
		 j++;
	}
	 
	
	makeChart_zhuzhuangtu(data_chart);

});

/*点击第一个下拉框*/
$("#select_id_extraction1").change(function(){
	
	$("#select_id_extraction2").empty();
	
	//获取第一级的选中Value
	var checkValue1=$("#select_id_extraction1").val();
	 
	var projectName = temp1[checkValue1][0];
	
    // 清空第三个select
    $("#select_id_extraction2").empty();

    // 重新填写数据
     for(var key3 in projectName){
		$("#select_id_extraction2").append("<option value='"+key3+"'>"+key3+"</option>");//添加第三层的文件名
	 }
});

/*点击第二个下拉框*/
$("#select_id_extraction2").change(function(){
	
	$("#morris-bar-chart").empty();
	//获取第一级的选中Value
	var checkValue1=$("#select_id_extraction1").val();
	 	
	//获取第一级的选中Value
	var checkValue2=$("#select_id_extraction2").val();
	
	var projectName = temp1[checkValue1][0];
	
	var fileName = projectName[checkValue2];

	var addPara = "<tbody>";
	var data = [];
	content="";
	for(var key4=0;key4<fileName.length;key4++){//网页中添加显示的table文本
		
		var strs= new Array(); //定义一数组 
		strs=fileName[key4].split("\t"); //字符分割 
		
		addPara = addPara+ "<tr class=\"odd gradeX\"><td>"+strs[0]+"</td><td>"+strs[1]+"</td></tr>";
		
		if(strs.length == 3){
			data.push(new ObjStory(strs[0],strs[2]));
		}
			
		content = content+fileName[key4]+"\n";
	}
	addPara = addPara + "</tbody>";
	$("#table1").html(addPara);
	
	makeChart_zhuzhuangtu(data);
});

function ObjStory(y,a) //声明对象
{
    this.y = y;
    this.a = a; 
}

function ObjStory1(label,value) //声明对象
{
    this.label= label;
    this.value= value;
}

function downloadFile_extraction(){
	
	var fileName = $("#select_id_extraction2").val();
    var aLink = $("#test_extraction");
    var blob = new Blob([content],{endings :"native"});
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", false, false);//initEvent
    aLink.download = fileName;
    $("#test_extraction").attr("download",fileName);
    //aLink.href = URL.createObjectURL(blob);
    $("#test_extraction").attr("href",URL.createObjectURL(blob));
    //alert(aLink.href);
    aLink.dispatchEvent(evt);//自动执行事件
	
}

/***************************************************
 *                                                 *
 *    feature 模块结束                                *
 *                                                 *
 * *************************************************/


/***************************************************
 *                                                 *
 *    cluster 模块开始                                *
 *                                                 *
 * *************************************************/

$("#3").click(function(){
	
    // 先清空第一级的select和第二级的select
	$("#select_id_clustering1").empty();
    $("#select_id_clustering2").empty();
	var j = 1;//j的作用就是实现二级联动的第一层级的限制
	var show = 1;
	for(var key2 in cluster_projectNames){
		
		$("#select_id_clustering1").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第一层的文件夹名
		var cluster_projectName = cluster_projectNames[key2];
		
		if(j == 1){
			for(var key3 in cluster_projectName){
				var pictureName = cluster_projectName[key3].split("/");
				$("#select_id_clustering2").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第二层的文件名
				if(show == 1){
					//显示图片
					
					var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+cluster_projectName[key3];
					var image = "<img src ="+imagePath+" width=\"90%\"/>";
					$("#image_1").html(image);
				}
				show ++ ;
			}
	     }
		 j++;
	}
	
});

$("#select_id_clustering1").change(function(){
	
	$("#select_id_clustering2").empty();
	var value = $("#select_id_clustering1").val();
	var cluster_projectName = cluster_projectNames[value];
		for(var key3 in cluster_projectName){
			var pictureName = cluster_projectName[key3].split("/");
			$("#select_id_clustering2").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第二层的文件名
			if(show == 1){
				//显示图片
				var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+cluster_projectName[key3];
				var image = "<img src ="+imagePath+" width=\"90%\"/>";
				$("#image_1").html(image);
			}
			show ++ ;
		}

	
});

$("#select_id_clustering2").change(function(){
	
	var value1 = $("#select_id_clustering1").val();
	var value2 = $("#select_id_clustering2").val();
	var cluster_projectName = cluster_projectNames[value1];
	for(var key3 in cluster_projectName){
		var pictureName = cluster_projectName[key3].split("/");
		if(pictureName[pictureName.length-1] == value2){
			//显示图片
			var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+cluster_projectName[key3];
			var image = "<img src ="+imagePath+" width=\"90%\"/>";
			$("#image_1").html(image);
			return true;
		}
	}
});

function downloadFile_clustering(){
	
	var value1 = $("#select_id_clustering1").val();
	var value2 = $("#select_id_clustering2").val();
	var cluster_projectName = cluster_projectNames[value1];
	for(var key3 in cluster_projectName){
		var pictureName = cluster_projectName[key3].split("/");
		if(pictureName[pictureName.length-1] == value2){
			//显示图片
			window.location.href="http://localhost:8088/textAnalysis/project/image?filePath="+cluster_projectName[key3];
			return true;
		}
	}
}

/***************************************************
 *                                                 *
 *    cluster 模块结束                                *
 *                                                 *
 * *************************************************/

/***************************************************
 *                                                 *
 *    classification 模块开始                         *
 *                                                 *
 * *************************************************/
$("#4").click(function(){
	
	$("#select_id_classification1").empty();
	$("#select_id_classification2").empty();
	$("#select_id_classification3").empty();
	
	var i = 1;//i的作用就是实现select的三级联动，并且以第一级的第一个项目未基准
	var j = 1;//j的作用就是实现三级联动的第二层级的限制
	var show = 1;//实现三级联动的第三层级限制
	for(var key1 in classification_Name){
		$("#select_id_classification1").append("<option value='"+key1+"'>"+key1+"</option>");//添加第一层select的工具名
		var toolsName = classification_Name[key1][0];//0的作用相当于去括号
		if(i == 1){
			for(var key2 in toolsName){
				$("#select_id_classification2").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层select的文件名
				if(j == 1){
					var projectName = toolsName[key2];
					
					for(var key3 in projectName){
						
						var pictureName = projectName[key3].split("/");
						$("#select_id_classification3").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第三层select的文件名
						if(show == 1){
							//显示图片
							var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+projectName[key3];
							var image = "<img src ="+imagePath+" width=\"90%\"/>";
							$("#image_2").html(image);
						}
						show ++ ;
					}
				}
				
				j++;
			}
		}
		i++;
	}
});

$("#select_id_classification1").change(function(){
	
	$("#select_id_classification2").empty();
	$("#select_id_classification3").empty();
	var j = 1;//j的作用就是实现三级联动的第二层级的限制
	var show = 1;//实现三级联动的第三层级限制
	var key1 = $("#select_id_classification1").val();
	var toolsName = classification_Name[key1][0];//0的作用相当于去括号
			for(var key2 in toolsName){
				$("#select_id_classification2").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第二层select的文件名
				if(j == 1){
					var projectName = toolsName[key2];
					
					for(var key3 in projectName){
						
						var pictureName = projectName[key3].split("/");
						$("#select_id_classification3").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第三层select的文件名
						if(show == 1){
							//显示图片
							
							var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+projectName[key3];
							var image = "<img src ="+imagePath+" width=\"90%\"/>";
							$("#image_2").html(image);
						}
						show ++ ;
					}
				}
				
				j++;
			}
});

$("#select_id_classification2").change(function(){
	$("#select_id_classification3").empty();
	var value1 = $("#select_id_classification1").val();
	var value2 = $("#select_id_classification2").val();
	var projectName = classification_Name[value1][0][value2];
			
			for(var key3 in projectName){
				var pictureName = projectName[key3].split("/");
				$("#select_id_classification3").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第三层select的文件名
				if(show == 1){
					//显示图片
					
					var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+projectName[key3];
					var image = "<img src ="+imagePath+" width=\"90%\"/>";
					$("#image_2").html(image);
				}
				show ++ ;
			}

});

$("#select_id_classification3").change(function(){
	
	var value1 = $("#select_id_classification1").val();
	var value2 = $("#select_id_classification2").val();
	var value3 = $("#select_id_classification3").val();
	var projectName = classification_Name[value1][0][value2];
	for(var key3 in projectName){
		var pictureName = projectName[key3].split("/");
		
		if(pictureName[pictureName.length-1] == value3){
			//显示图片
			
			var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+projectName[key3];
			var image = "<img src ="+imagePath+" width=\"90%\"/>";
			$("#image_2").html(image);
		}
	}
});

/***************************************************
 *                                                 *
 *    classification 模块结束                         *
 *                                                 *
 * *************************************************/

/***************************************************
 *                                                 *
 *    sentiment 模块结束                         *
 *                                                 *
 * *************************************************/

$("#5").click(function(){
	
	$("#analysis-pills").addClass(" active in");
	$("#morris-donut-chart").empty();
	$("#select_id_analysis1").empty();
	$("#select_id_analysis2").empty();
	
	var data = [];
	var show = 1;
	content = "";
	for(var key1 in sentiment_Name){//网页中添加显示的table文本
		$("#select_id_analysis1").append("<option value='"+key1+"'>"+key1+"</option>");	//添加第一层的文件夹名
		var strs=sentiment_Name[key1][0]["result.txt"];
		if(show == 1){
			for(var str in strs){
				content = "";
				var temp = strs[str].split(" ");				
				$("#select_id_analysis2").append("<option value='"+temp[0]+"'>"+temp[0]+"</option>");	//添加第二层的文件夹名
				if(str == 0){
					data.push(new ObjStory1("Negative index",temp[2]));
					data.push(new ObjStory1("Positive index",temp[4]));
				}
			}
		}
		show++;
	}
	makeChart_bingzhuangtu(data);
	
});

$("#select_id_analysis1").change(function(){
	$("#analysis-pills").addClass(" active in");
	$("#morris-donut-chart").empty();
	$("#select_id_analysis2").empty();
	var data = [];
	var value1 = $("#select_id_analysis1").val();
	var strs=sentiment_Name[value1][0]["result.txt"];
		for(var str in strs){
		
			var temp = strs[str].split(" ");				
			$("#select_id_analysis2").append("<option value='"+temp[0]+"'>"+temp[0]+"</option>");	//添加第二层的文件夹名
			if(str == 0){
				data.push(new ObjStory1("Negative index",temp[2]));
				data.push(new ObjStory1("Positive index",temp[4]));
			}
		}
		makeChart_bingzhuangtu(data);
});

$("#select_id_analysis2").change(function(){
	$("#analysis-pills").addClass(" active in");
	$("#morris-donut-chart").empty();
	var value1 = $("#select_id_analysis1").val();
	var value2 = $("#select_id_analysis2").val();
	var strs=sentiment_Name[value1][0]["result.txt"];
	var data = [];
	for(var str in strs){
		var temp = strs[str].split(" ");
		if(temp[0] == value2){
			data.push(new ObjStory1("Negative index",temp[2]));
			data.push(new ObjStory1("Positive index",temp[4]));
		}
	}
	makeChart_bingzhuangtu(data);
});
/***************************************************
 *                                                 *
 *    classification 模块结束                         *
 *                                                 *
 * *************************************************/

/***************************************************
 *                                                 *
 *    classification 模块结束                         *
 *                                                 *
 * *************************************************/

$("#6").click(function(){
	
    // 先清空第一级的select和第二级的select
	$("#select_id_statistics1").empty();
    $("#select_id_statistics2").empty();
	var j = 1;//j的作用就是实现二级联动的第一层级的限制
	var show = 1;
	for(var key2 in statistics_Name){
		
		$("#select_id_statistics1").append("<option value='"+key2+"'>"+key2+"</option>");	//添加第一层的文件夹名
		var statistics_projectName = statistics_Name[key2];
	
		if(j == 1){
			for(var key3 in statistics_projectName){
				var pictureName = statistics_projectName[key3].split("/");
				$("#select_id_statistics2").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第二层的文件名
				if(show == 1){
					//显示图片
					
					var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+statistics_projectName[key3];
					var image = "<img src ="+imagePath+"  width=\"70%\" style=\"margin-left:15%\"/>";
					$("#iamge_3").html(image);
				}
				show ++ ;
			}
	     }
		 j++;
	}
});

$("#select_id_statistics1").change(function(){
	
	$("#select_id_statistics2").empty();
	var value = $("#select_id_statistics1").val();
	var statistics_projectName = statistics_Name[value];
	var show = 1;
		for(var key3 in statistics_projectName){
			var pictureName = statistics_projectName[key3].split("/");
			$("#select_id_statistics2").append("<option value='"+pictureName[pictureName.length-1]+"'>"+pictureName[pictureName.length-1]+"</option>");//添加第二层的文件名
			if(show == 1){
				//显示图片
				var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+statistics_projectName[key3];
				var image = "<img src ="+imagePath+"  width=\"70%\" style=\"margin-left:15%\"/>";
				$("#iamge_3").html(image);
			}
			show ++ ;
		}

	
});

$("#select_id_statistics2").change(function(){
	var value1 = $("#select_id_statistics1").val();
	var value2 = $("#select_id_statistics2").val();
	var statistics_projectName = statistics_Name[value1];
	for(var key3 in statistics_projectName){
		var pictureName = statistics_projectName[key3].split("/");
		if(pictureName[pictureName.length-1] == value2){
			//显示图片
			var imagePath = "http://localhost:8088/textAnalysis/project/image?filePath="+statistics_projectName[key3];
			var image = "<img src ="+imagePath+"  width=\"70%\" style=\"margin-left:15%\"/>";
			$("#iamge_3").html(image);
			return true;
		}
	}
});

/***************************************************
 *                                                 *
 *    classification 模块结束                         *
 *                                                 *
 * *************************************************/