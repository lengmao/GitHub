/**
 * Created by scaf_xs on 2017/12/16.
 */
var tables={
    baseUrl: "/rest/pageList",
    tableId: "tables",
    toolbarId: "toolbar",
    unique: "id",
    order: "asc",
    currentItem: {}
};

tables.columns= function() {
    return [
        {checkbox:true},
        {
            field: 'tableName',
            title: '表名'
        }, {
            field: 'schema',
            title: 'schema'
        },{
            field: 'tableComment',
            title: '表说明',
        }
    ]
};

tables.queryParams=function (params) {
    if(!params)
        return {
            tableName:$("#tableName").val()
        };

        var temp={
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            tableName: $("#tableName").val()
        };
    return temp;
};

tables.init=function () {
    tables.table = $('#' + tables.tableId).bootstrapTable({
        url: tables.baseUrl, //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + tables.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: tables.id, //排序方式
        queryParams: tables.queryParams,//传递参数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: tables.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        singleSelect:true, //禁止多选
        columns: tables.columns()

    })
};

//函数主体
layui.use(['form', 'layedit', 'laydate'], function () {

    tables.init();
    var layer=layui.layer,
        from=layui.form(),
        layedit=layui.layedit,
        laydate = layui.laydate;
    var addBoxIndex = -1;

    //初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        tables.table.bootstrapTable('refresh', tables.queryParams());
    });

    $("#btn_add").on('click',function () {
        if(addBoxIndex !==-1)
            return;
        var row=tables.table.bootstrapTable('getSelections');
        $.get('/edit',null,function (form) {
            addBoxIndex=layer.open({
                type: 1,
                title: '添加表',
                content: form,
                btn: ['保存', '取消'],
                shade: false,
                offset: ['20px', '20%'],
                area: ['700px', '600px'],
                maxmin: true,
                yes:function (index) {
                    
                },
            });

        });
    });
    $("#btn_col").on('click',function(){
        $('#add_col').after($('#add_col'));
    })
});