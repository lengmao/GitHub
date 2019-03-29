var roleTable = null;
var currentRowId;
$(function () {
    roleTable = new initMainTable({
        url: 'sys/rolePage',
        columns: [
            {
                field: 'name',
                title: '名称'
            }, {
                field: 'description',
                title: '描述'
            }, {
                field: 'id',
                title: '操作',
                formatter: function (value, row, index) {
                    return [
                        '<button type="button" class="btn btn-default btn-xs" onclick="updateRole(\'' + row.id + '\')">修改</button>',
                        '<button type="button" class="btn btn-default btn-xs" onclick="updatePower(\'' + row.id + '\')">赋权</button>',
                        '<button type="button" class="btn btn-default btn-xs" onclick="deleteRole(\'' + row.id + '\')">删除</button>'
                    ]
                }
            }
        ]
    });

    $("#search-btn").click(function () {
        roleTable.search();
    });

    $("#add-btn").click(function () {
        addRole();
    });

});

function addRole() {
    var editModal = $("#editModal");
    util.showModal(editModal);
    editModal.find('.editTitle').text("新增角色");
    $('#form')[0].reset();
    var btn = $("button[type='submit']");
    btn.click(function () {
        if (btn.attr('mark') === 'add') {
            var role = util.serialize(editModal);
            util.create('sysRole', role, function (res) {
                if (res.ok) {
                    toastr.success('创建成功!');
                    util.hideModal(editModal);
                    roleTable.search();
                } else {
                    toastr.error(res.msg);
                }
            })
        }
    });
}

/**
 * 角色赋权
 * @param id
 */
function updatePower(id) {
    initMenuTree('menuTree', id);
    var updatePower = $("#updatePower");
    updatePower.find('.editTitle').text("角色赋权");
    util.showModal(updatePower);
    currentRowId=id;
}

$("#grant-btn").click(function () {
    var nodes = $("#menuTree").treeview(true).getChecked();
    var menuIds = [];
    if (nodes) {
        for (var i = 0; i < nodes.length; i++) {
            menuIds.push(nodes[i].id)
        }
    }
    var menuIdArry = {menuIds: menuIds.join(",")};
    util.post('sys/updatePower/' + currentRowId, menuIdArry, function (res) {
        if (res.ok) {
            toastr.success("修改成功!");
            util.hideModal($("#updatePower"));
        } else {
            toastr.error(res.msg)
        }
    })
});

/**
 * 初始化角色所拥有的菜单
 * @param domId
 * @param roleId
 */
function initMenuTree(domId, roleId) {
    function hasMenu(roleMenus, menuId) {
        for (var i = 0; i < roleMenus.length; i++) {
            if (roleMenus[i].code === menuId) {
                return true
            }
        }
        return false
    }

    util.get('sys/roleMenu/' + roleId, {}, function (res) {
        var menus = res.menus;
        var roleMenus = res.roleMenus;
        $.each(menus, function () {
            this.id = this.code;
            this.state = {
                checked: hasMenu(roleMenus, this.id) ? true : false,
                disabled: false,
                expanded: true,
                selected: false
            };
        });
        menus = util.list2Tree(menus, {nodeId: 'id', id: 'id', pId: 'parentCode', text: 'name'});
        renderMenuTree(domId, menus);
    })
}

function renderMenuTree(domId, nodes) {
    var $menuTree = $('#' + domId).treeview({
        data: nodes,
        showIcon: false,
        showCheckbox: true,
        multiSelect: false,
        levels: 5,
        state: {
            checked: false,
            disabled: false,
            expanded: true,
            selected: false
        },
        onNodeChecked: function (event, data) {
            var selectNodes = treeViewHelper.getChildrenNodeIdArr(data);// 获取所有子节点
            if (selectNodes) {
                $('#' + domId).treeview('checkNode',
                    [selectNodes, {
                        silent: true
                    }]);
            }
            var parNodes = treeViewHelper.getParentIdArr(domId, data);
            if (parNodes) {
                $('#' + domId).treeview('checkNode',
                    [parNodes, {
                        silent: true
                    }]);

            }

        },
        onNodeUnchecked: function (event, data) {
            var selectNodes = treeViewHelper.getChildrenNodeIdArr(data);// 获取所有子节点
            if (selectNodes) {
                $('#' + domId).treeview('uncheckNode',
                    [selectNodes, {
                        silent: true
                    }]);
            }
        }
    });
}


