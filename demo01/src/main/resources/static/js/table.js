function initMainTable(setting) {
    var defaultSetting = {
        table: '#tableId',
        url: 'http://localhost:8888/' + setting.url,
        method: 'get',
        toolbar: '#toolbar',
        striped: true,
        cache: false,
        pagination: true,
        sortable: true,
        sortOrder: 'id',
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        queryParams: function (params) {
            var temp = {
                pageSize: params.limit,                         //页面大小
                pageNumber: (params.offset / params.limit) + 1  //页码
            };
            return temp;
        }
    };
    $.extend(defaultSetting, setting);
    this.view = $(defaultSetting.table).bootstrapTable(defaultSetting);
}

initMainTable.prototype = {
    constructor: initMainTable,
    getRowById: function (id) {
        return this.view.bootstrapTable('getRowByUniqueId', id)
    },
    search:function(params){
        this.view.bootstrapTable('refresh',params);
    }
};





