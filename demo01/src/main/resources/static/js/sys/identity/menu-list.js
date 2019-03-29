var menuTreeTable = null;
$(function () {
    util.get("sys/sysMenu", {}, function (res) {
        for (var i = 0; i < res.length; i++) {
            res[i].id = res[i].code;
            res[i].parentId = res[i].parentCode;
        }
        menuTreeTable = $("#tableId").bootstrapTreeTable({
            expandColumn: '1',
            rootIdValue: '-1',
            toolbar: '#menu-toolbar',
            columns: [
                {field: 'selectItem', radio: true},
                {field: 'name', title: '名称'},
                {field: 'code', title: '编码'},
                {field: 'url', title: 'URL'}
            ],
            data: res
        });
        $("#add-btn").click(function () {
            addMenu();
        });
        $("#edit-btn").click(function () {
            updateMenu();
        });
        $("#del-btn").click(function () {
            deleteMenu();
        });
    });
});

function addMenu() {
    var editModal = $("#editModal");
    editModal.find('.editTitle').text("新增菜单");
    util.showModal(editModal);
    $('#form')[0].reset();
}
var btn = $("button[type='submit']");
btn.click(function () {
    if (btn.attr('mark') === 'add') {
        var menu = util.serialize($("#editModal"));
        util.create('sysMenu', menu, function (res) {
            if (res.ok) {
                toastr.success('创建成功!');
                util.hideModal($("#editModal"));
                menuTreeTable.bootstrapTreeTable('refresh',{params:'localhost:8888/sys/sysMenu'});
            }
        })
    }
});

function updateMenu() {
    var editModal = $('#editModal');
    editModal.find('.editTitle').text("修改菜单");
    var row = menuTreeTable.bootstrapTreeTable('getSelections')[0];
    if(row && row!==null){
        util.showModal(editModal);
        util.deserialize(editModal, row);
    }else{
        toastr.warning('请选择需要修改的菜单');
    }

}

function deleteMenu() {
    var row = menuTreeTable.bootstrapTreeTable('getSelections')[0];
    if (row && row !== null) {
        bootbox.confirm({
            size: 'small',
            title: '操作确认',
            message: '确认删除菜单：' + row.name + '?',
            callback: function (res) {
                if (res) {
                    util.delete('sys/sysMenu/' + row.code, {}, function (e) {
                        if (e.ok) {
                            toastr.success('删除成功!');
                            menuTreeTable.bootstrapTreeTable('refresh');
                        } else {
                            toastr.warning(e.msg)
                        }
                    })
                }
            }
        })
    } else {
        toastr.warning('请选择需要删除的菜单');
    }

}