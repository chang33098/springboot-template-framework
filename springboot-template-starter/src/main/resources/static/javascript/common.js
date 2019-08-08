var page = {
    init_table: function (option) {
        option.table.render({
            elem: option.elem,
            url: domain + option.url,
            toolbar: false,
            defaultToolbar: [],
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
                    'data': result.content,
                    'count': result.totalElements
                };
            },
            request: {
                pageName: 'pageNo',
                limitName: 'limit'
            }
        });

        table.on('tool(data-table)', option.event);
    },
    open_view: function (option) {
        layer.open({
            type: 2,
            title: option.title || 'title',
            content: domain + option.url, //连接
            maxmin: option.maxmin || true,
            area: option.area || ['100%', '100%'],
            btn: option.btn || ['确定', '取消'],
            yes: option.yes || function (index, layero) {
                var submit = layero.find('iframe').contents().find("#save-submit");
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
            success: option.success || function (result) {
                console.info(result);

                layer.msg(result.message, {icon: '1'});
                $('#form-search').submit();
            },
            error: option.error || function (error) {
                console.info(error);

                layer.msg('', {icon: '5'});

            }
        })
    }
};