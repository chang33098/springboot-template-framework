var page = {
    init_table: function (option) {
        option.component.render({
            id: option.id || 'data-table',
            elem: option.elem,
            url: domain + option.url,
            toolbar: false,
            defaultToolbar: [],
            cellMinWidth: 80,
            title: option.title,
            page: true,
            loading: true,
            limit: option.limit || 10,
            limits: [10, 30, 50, 100],
            cols: [option.cols],
            parseData: function (result) {
                return {
                    'code': '0',
                    'msg': '加载成功',
                    'data': result.data.content,
                    'count': result.data.totalElements
                };
            },
            request: {
                pageName: 'pageNo',
                limitName: 'limit'
            }
        });

        table.on('tool(data-table)', option.event);
    },
    file_upload: function (option) {
        option.component.render({
            url: domain + option.url,
            data: option.data || {},
            headers: option.headers || {},
            elem: option.elem || '#upload-button',
            field: option.field || 'file',
            multiple: option.multiple || false,
            number: option.number || 0,
            accept: option.accept || 'images',
            acceptMime: option.acceptMime || 'image/*',
            exts: option.exts || 'jpg|png|gif|bmp|jpeg',
            before: option.before || function (obj) {
                obj.preview(function (index, file, result) {
                    $('#file-src').attr('src', result); //图片base64
                });
            },
            done: option.done || function (result) {
                console.info('file upload done!');
                if (result.status === httpstatus.OK.code) {
                    $('#file-path').val(result.data.fileName);
                    $('#file-src').css('display', 'block').attr('src', result.data.resourceLink);
                    return layer.msg(result.message, {icon: '1'});
                } else {
                    layer.msg(result.message, {icon: 5})
                }
            },
            error: option.error || function (index, upload) {
                console.info('file upload error!');
                layer.closeAll('loading');
            }
        })
    },
    open_dialog: function (option) {
        layer.open({
            type: 2,
            title: option.title || 'title',
            content: domain + option.url, //连接
            maxmin: option.maxmin || true,
            area: option.area || ['100%', '100%'],
            btn: option.btn || ['确定', '取消'],
            yes: option.yes || function (index, layero) {
                var submit = layero.find('iframe').contents().find("#submit");
                submit.click();
            }
        });
    },
    async_request: function (option) {
        $.ajax({
            url: domain + option.url,
            async: option.async || true,
            type: option.type || 'get',
            data: option.data || {},
            dataType: option.dataType || 'json',
            contentType: option.contentType || 'application/json; charset=utf-8',
            success: option.success || function (response) {
                if (response.status === httpstatus.OK.code) {
                    layer.msg(response.message, {icon: 1, time: option.time || 1000}, function () {
                        option.callback.call(response);
                    });
                } else {
                    layer.msg(response.message, {icon: 5})
                }
            },
            error: option.error || function (error) {
                console.info(error);
                layer.msg(error.responseJSON.message, {icon: 5});
            }
        })
    }
};