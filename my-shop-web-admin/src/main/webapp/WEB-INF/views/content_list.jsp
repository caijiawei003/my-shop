<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
    <head>
        <title>我的商城|内容管理</title>
        <jsp:include page="../includes/header.jsp"/>
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <jsp:include page="../includes/nav.jsp"/>

            <jsp:include page="../includes/menu.jsp"/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        内容管理
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                        <li class="active">内容管理</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">

                    <div class="row box-search" style="display: none;">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">高级搜索</h3>

                                        <div class="row form-horizontal" style="margin-top: 15px">
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="title" class="col-sm-4 control-label">标题</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="title" placeholder="标题"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="subTitle" class="col-sm-4 control-label">子标题</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="subTitle" placeholder="子标题"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="titleDesc" placeholder="标题描述"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box-footer">
                                            <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                                        </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- /.row -->
                    <div class="row">
                        <div class="col-xs-12">

                            <c:if test="${baseResult != null}">
                                <div class="alert alert-${baseResult.status == 200?"success":"danger"} alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>

                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">内容列表</h3>
                                </div>

                                <div class="box-body">
                                        <div class="col-xs-12">
                                            <a href="/content/form" type="button" class="btn btn-default"><i class="fa fa-fw fa-plus"></i>新增</a> &nbsp;&nbsp;
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-default" onclick="App.deleteMulti('/user/delete')"><i class="fa fa-fw fa-trash-o"></i>删除</button>    &nbsp;&nbsp;
                                            <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-level-down"></i>导入</a> &nbsp;&nbsp;
                                            <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-level-up"></i>导出</a>   &nbsp;&nbsp;
                                            <button class="btn btn-primary" onclick="$('.box-search').css('display') == 'none'?$('.box-search').show('fast'):$('.box-search').hide('fast')"><i class="fa fa-fw fa-search"></i>搜索</button>
                                        </div>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="dataTable" class="table table-hover">
                                        <thead>
                                          <tr>
                                            <th><input type="checkbox" class="minimal master"></th>
                                            <th>ID</th>
                                            <th>标题</th>
                                            <th>子标题</th>
                                            <th>标题描述</th>
                                            <th>链接</th>
                                            <th>图片1</th>
                                            <th>图片2</th>
                                              <th>更新时间</th>
                                              <th>操作</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </section>
            </div>

            <jsp:include page="../includes/copyright.jsp"/>

        </div>

        <jsp:include page="../includes/footer.jsp"/>

        <!--模态框 -->
        <sys:modal/>
        <sys:detail title="查看内容详情"/>
<script>
    //表格变量
    var _dataTable = null;

    $(function(){
        //dataTables渲染
        var columns = [
            {"data": function ( row, type, val, meta ) {
                    return '<input id="'+ row.id +'"  type="checkbox" class="minimal">';
                }},
            { "data": "id" },
            { "data": "title" },
            { "data": "subTitle" },
            { "data": "titleDesc" },
            { "data": function ( row, type, val, meta ) {
                    return hrefVal(row.url);
                }},
            { "data": function ( row, type, val, meta ) {
                    return hrefVal(row.pic);
                }},
            { "data":function ( row, type, val, meta ) {
                    return hrefVal(row.pic2);
                }},
            { "data": function ( row, type, val, meta ) {
                    return DateTime.format(row.updated,"yyyy-MM-dd HH:mm:ss");
                }
            },
            {"data": function ( row, type, val, meta ) {
                    var url = '/content/detail?id='+row.id;
                    var urlDelete = '/content/deleteById';
                    return '<button type="button" class="btn btn-default" onclick="App.showDetail(\''+url+'\')"><i class="fa fa-fw fa-search"></i>查看</button> &nbsp;&nbsp;&nbsp'+
                        '<a href="/content/form?id='+ row.id +'" type="button" class="btn btn-primary"><i class="fa fa-fw fa-edit"></i>编辑</a>   &nbsp;&nbsp;&nbsp'+
                        '<button type="button" class="btn btn-danger" onclick="App.deleteById(\''+urlDelete+'\','+row.id+')"><i class="fa fa-fw fa-trash-o"></i>删除</button> &nbsp;&nbsp;&nbsp';
                }}
        ]
        _dataTable =  App.initDataTables("/content/page",columns);

    });

    function hrefVal(url){
        if(url == null){
            return "";
        }
        return '<a href="'+url+'" target="_blank">查看</a>';
    }

    function search(){
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();
        var param = {
            "title":title,
            "subTitle":subTitle,
            "titleDesc":titleDesc
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }

</script>
    </body>
</html>