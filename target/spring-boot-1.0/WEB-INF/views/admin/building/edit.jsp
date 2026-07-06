<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingList" value="/admin/building-list"/>
<html>
<head>
    <title>Thông Tin Tòa Nhà</title>
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
                    <a href="/admin/home">Home</a>
                </li>
                <li class="active">Quản lý tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="ace-settings-container" id="ace-settings-container">

            </div> <!--/.ace-settings-box -->
            <div class="page-header">
                <h1>
                    Quản lý tòa nhà
<%--                    <small>--%>
<%--                        <i class="ace-icon fa fa-angle-double-right"></i>--%>
<%--                        overview &amp; stats--%>
<%--                    </small>--%>
                </h1>
            </div><!-- /.page-header -->
            <form:form modelAttribute="buildingEdit" action="${buildingEditURL}" id="listForm" method="post">
                <div class="row" style="font-family:'Times New Roman', Times, serif ">
                    <div class="col-xs-12">
                        <form class="form-horizontal" id="form-edit">
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Tên tòa nhà </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="name" name="name" class="form-control">--%>
                                    <form:input path="name" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="" class="col-sm-3"> Quận </label>
                                <div class="col-sm-3">
                                        <%--                                <select name="district" id="district" class="form-control">--%>
                                        <%--                                    <option value="">--Chọn Quận--</option>--%>
                                        <%--                                    <option value="1"> Quận 1 </option>--%>
                                        <%--                                    <option value="2"> Quận Bình Thạnh </option>--%>
                                        <%--                                </select>--%>
                                    <form:select path="district" cssClass="form-control">
                                        <form:option value="" label="--Chọn Quận--"/>
                                        <form:options items="${districts}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phường </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="ward" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Đường </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="street" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Kết cấu </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="structure" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Số tầng hầm </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="numberOfBasement" id="numberOfBasement" class="form-control">--%>
                                    <form:input path="numberOfBasement" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Diện tích sàn </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="floorArea" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Hướng </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="direction" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Hạng </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="level" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Diện tích thuê </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="rentArea" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Giá thuê </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="rentPrice" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Mô tả giá </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="rentPriceDescription" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phí dịch vụ </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number"  name="" id="" class="form-control">--%>
                                    <form:input path="serviceFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phí ô tô </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="carFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phí mô tô </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="motoFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phí ngoài giờ </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="overtimeFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Tiền điện </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="electricityFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Đặt cọc </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="deposit" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Thanh toán </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" name="" id="" class="form-control">--%>
                                    <form:input path="payment" cssClass="form-control"/>
                                </div>
                            </div>
                                <%--                        <div class="form-group">--%>
                                <%--                            <label class="col-sm-3" for=""> Thanh toán </label>--%>
                                <%--                            <div class="col-sm-9">--%>
                                <%--                                <input type="text" name="" id="" class="form-control">--%>
                                <%--                            </div>--%>
                                <%--                        </div>--%>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Thời gian trang trí </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="decorationTime" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Tên quản lý </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="managerName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> SĐT quản lý </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="managerPhone" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Phí môi giới </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="number" value="0.0" name="" id="" class="form-control">--%>
                                    <form:input path="brokerageFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="" class="col-sm-3">Loại tòa nhà</label>
                                <div class="col-xs-3" id="typeCode" style="display: flex; flex-direction: row; justify-content: space-between;">
                                        <%--                                <label for="" class="checkbox-inline">--%>
                                        <%--                                    <input type="checkbox" name="typeCode" id="typeCode" value="noi-that"> Nội thất--%>
                                        <%--                                </label>--%>
                                        <%--                                <label for="" class="checkbox-inline">--%>
                                        <%--                                    <input type="checkbox" name="typeCode" id="typeCode" value="tang-tret"> Tầng trệt--%>
                                        <%--                                </label>--%>
                                        <%--                                <label for="" class="checkbox-inline">--%>
                                        <%--                                    <input type="checkbox" name="typeCode" id="typeCode" value="nguyen-can"> Nguyên căn--%>
                                        <%--                                </label>--%>
                                    <c:if test="${empty buildingEdit.id}">
                                        <form:checkboxes path="typeCode"  items="${typeCodes}"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3" for=""> Ghi chú </label>
                                <div class="col-sm-9">
                                        <%--                                <input type="text" name="" id="" class="form-control">--%>
                                    <form:input path="note" cssClass="form-control"/>
                                </div>
                            </div>
                                <%--                            <div class="form-group row">--%>
                                <%--                                <label class="col-sm-3" for=""> Hình đại diện </label>--%>
                                <%--                                <div class="col-sm-9">--%>
                                <%--                                    <input type="file" name="" id="" class="form-control">--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingEdit.image}">
                                        <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                        <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                    </c:if>
                                    <c:if test="${empty buildingEdit.image}">
                                        <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="" class="col-sm-3"></label>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingEdit.id}">
                                        <button class="btn btn-primary" type="button" id="btnAddBuilding"> Cập nhật tòa
                                            nhà
                                        </button>
                                        <button class="btn btn-primary" id="cancelBtn" type="button"> Hủy thao tác
                                        </button>
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </c:if>
                                    <c:if test="${empty buildingEdit.id}">
                                        <button class="btn btn-primary" type="button" id="btnAddBuilding"> Thêm tòa
                                            nhà
                                        </button>
                                        <button class="btn btn-primary" id="cancelBtn" type="button"> Hủy thao tác
                                        </button>
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>
                        </form>
                    </div>
                </div>
            </form:form>
        </div><!-- /.ace-settings-container -->

        <!-- Danh sách tòa nhà -->

    </div><!-- /.page-content -->
</div>
<script>
    var imageBase64 = '';
    var imageName = '';
    $('#btnAddBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, it) {
            if (it.name !== 'typeCode') {
                data[it.name] = it.value;
            } else {
                typeCode.push(it.value);
            }
        });
        if(imageBase64!==''){
            data['imageBase64'] = imageBase64;
            data['imageName'] = imageName;
        }
        // var type = typeCode.join();
        typeCode =  $('#typeCode').find('input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['typeCode'] = typeCode.join();
        if ($('#buildingId').val()!="") {
            handleWUpdateBuilding(data,$('#buildingId').val());
        } else {
            handleWAddBuilding(data);
        }
        $('#loading_image').show();
    });

    function handleWAddBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            // dataType: "JSON",
            contentType: "application/json",
            success: function (response) {
                window.location.href = "/admin/building-list?message=success";
            },
            error: function (response) {
                // console.log("fail",response.response.status);
                window.location.href = "/admin/building-edit?message=fail";
                console.log(response);
            }
        });
    }

    function handleWUpdateBuilding(data, buildingId) {
        $.ajax({
            type: "PUT",
            url: "${buildingAPI}/" + buildingId,
            data: JSON.stringify(data),
            // dataType: "JSON",
            contentType: "application/json",
            success:function (response){
                console.log("success",response)
                window.location.href = "${buildingList}";
            },
            error: function (response){
                // console.log("fail",response.status,response.responseText);
                window.location.href = "/admin/building-edit-"+buildingId+"?message=fail";
                // console.log(response,response.status);
            }
        });

    }

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $('#uploadImage').change(function (event){
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    $('#cancelBtn').click(function (e) {
        e.preventDefault();
        window.location.href = '${buildingList}';
    })
    function loadTypeCode(buildingId){
        $.ajax({
            url:"/api/building/"+buildingId+"/types",
            type: "GET",
            dataType: 'json',
            success: function (res) {
                var row='';
                $.each(res.data, function (index, item) {
                    row+='<span>';
                    row+='<input type="checkbox" name="checkType" value='+item.typeCode+' '+item.checked+'>'
                    row+='<label>'+item.typeName+'</label>';
                    row+='</span>'
                });
                $('#typeCode').html(row);
            },
            error : function(response){
                console.log('fail')
            }
        });
    }
    var buildingId = $('#buildingId').val();
    loadTypeCode(buildingId);
</script>
</body>
</html>
