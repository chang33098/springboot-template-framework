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
            limit: 10,
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
    }
};