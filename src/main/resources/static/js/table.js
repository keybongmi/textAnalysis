 function makeChart_zhuzhuangtu(data_chart) {
	            /*MENU 
	            ------------------------------------*/
	            $('#main-menu').metisMenu();
				
	            $(window).bind("load resize", function () {
	                if ($(this).width() < 768) {
	                    $('div.sidebar-collapse').addClass('collapse')
	                } else {
	                    $('div.sidebar-collapse').removeClass('collapse')
	                }
	            });
	
	            
		       	 Morris.Bar({
		             element: 'morris-bar-chart',
		             data:data_chart,
		             xkey: 'y',
		             ykeys: ['a'],
		             labels: ['Series A'],
		    			 barColors: ['#2DAFCB'],
		             hideHover: 'auto',
		             resize: true
		         });
		       
	            
	        }
 
 function makeChart_bingzhuangtu(data_chart){
     /*MENU 
     ------------------------------------*/
     $('#main-menu').metisMenu();
		
     $(window).bind("load resize", function () {
         if ($(this).width() < 768) {
             $('div.sidebar-collapse').addClass('collapse')
         } else {
             $('div.sidebar-collapse').removeClass('collapse')
         }
     });

     /* MORRIS DONUT CHART
		----------------------------------------*/
     Morris.Donut({
         element: 'morris-donut-chart',
         data:data_chart
         ,
			   colors: [
					    '#2DAFCB',
					    '#F98484' 
					  ],
         resize: true
     });
	 
 }