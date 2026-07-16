<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditURL" value="/admin/building-edit" />
<c:url var="buildingListURL" value="/admin/building-list" />
<c:url var="buildingAPIURL" value ="/api/building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="ace-settings-container" id="ace-settings-container">
            </div> <!--/.ace-settings-box -->
            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
<%--                    <small>--%>
<%--                        <i class="ace-icon fa fa-angle-double-right"></i>--%>
<%--                        overview &amp; stats--%>
<%--                    </small>--%>
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
                                <form:form modelAttribute="buildingSearch" action="${buildingListURL}" method="GET" id="searchForm">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <label class="name">Tên tòa nhà</label>

                                                        <%--                                                <input type="text" id="name" class="form-control" name="">--%>
                                                    <form:input path="name" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-6">
                                                    <label for="form-field-8">Diện tích sàn</label>

                                                        <%--                                                <input type="number" class="form-control" name="" id="" value="">--%>
                                                    <form:input path="floorArea" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-2">
                                                    <label for="form-field-9">Quận hiện có</label>
                                                    <form:select path="district" cssClass="form-control">
                                                        <form:option value=""  label="--Chọn Quận--"/>
                                                        <form:options items="${districts}"  />
                                                    </form:select>
                                                        <%--                                                <select name="" id="district" class="form-control">--%>
                                                        <%--                                                    <option value="">--Chọn Quận--</option>--%>
                                                        <%--                                                    <option value="1"> Quận 1</option>--%>
                                                        <%--                                                    <option value="2"> Quận 2</option>--%>
                                                        <%--                                                    <option value="3"> Quận Bình Thạnh</option>--%>
                                                        <%--                                                    <option value="10"> Quận 10</option>--%>
                                                        <%--                                                </select>--%>
                                                </div>
                                                <div class="col-sm-5">
                                                    <label for="form-field-9">Phường</label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="ward" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-5">
                                                    <label for="form-field-9"> Đường </label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="street" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label for="form-field-9" class="name"> Số tấng hầm </label>
                                                        <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                    <form:input path="numberOfBasement" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <label for="form-field-8" class="name"> Hướng </label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="direction" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <label for="form-field-8" class="name"> Hạng </label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="level" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-3">
                                                    <label for="" class="name"> Diện tích từ </label>
                                                        <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                    <form:input path="rentAreaFrom" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="" class="name"> Diện tích đến </label>
                                                        <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                    <form:input path="rentAreaTo" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="" class="name"> Giá thuê từ </label>
                                                        <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                    <form:input path="rentPriceFrom" cssClass="form-control" />
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="" class="name"> Giá thuê đến </label>
                                                        <%--                                                <input type="number" name="" id="" class="form-control">--%>
                                                    <form:input path="rentPriceTo" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label for="form-field-9"> Tên quản lý </label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="managerName" cssClass="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="" class="name"> Điện thoại quản lý </label>
                                                        <%--                                                <input type="text" name="" id="" class="form-control">--%>
                                                    <form:input path="managerPhone" cssClass="form-control" />
                                                </div>
                                                <div class="col-xs-2">
                                                        <%--                                                <select name="" id="" class="form-control">--%>
                                                        <%--                                                    <option value=""> -- Chọn Nhân Viên --</option>--%>
                                                        <%--                                                    <option value=""> Trọng</option>--%>
                                                        <%--                                                    <option value=""> Bình</option>--%>
                                                        <%--                                                    <option value=""> Kha</option>--%>
                                                        <%--                                                    <option value=""> Mạnh</option>--%>
                                                        <%--                                                </select>--%>
                                                    <security:authorize access="hasRole('MANAGER')">
                                                    <label for="" class="name"> Chọn nhân viên phụ trách</label>
                                                        <form:select path="staffId" cssClass="form-control">
                                                            <form:option value="" label="--Chọn nhân viên--"/>
                                                            <form:options items="${staffs}" />
                                                        </form:select>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                        <%--                                                <label for="" class="checkbox-inline">--%>
                                                        <%--                                                    <input type="checkbox" name="" id="typeCode" value="noi-that"> Nội--%>
                                                        <%--                                                    Thất--%>

                                                        <%--                                                </label>--%>
                                                        <%--                                                <label for="" class="checkbox-inline">--%>
                                                        <%--                                                    <input type="checkbox" name="" id="typeCode" value="tang-tret"> Tầng--%>
                                                        <%--                                                    trệt--%>
                                                        <%--                                                </label>--%>
                                                        <%--                                                <label for="" class="checkbox-inline">--%>
                                                        <%--                                                    <input type="checkbox" name="" id="typeCode" value="nguyen-can">--%>
                                                        <%--                                                    Nguyên căn--%>
                                                        <%--                                                </label>--%>
                                                    <form:checkboxes path="typeCode" id="typeCode" items="${typeCodes}" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button class="btn btn-xs btn-danger" id="btnSearchBuilding">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>

                            </div>

                            <!-- <div class="pull-right">
                                        <button>
                                            <i class="bi bi-building-add"></i>
                                        </button>
                                    </div> -->


                        </div>
                        <div class="pull-right">
                            <a href="${buildingEditURL}">
                                <button class="btn btn-info btn-white"  title="Thêm tòa nhà">
                                    <i class="bi bi-building-fill-add"></i>
                                </button>
                            </a>
                            <button class="btn btn-white btn-danger btn-bold" id="btnDeleteBuilding" title="Xoá tòa nhà">
                                <i class="bi bi-building-fill-dash"></i>
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
            <display:table name="buildingSearch.listResult" requestURI="${buildingListURL}" size="${buildingSearch.totalItems}"
                           pagesize="${buildingSearch.maxPageItems}"
                           id="tableList" cellspacing="0" cellpadding="0" partialList="true" export="false" sort="external"
                           defaultsort="3" defaultorder="ascending"
                           class="table table-striped table-bordered table-hover"
                           style="font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;">
                <%--                <thead>--%>
                <%--                <tr>--%>
                <%--                    <th class="center">--%>
                <%--                        <label class="pos-rel">--%>
                <%--                            <input type="checkbox" id="checkAll" name="checkList" class="ace"/>--%>
                <%--                            <span class="lbl"></span>--%>
                <%--                        </label>--%>
                <%--                    </th>--%>
                <display:column title="
                        <label class='pos-rel'>
                            <input type='checkbox' id='checkAll' name='checkList' class='ace'/>
                            <span class='lbl'></span>
                        </label>" class="center">
                    <label class="pos-rel">
                        <input type="checkbox" id="checkAll" value="${tableList.id}" name="checkList" class="ace"/>
                        <span class="lbl"></span>
                    </label>
                </display:column>
                <display:column property="name" title="Tên tòa nhà"/>
                <display:column property="address" title="Địa chỉ"/>
                <display:column property="numberOfBasement" title="Số tầng hầm" />
                <display:column property="managerName" title="Tên quản lý" />
                <display:column property="managerPhone" title="Số điện thoại" />
                <display:column property="floorArea" title="D.T sàn<" />
                <display:column property="emptyArea" title="D.T trống"/>
                <display:column property="rentPrice" title="Giá thuê"/>
                <display:column property="rentArea" title="D.T thuê"/>
                <display:column property="serviceFee" title="Phí dịch vụ" />
                <display:column title="Phí MG" property="brokerageFee" />
                <%--                    <th>Thao tác</th>--%>
                <%--                </tr>--%>
                <%--                </thead>--%>

                <%--                <tbody>--%>
                <%--                <c:forEach var="item" items="${buildings}">--%>
                <%--                <tr>--%>
                <%--                    <td class="center">--%>
                <%--                        <label class="pos-rel">--%>
                <%--                            <input type="checkbox" class="ace"  value="${item.id}"/>--%>
                <%--                            <span class="lbl"></span>--%>
                <%--                        </label>--%>
                <%--                    </td>--%>
                <%--                    <td>--%>
                <%--                        ${item.name}--%>
                <%--                    </td>--%>
                <%--                    <td>${item.address}</td>--%>
                <%--                    <td>${item.numberOfBasement}</td>--%>
                <%--                    <td>${item.managerName}</td>--%>
                <%--                    <td>${item.managerPhone}</td>--%>
                <%--                    <td>${item.floorArea}</td>--%>
                <%--                    <td>${item.emptyArea}</td>--%>
                <%--                    <td>${item.rentPrice}</td>--%>
                <%--                    <td>${item.rentArea}</td>--%>
                <%--                    <td>${item.serviceFee}</td>--%>
                <%--                    <td>${item.brokeragefee}</td>--%>

                <display:column title="Thao tác">
                    <div class="hidden-sm hidden-xs btn-group">
                        <security:authorize access="hasRole('MANAGER')">
                        <button class="btn btn-xs btn-success" title="Giao toà nhà"
                                onclick="assignmentBuilding(${tableList.id})">
                            <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                        </button>
                </security:authorize>

                        <a class="btn btn-xs btn-info" title="Sửa tòa nhà"
                           href="<c:url value="/admin/building-edit-${tableList.id}"/>">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-xs btn-danger" onclick="deleteBuilding(${tableList.id})" title="Xóa tòa nhà">
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
                <%--                </tr>--%>

                <%--                </c:forEach>--%>
                <%--                </tbody>--%>
            </display:table>
        </div><!-- /.span -->
    </div><!-- /.row -->

    <div class="hr hr-18 dotted hr-double"></div>

    <!--hết-->


</div><!-- /.col -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog"
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
                <input type="hidden"  id="buildingId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAssignmentBuilding">Giao tòa
                    nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal"> Đóng </button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentBuilding(buildingID) {
        $('#buildingId').val(buildingID);
        assignmentBuildingModalOpen();
        loadStaff(buildingID);
        // console.log(buildingID);
    }
    function assignmentBuildingModalOpen() {
        $('#assignmentBuildingModal').modal();
    }
    function loadStaff(buildingID){
        $.ajax({
            url: "${buildingAPIURL}/"+buildingID+"/staffs",
            type: "GET",
            dataType: 'JSON',
            success: function (res) {
                var row='';
                $.each(res.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class="check-box-element"' + item.checked +'/></td>';
                    row += '<td class="text-center">'+ item.fullName +'</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error : function(response){
                console.log('fail')
            }
        });
    }
    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data={};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        var jsonString = JSON.stringify(data);
        console.log(jsonString);
        var parsedObject = JSON.parse(jsonString);
        console.log(parsedObject)
        handleAssignBuilding(data);
    });

    function deleteBuilding(buildingId){
        showAlertBeforeDelete(function (){
            handleDeleteBuilding([buildingId]);
        });
    }

    $('#btnDeleteBuilding').click(function (e){
        e.preventDefault();
        showAlertBeforeDelete(function (){
            var ids = $('#tableList').find('tbody input[type =checkbox]:checked').map(function (){
                return $(this).val();
            }).get();
            console.log(ids);

            handleDeleteBuilding(ids);
        });
    })

    function handleDeleteBuilding(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPIURL}",
            data: JSON.stringify(data),
            contentType:"application/json",
            success: function (res){
                // console.log("success")
                window.location.href ="/admin/building-list?message=delete_success";
            },
            error: function (res){
                console.log(res,res.responseText,res.status);
                // window.location.href="/admin/building-list?message=delete_error";
            }
        });
    }

    function handleAssignBuilding(data){
        $.ajax({
            url: "/api/user/assignment",
            type: "POST",
            data: JSON.stringify(data),
            // dataType: 'json',
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                console.log(JSON.parse(response).success());
            },
            // error : function(response){
            //     console.log(response,response.responseText,response.status);
            //     console.log('fail');
            // }
            error: function (xhr, textStatus) {
                console.log('Error:', textStatus);
                // console.log(JSON.parse(xhr).error());
                console.log('Response:', xhr.responseText);
            }
        });
    }
    $('#btnSearchBuilding').click(function (e){
        e.preventDefault();
        $('#searchForm').submit();
    });
</script>
</body>
</html>