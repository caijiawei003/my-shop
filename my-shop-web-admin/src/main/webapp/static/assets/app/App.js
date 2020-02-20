
/**
 * 函数对象
 */
var App = function () {

    //定义ICheck对象
    var _master;
    var _checkbox;

    //定义一个存放id数组
    var _idArray = new Array();

    /**
     * 初始化iCheck
     */
    var handlerInitICheck = function () {
        //激活checkbox
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })

        //获取Checkbox控制器
        _master =  $('input[type="checkbox"].minimal.master');
        //获取全部Checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * iCheck全选功能
     */
    var handlerCheckAll = function(){
        _master.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 批量删除功能
     */
    var handlerDeleteMulti = function (url) {
            //将选中元素放入数组
            _checkbox = App.getCheckbox();
            _checkbox.each(function (){
                var _id = $(this).attr("id");
                if(_id != null && _id != undefined && $(this).is(':checked')){
                    _idArray.push(_id);
                }
            });

            if(_idArray.length == 0){
                $("#modal-message").html("您当前未选择任何数据项，请至少选择一项！");
            }
            else{
                $("#modal-message").html("是否删除选中的数据项？");
            }

            //绑定确定按钮事件
            $("#modal-buttonOk").bind("click",function () {
                del();
            });

        /**
         * 私有函数的私有函数
         */
        function del() {

            if(_idArray.length == 0){
                $("#modal-default").modal("hide");
            }
            else{
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "data":{"ids":_idArray.toString()},
                        "type": "POST",
                        "sync":false,
                        "dataType": "JSON",
                        "success": function (data) {
                            //删除成功
                            if(data.status == 200){
                                window.location.reload();
                            }
                            //删除失败
                            else{
                                $("#modal-buttonOk").unbind("click");
                                $("#modal-buttonOk").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                                $("#modal-message").html("删除失败，请重新尝试！");
                                $("#modal-default").modal("show");
                            }
                        }
                    });
                },500);
            }
        }
    };

    /**
     * dataTables渲染
     * @param url
     * @param columns
     */
    var handlerInitDataTables = function (url,columns) {
      var _dataTable =  $('#dataTable').DataTable({
            "paging": true,
            "lengthChange": false,
            "autoWidth": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "info": true,
            "deferRender": true,
            "serverSide": true,
            "ajax":{
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            //表格重绘触发js渲染
            "drawCallback": function( settings ) {
                handlerInitICheck();
                handlerCheckAll();
            }
        });
      return _dataTable;
    };

    /**
     * 详情显示
     * @param url
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    return{
        init : function (){
            handlerInitICheck();
            handlerCheckAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        initDataTables: function (url,columns) {
           return handlerInitDataTables(url,columns);
        },

        showDetail: function (url) {
            handlerShowDetail(url);
        }
    }
}();

$(document).ready(function () {
    App.init();
});