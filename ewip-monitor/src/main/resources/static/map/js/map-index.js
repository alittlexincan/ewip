$(function(){

/*

    //创建图片对象
    var createIcon = (iconUrl)=>{
        return new T.Icon({
            iconUrl: iconUrl,
            iconSize: new T.Point(40, 40),
            iconAnchor: new T.Point(10, 25)
        });
    };


    // 后台获取数据
    var data = [{
        wei:108.00372,
        jin:36.41957,
        title:'华池县发布暴雨红色预警',
        content: '华池县发布暴雨红色预警,预计今天到明天将有强降雨',
        icon: 'http://192.168.1.133:8080/client/disaster/11B03-0.gif'
    },{
        wei:108.00367,
        jin:36.42914,
        title:'华池县发布大雾红色预警',
        content: '华池县发布大雾黄色预警,预计今天到明天将有大雾，能见度10米',
        icon: 'http://192.168.1.133:8080/client/disaster/11B17-2.gif'
    }];

    // 点击图标显示弹出层信息
    var content = data => {
        return "<div>"
            + "<ul>预警名称："+data.title+"</ul>"
            + "<ul>预警内容："+data.content+"</ul>"
            + "</div>";
    };

    // 信息窗口展开事件
    var openInfo = (content,e) =>{
        var point = e.lnglat;
        marker = new T.Marker(point);												// 创建标注
        var markerInfoWin = new T.InfoWindow(content,{offset:new T.Point(0,-30)}); 	// 创建信息窗口对象
        map.openInfoWindow(markerInfoWin,point); //开启信息窗口
    };

    // 图标点击事件
    var addClickHandler = (content,marker)=>{
        marker.addEventListener("click",function(e){
            openInfo(content,e)}
        );
    };

    // 循环遍历后台数据
    data.forEach(item=>{
        var icon = createIcon(item.icon);			// 创建图标对象
        var marker = new T.Marker(new T.LngLat(item.wei,item.jin), {icon: icon});  	// 创建标注
        map.addOverLay(marker);               										// 将标注添加到地图中
        addClickHandler(content(item),marker);										// 追加图标点击事件
    });
*/


});
   