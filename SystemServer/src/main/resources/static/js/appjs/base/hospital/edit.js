$().ready(function() {
    validateRule();
   //  loadAllType();
   //  //加载所有的省级信息
   // loadAllProvince();
   //  //从县级信息开始，回显数据
   //  loadCounty();

});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
//根据类型id查询类型信息
function loadType(hospitalTypeId) {
    if(hospitalTypeId!=null){
        $.get("/base/hospitalType/getById",{
            id : hospitalTypeId  //传递ID到后台
        }, function(data) {
            if (data != null){
                //将类型信息传给select，
                selectValue(data.id);
            }
        });
    }
}
//如果select的默认值和医院类型id相同，则选定默认值=======>>因为之前把option的vlaue设为typeId,
function selectValue(value){
    var s = document.getElementById("typeId");
    var ops = s.options;
    for(var i=0;i<ops.length; i++){
        var tempValue = ops[i].value;
        if(tempValue == value){
            ops[i].selected = true;
        }
    }
}
// 直接获取所有的类型信息，
function loadAllType() {
    //页面加载完毕以后获取当前医院的类型id，传到下一个函数
    var hospitalTypeId = $("#typeId").val();
    $.get("/base/hospitalType/list",{
        offset : 0,
        limit:1000
    }, function(data) {
        if (data != null){
            var rows = data.rows;
            $("#typeId").empty();
            for(var i = 0;i<rows.length;i++){
                var type = rows[i];
                var str = '<option id="'+type.id+'" value="'+type.id+'">'+type.hospitalTypeName+'</option>';
                $("#typeId").append(str);
            }
            //根据当前医院的id，查询类型信息；
            loadType(hospitalTypeId);
        }
    });
}



//根据区县id查询省市区县信息
function loadCounty() {
    var countyId = $("#cityId").val();
    if(countyId!=null){
        $.get("/base/city/getById",{
            id : countyId  //传递区县ID到后台
        }, function(cityVO) {
            //cityVO中包含county  city  province三层信息
            if (cityVO != null) {
                $("#cityId").empty();   //清空省市县三级默认值
                $("#cityId1").empty();
                $("#cityId1").empty();
                var str = '<option id="' + cityVO.county.id + '" value="' + cityVO.county.id + '">' + cityVO.county.cityName + '</option>';
                var str1 = '<option id="' + cityVO.city.id + '" value="' + cityVO.city.id + '">' + cityVO.city.cityName + '</option>';
                var str2 = '<option id="' + cityVO.province.id + '" value="' + cityVO.province.id + '">' + cityVO.province.cityName + '</option>';
                $("#cityId").append(str);
                $("#cityId1").append(str1);
                $("#cityId2").append(str2);
            }
        });
    }
    //updateProvince();
}

//修改区县信息
function updateCounty() {
    $("#cityId").empty();
    //获取上级的地市信息，主要是id
    var cityID = $("#cityId1").val();
    $.get("/base/city/ChildList",{
        offset : 0,
        limit:1000,
        "parentCityId":cityID
    }, function(data) {
        if (data != null){
            var rows = data.rows;
            for(var i = 0;i<rows.length;i++){
                var city = rows[i];
                var str = '<option id="'+city.id+'" value="'+city.id+'">'+city.cityName+'</option>';
                $("#cityId").append(str);
            }
        }
    });
}
//修改地市信息
function updateCity() {
    $("#cityId1").empty();
    //获取上级的地市信息，主要是id
    var cityID = $("#cityId2").val();
    $.get("/base/city/ChildList",{
        offset : 0,
        limit:1000,
        "parentCityId":cityID
    }, function(data) {
        if (data != null){
            var rows = data.rows;
            for(var i = 0;i<rows.length;i++){
                var city = rows[i];
                var str = '<option id="'+city.id+'" value="'+city.id+'">'+city.cityName+'</option>';
                $("#cityId1").append(str);
            }
        }
    });
}
//修改省区信息
function loadAllProvince() {
    var hospitalCityId = $("#cityId").val();
    //直接加载所有的省区信息
    $.get("/base/city/list",{
        offset : 0,
        limit:5000,
    }, function(data) {
        $("#cityId2").empty();
        if (data != null){
            var rows = data.rows;
            for(var i = 0;i<rows.length;i++){
                var province = rows[i];
                if(province.cityType == '1'){
                    var str = '<option id="'+province.id+' " value="'+province.id+'">'+province.cityName+'</option>';
                    $("#cityId2").append(str);
                }
            }
        }
        loadProvince(hospitalCityId);
    });

}
function loadProvince(hospitalCityId) {
    if(hospitalCityId!=null){
        $.get("/base/city/getById",{
            id : hospitalCityId  //传递ID到后台
        }, function(data) {
            if (data != null){
                //将类型信息传给select，
                selectProvinceValue(data.province.id);
                // selectCityValue(data.city.id);
                // selectCountyValue(data.county.id);
            }
        });
    }
}

function selectProvinceValue(province) {
    var s = document.getElementById("cityId2");
    var ops = s.options;
    for(var i=0;i<ops.length; i++){
        var tempValue = ops[i].value;
        if(tempValue == province){
            ops[i].selected = true;
        }
    }
}
function selectCityValue(city) {
    var s = document.getElementById("cityId1");
    var ops = s.options;
    for(var i=0;i<ops.length; i++){
        var tempValue = ops[i].value;
        if(tempValue == city){
            ops[i].selected = true;
        }
    }
}

function selectCountyValue(County) {
    var s = document.getElementById("cityId");
    var ops = s.options;
    for(var i=0;i<ops.length; i++){
        var tempValue = ops[i].value;
        if(tempValue == County){
            ops[i].selected = true;
        }
    }
}

function update() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/base/hospital/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 20000) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入名字"
            }
        }
    })
}