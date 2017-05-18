$(function(){
	$('#myCheckInfo').css('height',$('#loadarea').height()-30 +'px');
	
	timeWidget('myCheckInfo');
	
	// 初始化
	var today = $('#myCheckInfo .calendarTable .currentDay').attr('data');
	var todayStr = today.substr(0,4) + '年' + today.substr(4,2) + '月' + today.substr(6,2) + '日的考勤信息';
	$('#myCheckInfo .myCheckInfoDetail .title').text(todayStr);
	
	// 点击日期，展示右边详细的考勤信息
	$('#myCheckInfo .calendarTable .currentMonth').click(function(){
		var day = $(this).attr('data');
		var str = day.substr(0,4) + '年' + day.substr(4,2) + '月' + day.substr(6,2) + '日的考勤信息';
		$('#myCheckInfo .myCheckInfoDetail .title').text(str);
		
	})
	
});

//时间控件
function timeWidget(obj){
	/******************时间控件*************************/
	/*
	1.声明dateObj变量，并赋初值为当前系统时间
	2.给div中渲染html元素
	3.通过dateObj获取当月1号的信息，并以此遍历出表格中所有日期
	4.绑定事件
	*/
    /*
    * 用于记录日期，显示的时候，根据dateObj中的日期的年月显示
    */
    var dateObj = (function(){
        var _date = new Date();    // 默认为当前系统时间
        return {
          getDate : function(){
            return _date;
          },
          setDate : function(date) {
            _date = date;
          }
        };
    })();
 
    // 初始化
    renderHtml();
    showCalendarData();
    
    // 绑定事件
    //点击上下月
    $("#"+ obj +" .calendarTop .prevMonth").click(function(){
    	var date = dateObj.getDate();
        dateObj.setDate(new Date(date.getFullYear(), date.getMonth() - 1, 1));
        showCalendarData();
    })
    $("#"+ obj +" .calendarTop .nextMonth").click(function(){
    	 var date = dateObj.getDate();
         dateObj.setDate(new Date(date.getFullYear(), date.getMonth() + 1, 1));
         showCalendarData();
    })
    
    //年月输入框变化事件
    $("#"+ obj +" .calendarTop .yearsSelect").change(function(){
    	var date = dateObj.getDate();
        dateObj.setDate(new Date($(this).val(), date.getMonth(), 1));
        showCalendarData();
    });
    $("#"+ obj +" .calendarTop .monthsSelect").change(function(){
    	var date = dateObj.getDate();
        dateObj.setDate(new Date(date.getFullYear(), $(this).val(), 1));
        showCalendarData();
    });
 
    /**
    * 渲染html结构
    */
    function renderHtml() {
        var titleBox = "<div class='calendarTitle'>";
        var bodyBox = "<div class='calendarBody'>";

        // 设置顶部盒子中的html
        var yearsOptions = '';
        var monthOptions = '';
        //生成年份
        for (var i = 2000; i < 2051; i++) {
          yearsOptions += '<option value="'+ i +'">'+ i +'年</option>'
        };
        //生成月份
        for (var i = 0; i < 12; i++) {
          monthOptions += '<option value="'+ i +'">'+ (i+1) +'月</option>'
        };
        titleBox += "<div class='calendarTop'>"+
			            "<select class='yearsSelect'>"+ yearsOptions +"</select>"+
			            "<span class='prevMonth'>&lt;</span>" +
			            "<select class='monthsSelect'>"+ monthOptions +"</select>"+
			            "<span class='nextMonth'>&gt;</span>"+
			          "</div></div>";       
          
        $("#"+ obj +" .Calendar").append(titleBox);    // 添加到calendar div中

        // 设置表格区的html结构
        var _headHtml = "<tr>" + 
                  "<th>日</th>" +
                  "<th>一</th>" +
                  "<th>二</th>" +
                  "<th>三</th>" +
                  "<th>四</th>" +
                  "<th>五</th>" +
                  "<th>六</th>" +
                "</tr>";
        var _bodyHtml = "";

        // 一个月最多31天，所以一个月最多占6行表格
        for(var i = 0; i < 6; i++) {  
          _bodyHtml += "<tr>" +
                  "<td></td>" +
                  "<td></td>" +
                  "<td></td>" +
                  "<td></td>" +
                  "<td></td>" +
                  "<td></td>" +
                  "<td></td>" +
                "</tr>";
        }
        bodyBox += "<table class='calendarTable'>" +
                  _headHtml + _bodyHtml +
                  "</table></div>";
        // 添加到calendar div中
        $("#"+ obj +" .Calendar").append(bodyBox);
    }
 
    /**
    * 表格中显示数据，并设置类名
    */
    function showCalendarData() {
        var _year = dateObj.getDate().getFullYear();
        var _month = dateObj.getDate().getMonth() + 1;
        var _dateStr = getDateStr(dateObj.getDate());

        // 设置顶部标题栏中的 年、月信息
        //年
        var yearsSelect = $("#"+ obj +" .yearsSelect").find('option');
        var monthsSelect = $("#"+ obj +" .monthsSelect").find('option');
        for(var data in yearsSelect){
          if(_dateStr.substr(0,4) == yearsSelect[data].value){
            yearsSelect[data].selected = true;
            break ;
          }
        }
        //月
        for(var data in monthsSelect){
          if(Number(_dateStr.substr(4,2)) == Number(monthsSelect[data].value)+1){
            monthsSelect[data].selected = true;
            break ;
          }
        }

        // 设置表格中的日期数据
        var _table = $("#"+ obj +" .calendarTable");
        var _tds = _table.find("td");
        var _firstDay = new Date(_year, _month - 1, 1);  // 当前月第一天

//        var workStatus = {
//        	'vacate':'请假',
//        	'normal':'正常',
//        	'absenteeism':'旷工',
//        	'out':'外出',
//        	'clocking':'迟到早退'
//        }
        for(var i = 0; i < _tds.length; i++) {
          var workStatus = '';
          var _thisDay = new Date(_year, _month - 1, i + 1 - _firstDay.getDay());
          var _thisDayStr = getDateStr(_thisDay);
          var str = '<span class="dateNember">' + _thisDay.getDate() + '</span>';
//          			'<ul>'+
//          				'<li>上班状态：<i></i></li>'+
//          			'</ul>';
          
          _tds.eq(i).html(str);          
          _tds.eq(i).attr('data', _thisDayStr);          
         
          if(_thisDayStr.substr(0, 6) == getDateStr(_firstDay).substr(0, 6)) { // 当前月
        	_tds.eq(i).attr('class','currentMonth');
        	
        	if(_thisDayStr < getDateStr(new Date())&&(_thisDay.getDay()!=0&&_thisDay.getDay()!=6)){//操作工作状态，给不同的类
//        		_tds.eq(i).find('span').addClass('prevDateNember');
//        		_tds.eq(i).find('i').text()
        	}        	 
          }else{    // 其他月
        	  _tds.eq(i).attr('class','otherMonth'); 
          }
          if(_thisDayStr == getDateStr(new Date())) {    // 当前天
          	_tds.eq(i).addClass('currentDay');
          }
          
        }
    }

    /**
    * 日期转化为字符串， 4位年+2位月+2位日
    */
    function getDateStr(date) {
        var _year = date.getFullYear();
        var _month = date.getMonth() + 1;    // 月从0开始计数
        var _d = date.getDate();
         
        _month = (_month > 9) ? ("" + _month) : ("0" + _month);
        _d = (_d > 9) ? ("" + _d) : ("0" + _d);
        return _year + _month + _d;
    }
}

