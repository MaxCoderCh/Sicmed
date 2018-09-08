window.onload = function(){
    getFeedback();
}

//将隐藏域中的文本信息显示在文本域中================注释掉的是用户反馈信息的回复内容
function getFeedback() {


	var feedbackText2 = $("#feedbackText2").val();
    //var replyInfo2 = $("#replyInfo2").val();
    $("#feedbackText").val(feedbackText2);
   // $("#replyInfo").val(replyInfo2);
}
