<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
    <head>
        <title>我的商城|用户管理</title>
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
                        用户管理
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                        <li class="active">用户管理</li>
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
                                                    <label for="email" class="col-sm-4 control-label">邮箱</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="email" placeholder="邮箱"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-4 control-label">姓名</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="username" placeholder="姓名"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="phone" class="col-sm-4 control-label">手机号</label>

                                                    <div class="col-sm-8">
                                                        <input class="form-control" id="phone" placeholder="手机号"/>
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
                                    <h3 class="box-title">用户列表</h3>
                                </div>

                                <div class="box-body">
                                        <div class="col-xs-12">
                                            <a href="/user/form" type="button" class="btn btn-default"><i class="fa fa-fw fa-plus"></i>新增</a> &nbsp;&nbsp;
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
                                            <th>用户名</th>
                                            <th>手机号</th>
                                            <th>邮箱</th>
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
        <sys:detail title="查看用户详情"/>
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
            { "data": "username" },
            { "data": "phone" },
            { "data": "email" },
            { "data": "updated" },
            {"data": function ( row, type, val, meta ) {
                    var url = '/user/detail?id='+row.id;
                    var urlDelete = '/user/deleteById';
                    return '<button type="button" class="btn btn-default" onclick="App.showDetail(\''+url+'\')"><i class="fa fa-fw fa-search"></i>查看</button> &nbsp;&nbsp;&nbsp'+
                        '<a href="/user/form?id='+ row.id +'" type="button" class="btn btn-primary"><i class="fa fa-fw fa-edit"></i>编辑</a>   &nbsp;&nbsp;&nbsp'+
                        '<button type="button" class="btn btn-danger" onclick="App.deleteById(\''+urlDelete+'\','+row.id+')"><i class="fa fa-fw fa-trash-o"></i>删除</button> &nbsp;&nbsp;&nbsp';
                }}
        ]
        _dataTable =  App.initDataTables("/user/page",columns);

    });

    function search(){
        var username = $("#username").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        var param = {
            "username":username,
            "email":email,
            "phone":phone
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }

</script>
    </body>
</html>