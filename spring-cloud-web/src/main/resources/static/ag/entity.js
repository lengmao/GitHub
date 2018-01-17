/**
 * Created by scaf_xs on 2017/12/16.
 */
var entity={
    baseUrl: "/rest/pageEntitys",
    tableId: "table_entity",
    toolbarId: "toolbar_entity",
    unique: "id",
    order: "asc",
    currentItem: {}
};

entity.columns= function() {
    return [
        {checkbox:true},
        {
            field: 'entity_id',
            title: 'entityId'
        }, {
            field: 'parent_id',
            title: 'parentId'
        },{
            field: 'entity_code',
            title: 'entityCode',
        },{
            field: 'entity_name',
            title: 'entityName',
        }
    ]
};

entity.queryParams=function (params) {
    if(!params)
        return {
            entityName:$("#entity_table").val()
        };

    var temp={
        limit: params.limit, //页面大小
        offset: params.offset, //页码
        entityName: $("#entity_table").val(),
        instanceId:$('#instanceId').val()
    };
    return temp;
};

entity.init=function () {
    entity.table = $('#' + entity.tableId).bootstrapTable({
        url: entity.baseUrl, //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + entity.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: entity.id, //排序方式
        queryParams: entity.queryParams,//传递参数（*）
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
        uniqueId: entity.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        singleSelect:true, //禁止多选
        columns: entity.columns()

    })
};

//函数主体
layui.use(['form', 'layedit', 'laydate'], function () {

    entity.init();
    var editIndex;
    var layer=layui.layer,
        from=layui.form(),
        layedit=layui.layedit,
        laydate = layui.laydate,
        layerTips = parent.layer === undefined ? layui.layer : parent.layer;

    var addBoxIndex = -1;

    //初始化页面上面的按钮事件
    $('#btn_query_entity').on('click', function () {
        entity.table.bootstrapTable('refresh', entity.queryParams());
    });

    $('#btn_add_entity').click(function () {
       $.get('/entityEdit', null, function (form){
           if(!entity.compareWindow){
               entity.compareWindow=layer.open({
                   type: 1,
                   title: '添加entity',
                   btn: ['添加','关闭'],
                   yes: function (index){
                      addEntity();
                      layui.layer.close(index)
                   },
                   end:function(){
                       entity.compareWindow=null;
                   },
                   shade: false,
                   offset: ['20px', '20%'],
                   area: ['800px', '600px'],
                   maxmin: true,
                   content: form
               });
           }
       })
    });

    function addEntity(){
        var data={"entity_id":$('#entity_id').val(),"parent_id":$('#parent_id').val(),
                    "entity_code":$('#entity_code').val(),"entity_name":$('#entity_name').val()};
        $.ajax({
            url: 'rest/addEntity',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (mg) {
                if(mg)
                    layui.layer.msg("添加成功!!!!");
                else {layui.layer.msg("添加失败，请检查问题!!!!");}
            }
        });
    }

    $('#btn_edit_entity').click(function(){
        if (addBoxIndex !== -1)
            return;

        var selectData= entity.table.bootstrapTable('getSelections')[0];
        if(!selectData){
            layerTips.msg('先选中一条数据!');
            return
        }

        $.get('/entityEdit',null,function(form){

            if(!entity.compareWindow){
                entity.compareWindow=layer.open({
                    type: 1,
                    title: '修改entity',
                    btn: ['保存','关闭'],
                    yes: function (index){
                        editEntity();
                        layui.layer.close(index)
                    },
                    end:function(){
                        entity.compareWindow=null;
                    },
                    shade: false,
                    offset: ['20px', '20%'],
                    area: ['800px', '600px'],
                    maxmin: true,
                    content: form,
                    success:function(){
                        var id={'id':selectData.entity_id}
                        $.ajax({
                            url: 'rest/getEntityById',
                            type: 'POST',
                            data: JSON.stringify(id),
                            contentType: "application/json",
                            dataType: "json",
                            success: function (entity) {
                                $('#entity_id').val(entity.entity_id);
                                $('#parent_id').val(entity.parent_id);
                                $('#entity_code').val(entity.entity_code);
                                $('#entity_name').val(entity.entity_name);
                            }
                        });

                    }
                });
            }
        });

        function editEntity() {
            var data={"entity_id":selectData.entity_id,"parent_id":$('#parent_id').val(),
                "entity_code":$('#entity_code').val(),"entity_name":$('#entity_name').val()};
            $.ajax({
                url: 'rest/editEntity',
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (mg) {
                    if(mg)
                    layui.layer.msg("修改成功!!!!");
                    else {layui.layer.msg("修改失败，请检查我题!!!!");}
                }
            });
        }
    })
});