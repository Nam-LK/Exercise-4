<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/admin/building"/>
<html>
<head>
    <title>Thêm/sửa tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-content">
    <script type="text/javascript">
        try{ace.settings.check('main-content' , 'fixed')}catch(e){}
    </script>
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
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1 style="font-family: 'Times New Roman', Times, serif;">
                        Sửa hoặc thêm mới tòa nhà

                    </h1>
                </div><!-- /.page-header -->

                <div class = "row">
                    <div class="col-xs-12">
                    </div>
                </div>

                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="buildingEdit" id="listForm" method="get" action="/admin/building-edit">
                         <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="form-edit">
                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Tên tòa nhà
                                </label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="name" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">Quận</label>
                                <div class="col-xs-2">
                                    <form:select class="form-control" path="district">
                                        <form:option value=""> --- Chon Quận --- </form:option>
                                        <form:options items="${districts}" />
                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phường
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="ward" id="ward" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Đường
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="street" id="street" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Kết cấu
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="structure" id="structure" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Số tầng hầm
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="numberofbasement" id="numberofbasement" value="">
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Diện tích sàn
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="floorarea" id="floorarea" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Hướng
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="direction" id="direction" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Hạng
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="level" id="level" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Diện tích thuê
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentarea" id="rentarea" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Giá thuê
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentprice" id="rentprice" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Mô tả giá
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentpricedescription" id="rentpricedescription" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí dịch vụ
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="serviceFee" id="serviceFee" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí ô tô
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="" id="otoFee" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí mô tô
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="motoFee" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí ngoài giờ
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="outFee" value="">
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Tiền điện
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Đặt cọc
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Thanh toán
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Thời hạn thuê
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Thời gian trang trí
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Tên quản lý
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    SĐT quản lý
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí môi giới
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">Loại tòa nhà</label>
                                <div class="col-xs-9">
<%--                                    <label for="" class="checkbox-inline">--%>
<%--                                        <input type="checkbox" name="typecode" id="" value="noi-that">Nội thất--%>
<%--                                    </label>--%>

<%--                                    <label for="" class="checkbox-inline">--%>
<%--                                        <input type="checkbox" name="typecode" id="" value="nguyen-can">Nguyên căn--%>
<%--                                    </label>--%>

<%--                                    <label for="" class="checkbox-inline">--%>
<%--                                        <input type="checkbox" name="typecode" id="" value="tang-tret">Tầng trệt--%>
<%--                                    </label>--%>
                                    <form:checkboxes items="${typeCodes}" path="typeCode" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Ghi chú
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Hình đại diện
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                    </c:if>
                                    <c:if test="${ empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>

                        </form>
                    </div>

                    </form:form>


                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>
    $('#btnAddOrUpdateBuilding').click(function(){
        var data = {};
        var typeCode =[];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i,v){
            if(v.name != 'typeCode'){
                data[""+ v.name + ""] = v.value;
            }else{
                typeCode.push(v.value);
            }

        });
        data['typeCode' ] = typeCode;
        if (typeCode != ' '){
            addOrUpdateBuilding(data);
        }else{
            window.location.href= "<c:url value="/admin/building-edit?typeCode=require"/>";
        }
    });
    function addOrUpdateBuilding(data){
        $.ajax({
            type:"POST",
            // url: "/admin/building",
            url: "${buildingAPI}",
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
    $('#btnCancel').click(function(e){
        window.location.href = "/admin/building-list";
    });
</script>

</body>
</html>
