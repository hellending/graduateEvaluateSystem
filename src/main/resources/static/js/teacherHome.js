var a = new Vue({
    el: "#select",
    data: {
        list_classType: []
    },
    methods: {
        update(){
            this.$forceUpdate();
        }
    }
});


$(function(){
    $("#preview").click(function(){
         $("#form").attr('action','/saveTempExcel');
         $("#form").attr('target','iframe1');
         return true;
    });
    $("#iframe1").load(function(){
         $("#preview_page").attr('src', $("#preview_page").attr('src'))
    });
    $("#iframe2").load(function (){
        $("#file").val('');
        layui.use(function(){
            layui.layer.msg("数据插入成功");
        });
    });
    $("#insertExcel").click(function(){
         $("#form").attr('action','/insertExcel');
         $("#form").attr('target','iframe2');
         return true;
    });
    $("#toInsert").click(function (){
         $("#select").hide();
         $("#insert").show();
         $("#preview_page").attr('src', $("#preview_page").attr('src'))
    });
    $("#toSelect").click(function (){
         $("#insert").hide();
         $("#select").show();
         $.ajax({
             url: "/reformatExcel"
         });
    });
    $("#iframe3").load(function(){
        $("#file1").val('');
        //加载模块（从layui 2.6 开始，第一个参数不传即代表加载所有内置模块）
        $("#grade").hide();
        $("#grade1").hide();
        $("#grade2").hide();
        $("#stu_tea").show();
        layui.use(function(){ //加载特定模块：layui.use(['layer', 'laydate', function(){
            //得到各种内置组件
            var layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element //元素操作
                ,dropdown = layui.dropdown //下拉菜单
                ,stu_num1 = 0

            //监听Tab切换
            element.on('tab(demo)', function(data){
                layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                    tips: 1
                });
            });

            //执行一个 table 实例
            table.render({
                elem: '#demo11'
                ,height: 420
                ,url: '/readExcelResponse' //数据接口
                ,title: '用户表'
                ,page: true //开启分页
                ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    ,{field: 'index', title: '#', width:40,align: "center"}
                    ,{field: 'stu_num', title: '学号', width: 140, sort: true, totalRow: true,align: "center"}
                    ,{field: 'name', title: '姓名', width:80, sort: true,align: "center"}
                    ,{field: 'eva_value', title: '毕业要求总达成度', width:180, sort: true,align: "center"}
                    ,{fixed: 'right', title: '操作', width: 80, align:'center', toolbar: '#barDemo'}
                ]]
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if($("#stu_tea").css('display')!=='none'){
                    $("#stu_tea").hide();
                    $("#grade").show();
                    stu_num1 = data.stu_num;
                    table.render({
                        elem: '#demo12'
                        ,height: 420
                        ,url: 'getSelectedStudent?stu_num='+data.stu_num //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'content', title: '毕业要求描述', width: 140, sort: true, totalRow: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                            ,{fixed: 'right', title: '操作', width: 80, align:'center', toolbar: '#barDemo'}
                        ]]
                    });
                }
                else if($("#grade").css('display')!=='none'){
                    $("#grade").hide();
                    $("#grade1").show();
                    table.render({
                        elem: '#demo1'
                        ,height: 420
                        ,url: '/getStudentIndexPointEvaluationValueInfo?stu_num='+stu_num1+"&content="+data.content //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'content', title: '指标点描述', width: 120, sort: true, totalRow: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                            ,{fixed: 'right', title: "操作", width: 80, align:'center', toolbar: '#barDemo1'}
                        ]]
                    });
                }
                else if($("#grade1").css('display')!=='none'){
                    $("#grade1").hide();
                    $("#grade2").show();
                    table.render({
                        elem: '#demo2'
                        ,height: 420
                        ,url: '/getStudentCourseIndexPointInfo?content='+data.content+"&stu_num="+stu_num1 //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'name', title: '课程名称', width: 120, sort: true, totalRow: true,align: "center"}
                            ,{field: 'weight', title: '权重', width:100, sort: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                        ]]
                    });
                }
            });
        });
    });

    $("#iframe4").load(function(){
        //加载模块（从layui 2.6 开始，第一个参数不传即代表加载所有内置模块）
        $("#grade").hide();
        $("#grade1").hide();
        $("#grade2").hide();
        $("#stu_tea").show();
        layui.use(function(){ //加载特定模块：layui.use(['layer', 'laydate', function(){
            //得到各种内置组件
            var layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element //元素操作
                ,dropdown = layui.dropdown //下拉菜单
                ,stu_num1 = 0

            //监听Tab切换
            element.on('tab(demo)', function(data){
                layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                    tips: 1
                });
            });

            //执行一个 table 实例
            table.render({
                elem: '#demo'
                ,height: 420
                ,url: '/readExcelResponse' //数据接口
                ,title: '用户表'
                ,page: true //开启分页
                ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    ,{field: 'index', title: '#', width:40,align: "center"}
                    ,{field: 'stu_num', title: '学号', width: 140, sort: true, totalRow: true,align: "center"}
                    ,{field: 'name', title: '姓名', width:80, sort: true,align: "center"}
                    ,{field: 'eva_value', title: '毕业要求总达成度', width:180, sort: true,align: "center"}
                    ,{fixed: 'right', title: '操作', width: 80, align:'center', toolbar: '#barDemo'}
                ]]
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if($("#stu_tea").css('display')!=='none'){
                    $("#stu_tea").hide();
                    $("#grade").show();
                    stu_num1 = data.stu_num;
                    table.render({
                        elem: '#demo0'
                        ,height: 420
                        ,url: 'getSelectedStudent?stu_num='+data.stu_num //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'content', title: '毕业要求描述', width: 140, sort: true, totalRow: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                            ,{fixed: 'right', title: '操作', width: 80, align:'center', toolbar: '#barDemo'}
                        ]]
                    });
                }
                else if($("#grade").css('display')!=='none'){
                    $("#grade").hide();
                    $("#grade1").show();
                    table.render({
                        elem: '#demo1'
                        ,height: 420
                        ,url: '/getStudentIndexPointEvaluationValueInfo?stu_num='+stu_num1+"&content="+data.content //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'content', title: '指标点描述', width: 120, sort: true, totalRow: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                            ,{fixed: 'right', title: "操作", width: 80, align:'center', toolbar: '#barDemo1'}
                        ]]
                    });
                }
                else if($("#grade1").css('display')!=='none'){
                    $("#grade1").hide();
                    $("#grade2").show();
                    table.render({
                        elem: '#demo2'
                        ,height: 420
                        ,url: '/getStudentCourseIndexPointInfo?content='+data.content+"&stu_num="+stu_num1 //数据接口
                        ,title: '用户表'
                        ,page: true //开启分页
                        ,toolbar: 'none' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'index', title: '#', width:40,align: "center"}
                            ,{field: 'name', title: '课程名称', width: 120, sort: true, totalRow: true,align: "center"}
                            ,{field: 'weight', title: '权重', width:100, sort: true,align: "center"}
                            ,{field: 'eva_value', title: '评价值', width:100, sort: true,align: "center"}
                        ]]
                    });
                }
            });
        });
    });

    $("#re0").click(function(){
        $("#grade").hide();
        $("#stu_tea").show();
    });
    $("#re1").click(function(){
        $("#grade1").hide();
        $("#grade").show();
    });
    $("#re2").click(function (){
        $("#grade2").hide();
        $("#grade1").show();
    });
    $("#model").click(function(){
       location.href = "/downloadModel?fileName=model1.xls"
    });

    $(window).bind('beforeunload',function(){
        $.ajax({
           url: "/reformatExcel"
        });
    });
})