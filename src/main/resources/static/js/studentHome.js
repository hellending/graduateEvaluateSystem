$(function(){
    $("#info_check").click(function(){
        $("#grade1").hide();
        $("#grade").hide();
        $("#grade2").hide();
        $("#stu_info").show();
    });
    $("#info_check").click(function(){
        $("#stu_info").show();
        $("#grade").hide();
    });
    $("#grade-select").click(function(){
        $("#stu_info").hide();
        $("#grade").show();
    });
    $("#re1").click(function(){
        $("#grade1").hide();
        $("#grade").show();
    });
    $("#re2").click(function (){
        $("#grade2").hide();
        $("#grade1").show();
    });
})