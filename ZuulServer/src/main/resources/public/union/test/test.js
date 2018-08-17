$(function() {
    var type = myLocal.getItem('scoreType');
    if (type == 'A') {
        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/patientNihCpsiScore/getById',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "nihCpsiScoreId": myLocal.getItem('testId'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    // 评测ID
                    myLocal.setItem('testId', data.result.id);
                    // 量表答案
                    if (data.result.optionScore) {
                        myLocal.setItem('nihResult', data.result.optionScore);
                    } else {
                        myLocal.setItem('nihResult', '');
                    }
                    $('.review .assessName').html('NIH-CPSL评估');
                    $('.review .assessDate').html(data.result.createTime);
                    $('.degree').html('前列腺炎症状的程度为' + data.result.caution);
                    $('.totalScore').html(data.result.nihCpsiScore + '分');
                    $('.examineBtn').attr('href', '/union/nih/nih.html')
                    $('.reviewUl').html('<li class="reviewItem"><b>疼</b>疼痛评分 <span class="itemScore">' + data.result.inflammationScore + '分</span></li><li class="reviewItem"><b>生</b>尿路症评分 <span class="itemScore">' + data.result.lifeScore + '分</span></li><li class="reviewItem"><b>前</b>生活质量评分 <span class="itemScore">' + data.result.symptomScore + '分</span></li>');
                } else if (data.code == 40001) {
                    window.location = '/union/login/login.html';
                }
            },
            error: function(err) {

            },
        });
    } else if (type == 'B') {
        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/patientIpssScore/getById',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "ipssScoreId": myLocal.getItem('testId'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    // 评测ID
                    myLocal.setItem('testId', data.result.id);
                    // 量表答案
                    if (data.result.optionScore) {
                        myLocal.setItem('ipssResult', data.result.optionScore);
                    } else {
                        myLocal.setItem('ipssResult', '');
                    }
                    $('.review .assessName').html('IPSS评估');
                    $('.review .assessDate').html(data.result.createTime);
                    $('.degree').html('前列腺症状的程度为' + data.result.caution);
                    $('.totalScore').html(data.result.ipssScore + '分');
                    $('.examineBtn').attr('href', '/union/ipss/ipss.html')
                    $('.reviewUl').html('<li class="reviewItem"><b>前</b>前列腺症状评分 <span class="itemScore">' + data.result.symptomScore + '分</span></li><li class="reviewItem"><b>生</b>生活质量评分 <span class="itemScore">' + data.result.lifeScore + '分</span></li>');
                } else if (data.code == 40001) {
                    window.location = '/union/login/login.html';
                }
            },
            error: function(err) {

            },
        });
    } else {
        var obj = myLocal.getItem('patientInfo');
        if (obj.patientAge >= 50) {
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/patientIpssScore/getById',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "ipssScoreId": myLocal.getItem('testId'),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        // 评测ID
                        myLocal.setItem('testId', data.result.id);
                        // 量表答案
                        if (data.result.optionScore) {
                            myLocal.setItem('ipssResult', data.result.optionScore);
                        } else {
                            myLocal.setItem('ipssResult', '');
                        }
                        $('.review .assessName').html('IPSS评估');
                        $('.review .assessDate').html(data.result.createTime);
                        $('.degree').html('前列腺症状的程度为' + data.result.caution);
                        $('.totalScore').html(data.result.ipssScore + '分');
                        $('.examineBtn').attr('href', '/union/ipss/ipss.html')
                        $('.reviewUl').html('<li class="reviewItem"><b>前</b>前列腺症状评分 <span class="itemScore">' + data.result.symptomScore + '分</span></li><li class="reviewItem"><b>生</b>生活质量评分 <span class="itemScore">' + data.result.lifeScore + '分</span></li>');
                    } else if (data.code == 40001) {
                        window.location = '/union/login/login.html';
                    }
                },
                error: function(err) {

                },
            });
        } else {
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/patientNihCpsiScore/getById',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "nihCpsiScoreId": myLocal.getItem('testId'),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        // 评测ID
                        myLocal.setItem('testId', data.result.id);
                        // 量表答案
                        if (data.result.optionScore) {
                            myLocal.setItem('nihResult', data.result.optionScore);
                        } else {
                            myLocal.setItem('nihResult', '');
                        }
                        $('.review .assessName').html('NIH-CPSL评估');
                        $('.review .assessDate').html(data.result.createTime);
                        $('.degree').html('前列腺炎症状的程度为' + data.result.caution);
                        $('.totalScore').html(data.result.nihCpsiScore + '分');
                        $('.examineBtn').attr('href', '/union/nih/nih.html')
                        $('.reviewUl').html('<li class="reviewItem"><b>疼</b>疼痛评分 <span class="itemScore">' + data.result.inflammationScore + '分</span></li><li class="reviewItem"><b>生</b>尿路症评分 <span class="itemScore">' + data.result.lifeScore + '分</span></li><li class="reviewItem"><b>前</b>生活质量评分 <span class="itemScore">' + data.result.symptomScore + '分</span></li>');
                    } else if (data.code == 40001) {
                        window.location = '/union/login/login.html';
                    } 
                },
                error: function(err) {

                },
            });
        }
    }

    $('.tabContent > a').click(function() {
        $(this).addClass('active').siblings('a').removeClass('active');
        var _index = $(this).index();
        $('.container .item').removeClass('active').eq(_index).addClass('active');
    });

    var obj = myLocal.getItem('patientInfo');
    $('.name').html(obj.patientName ? obj.patientName : '');
    $('.patientNumber').html(obj.patientNumber ? obj.patientNumber : '');
    $('.createTime').html(obj.createTime ? obj.createTime : '');
    $('.patientCard').html(obj.patientCard ? obj.patientCard : '');
    $('.patientPhone').html(obj.patientPhone ? obj.patientPhone : '');
    if (obj.cityDetailBean && obj.detailAddress) {
        var _html = '';
        obj.cityDetailBean.grandfatherCityName ? _html += obj.cityDetailBean.grandfatherCityName + '-' : '';
        obj.cityDetailBean.parentCityName ? _html += obj.cityDetailBean.parentCityName + '-' : '';
        obj.cityDetailBean.cityName ? _html += obj.cityDetailBean.cityName + '-' : '';
        obj.detailAddress ? _html += obj.detailAddress : '';
        $('.detailAddress').html(_html);
    } else if (obj.detailAddress) {
        $('.detailAddress').html(obj.detailAddress);
    }

    var _html = '';
    for (var i = 0; i < obj.anamnesisAllergyDrugList.length; i++) {
        _html += obj.anamnesisAllergyDrugList[i].anamnesisRemark + '、'
    }
    for (var i = 0; i < obj.anamnesisEatingDrugList.length; i++) {
        _html += obj.anamnesisEatingDrugList[i].anamnesisRemark + '、'
    }
    for (var i = 0; i < obj.anamnesisIllnessList.length; i++) {
        _html += obj.anamnesisIllnessList[i].anamnesisRemark + '、'
    }
    for (var i = 0; i < obj.anamnesisSurgicalHistoryList.length; i++) {
        _html += obj.anamnesisSurgicalHistoryList[i].anamnesisRemark + '、'
    }
    for (var i = 0; i < obj.otherList.length; i++) {
        _html += obj.otherList[i].anamnesisRemark + '、'
    }
    $('.orderBean').html(_html);

    // 查询化验单解读结果
    $.ajax({
        type: 'POST',
        url: IP + '/api-assessmen/medicalExamination/getByPatientAndDate',
        dataType: 'json',
        data: {
            "token": $.cookie('token'),
            "patientId": myLocal.getItem("patientId"),
            "createDate": myLocal.getItem("createDate"),
        },
        success: function(data) {
            console.log(data)
            var _html = '';
            if (data.code == '20000') {
                $('.readings .assessDate').html(data.result.createTime);
                if (data.result.bloodRoutineAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getBloodRoutine',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            if (data.code == 20000) {
                                topicArr = data.result;
                            } else if (data.code == 40004) {
                                topicArr = [];
                            }
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>血常规</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.bloodRoutineAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }
                    _html += '</td>\
                        <td>' + data.result.bloodRoutineRemark + '</td>\
                    </tr>'
                }
                if (data.result.urineRoutineAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getUrineRoutine',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>尿常规</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.urineRoutineAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }
                    _html += '</td>\
                        <td>' + data.result.urineRoutineRemark + '</td>\
                    </tr>'
                }
                if (data.result.expressedProstaticSecretionAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getExpressedProstaticSecretion',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>前列腺按摩液</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.expressedProstaticSecretionAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }
                    _html += '</td>\
                        <td>' + data.result.expressedProstaticSecretionRemark + '</td>\
                    </tr>'
                }
                if (data.result.specificAntigenAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getSpecificAntigen',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>特异性抗原</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.specificAntigenAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }

                    _html += '</td>\
                        <td>' + data.result.specificAntigenRemark + '</td>\
                    </tr>'
                }
                if (data.result.ultrasonographyBAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getUltrasonographyB',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>B超报告单</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.ultrasonographyBAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }
                    _html += '</td>\
                        <td>' + data.result.ultrasonographyBRemark + '</td>\
                    </tr>'
                }
                if (data.result.digitalRectalAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getDigitalRectal',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>前列腺指诊</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.digitalRectalAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }
                    _html += '</td>\
                        <td>' + data.result.digitalRectalRemark + '</td>\
                    </tr>'
                }
                if (data.result.urineFlowRateAnswer) {
                    var topicArr = [];
                    $.ajax({
                        type: 'POST',
                        url: IP + '/api-stata/scale/getUrineFlowRate',
                        dataType: 'json',
                        data: {
                            "token": $.cookie('token'),
                        },
                        async: false,
                        success: function(data) {
                            console.log(data);
                            topicArr = data.result;
                        },
                        error: function(err) {
                            console.log(err)
                        }
                    })
                    _html += '<tr>\
                        <td>尿流率</td>\
                        <td>';
                    for (var i = 0; i < topicArr.length; i++) {
                        _html += '<span>' + topicArr[i].scaleTitle + '</span>'
                    }
                    _html += '</td><td>';
                    var startIndex = 0;
                    for (var i = 0; i < topicArr.length; i++) {
                        var str = data.result.urineFlowRateAnswer.substr(startIndex, startIndex + topicArr[i].childList.length);
                        startIndex += topicArr[i].childList.length;
                        _html += '<span>' + topicArr[i].childList[str.indexOf('1')].scaleTitle + '</span>'
                    }

                    _html += '</td>\
                        <td>' + data.result.urineFlowRateRemark + '</td>\
                    </tr>'
                }
                $('.tBody').html(_html);
            } else if (data.code == '40004') {
                $('.readings').hide();
                $('.tableBox').hide();
            }
        },
        error: function(err) {

        },
    });

    // 查询已传图片
    $.ajax({
        type: 'POST',
        url: IP + '/api-assessmen/record/getInspectionRecord',
        dataType: 'json',
        data: {
            "token": $.cookie('token'),
            "patientId": myLocal.getItem("patientId"),
            "createDate": myLocal.getItem('createDate'),
        },
        success: function(data) {
            console.log(data);
            if (data.code == 20000) {
                var tempArr = data.result;
                var _html = "";
                for (var i = 0; i < tempArr.length; i++) {
                    _html += '<li class="imgFile" url="' + tempArr[i].recordPath + '">\
                        <img class="img" src="' + tempArr[i].recordPath + '" alt="">\
                        <img url="' + tempArr[i].recordPath + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>'
                }
                $('.inspectionBox').append(_html);
            }
        },
        error: function(err) {
            console.log(err)
        }
    })
    $.ajax({
        type: 'POST',
        url: IP + '/api-assessmen/record/getCheckupRecord',
        dataType: 'json',
        data: {
            "token": $.cookie('token'),
            "patientId": myLocal.getItem("patientId"),
            "createDate": myLocal.getItem('createDate'),
        },
        success: function(data) {
            console.log(data);
            if (data.code == 20000) {
                var tempArr = data.result;
                var _html = "";
                for (var i = 0; i < tempArr.length; i++) {
                    _html += '<li class="imgFile" url="' + tempArr[i].recordPath + '">\
                        <img class="img" src="' + tempArr[i].recordPath + '" alt="">\
                        <img url="' + tempArr[i].recordPath + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>'
                }
                $('.checkupBox').append(_html);
            }
        },
        error: function(err) {
            console.log(err)
        }
    })
    $.ajax({
        type: 'POST',
        url: IP + '/api-assessmen/record/getDiseaseRecord',
        dataType: 'json',
        data: {
            "token": $.cookie('token'),
            "patientId": myLocal.getItem("patientId"),
            "createDate": myLocal.getItem('createDate'),
        },
        success: function(data) {
            console.log(data);
            if (data.code == 20000) {
                var tempArr = data.result;
                var _html = "";
                for (var i = 0; i < tempArr.length; i++) {
                    _html += '<li class="imgFile" url="' + tempArr[i].recordPath + '">\
                        <img class="img" src="' + tempArr[i].recordPath + '" alt="">\
                        <img url="' + tempArr[i].recordPath + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>'
                }
                $('.diseaseBox').append(_html);
            }
        },
        error: function(err) {
            console.log(err)
        }
    })
    $.ajax({
        type: 'POST',
        url: IP + '/api-assessmen/record/getHospitalRecord',
        dataType: 'json',
        data: {
            "token": $.cookie('token'),
            "patientId": myLocal.getItem("patientId"),
            "createDate": myLocal.getItem('createDate'),
        },
        success: function(data) {
            console.log(data);
            if (data.code == 20000) {
                var tempArr = data.result;
                var _html = "";
                for (var i = 0; i < tempArr.length; i++) {
                    _html += '<li class="imgFile" url="' + tempArr[i].recordPath + '">\
                        <img class="img" src="' + tempArr[i].recordPath + '" alt="">\
                        <img url="' + tempArr[i].recordPath + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>'
                }
                $('.hospitalBox').append(_html);
            }
        },
        error: function(err) {
            console.log(err)
        }
    })


    // inspection-records:检验记录 新添加的图片
    // var inspectionArr = [];
    // checkup-records:体检记录 新添加的图片
    // var checkupArr = [];
    // disease-records: 病程记录 新添加的图片
    // var diseaseArr = [];
    // hospital-records: , 住院记录 新添加的图片
    // var hospitalArr = [];

    // 上传图片按钮
    // inspection-records:检验记录
    $('.inspectionRecords').change(function() {
        var fb = new FormData();
        var files = $('.inspectionRecords')[0].files;
        fb.append("token", myLocal.getItem('token'));
        fb.append("file", files[0]);
        fb.append("recordType", "inspection-records");
        // checking-records:检查记录,checkup-records:体检记录,diagnosis-records:诊断记录,disease-records:病程记录,hospital-records:,住院记录,inspection-records:检验记录
        var url = ''; // 图片地址
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function(e) {
            if (e.target.result) {
                url = e.target.result;
            }
        }
        $.ajax({
            type: 'POST',
            url: IP + '/api-file/file/upload',
            dataType: 'json',
            data: fb,
            processData: false,
            contentType: false,
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    $('.inspectionBox').append('<li class="imgFile newFile" url="' + data.result + '">\
                        <img class="img" src="' + url + '" alt="">\
                        <img url="' + data.result + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>');
                } else if (data.code == '40001') {
                    // window.location = '/union/login/login.html';
                } else {
                    layer.msg("上传失败")
                }
            },
            error: function(err) {

            },
        });
    })
    // checkup-records:体检记录
    $('.checkupRecords').change(function() {
        var fb = new FormData();
        var files = $('.checkupRecords')[0].files;
        fb.append("token", myLocal.getItem('token'));
        fb.append("file", files[0]);
        fb.append("recordType", "checkup-records");
        // checking-records:检查记录,checkup-records:体检记录,diagnosis-records:诊断记录,disease-records:病程记录,hospital-records:,住院记录,inspection-records:检验记录
        var url = ''; // 图片地址
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function(e) {
            if (e.target.result) {
                url = e.target.result;
            }
        }
        $.ajax({
            type: 'POST',
            url: IP + '/api-file/file/upload',
            dataType: 'json',
            data: fb,
            processData: false,
            contentType: false,
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    $('.checkupBox').append('<li class="imgFile newFile" url="' + data.result + '">\
                        <img class="img" src="' + url + '" alt="">\
                        <img url="' + data.result + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>');
                } else if (data.code == '40001') {
                    // window.location = '/union/login/login.html';
                } else {
                    layer.msg("上传失败")
                }
            },
            error: function(err) {

            },
        });
    })
    // disease-records: 病程记录
    $('.diseaseRecords').change(function() {
        var fb = new FormData();
        var files = $('.diseaseRecords')[0].files;
        fb.append("token", myLocal.getItem('token'));
        fb.append("file", files[0]);
        fb.append("recordType", "disease-records");
        // checking-records:检查记录,checkup-records:体检记录,diagnosis-records:诊断记录,disease-records:病程记录,hospital-records:,住院记录,inspection-records:检验记录
        var url = ''; // 图片地址
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function(e) {
            if (e.target.result) {
                url = e.target.result;
            }
        }
        $.ajax({
            type: 'POST',
            url: IP + '/api-file/file/upload',
            dataType: 'json',
            data: fb,
            processData: false,
            contentType: false,
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    $('.diseaseBox').append('<li class="imgFile newFile" url="' + data.result + '">\
                        <img class="img" src="' + url + '" alt="">\
                        <img url="' + data.result + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>');
                } else if (data.code == '40001') {
                    // window.location = '/union/login/login.html';
                } else {
                    layer.msg("上传失败")
                }
            },
            error: function(err) {

            },
        });
    })
    // hospital-records: , 住院记录
    $('.hospitalRecords').change(function() {
        var fb = new FormData();
        var files = $('.hospitalRecords')[0].files;
        fb.append("token", myLocal.getItem('token'));
        fb.append("file", files[0]);
        fb.append("recordType", "hospital-records");
        // checking-records:检查记录,checkup-records:体检记录,diagnosis-records:诊断记录,disease-records:病程记录,hospital-records:,住院记录,inspection-records:检验记录
        var url = ''; // 图片地址
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function(e) {
            if (e.target.result) {
                url = e.target.result;
            }
        }
        $.ajax({
            type: 'POST',
            url: IP + '/api-file/file/upload',
            dataType: 'json',
            data: fb,
            processData: false,
            contentType: false,
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    $('.hospitalBox').append('<li class="imgFile newFile" url="' + data.result + '">\
                        <img class="img" src="' + url + '" alt="">\
                        <img url="' + data.result + '" class="delimgBtn" src="./delimg.png" alt="">\
                    </li>');
                } else if (data.code == '40001') {
                    // window.location = '/union/login/login.html';
                } else {
                    layer.msg("上传失败")
                }
            },
            error: function(err) {

            },
        });
    })
    // 检验删除图片按钮
    $('.inspectionBox').delegate('.delimgBtn', 'click', function() {
        var objParent = $(this).parents('.imgFile');
        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/record/deleteInspectionRecord',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "imgPath": $(this).attr('url'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    objParent.remove();
                } else if (data.code == '40001') {
                    window.location = '/union/login/login.html';
                } else {
                    layer.msg("删除失败")
                }
            },
            error: function(err) {

            },
        });
        return false;
    })
    // 体检删除图片按钮
    $('.checkupBox').delegate('.delimgBtn', 'click', function() {
        var objParent = $(this).parents('.imgFile');
        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/record/deleteCheckupRecord',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "imgPath": $(this).attr('url'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    objParent.remove();
                } else if (data.code == '40001') {
                    window.location = '/union/login/login.html';
                } else {
                    layer.msg("删除失败")
                }
            },
            error: function(err) {

            },
        });
        return false;
    })
    // 病程删除图片按钮
    $('.diseaseBox').delegate('.delimgBtn', 'click', function() {
        var objParent = $(this).parents('.imgFile');

        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/record/deleteDiseaseRecord',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "imgPath": $(this).attr('url'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    objParent.remove();
                } else if (data.code == '40001') {
                    window.location = '/union/login/login.html';
                } else {
                    layer.msg("删除失败")
                }
            },
            error: function(err) {

            },
        });
        return false;
    })
    // 住院删除图片按钮
    $('.hospitalBox').delegate('.delimgBtn', 'click', function() {
        var objParent = $(this).parents('.imgFile');
        $.ajax({
            type: 'POST',
            url: IP + '/api-assessmen/record/deleteHospitalRecord',
            dataType: 'json',
            data: {
                "token": $.cookie('token'),
                "imgPath": $(this).attr('url'),
            },
            success: function(data) {
                console.log(data)
                if (data.code == '20000') {
                    objParent.remove();
                } else if (data.code == '40001') {
                    window.location = '/union/login/login.html';
                } else {
                    layer.msg("删除失败")
                }
            },
            error: function(err) {

            },
        });
        return false;
    })
    // 查看大图
    $('.upfileContent').delegate('.imgFile', 'click', function() {
        var $ = layui.jquery;
        // 弹出层
        layer.open({
            type: 1,
            title: '',
            area: ['1000px', '700px'],
            closeBtn: true,
            shade: [0.7, '#000000'],
            shadeClose: true,
            content: $('.bigImgContent'),
        });
        $('.imgBox').css("backgroundImage", "url(" + $(this).attr('url') + ")");
    });

    // 保存-btn
    $('.keep').click(function() {
        if ($('.inspectionBox .newFile').length > 0) {
            var inspectionArr = [];
            for (var i = 0; i < $('.inspectionBox .newFile').length; i++) {
                inspectionArr.push($('.inspectionBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addInspectionRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": inspectionArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        inspectionArr = [];
                    } else if (data.code == '40001') {

                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.checkupBox .newFile').length > 0) {
            var checkupArr = [];
            for (var i = 0; i < $('.checkupBox .newFile').length; i++) {
                checkupArr.push($('.checkupBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addCheckupRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": checkupArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        checkupArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.diseaseBox .newFile').length > 0) {
            var diseaseArr = [];
            for (var i = 0; i < $('.diseaseBox .newFile').length; i++) {
                diseaseArr.push($('.diseaseBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addDiseaseRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": diseaseArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        diseaseArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.hospitalBox .newFile').length > 0) {
            var hospitalArr = [];
            for (var i = 0; i < $('.hospitalBox .newFile').length; i++) {
                hospitalArr.push($('.hospitalBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addHospitalRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": hospitalArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        hospitalArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        setTimeout(function() {
            history.go(-1);
        })
    })
    // 后退按钮
    $('.backBtn').click(function() {
        if ($('.inspectionBox .newFile').length <= 0 && $('.checkupBox .newFile').length <= 0 && $('.diseaseBox .newFile').length <= 0 && $('.hospitalBox .newFile').length <= 0) {
            history.go(-1);
        } else {
            var _$ = layui.jquery;
            // 弹出层
            layer.open({
                type: 1,
                title: '',
                area: ['500px', '200px'],
                closeBtn: true,
                shade: [0.7, '#000000'],
                shadeClose: true,
                content: _$('.savePrompt'),
            });
        }
    })
    $('.savePrompt').find('.noBtn').click(function() {
        layer.closeAll();
        history.go(-1);
    })
    $('.savePrompt').find('.yesBtn').click(function() {
        if ($('.inspectionBox .newFile').length > 0) {
            var inspectionArr = [];
            for (var i = 0; i < $('.inspectionBox .newFile').length; i++) {
                inspectionArr.push($('.inspectionBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addInspectionRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": inspectionArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        inspectionArr = [];
                    } else if (data.code == '40001') {

                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.checkupBox .newFile').length > 0) {
            var checkupArr = [];
            for (var i = 0; i < $('.checkupBox .newFile').length; i++) {
                checkupArr.push($('.checkupBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addCheckupRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": checkupArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        checkupArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.diseaseBox .newFile').length > 0) {
            var diseaseArr = [];
            for (var i = 0; i < $('.diseaseBox .newFile').length; i++) {
                diseaseArr.push($('.diseaseBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addDiseaseRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": diseaseArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        diseaseArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        if ($('.hospitalBox .newFile').length > 0) {
            var hospitalArr = [];
            for (var i = 0; i < $('.hospitalBox .newFile').length; i++) {
                hospitalArr.push($('.hospitalBox .newFile').eq(i).attr('url'))
            }
            $.ajax({
                type: 'POST',
                url: IP + '/api-assessmen/record/addHospitalRecord',
                dataType: 'json',
                data: {
                    "token": $.cookie('token'),
                    "patientId": myLocal.getItem('patientId'),
                    "recordPaths": hospitalArr.join(','),
                },
                success: function(data) {
                    console.log(data)
                    if (data.code == '20000') {
                        hospitalArr = [];
                    } else if (data.code == '40001') {
                        // window.location = '/union/login/login.html';
                    } else {
                        layer.msg("上传失败")
                    }
                },
                error: function(err) {

                },
            });
        }
        setIimeout(function() {
            history.go(-1);
        })
    })

})
