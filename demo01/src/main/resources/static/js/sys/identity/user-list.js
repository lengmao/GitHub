var userTable = null;
var currentRowId = null;
$(function () {
    userTable = new initMainTable({
        url: 'sys/userPage',
        columns: [
            {
                field: 'name',
                title: '用户名'
            }, {
                field: 'email',
                title: '邮箱'
            }, {
                field: 'createTime',
                title: '创建时间',
                formatter: function (value) {
                    return util.renderTime(value);
                }
            }, {
                field: 'status',
                title: '用户状态',
                formatter: function (value) {
                    return value === "1" ? "激活" : "禁用";
                }
            }, {
                field: 'id',
                title: '操作',
                formatter: function (value, row, index) {
                    return [
                        '<button type="button" class="btn btn-default btn-xs" onclick="updateUser(\'' + row.id + '\')">修改</button>',
                        '<button type="button" class="btn btn-default btn-xs" onclick="assortRoles(\'' + row.id + '\')">分配角色</button>'
                    ]
                }
            }
        ]
    });

    $("#search-btn").click(function () {
        userTable.search();
    });

    $("#add-btn").click(function () {
        addUser();
    });


});

/**
 * 创建用户
 */
function addUser() {
    $('#editModal').find('.editTitle').text("新增用户");
    util.showModal($('#editModal'));
    $('#form')[0].reset();

}

var btn = $("button[type='submit']");
btn.click(function () {
    if (btn.attr('mark') === "add") {
        var user = util.serialize($('#editModal'));
        if (user.password === user.rePassword) {
            delete user.rePassword;
            util.create('sysUser', user, function (res) {
                if (res.ok) {
                    toastr.success("用户创建成功!");
                    util.hideModal($('#editModal'));
                    userTable.search();
                } else {
                    toastr.error(res.msg);
                }
            })
        }
    }
});

/**
 * 修改用户
 * @param id
 */
function updateUser(id) {
    var editModal = $('#editModal');
    editModal.find('.editTitle').text("修改用户");
    var user = userTable.getRowById(id);
    user.password = '';
    util.showModal(editModal);
    util.deserialize(editModal, user);
    currentRowId = id;
}

var btn = $("button[type='submit']");
btn.click(function () {
    if (btn.attr('mark') !== "add") {
        var upUser = util.serialize($('#editModal'));
        if (upUser.password === upUser.rePassword) {

        }
    }

});

/**
 * 用户分配角色
 * @param id
 */
function assortRoles(id) {

    var userRoles;
    util.get('sys/userRole/' + id, {}, function (res) {
        userRoles = res;
    },{async : false});
    util.get('sys/rolePage', {}, function (res) {
        if (res.rows) {
            var roleList = res.rows;
            $("#roleTable").empty();
            var h = '';
            h += '<thead>';
            h += '<tr style="font-size: 16px">';
            h += '<th width="100px">选择</th>';
            h += '<th>角色名称</th>';
            h += '</tr>';
            h += '</thead>';
            h += '<tbody>';
            for (var i = 0; i < roleList.length; i++) {
                h += '<tr>';
                h += '<td><input type="checkbox" id="' + roleList[i].id + '"/></td>';
                h += '<td>' + roleList[i].name + '</td>';
                h += '</tr>';
            }
            h += '</tbody>';
            $("#roleTable").append(h);
            for (var i = 0; i < roleList.length; i++) {
                if (userRoles.length && userRoles.length > 0) {
                    for (var j = 0; j < userRoles.length; j++) {
                        if (roleList[i].id === userRoles[j].id) {
                            var checkbox = document.getElementById(roleList[i].id);
                            checkbox.checked = true;
                        }
                    }
                }
            }
        }
    });
    var assortRolesModal = $("#assortRolesModal");
    assortRolesModal.find('.editTitle').text("分配角色");
    util.showModal(assortRolesModal);
    currentRowId = id;
}


$("#assortRoles").click(function () {
    var checkbox = $("#roleTable").find('input').attr('checked', true);
    var checkedIds = [];
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            checkedIds.push(checkbox[i].id);
        }
    }
    var checkIdArry = {roleIds: checkedIds.join(',')};
    util.post('sys/assortUserRole/' + currentRowId, checkIdArry, function (res) {
        if (res.ok) {
            util.hideModal($("#assortRolesModal"));
            toastr.success('角色分配成功!')
        } else {
            util.hideModal($("#assortRolesModal"));
            toastr.error(res.msg);
        }
    })
});







