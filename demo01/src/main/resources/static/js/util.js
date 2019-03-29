var util = {
    get: function (url, params, callback, options) {
        if (!options) options = {};
        options.type = 'GET';
        return this.request(url, params, callback, options)
    },

    put: function (url, params, callback, options) {
        if (!options) options = {};
        options.type = 'PUT';
        return this.request(url, params, callback, options);
    },

    post: function (url, params, callback, options) {
        if (!options) options = {};
        options.type = 'POST';
        return this.request(url, params, callback, options);
    },

    delete: function (url, params, callback, options) {
        if (!options) options = {};
        options.type = 'DELETE';
        return this.request(url, params, callback, options);
    },

    request: function (url, params, callback, options) {
        if (!options) options = {};
        var defaultOptions = {
            type: 'GET',
            url: "http://localhost:8888/" + url,
            dataType: 'json',
            data: params,
            success: function (data) {
                callback(data);
            },
            error: function (data) {
                toastr.error(data)
            }
        };
        for (var p in options) {
            defaultOptions[p] = options[p];
        }
        $.ajax(defaultOptions);
    },

    create: function (beanName, bean, callback) {
        $.ajax({
            type: 'post',
            url: 'http://localhost:8888/sys/' + beanName,
            data: {beanJson: JSON.stringify(bean)},
            success: function (data) {
                callback(data);
            },
            error: function () {
                alert("系统错误!");
            }
        })
    },

    deserialize: function ($fm, bean) {
        $fm.find("[name]").each(function () {
            var name = $(this).attr("name");
            if (bean[name] === undefined || bean[name] === null)
                bean[name] = '';
            var type = $(this).attr('type');
            if (type && type.toLowerCase() === 'checkbox') {
                if (bean[name] === '1') {
                    this.checked = true;
                } else {
                    this.checked = false;
                }
            } else {
                $(this).val(bean[name]);
            }
        });
    },

    serialize: function ($fm) {
        var serializeObj = {};
        $fm.find("[name]").each(function () {
            var type = $(this).attr('type');
            if (type && type.toLowerCase() === 'checkbox') {
                if ($(this).is(":checked")) {
                    serializeObj[$(this).attr('name')] = true;
                } else {
                    serializeObj[$(this).attr('name')] = false;
                }
            } else {
                serializeObj[$(this).attr('name')] = $(this).val();
            }
        });
        return serializeObj;
    },

    showModal: function (acc) {
        acc.modal('show');
    },

    hideModal: function (acc) {
        acc.modal('hide')
    },

    renderTime: function (date) {
        var dateee = new Date(date).toJSON();
        return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    },

    /**
     * 左侧菜单树结构生成
     * @param rootList
     * @returns {Array}
     */
    listTree: function (rootList) {
        var tree = new Array();
        for (var i = 0; i < rootList.length; i++) {
            if ('-1' === rootList[i].parentCode) {
                tree.push(rootList[i]);
            }
        }
        for (var j = 0; j < tree.length; j++) {
            tree[j].childs = getChild(tree[j].code, rootList);
        }

        function getChild(code, rList) {
            var childList = new Array();
            for (var i = 0; i < rList.length; i++) {
                if (rList[i].parentCode !== null || rList[i].parentCode !== '') {
                    if (rList[i].parentCode === code) {
                        childList.push(rList[i]);
                    }
                }
            }
            for (var j = 0; j < childList.length; j++) {
                if ('' === childList[j].url || null === childList[j].url) {
                    childList[j].childs = getChild(childList[j].code, rList);
                }
            }
            if (childList.length === 0) {
                return null;
            }
            return childList;
        }

        return tree;
    },


    /**
     * 角色赋权菜单树
     * @param list
     * @param opt
     * @returns {Array}
     */
    list2Tree: function (list, opt) {

        function getRoot(list, pId) {
            var roots = [];
            for (var i = 0; i < list.length; i++) {
                if (list[i].pId == pId) {
                    roots.push(list[i]);
                }
            }
            return roots;
        }

        function buildTree(roots, list) {
            for (var i = 0; i < roots.length; i++) {
                var root = roots[i];
                var nodes = [];
                for (var j = 0; j < list.length; j++) {
                    var node = list[j];
                    if (node.pId == root.id) {
                        nodes.push(node);
                    }
                }
                root.nodes = nodes;
                buildTree(root.nodes, list);
            }

            return roots;
        }

        if (!list) return [];
        if (!opt) opt = {};

        for (var i = 0; i < list.length; i++) {
            for (var p in opt) {
                list[i][p] = list[i][opt[p]];
            }
            if (!list[i].pId) list[i].pId = -1;
        }

        var tree = buildTree(getRoot(list, -1), list);

        return tree;
    },

};

var treeViewHelper = {};
/**
 * tree view遍历节点值
 * @param node
 * @returns {Array}
 */
treeViewHelper.getChildrenNodeIdArr = function (node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId)
            if (node.nodes[x].nodes) {
                var getNodeDieDai = this.getChildrenNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}
/**
 * 获取treeview的父级节点
 * @param treeId
 * @param node
 * @returns {Array}
 */
treeViewHelper.getParentIdArr = function (treeId, node) {
    var ts = [];
    var parent = $('#' + treeId).treeview('getParent', node);
    while (parent.id && parent.id != 0) {
        ts.push(parent);
        parent = $('#' + treeId).treeview('getParent', parent);
    }
    return ts;
};

$(function () {
    /* 所有模态框关闭按钮事件 */
    $("button[cancel='close']").click(function () {
        $("div[role='dialog']").modal('hide');
    });
});