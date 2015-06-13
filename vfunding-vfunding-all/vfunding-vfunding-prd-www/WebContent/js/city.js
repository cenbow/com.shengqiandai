//选择控件的名称
var selector = [ "province", "city"];
//初始值输入框的名称，可以用两个隐含输入框代替
var initInput=["province_init","city_init"];
//["请选择","请选择"],

//地方名称
var memu=[
    ["上海","上海市"],
    ["北京","北京市"],
    ["天津","天津市"],
    ["重庆","重庆市"],
    ["山东省","济南","青岛","滨州","德州","东营","荷泽","济宁","莱芜","聊城","临沂","日照","泰安","威海","潍坊","烟台","枣庄","淄博"],
    ["江苏省","南京","苏州","常州","淮安","连云港","南通","宿迁","无锡","徐州","盐城","泰州","扬州","镇江"],
    ["浙江省","杭州","湖州","嘉兴","金华","丽水","宁波","衢州","绍兴","台州","温州","舟山"],
    ["福建省","福州","龙岩","南平","宁德","莆田","泉州","三明","厦门","漳州"],
    ["广东省","广州","深圳","潮州","东莞","佛山","河源","惠州","江门","揭阳","茂名","梅州","清远","汕头","汕尾","韶关","阳江","云浮","湛江","肇庆","中山","珠海"],
    ["黑龙江省","哈尔滨","大庆","大兴安岭","鹤岗","黑河","鸡西","佳木斯","牡丹江","七台河","齐齐哈尔","双鸭山","绥化","伊春"],
    ["吉林省","长春","白城","白山","吉林","辽源","四平","松原","通化","延边"],
    ["辽宁省","沈阳","大连","鞍山","北票","本溪","朝阳","丹东","抚顺","阜新","葫芦岛","锦州","辽阳","盘锦","铁岭","营口"],
    ["内蒙古","呼和浩特","阿拉善盟","巴彦淖尔盟","包头","赤峰","鄂尔多斯","呼伦贝尔","通辽","乌海","乌兰察布盟","锡林郭勒盟","兴安盟"],
    ["河北省","石家庄","保定","沧州","承德","定州","邯郸","衡水","廊坊","皇岛","唐山","邢台","张家口"],
    ["山西省","太原","长治","大同","晋城","晋中","临汾","吕梁","朔州","忻州","阳泉","运城"],
    ["河南省","郑州","开封","安阳","鹤壁","济源","焦作","漯河","洛阳","南阳","平顶山","濮阳","三门峡","商丘","新乡","信阳","许昌","周口","驻马店"],
    ["安徽省","合肥","安庆","蚌埠","亳州","巢湖","滁州","阜阳","淮北","淮南","黄山","六安","马鞍山","宿州","铜陵","芜湖","宣城","池州"],
    ["陕西省","西安","安康","宝鸡","汉中","商洛","铜川","渭南","咸阳","延安","榆林"],
    ["江西省","南昌","抚州","赣州","吉安","景德镇","九江","萍乡","上饶","新余","宜春","鹰潭"],
    ["湖北省","武汉","鄂州","恩施","黄冈","黄石","荆门","荆州","神农架林","十堰","随州","天门","仙桃","咸宁","襄樊","孝感","宜昌"],
    ["湖南省","长沙","常德","郴州","衡阳","怀化","娄底","邵阳","湘潭","湘西","益阳","永州","岳阳","张家界","株洲"],
    ["甘肃省","兰州","白银","定西","甘南","嘉峪关","金昌","酒泉","临夏","陇南","平凉","庆阳","天水","武威","张掖"],
    ["宁夏","银川","固原","石嘴山","吴忠"],
    ["青海省","西宁","果洛","海北","海东","海南","海西","黄南","玉树"],
    ["四川省","成都","阿坝","巴中","达州","德阳","甘孜","广安","广元","乐山","凉山","眉山","绵阳","内江","南充","攀枝花","遂宁","雅安","宜宾","资阳","泸州","自贡"],
    ["贵州省","贵阳","安顺","毕节","六盘水","铜仁","遵义","黔东南","黔南","黔西南"],
    ["云南省","昆明","保山","楚雄","大理","德宏","迪庆","红河","丽江","临沧","怒江","曲靖","思茅","西双版纳","玉溪","昭通","中甸"],
    ["广西","南宁","百色","北海","崇左","防城港","贵港","桂林","合山","河池","贺州","来宾","柳州","钦州","梧州","玉林"],
    ["西藏","拉萨","阿里","昌都","林芝","那曲","日喀则","山南"],
    ["新疆","乌鲁木齐","阿克苏","阿拉尔","阿勒泰","阿图什","巴音郭楞","博尔塔拉","博乐","昌吉","哈密","和田","喀什","克拉玛依","克孜勒苏","库尔勒","奎屯","石河子","塔城","吐鲁番","五家渠","伊犁","伊宁"],
    ["海南省","海口","白沙","保亭","昌江","澄迈","儋州","定安","东方","乐东","临高","陵水","琼海","琼中","三亚","屯昌","万宁","文昌","五指山"]
];

var menuProvice = document.getElementById(selector[0]);
var menuCity = document.getElementById(selector[1]);


function LoadProvice()
{
    menuProvice.length++;
    menuProvice.options[menuProvice.length - 1].text = "选择省";
    menuProvice.options[menuProvice.length - 1].value = "";


    for(var i = 0;i < memu.length;i++)
    {
        menuProvice.length++;
        menuProvice.options[menuProvice.length - 1].text = memu[i][0];
        menuProvice.options[menuProvice.length - 1].value = memu[i][0];
    }
    menuProvice.onchange = Function("proviceChanged()");

}
LoadProvice();

function proviceChanged()
{
    var selValue = menuProvice.value;
    var selectIndex = 0;

    menuCity.length = 0;
    menuCity.length++;
    menuCity.options[menuCity.length - 1].text = "选择市";
    menuCity.options[menuCity.length - 1].value = "";
    if(selValue == "" || selValue == "选择省")
        return;

    for(var i = 0;i< memu.length;i++)
    {
        if(memu[i][0] == selValue)
        {
            selectIndex = i;
            break;
        }

    }


    for(var i = 1;i < memu[selectIndex].length;i++)
    {
        menuCity.length++;
        menuCity.options[menuCity.length - 1].text = memu[selectIndex][i];
        menuCity.options[menuCity.length - 1].value = memu[selectIndex][i];

    }
    return;


}