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
        <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css">
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
                                    <h3 class="box-title">分类列表</h3>
                                </div>

                                <div class="box-body">
                                    <div class="col-xs-12">
                                        <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-plus"></i>新增</a> &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-default" onclick="App.deleteMulti('/user/delete')"><i class="fa fa-fw fa-trash-o"></i>删除</button>    &nbsp;&nbsp;
                                        <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-level-down"></i>导入</a> &nbsp;&nbsp;
                                        <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-level-up"></i>导出</a>   &nbsp;&nbsp;
                                    </div>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="treeTable" class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>排序</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach items="${content_category_list}" var="contentCategory">
                                               <tr id="${contentCategory.id}" pId="${contentCategory.parentId}">
                                                   <td>${contentCategory.id}</td>
                                                   <td>${contentCategory.name}</td>
                                                   <td>${contentCategory.sortOrder}</td>
                                                   <td>
                                                       <a href="#" type="button" class="btn btn-primary"><i class="fa fa-fw fa-edit"></i>编辑</a>   &nbsp;&nbsp;&nbsp
                                                       <button type="button" class="btn btn-danger"><i class="fa fa-fw fa-trash-o"></i>删除</button> &nbsp;&nbsp;&nbsp
                                                       <a href="#" type="button" class="btn btn-default"><i class="fa fa-fw fa-plus"></i>新增下级菜单</a>   &nbsp;&nbsp;&nbsp
                                                   </td>
                                               </tr>
                                           </c:forEach>
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

        <script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
        <script>
           $(function () {
              $("#treeTable").treeTable({
                  expandLevel:2,
                  column:1
              }) ;
           });
        </script>

    </body>
</html>