<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var = "buildingListURL" value="/admin/building-list" />
<c:url var="buildingAPI" value = " /api/building"/>

<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-content">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Quản lý tòa nhà</li>
                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Dashboard
                    </h1>


                </div><!-- /.page-header -->

                <div class = "row">
                    <div class="col-xs-12 ">
                        <div class="widget-box ui-sortable-handle">
                            <div class="widget-header">
                                <h5 class="widget-title">Tìm kiếm</h5>

                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="widget-body" style="font-family: 'Times New Roman', Times, serif">
                                <div class="widget-main">
                                    <form:form id="listForm" action="${buildingListURL}" method="get" modelAttribute="modelSearch">  <!--modelAttribute se
                                    cầm cái dto mình vừa gửi param vào fe để gắn vào các value cho input html để lưu dữ liệu mình mới nhập-->
                                        <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label for="" class="name">Tên tòa nhà</label>
<%--                                                    <input type="text" class="form-control" name="name" id="name" value="${modelSearch.name}">--%>
                                                    <form:input class = "form-control" path="name" /> <!--sử dụng input của spring-->
                                                </div>
                                                <div class="col-xs-6">
                                                    <label for="" class="name">Diện tích sàn</label>
<%--                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input class = "form-control" path="floorArea" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label for="" class="name">Quận</label>
<%--                                                    <select class="form-control" name="district">--%>
<%--                                                        <option value="">----Chọn quận---</option>--%>
<%--                                                        <option value="Quan_1">Quận 1</option>--%>
<%--                                                        <option value="Quan_2">Quận 2</option>--%>
<%--                                                        <option value="Quan_3">Quận 3</option>--%>
<%--                                                    </select>--%>
                                                    <form:select class="form-control" path="district">
                                                        <form:option value=""> --- Chon Quận --- </form:option>
                                                        <form:options items="${districts}" />
<%--                                                        <form:option value="Quan_2">Quận 2</form:option>--%>
<%--                                                        <form:option value="Quan_3">Quận 3</form:option>--%>
<%--                                                        <form:option value="Quan_10">Quận 10</form:option>--%>
                                                    </form:select>
                                                </div>

                                                <div class="col-xs-5">
                                                    <label for="" class="name">Phường</label>
                                                    <input type="text" class="form-control" name="ward" value="${modelSearch.ward}">
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="" class="name">Đường</label>
                                                    <input type="text" class="form-control" name="street" value="${modelSearch.street}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label for="" class="name">Số tầng hầm</label>
                                                    <input type="text" class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}">
                                                </div>
                                                <div class="col-xs-4">
                                                    <label for="" class="name">Hướng</label>
                                                    <input type="text" class="form-control" name="direction" value="${modelSearch.direction}">
                                                </div>
                                                <div class="col-xs-4">
                                                    <label for="" class="name">Hạng</label>
                                                    <input type="number" class="form-control" name="level" value="${modelSearch.level}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label for="" class="name">Diện tích từ</label>
                                                    <input type="number" class="form-control" name="areaFrom" value="">
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="" class="name">Diện tích đến</label>
                                                    <input type="number" class="form-control" name="areaTo" value="">
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="" class="name">Giá thuê từ</label>
                                                    <input type="number" class="form-control" name="rentPriceFrom" value="">
                                                </div>
                                                <div class="col-xs-3">
                                                    <label for="" class="name">Giá thuê đến</label>
                                                    <input type="number" class="form-control" name="rentPriceTo" value="">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label for="" class="name">Tên quản lý</label>
                                                    <input type="text" class="form-control" name="managerName" value="">
                                                </div>
                                                <div class="col-xs-5">
                                                    <label for="" class="name">Điện thoại quản lý</label>
                                                    <input type="number" class="form-control" name="managerPhone" value="">
                                                </div>
                                                <div class="col-xs-2">
                                                    <label for="" class="name">Chọn nhân viên phụ trách</label>
                                                    <form:select path="staffId" class = "form-control">
                                                        <form:option value=""> --Chọn nhân viên-- </form:option>
<%--                                                        <form:option value="1">Nhân viên A </form:option>--%>
<%--                                                        <form:option value="2"> Nhân viên B</form:option>--%>
                                                        <form:options items="${listStaffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
<%--                                                    <label for="" class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="noi-that"> Nội thất--%>
<%--                                                    </label>--%>
<%--                                                    <label for="" class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="nguyen-can"> Nguyên căn--%>
<%--                                                    </label>--%>
<%--                                                    <label for="" class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="tang-tret"> Tầng trệt--%>
<%--                                                    </label>--%>

                                                    <form:checkboxes items="${typeCodes}" path="typeCode" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-xs btn-danger" id="btnSearchBuilding">
                                                        <i class="ace-icon fa fa-search nav-search-icon"></i>Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    </form:form>

                                </div>
                            </div>

                            <div class="pull-right">
                                <a href="/admin/building-edit ">
                                    <button class="btn btn-info" title="Thêm tòa nhà">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>

                                    </button>
                                </a>

                                <a href="">
                                    <button class="btn btn-info " title="Xóa tòa nhà" id="btnDeleteBuilding">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </a>
                            </div>
                        </div>



                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table id="tableList" class="table table-striped table-bordered table-hover"  style="margin: 3em;">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name="checkList" value="" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa  nhà</th>
                            <th>Địa chỉ</th>
                            <th >Số tầng hầm</th>

                            <th>Tên quản lý</th>
                            <th >Số điện thoại quản lý</th>

                            <th>Diện tích sàn</th>
                            <th>Diện tích trống</th>
                            <th>Diện tích thuê</th>
                            <th>Phí MG</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${buildingList}">
                            <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td>${item.name}</td>
                            <td>${item.address}</td>
                            <td >${item.numberOfBasement}</td>
                            <td>${item.managerName}</td>
                            <td>${item.managerPhone}</td>
                            <td>${item.floorArea}</td>
                            <td>${item.rentArea}</td>
                            <td>${item.id}</td>

                            <td class="hidden-480">
                                <span class="label label-sm label-warning"></span>
                            </td>

                            <td>
                                <div class="hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>

                                    <a class="btn btn-xs btn-info" title="sửa tòa nhà" href="/admin/building-edit-${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>

                                    <button class="btn btn-xs btn-danger" title="Xoá tòa nhà" onclick="deleteBuilding(${item.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>

                                </div>

                                <div class="hidden-md hidden-lg">
                                    <div class="inline pos-rel">
                                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                        </button>

                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a href="#" class="tooltip-info" data-rel="tooltip" title="" data-original-title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>


                        </c:forEach>

<%--                        <tr>--%>
<%--                            <td class="center">--%>
<%--                                <label class="pos-rel">--%>
<%--                                    <input type="checkbox" class="ace">--%>
<%--                                    <span class="lbl"></span>--%>
<%--                                </label>--%>
<%--                            </td>--%>

<%--                            <td>--%>
<%--                                Nam Giao Building--%>
<%--                            </td>--%>
<%--                            <td></td>--%>
<%--                            <td ></td>--%>
<%--                            <td></td>--%>
<%--                            <td></td>--%>
<%--                            <td></td>--%>
<%--                            <td></td>--%>
<%--                            <td></td>--%>

<%--                            <td class="hidden-480">--%>

<%--                            </td>--%>

<%--                            <td>--%>
<%--                                <div class="hidden-sm hidden-xs btn-group">--%>
<%--                                    <button class="btn btn-xs btn-success">--%>
<%--                                        <i class="ace-icon fa fa-check bigger-120"></i>--%>
<%--                                    </button>--%>

<%--                                    <button class="btn btn-xs btn-info">--%>
<%--                                        <i class="ace-icon fa fa-pencil bigger-120"></i>--%>
<%--                                    </button>--%>

<%--                                    <button class="btn btn-xs btn-danger">--%>
<%--                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                                    </button>--%>


<%--                                </div>--%>

<%--                                <div class="hidden-md hidden-lg">--%>
<%--                                    <div class="inline pos-rel">--%>
<%--                                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">--%>
<%--                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>--%>
<%--                                        </button>--%>

<%--                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">--%>
<%--                                            <li>--%>
<%--                                                <a href="#" class="tooltip-info" data-rel="tooltip" title="" data-original-title="View">--%>
<%--																			<span class="blue">--%>
<%--																				<i class="ace-icon fa fa-search-plus bigger-120"></i>--%>
<%--																			</span>--%>
<%--                                                </a>--%>
<%--                                            </li>--%>

<%--                                            <li>--%>
<%--                                                <a href="#" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">--%>
<%--																			<span class="green">--%>
<%--																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>--%>
<%--																			</span>--%>
<%--                                                </a>--%>
<%--                                            </li>--%>

<%--                                            <li>--%>
<%--                                                <a href="#" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">--%>
<%--																			<span class="red">--%>
<%--																				<i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--																			</span>--%>
<%--                                                </a>--%>
<%--                                            </li>--%>
<%--                                        </ul>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </tr>--%>

                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div> <!-- bảng trả kết quả-->

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times </button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table  class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
<%--                    <tr>--%>
<%--                        <td class="center">--%>
<%--                            <input type="checkbox"  id="checkbox_1" value="1" >--%>
<%--                        </td>--%>
<%--                        <td>Nguyễn Văn A</td>--%>
<%--                    </tr>--%>




                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnassignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentBuilding(buildingid){
        $('#assignmentBuildingModal').modal();
        loadStaff(buildingid);
        $('buildingId').val(buildingid); //đẩy id về cho hidden iput

    }
    function loadStaff(buildingId){
        $.ajax({
            type:"GET",
            // url: "/admin/building",
            url: "${buildingAPI}/"+ buildingId + "/staffs",
            data : JSON.stringify(data),
            contentType: "application/json",
            dataType:"JSON",
            success: function(response) {
                var row = '';
                $.each(response.data, function (index, item){
                    row += '<tr>';
                    row +='<td class="text-center"><input type="checkbox" value='+ item.staffId + 'id = "checkbox_' + item.staffId + ' class = "check-box-element" ' + item.checked + '/></td>';
                    row += '<td class = "text-center"> ' + item.fullName + '</td>';
                    row+='</tr>';
                })
                $('#staffList tbody').html(row);

            },
            error: function(response){
                console.log(response);
                window.location.href = "<c:url value="/admin/admin/building-list?message=error"/> ";
            }
        })
    }

    $('#btnassignmentBuilding').click(function(e){
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this.val());
        }).get();
        data['staffs'] = staffs;
        if (data['staffs'] != ''){
            assignment(data);
        }
    });
    function assignment(data){
        $.ajax({
            type:"POST",
            // url: "/admin/building",
            url: "${buildingAPI}/" + 'assignment',
            data : JSON.stringify(data),
            contentType: "application/json",
            dataType:"JSON",
            success: function(response) {

            },
            error: function(response){
                console.info("Giao thành công");
                window.location.href = "<c:url value="/admin/admin/building-list?message=error"/> ";
            }
        })
    }

    $('#btnSearchBuilding').click(function(e){
        e.preventDefault();
        $('#listForm').submit();
    });
    function deleteBuilding(data){
        var buildingIds = [data];
        deleteBuildings((data))

    }
    $('#btnDeleteBuilding').click(function(e){
        e.preventDefault();
        var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val());
        }).get();
        deleteBuildings(buildingIds);
    });

    function deleteBuildings(data){
        $.ajax({
            type:"DELETE",
            // url: "/admin/building",
            url: "${buildingAPI}/"+ data,
            data : JSON.stringify(data),
            contentType: "application/json",
            dataType:"JSON",
            success: function(respon) {
                $("#h11").html(respon);
            },
            error: function(respon){
                console.log(respon);
            }
        })
    }

</script>
</body>

</html>
