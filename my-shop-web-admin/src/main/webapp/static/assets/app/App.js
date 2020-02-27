
/**
 * 函数对象
 */
var App = function () {

    //定义ICheck对象
    var _master;
    var _checkbox;

    //定义一个存放id数组
    var _idArray;

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
            _idArray = new Array();
            _checkbox.each(function (){
                var _id = $(this).attr("id");
                if(_id != null && _id != undefined && $(this).is(':checked')){
                    _idArray.push(_id);
                }
            });

            //判断用户是否选择了数据项
            if(_idArray.length == 0){
                $("#modal-message").html("您当前未选择任何数据项，请至少选择一项！");
            }
            else{
                $("#modal-message").html("是否删除选中的数据项？");
            }

            //绑定确定按钮事件,如果用户点击确定删除
            $("#modal-buttonOk").bind("click",function () {
                del();
            });

        /**
         * 私有函数的私有函数
         */
        function del() {

            //数据项为0关闭模态框
            if(_idArray.length == 0){
                $("#modal-default").modal("hide");
            }
            //删除操作
            else{
                var paramData = {"ids":_idArray.toString()};
                deleteDo(url,paramData);
            }
        }
    };

    function deleteDo(url,paramData) {
        setTimeout(function () {
            $.ajax({
                "url": url,
                "data":paramData,
                "type": "POST",
                "sync":false,
                "dataType": "JSON",
                "success": function (data) {
                    //无论成功失败，都需要弹出模态框进行提示，需要先解绑确定按钮
                    $("#modal-buttonOk").unbind("click");
                    //请求删除成功
                    if(data.status == 200){
                        //刷新页面
                        $("#modal-buttonOk").bind("click",function () {
                            window.location.reload();
                        });
                    }
                    //请求删除失败
                    else{
                        //确定按钮的时间改为隐藏模态框
                        $("#modal-buttonOk").bind("click",function () {
                            $("#modal-default").modal("hide");
                        });
                    }
                    //无论成功失败都显示提示信息，模态框为提示
                    $("#modal-message").html(data.message);
                    $("#modal-default").modal("show");
                }
            });
        },500);
    }

    var handlerDelete = function (url,id) {
        $("#modal-message").html("是否删除选中的数据项？");
        $("#modal-default").modal("show");
        //绑定确定按钮事件,如果用户点击确定删除
        $("#modal-buttonOk").bind("click",function () {
            var paramData = {"id":id};
            deleteDo(url,paramData);
        });
    }

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
        //通过ajax请求html的方式将jsp装载进模态框中
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
        //初始化
        init : function (){
            handlerInitICheck();
            handlerCheckAll();
        },

        //批量删除
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        //初始化dataTables
        initDataTables: function (url,columns) {
           return handlerInitDataTables(url,columns);
        },

        //显示详情
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        //单条删除
        deleteById: function (url,id) {
            handlerDelete(url,id);
        }
    }
}();

$(document).ready(function () {
    App.init();
});