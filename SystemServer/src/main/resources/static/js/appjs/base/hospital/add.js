$().ready(function() {
	validateRule();
    // loadHosType();
    // loadProvince();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function loadHosType() {
    //alert("医院类型");
    $.ajax({
        url : "/base/hospitalType/list",
        type : "get",
        data : {"offset":0,"limit":100},
        success : function(data) {
            var rows = data.rows;
            //alert(rows);
            $("#typeId").empty();
            for(var i = 0;i<rows.length;i++){
                var type = rows[i];
                var str = '<option id="'+type.id+'" value="'+type.id+'">'+type.hospitalTypeName+'</option>';
                $("#typeId").append(str);
            }
        }
    });
}

function loadProvince() {
   // $("#provinceId").find("option").remove();
    $.ajax({
        url : "/base/city/list",
        type : "get",
        data : {"offset":0,"limit":9999},
        success : function(data) {
            var rows = data.rows;
            //alert(rows);
            $("#cityId2").empty();
            for(var i = 0;i<rows.length;i++){
                var province = rows[i];
                if(province.cityType == '1'){
                    var str = '<option id="'+province.id+'" value="'+province.id+'">'+province.cityName+'</option>';
                    $("#cityId2").append(str);
				}
            }
        }
    });
}
function loadCity(){
	var cityid = $("#cityId2").val();
	//alert(cityid);
    $.ajax({
        url : "/base/city/ChildList",
        type : "get",
        data : {"offset":0,"limit":9999,"parentCityId":cityid},
        success : function(data) {
            var rows = data.rows;
            //alert(rows);
            $("#cityId1").empty();
            for(var i = 0;i<rows.length;i++){
                var city = rows[i];
                if(city.cityType == '2'){
                    var str = '<option id="'+city.id+'" value="'+city.id+'">'+city.cityName+'</option>';
                    $("#cityId1").append(str);
                }
            }
        }
    });
}


function loadCounty(){
    var countyid = $("#cityId1").val();
    $.ajax({
        url : "/base/city/ChildList",
        type : "get",
        data : {"offset":0,"limit":9999,"parentCityId":countyid},
        success : function(data) {
            var rows = data.rows;
            $("#cityId").empty();
            for(var i = 0;i<rows.length;i++){
                var city = rows[i];
                if(city.cityType == '3'){
                    var str = '<option id="'+city.id+'" value="'+city.id+'">'+city.cityName+'</option>';
                    $("#cityId").append(str);
                }
            }
        }
    });
}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/base/hospital/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}