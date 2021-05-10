$(function(){
    $("#student").click(function(){
       $("#dropdownMenuLink").text($("#student").text());
        $("#msg").text('');
    });
    $("#teacher").click(function(){
       $("#dropdownMenuLink").text($("#teacher").text());
        $("#msg").text('');
    });
    $("#login").click(function(){
       if($("#dropdownMenuLink").text().trim()==='登录身份'){
            $("#msg").text('请选择身份');
            return false;
       }
       else{
           if($("#dropdownMenuLink").text().trim()==='学生'){
               $("#form").attr('action','/studentLogin');
           }
           else{
               $("#form").attr('action','/teacherLogin');
           }
       }
       return true;
    });
})