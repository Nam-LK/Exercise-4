<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
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
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="form-edit">
                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Tên tòa nhà
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Quận
                                </label>
                                <div class="col-xs-2">
                                    <select id="districtid" class="form-control " name="districtid" id="districtid">
                                        <option value="">--Chọn quận--</option>
                                        <option value="">Quận 1</option>
                                        <option value="">Quận 2</option>
                                        <option value="">Quận 10</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phường
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="ward" id="ward">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Đường
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="street" id="street">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Kết cấu
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="structure" id="structure">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Số tầng hầm
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="numberofbasement" id="numberofbasement">
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Diện tích sàn
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="floorarea" id="floorarea">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Hướng
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="direction" id="direction">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Hạng
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="level" id="level">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Diện tích thuê
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentarea" id="rentarea">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Giá thuê
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentprice" id="rentprice">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Mô tả giá
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="rentpricedescription" id="rentpricedescription">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí dịch vụ
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí ô tô
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí mô tô
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-xs-3">
                                    Phí ngoài giờ
                                </label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" name="name" id="name">
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
                                <label for="" class="col-xs-3">
                                    Loại tòa nhà
                                </label>
                                <div class="col-xs-9">
                                    <label for="" class="checkbox-inline">
                                        <input type="checkbox" name="typecode" id="" value="noi-that">Nội thất
                                    </label>

                                    <label for="" class="checkbox-inline">
                                        <input type="checkbox" name="typecode" id="" value="nguyen-can">Nguyên căn
                                    </label>

                                    <label for="" class="checkbox-inline">
                                        <input type="checkbox" name="typecode" id="" value="tang-tret">Tầng trệt
                                    </label>
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

                                    <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm tòa nhà</button>
                                    <button type="button" class="btn btn-primary">Hủy thao tác</button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->


</body>
</html>
