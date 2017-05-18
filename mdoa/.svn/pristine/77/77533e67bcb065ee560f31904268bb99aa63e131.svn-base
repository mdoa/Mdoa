
window.onload = function(){
	setTimeout(function(){
		var wx = $(window).width();   //获取可视区屏幕的宽度
		var body = document.getElementById('body');
		var stars = [];
		var countNum = 0;      //计数
		var countSp = 0;		//计数
		var timer = setInterval(function(){
			if(countNum%10 == 0){
				var w = 50;
				var h = 50;
				var x = getRun(0,wx);
				var y = getRun(0,20);
				var speedx = getRun(10,20);
				var speedy = getRun(12,24);
				var speedo = (Math.random()*0.02+0.02);
				var star = new Star(w,h,x,y,body,'../../img/login/star.png',1,speedx,speedy,speedo);
				star.createEle();
				stars.push(star);
				if(countNum == 80){
					countNum = 0;
				}
			}
			if(countSp%3 == 0){
				for(var i=(stars.length-1);i>=0;i--){
					if(stars[i].opacity <= 0){
						body.removeChild(stars[i].ele);
						stars.splice(i,1);
					}
					stars[i].move();
				}
				if(countSp == 60){
					countSP = 0;
				}
			}
			countNum++;
			countSp++;
		},1000/60);
	},0)
}
function fun(){
	var reamber = null;
	if($("input[name='reamberMe']").attr("checked") == 'checked'){
		reamber = 1;
	}
	if($("input[name='userAccount']").val() == ''){
		$("#login-tip").text("账号不能为空");
		return ;
	}
	if($("input[name='password']").val() == ''){
		$("#login-tip").text("密码不能为空");
		return ;
	}
	/*if($("input[name='authcode']").val() == ''){
		$("#login-tip").text("验证码不能为空");
		return ;
	}*/
	$("#login-tip").text("");
	
	$.ajax({
		data:{
			userAccount : $("input[name='userAccount']").val(),
			password : $("input[name='password']").val(),
			authcode : $("input[name='authcode']").val(),
			reamberMe : reamber
		},
		url:"../../user/login.do?getMs="+getMS(),
		type:"post",
		success:function(data){
			if(data == 200){
				window.location.href = '../../html/index/index.html';
			}else if(data == 400){
				$("#login-tip").text("密码或验证码错误，请重新输入！");
			}else if(data == 500){
				$("#login-tip").text("服务器异常，请联系管理员！");
			}
		},
		error:function(){
			
		}
	})
}
//获取毫秒数
function getMS(){
	var date = new Date();
	return date.getTime();
}

/*流星的运动*/
function getRun(min,max){
	return Math.ceil(Math.random()*(max-min)+min);
}
function Star(w,h,x,y,parent,bgSrc,opacity,speedx,speedy,speedo){
	this.w = w;
	this.h = h;
	this.x = x;
	this.y = y;
	this.parent = parent;
	this.bgSrc = bgSrc;
	this.opacity = opacity;
	this.speedx = speedx;
	this.speedy = speedy;
	this.speedo = speedo;
}
Star.prototype.createEle = function(){
	this.ele = document.createElement('div');
	this.ele.style.cssText ='width:'+this.w+'px;height:'+this.h+'px;background:url('+this.bgSrc+') no-repeat;left:'+this.x+'px;top:'+this.y
	+'px;position: absolute;filter:alpha(opacity='+this.opacity*100+');opacity:'+this.opacity+';';
	this.parent.appendChild(this.ele);
}
Star.prototype.move = function(){
	this.x = this.x+this.speedx;
	this.y = this.y+this.speedy;
	this.opacity -= this.speedo;
	this.ele.style.left = this.x +'px';
	this.ele.style.top = this.y +'px';
	this.ele.style.opacity = this.opacity;
}