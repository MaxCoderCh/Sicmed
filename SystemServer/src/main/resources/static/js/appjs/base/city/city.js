var prefix = "/base/city";
var pid;
$(function() {
	//load();
	var parentCityId = 'cc9e0348b3c311e7b77800163e08d49b';
	loadCity(parentCityId);

});

//加载城市信息
function loadCity(parentCityId){
    $.ajax({
        url : prefix+"/ChildList/"+parentCityId,
        type : "get",
        success : function(result) {
            if (result.code==20000) {
            	$("#mycity").empty();
                var cityList = result.data;
                for (var i = 0;i < cityList.length;i++){
                	var city = cityList[i];
                    var cityName = city.cityName;
                    var id = city.id;
                    var orderWeight =  city.orderWeight;
                    var parentCityId = city.parentCityId;
                    var cityStr = '';
                    cityStr+='<tr>';
                    cityStr+='<td>'+cityName+'</td>';
                    cityStr+='<td>'+orderWeight+'</td>';
                    cityStr+='<td><a class="btn btn-primary btn-sm" title="编辑" onclick="edit(\''+ id+ '\')"><i class="fa fa-edit"></i></a>';
                    cityStr+='<a class="btn btn-warning btn-sm" style="margin: 0px 1px" title="删除"  onclick="remove(\''+id+'\');"><i class="fa fa-remove"></i></a>';
                    cityStr+='<a class="btn btn-success btn-sm" style="margin: 0px 1px" title="增加"  onclick="add(\''+id+'\');"><i class="fa fa-plus"></i></a>';
                    cityStr+='<a class="btn btn-primary btn-sm" style="margin: 0px 1px" title="详情"  onclick="getchildren(\''+id+'\');"><i class="glyphicon glyphicon-list"></i></a>';
                    cityStr+='</td>';
                    cityStr+='</tr>';
                    $("#mycity").append(cityStr);

				}
                loadBreadcrumb(parentCityId);
            }else{
                layer.msg("查询失败。");
            }
        }
    });
    //加载导航信息
    loadBreadcrumb(parentCityId);
	pid = parentCityId;
}

function myReLoad(id) {
    if (id == null || id == ''){
        id = 'cc9e0348b3c311e7b77800163e08d49b';
    }
    loadCity(id);
}
function add(id) {
	id=pid;
	//这里需要获取当前城市的id
	if (id == null || id == ''){
		id = 'cc9e0348b3c311e7b77800163e08d49b';
	}
	layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add/'+id // iframe的url
    });
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==20000) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function getchildren(id) {
    loadCity(id);
}

//加载导航条====传入当前城市的ID和名称
function loadBreadcrumb(id) {
	//根据当前ID查询城市信息
    $.ajax({
        url : prefix+"/getById02/"+id,
        type : "get",
        success : function(r) {
            if (r.code==20000) {
            	$("#daohang").empty();
               var cityList = r.data;
             	for (var i = 0;i <cityList.length;i++ ){
             		var cityName = cityList[i].cityName;
             		var id = cityList[i].id;
             		var str = '';
                	str +='<li><a onclick="myReLoad(\''+id+'\');">'+cityName+'</a></li>';
                    $("#daohang").append(str);
                }
            }
        }
    });
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 20000) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

