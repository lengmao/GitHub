$(function () {
    util.get('menuList', {}, function (res) {
        var $container = $('#menuNav');
        $container.empty();
        createMenuEle($container.get(0), res);
    })
});

//动态切换iframe里边的内容
function pageTo (href,name) {
    var iframe=document.createElement('iframe');
    iframe.setAttribute('src',href);
    iframe.setAttribute('height','100%');
    iframe.setAttribute('width','100%');
    iframe.setAttribute('scrolling','yes');
    iframe.setAttribute('frameborder','0');
    iframe.setAttribute('margin-left','0px');
    iframe.setAttribute('name',name);
    $('#iframeContent').empty().append(iframe);
}

//生成左边的菜单
function createMenuEle(acc, array) {
    var l = array.length;
    for (var i = 0; i < l; i++) {
        var li = document.createElement('li');
        var a = document.createElement('a');
        var iEle = document.createElement('i');
        var spanEle = document.createElement('span');
        acc.appendChild(li);
        if (array[i].childs) {
            (function () {
                var iArrow = document.createElement('i');
                var div = document.createElement('div');
                var ul = document.createElement('ul');
                div.setAttribute('id', array[i].code);
                div.setAttribute('class', 'collapse');
                ul.setAttribute('class', 'nav');
                a.setAttribute('href', '#' + array[i].code);
                a.setAttribute('data-toggle', 'collapse');
                a.setAttribute('class', 'collapsed');
                iEle.setAttribute('class', array[i].icon);
                spanEle.innerHTML = array[i].name;
                iArrow.setAttribute('class', 'icon-submenu lnr lnr-chevron-left');
                li.appendChild(a);
                li.appendChild(div);
                a.appendChild(iEle);
                a.appendChild(spanEle);
                a.appendChild(iArrow);
                div.appendChild(ul);
                createMenuEle(ul, array[i].childs);
            })();
            continue;
        }
        a.onclick = (function (url,name) {
            return function () {
                pageTo(url,name);
            }
        })(array[i].url,array[i].name);
        iEle.setAttribute('class', array[i].icon);
        spanEle.innerHTML = array[i].name;
        a.appendChild(iEle);
        a.appendChild(spanEle);
        a.setAttribute('href','#');
        li.appendChild(a);
    }
    return acc;
}