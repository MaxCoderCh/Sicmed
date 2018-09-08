$().ready(function() {
    weekDetails();
});

//默认显示最近七天的数据
function weekDetails() {
    var doctorId = $("#doctorId").val();
    $.ajax({
        cache : true,
        type : "GET",
        url : "/pra/countDoctor/weekDetails/"+doctorId,
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:map.name
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['问诊人数','访问人数','关注人数']
                },
                grid: {
                    left: '4%',
                    right: '4%',
                    bottom: '4%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'问诊人数',
                        type:'line',
                        data:map.inquriy
                    }, {
                        name:'访问人数',
                        type:'line',
                        data:map.click
                    }, {
                        name:'关注人数',
                        type:'line',
                        data:map.focus
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

}


//查询最近30天的数据
function moothDetails() {
    var doctorId = $("#doctorId").val();
    $.ajax({
        cache : true,
        type : "GET",
        url : "/pra/countDoctor/moothDetails/"+doctorId,
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:map.name
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['问诊人数','访问人数','关注人数']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'问诊人数',
                        type:'line',
                        data:map.inquriy
                    }, {
                        name:'访问人数',
                        type:'line',
                        data:map.click
                    }, {
                        name:'关注人数',
                        type:'line',
                        data:map.focus
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

}

//显示最近的12个月份数据
function yearDetails() {
    var doctorId = $("#doctorId").val();
    $.ajax({
        cache : true,
        type : "GET",
        url : "/pra/countDoctor/yearDetails/"+doctorId,
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:map.name
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['问诊人数','访问人数','关注人数']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'问诊人数',
                        type:'line',
                        data:map.inquriy
                    }, {
                        name:'访问人数',
                        type:'line',
                        data:map.click
                    }, {
                        name:'关注人数',
                        type:'line',
                        data:map.focus
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

}