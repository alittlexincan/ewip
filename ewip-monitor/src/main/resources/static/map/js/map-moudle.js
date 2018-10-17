/**
 * 全局变量
 */
var Globel = {
	interval:null 				//网络预警终端监控  定时器
	,netInterval:null 			//虚拟化网络监控  定时器
	,areaCode:"" 				//地区圈选的市、县
	,areaIds:[] 				//地区圈选的ID
	,mapMgLayerFlag:false		//地图白色蒙层标识
	,radarIntervalid:null			//雷达定时器
	,cloudIntervalid:null			//卫星云图定时器
    ,map : null 
	,zoom : 12
};