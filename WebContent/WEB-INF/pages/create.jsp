<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<script>
	$(document).ready(function() {
		$("#addbtn").click(function() {
			var isbn = $("#isbn").val();
			var title = $("#title").val();
			var authorid = $("#authorid").val();
			var publisher = $("#publisher").val();
			var publishdate = $("#publishdate").val();
			var price = $("#price").val();

			$.ajax({
				type : "POST",
				url : "add",
				data : {
					"adddata" : isbn.toString()+","
									+title.toString()+","
									+authorid.toString()+","
									+publisher.toString()+","
									+publishdate.toString()+","
									+price.toString()
				},
				datatype : "html",
				success : function(data) {
					
					if(data[8]=="0"){
						
						$("#AuthorID").val(authorid);
						
						$("#myAuthor").modal('show');
						
					}
					else{
						location.reload();
					}
					alert('增加成功');
				},
				error : function(data) {
					
					alert('增加失败');
				}
			})
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#authorbtn").click(function() {
			var authorid = $("#AuthorID").val();
			var Name = $("#Name").val();
			var Age = $("#Age").val();
			var Country = $("#Country").val();

			$.ajax({
				type : "POST",
				url : "author",
				data : {
					"authordata" : authorid.toString()+","
									+Name.toString()+","
									+Age.toString()+","
									+Country.toString()
				},
				datatype : "html",
				success : function(data) {
					$("#myAuthor").modal("hide");
					location.reload();
					alert('增加成功');
				},
				error : function(data) {
					alert('增加失败');
				}
			})
		});
	});
</script>
<title>Add User From</title>
</head>
<body>
	<form action="query" method="post">
		<fieldset>
			<legend>请输入作者姓名</legend>
			<p>
				<label>作者：</label> <input type="text" id="name" name="name"
					tabindex="1">
			</p>
			<p id="buttons">
				<input id="reset" type="reset" tabindex="4" value="取消"> <input
					id="submit" type="submit" tabindex="5" value="查询">
			</p>
		</fieldset>
	</form>

	<button class="btn btn-primary" type="button" data-toggle="collapse"
		data-target="#collapseExample" aria-expanded="false"
		aria-controls="collapseExample">增加图书</button>
	<div class="collapse" id="collapseExample">
		<div class="well">
			<div class="input-group">
				<span class="input-group-addon">ISBN</span> <input type="number"
					class="form-control" id="isbn">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Title</span> <input type="text"
					class="form-control" id="title">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">AuthorID</span> <input type="number"
					class="form-control" id="authorid">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Publisher</span> <input type="text"
					class="form-control" id="publisher">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">PublishDate</span> <input
					type="date" class="form-control" id="publishdate">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Price</span> <input type="number"
					class="form-control" id="price">
			</div>
			<br>
			<button type="button" class="btn btn-primary" id="addbtn">增加</button>
		</div>
	</div>
	
	<div class="modal fade" id="myAuthor" tabindex="-1" role="dialog"
		aria-labelledby="myAuthorLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myAuthorLabel">修改</h4>
				</div>
				<div class="modal-body" style="padding: 100px 100px 10px;">
				<div class="input-group">
				<span class="input-group-addon" >AuthorID</span> <input type="number"
					class="form-control" id="AuthorID">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Name</span> <input type="text"
					class="form-control" id="Name">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Age</span> <input type="number"
					class="form-control" id="Age">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">Country</span> <input type="text"
					class="form-control" id="Country">
			</div>
			<br>
			
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="authorbtn">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>