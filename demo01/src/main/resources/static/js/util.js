var util = {
    get: function (url, params, callback, options) {
        if (!options) options = {};
        options.type='GET';
        return this.request(url,params,callback,options)
    },

    request: function (url, params, callback, options) {
        if(!options) options={};
        var defaultOptions={
            type:'GET',
            url:"http://localhost:8888/"+url,
            dataType:'json',
            data:params,
            success:function (data) {
                callback(data);
            },
            error:function () {
                alert("系统错误")
            }
        };
        for(var p in options){
            defaultOptions[p]=options[p];
        }
        $.ajax(defaultOptions);
    }
};