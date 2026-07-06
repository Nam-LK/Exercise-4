<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/admin/home">Trang chủ</a>
                </li>
                <li class="active">Quản lý khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="ace-settings-container" id="ace-settings-container">
            </div> <!--/.ace-settings-box -->
            <div class="page-header">
                <h1>
                    Quản lý khách hàng
                    <small>
                        <%--                        <i class="ace-icon fa fa-angle-double-right"></i>--%>
                        <%--                        overview &amp; stats--%>
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title"> Tìm kiếm </h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>


                            </div>
                        </div>

                        <div class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
                            <div class="widget-main">
                                <form:form modelAttribute="customerSearch" method="get" action="/admin/customer-list" id="listForm">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-sm-4">
                                                <label class="name">Tên khách hàng</label>

                                                    <%--                                                <input type="text" id="name" class="form-control" name="" >--%>
                                                <form:input path="customerName" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="form-field-8">Di động</label>

                                                    <%--                                                <input type="number" class="form-control" name="" id="">--%>
                                                <form:input path="phone" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="form-field-9" class="name"> Email </label>
                                                    <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                <form:input path="email" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-sm-4">
                                                <security:authorize access="hasRole('MANAGER')">
                                                <label for="" class="name"> Chọn nhân viên</label>
                                                    <form:select path="staffId" cssClass="form-control">
                                                        <form:option value="" label="-- Chọn nhân viên --"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </security:authorize>
<%--                                                <select name="" id="" class="form-control">--%>
<%--                                                    <option value=""> -- Chọn Nhân Viên --</option>--%>
<%--                                                    <option value=""> Trọng</option>--%>
<%--                                                    <option value=""> Bình</option>--%>
<%--                                                    <option value=""> Kha</option>--%>
<%--                                                    <option value=""> Mạnh</option>--%>
<%--                                                </select>--%>
                                                    <%--                                            </div>--%>
                                            </div>
<%--                                        </div>--%>

<%--                                            <div class="form-group">--%>
                                                    <%--                                        </div>--%>
<%--                                            </div>--%>
                                        </div>
                                    </div>
                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <button class="btn btn-xs btn-danger" id="btnSearchCustomer">
                                                            <i class="ace-icon glyphicon glyphicon-search"></i>
                                                            Tìm kiếm
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
<%--                                    </div>--%>
                                    </form:form>

                                    <!-- <div class="pull-right">
                                                <button>
                                                    <i class="bi bi-building-add"></i>
                                                </button>
                                            </div> -->

                                </div>
                            </div>
                            <div class="pull-right">
                                <a href="/admin/customer-edit">
                                    <button class="btn btn-info btn-white" title="Thêm khách hàng">
                                        <i class="bi bi-person-fill-add"></i>
                                    </button>
                                </a>
                                <button class="btn btn-white btn-danger btn-bold" id="btnDeleteCustomer" title="Xoá khách hàng">
                                    <%--                                <i class="bi bi-building-fill-dash"></i>--%>
                                    <i class="bi bi-person-fill-dash"></i>
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div><!-- /.ace-settings-container -->

        </div><!-- /.span -->
        <!-- Danh sách tòa nhà -->
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-xs-12">
                <display:table name="customerSearch.listResult" requestURI="/admin/customer-list" defaultsort="3" defaultorder="ascending"
                               size="${customerSearch.totalItems}" pagesize="${customerSearch.maxPageItems}"
                               id="tableList"
                        cellspacing="0" cellpadding="0" partialList="true" export="false" sort="external" class="table table-striped table-bordered table-hover"
                       style="font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;">
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th class="center">--%>
<%--                            <label class="pos-rel">--%>
<%--                                <input type="checkbox" class="ace"/>--%>
<%--                                <span class="lbl"></span>--%>
<%--                            </label>--%>
<%--                        </th>--%>
<%--                        <th> Tên khách hàng</th>--%>
<%--                        <th> Di động</th>--%>
<%--                        <th> Email</th>--%>
<%--                        <th> Nhu cầu</th>--%>
<%--                        <th> Người thêm</th>--%>
<%--                        <th> Ngày thêm</th>--%>
<%--                        <th> Tình trạng</th>--%>
<%--                        <th>Thao tác</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
                    <display:column title="
                        <label class='pos-rel'>
                            <input type='checkbox' id='checkAll' name='checkList' class='ace'/>
                            <span class='lbl'></span>
                        </label>" class="center">
                    <label class="pos-rel">
                        <input type="checkbox" id="checkALl" name="checkList" value="${tableList.id}" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                    </display:column>
<%--                <c:forEach var="item" items="${customers}">--%>
                    <display:column property="fullName" title="Tên khách hàng"/>
                    <display:column property="phone" title="Di động"/>
                    <display:column property="email" title="Email"/>
                    <display:column property="demand" title="Nhu cầu"/>
                    <display:column property="createdBy" title="Người thêm"/>
                    <display:column property="createdDate" title="Ngày thêm"/>
                    <display:column  title="Tình trạng" >
                        <c:if test="${tableList.status eq 'DANG_XU_LY'}">
                            <c:out value="Đang xử lý"/>
                        </c:if>

                        <c:if test="${tableList.status eq 'DA_XU_LY'}">
                            <c:out value="Đã xử lý"/>
                        </c:if>


                        <c:if test="${tableList.status eq 'CHUA_XU_LY'}">
                            <c:out value="Chưa xử lý"/>
                        </c:if>
                    </display:column>
                    <display:column title="Thao tác">
<%--                    <tbody>--%>
<%--                    <tr>--%>
<%--                        <td class="center">--%>
<%--                            <label class="pos-rel">--%>
<%--                                <input type="checkbox" value="${item.id}" class="ace"/>--%>
<%--                                <span class="lbl"></span>--%>
<%--                            </label>--%>
<%--                        </td>--%>
<%--                        <td>${item.fullName}</td>--%>
<%--                        <td>${item.phone}</td>--%>
<%--                        <td>${item.email}</td>--%>
<%--                        <td>${item.demand}</td>--%>
<%--                        <td>${item.createdBy}</td>--%>
<%--                        <td>${item.createdDate}</td>--%>
<%--                        <td>${item.note}</td>--%>

<%--                        <td>--%>
                        <security:authorize access="hasRole('MANAGER')">
                            <div class="hidden-sm hidden-xs btn-group" onclick="">
                                <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                onclick="assignmentCustomer(${tableList.id})">
                                    <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                                </button>
                    </security:authorize>
                                <a class="btn btn-xs btn-info" title="Sửa thông tin"
                                    href="/admin/customer-edit-${tableList.id}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>

                            <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-xs btn-danger" onclick="deleteCustomer(${tableList.id})" title="Xóa thông tin">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </security:authorize>


                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline pos-rel">
                                    <button class="btn btn-minier btn-primary dropdown-toggle"
                                            data-toggle="dropdown" data-position="auto">
                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                    </button>

                                    <ul
                                            class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                        <li>
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
														<span class="blue">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
														<span class="green">
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
														</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
														<span class="red">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                    </display:column>
<%--                        </td>--%>
<%--                    </tr>--%>


<%--                    </tbody>--%>
<%--                </c:forEach>--%>
                </display:table>
            </div><!-- /.span -->
        </div><!-- /.row -->

        <div class="hr hr-18 dotted hr-double"></div>

        <!--hết-->

    </div><!-- /.col -->

    <div class="modal fade" id="assignmentCustomerModal" role="dialog">
         style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table id="staffList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">Chọn</th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <input type="hidden"  id="customerId">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" id="btnAssignmentCustomer">Giao tòa
                        nhà</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"> Đóng </button>
                </div>
            </div>

        </div>
    </div>
</div>

<script>
    $('#btnSearchCustomer').click(function (e){
        $('#listForm').submit();
    })
    function assignmentCustomer(customerId){
        $('#customerId').val(customerId);
        assginmentCustomerModalOpen();
        displayStaff(customerId);
    }
    function assginmentCustomerModalOpen(){
        $('#assignmentCustomerModal').modal();
    }

    $('#btnDeleteCustomer').click(function (e){
        e.preventDefault();
        showAlertBeforeDelete(function (){
            var ids = $('#tableList').find('tbody input[type = checkbox]:checked').map(function (){
                return $(this).val();
            }).get();
            handleWDeleteCustomer(ids);
        });
    });

    function handleWDeleteCustomer(data){
        $.ajax({
           type: "DELETE",
           url: "/api/customer",
           data: JSON.stringify(data),
           contentType: "application/json",
            success:function (response){
               alert("Delete customer successfully");
                window.location.href = "/admin/customer-list?message=success";
            },
            error:function (response){
               window.location.href = "/admin/customer-list?message=error";
                console.log(response);
            }
        });
    }

    function deleteCustomer(customerId){
        showAlertBeforeDelete(function (){
           handleWDeleteCustomer([customerId]);
        });
    }

    function displayStaff(customerId){
        $.ajax({
           type: "GET",
           url: "/api/customer/"+customerId+"/staffs",
            dataType: "json",
            success:function (res){
               var row = '';
               $.each(res.data,function (index,item){
                   row+='<tr>';
                   row+='<td class="text-center"><input type="checkbox" value='+ item.staffId + ' id = "checkbox_' + item.staffId + '" class="check-box-element"' + item.checked +'/></td>';
                   row+='<td class = "text-center">' + item.fullName + '</td>';
                   row+='</tr>';
               });
               $('#staffList').html(row);
            },
            error:function (res){
               console.log(res);
            }
        });
    }

    $('#btnAssignmentCustomer').click(function (e){
       e.preventDefault();
       var data = {};
       data['customerId'] = $('#customerId').val();
        data['staffId'] = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
           return $(this).val();
       }).get();
       handleUpdateAssignCustomer(data);
    });

    function handleUpdateAssignCustomer(data){
        $.ajax({
            type: "POST",
            url: "/api/customer/assignment",
            data: JSON.stringify(data),
            contentType: "application/json",
            success:function (response){
                alert("The process is complete");
            },
            error:function (response){
                window.location.href = "/admin/customer-list?message=assign_error";
            }
        });
    }

</script>
</body>
</html>