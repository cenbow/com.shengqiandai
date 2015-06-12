if(typeof console=="undefined"){
	var console={
		log:function(){}
	}
}

MySocialShares=[];

$(document).ready(function(){
	$("div.mysocialshare").each(function(){
		MySocialShares.push(new MySocialShare(this))
	})
});

MySocialShare=function(e){
	var t=this;
	var n=e;
	var r=[];
	var i=document.URL;
	var s="bubble";
	var o="arc";
	var u=0;
	var a=180;
	var f=80;
	var l=70;
	var c="none";
	var h="picture";
	if($(n).attr("data-url"))i=$(n).attr("data-url");
	if($(n).attr("data-networks"))r=$(n).attr("data-networks").toLowerCase().split(",");
	if($(n).attr("data-orientation"))o=$(n).attr("data-orientation").toLowerCase();
	if($(n).attr("data-arc-start"))u=parseInt($(n).attr("data-arc-start"));
	if($(n).attr("data-arc-length"))a=parseInt($(n).attr("data-arc-length"));
	if($(n).attr("data-radius"))f=parseInt($(n).attr("data-radius"));
	if($(n).attr("data-gap"))l=parseInt($(n).attr("data-gap"));
	if($(n).attr("data-picture"))c=$(n).attr("data-picture");
	if($(n).attr("data-image-type"))h=$(n).attr("data-image-type").toLowerCase();
	if(c=="none"){
		console.log("MySocialShare | ERROR:data-picture not provided");
		return
	}
	var p=function(){
		if(!d[s]){
			console.log("MySocialShare | INFO:Invalid style supplied. Exiting.");
			return
		}
		$(n).addClass(s);
		template=d[s];
		if(h=="jieshao")c="#"+c+"";
		$(n).html(template["holder"].replace(/\[IMAGE_URL\]/gi,c));
		var e=$(n).find(".msb_network_holder");
		for(var t=0;t<r.length;t++){
			var i=r[t];
			if(!m[i]){
				console.log('MySocialShare | WARNING:Unsupported Network "'+i+'" specified. Skipping.');
				continue
			}
			var o=m[i];
			var u;
			if(o["profile_url"]&&$(n).attr("data-"+i+"-handle"))u=o["profile_url"].replace(/\[HANDLE\]/gi,$(n).attr("data-"+i+"-handle"));
			else continue;
			var a=template["network_button"].replace(/\[URL\]/gi,u).replace(/\[NAME\]/gi,o["name"]).replace(/\[NETWORK\]/gi,i);
			var f=$(a);
			e.append(f)
		}
		if(v[s])v[s](template)
	};
	
	var d={
		bubble:{holder:'<a class="msb_main" title="什么是微积金" alt="什么是微积金">'+'<img src="[IMAGE_URL]" title="什么是微积金" alt="什么是微积金"/>'+"</a>"+'<div class="msb_network_holder"></div>',
		network_button:'<a class="msb_network_button [NETWORK]" target="_blank" href="[URL]" data-network="[NETWORK]">[NAME]</a>',
		orientations:{
			arc:function(){
				if($(this).hasClass("disabled"))return;
				var e=250;
				var t=250;
				var r=$(n).find(".msb_network_button").length;
				var i=e+(r-1)*t;
				var s=0;
				var o=$(this).outerWidth();
				var l=$(this).outerHeight();
				var c=$(n).find(".msb_network_button:eq(0)").outerWidth();
				var h=$(n).find(".msb_network_button:eq(0)").outerHeight();
				var p=(o-c)/2;
				var d=(l-h)/2;
				if(!$(this).hasClass("active")){
					$(this).addClass("disabled").delay(i).queue(function(e){
						$(this).removeClass("disabled").addClass("active");
						e()
					});
					var v=a/r;
					var m=u+v/2;
					$(n).find(".msb_network_button").each(function(){
						var n=m/180*Math.PI;
						var r=p+f*Math.cos(n);
						var i=d+f*Math.sin(n);
						$(this).css({display:"block",left:p+"px",top:d+"px"}).stop().delay(t*s).animate({left:r+"px",top:i+"px"},e);
						m+=v;
						s++
					})
				}else{
					s=r-1;
					$(this).addClass("disabled").delay(i).queue(function(e){
						$(this).removeClass("disabled").removeClass("active");
						e()
					});
					$(n).find(".msb_network_button").each(function(){
						$(this).stop().delay(t*s).animate({left:p,top:d},e);
						s--
					})
				}
			},line:function(){
				if($(this).hasClass("disabled"))return;
				var e=500;
				var t=250;
				var r=$(n).find(".msb_network_button").length;
				var i=l;
				var s=e+(r-1)*t;
				var o=1;
				var a=$(this).outerWidth();
				var f=$(this).outerHeight();
				var c=$(n).find(".msb_network_button:eq(0)").outerWidth();
				var h=$(n).find(".msb_network_button:eq(0)").outerHeight();
				var p=(a-c)/2;
				var d=(f-h)/2;
				var v=u/180*Math.PI;
				if(!$(this).hasClass("active")){
					$(this).addClass("disabled").delay(s).queue(function(e){
						$(this).removeClass("disabled").addClass("active");
						e()
					});
					$(n).find(".msb_network_button").each(function(){
						var n=p+(p+i*o)*Math.cos(v);
						var r=d+(d+i*o)*Math.sin(v);
						$(this).css({display:"block",left:p+"px",top:d+"px"}).stop().delay(t*o).animate({left:n+"px",top:r+"px"},e);
						o++
					})
				}else{
					
					
					
					
					o=r;
					$(this).addClass("disabled").delay(s).queue(function(e){
						$(this).removeClass("disabled").removeClass("active");
						e()
					});
					$(n).find(".msb_network_button").each(function(){
						$(this).stop().delay(t*o).animate({left:p,top:d},e);
						o--
					})
	
				
				}
			}
		}
	}};

	var v={
		bubble:function(e){
			var t=e["orientations"]["line"];
			if(e["orientations"][o])t=e["orientations"][o];
			$(n).find(".msb_main").click(t);
		
			
			
			
		}
	};
	
	var m={
		jieshao:{profile_url:"#",name:"jieshao"},
		baozhang:{profile_url:"#",name:"baozhang"},
		zhuanye:{profile_url:"#",name:"zhuanye"},
		touming:{profile_url:"#",name:"touming"},
		youshi:{profile_url:"#",name:"youhsi"},
	
	};
	
	t.url=i;
	t.me=e;
	t.networks=r;
	p()

}