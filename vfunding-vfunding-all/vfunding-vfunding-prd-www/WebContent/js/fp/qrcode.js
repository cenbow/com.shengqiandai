var qrCode = {
    //初始化属性
    jsonData:{
        content     : '',  //内容，可为utl,如html://www.baidu.com 或文字，图片信息之类的
        logo        : '',  //二维码中间显示图片，   如:html://wwww.xxx.com/imgname.jpg
        bgColor     : '',  //背景颜色，             格式 ：颜色代码            如fffaf0
        fgColor     : '',  //前景颜色，即条纹颜色     格式 ：同上
        gcColor     : '',  //渐变颜色,              格式 : 同上
        ptColor     : '',  //定位点颜色(外框)        格式：同上
        inptColor   : '',  //定位点颜色(内点)        格式：同上
        eLevel      : '',  //纠错等级, 可用值:h\q\m\l  格式 : 单个字符         如 h
        w           : '',  //宽度尺寸               格式：像素值              如  200
        m           : ''   //外边距尺寸               格式：如上
    },
    //获取二维码图片
    getQrcode:function(divId){
        //javascript写法
        var divElement = document.getElementById(divId),
            imgHtml    = this.setImgHeml(this.jsonData);
        divElement.innerHTML = imgHtml;
        /* //jQuery写法
        var imgHtml    = this.setImgHeml(this.jsonData);
        $("#"+divId).append(imgHtml);*/
    },
    //构造图片
    setImgHeml:function(jsonData){
        var imgHtml = "<img src='http://qr.liantu.com/api.php?";
        imgHtml += jsonData.content?"&text="+jsonData.content:"";
        imgHtml += jsonData.logo?"&logo="+jsonData.logo:"";
        imgHtml += jsonData.bgColor?"&bg="+jsonData.bgColor:"";
        imgHtml += jsonData.fgColor?"&fg="+jsonData.fgColor:"";
        imgHtml += jsonData.gcColor?"&gc="+jsonData.gcColor:"";
        imgHtml += jsonData.ptColor?"&pg="+jsonData.ptColor:"";
        imgHtml += jsonData.inptColor?"&inpt="+jsonData.inptColor:"";
        imgHtml += jsonData.eLevel?"&el="+jsonData.eLevel:"";
        imgHtml += jsonData.w?"&w="+jsonData.w:"";
        imgHtml += jsonData.m?"&m="+jsonData.m:"";
        imgHtml += "'>";
        return imgHtml;
    }
};