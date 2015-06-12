
var webUtil=webUtil||{};







webUtil.getID=function(id){

    return document.getElementById(id)

};









webUtil.jDialog={
	
	Mask:function(dia,confirmCallback,cancelCallback){
		if(typeof($('#overlayModal')[0])=='undefined'){
			
			$('body').append('<div id="overlayModal"></div>');
			var mod=$('#overlayModal');
			var tmpWidth=$(document.body).width();
			var tmpHeight=$(document.body).height();

			mod.css({position:'absolute',left:0,top:0,right:0,bottom:0,width:tmpWidth,height:tmpHeight,background:'#000000',zIndex:9997}).fadeTo(0,0.3);
			webUtil.jDialog.Event(dia,mod,confirmCallback,cancelCallback);
			
			}
		},
		
	Event:function(dia,mod,confirmCallback,cancelCallback){
		dia.find('.closeModal').click(function(){
			dia.fadeOut();
			mod.fadeOut(function(){mod.remove()});
			if(cancelCallback!=false)cancelCallback();
			return;
			});
			
	    dia.find('.verifyModal').click(function(){
			dia.fadeOut();
			mod.fadeOut(function(){mod.remove()});
			if(confirmCallback!=false)confirmCallback();
			return;
			});
			
	 document.onkeydown=function(event){
		 event=window.event||event;
		 var keyCode=event.keyCode||event.which||event.charCode;
		 if(event.keyCode==27){
			 dia.fadeOut();
			 mod.fadeOut(function(){mod.remove()});
			 if(cancelCallback!=false)cancelCallback();
			 return}
		   }},
	
	   Pois:function(dia){
		   var tmpH=dia.height();
		   var tmpW=dia.width();
		   var tmpL=($(window).width()-tmpW)/2;
		   var tmpT=($(window).height()-tmpH)/2+$(document).scrollTop();

 
		   //var tmpL = ($(window).width()-tmpW)/2;
		  // var tmpT=($(window).height()-tmpH)/2;
		   dia.css({left:tmpL,top:tmpT,width:tmpW,zIndex:9999}).fadeIn(200);
		   },
	
	
	
	
	  Modal:function(jdia,undia){
		  var dia=$('#'+jdia);
		  if(webUtil.getID(jdia)&&webUtil.getID(undia)){
			  webUtil.jDialog.Mask(dia,false,false);
			  webUtil.jDialog.Pois(dia);
			  //webUtil.mDrag(jdia,undia);
			  }
			},

	
	getPois:function(a,b){
		this._b=null;
		if(b===null||b===''||typeof b==='undefined'){
			return a.width()
			}else{
			return b
			};
			this._b=b
		  }
	
	};

webUtil.mDrag=function(a,b){
	var bDrag=false;
	var disX=0,disY=0;
	var win=webUtil.getID(a);
	var obj=webUtil.getID(b);
	if(win&&obj){
		obj.onmousedown=function(event){(event||window.event).cancelBubble=true};
		win.onmousedown=function(event){
		event=event||window.event;
		bDrag=true;
		disX=event.clientX-win.offsetLeft;
		disY=event.clientY-win.offsetTop;
		this.setCapture&&this.setCapture();
		return false;
		};
		
		document.onmousemove=function(event){
			if(!bDrag)return;
			event=event||window.event;
			var iL=event.clientX-disX;
			var iT=event.clientY-disY;
			var maxL=document.documentElement.clientWidth-win.offsetWidth;
			var maxT=document.documentElement.scrollHeight-win.offsetHeight;
			iL=iL<0?0:iL;
			iL=iL>maxL?maxL:iL;
			iT=iT<0?0:iT;
			iT=iT>maxT?maxT:iT;
			win.style.marginTop=win.style.marginLeft=0;
			win.style.left=iL+'px';
			win.style.top=iT+'px';
			return false;
			};
		document.onmouseup=window.onblur=win.onlosecapture=function(){
			bDrag=false;
			win.releaseCapture&&win.releaseCapture()
			}
		}
	};









	
