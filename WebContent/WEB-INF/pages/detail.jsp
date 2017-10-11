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
		$(".del").click(function() {
			var isbn = $(this).attr("id");

			$.ajax({
				type : "POST",
				url : "del",
				data : {
					"isbn" : isbn.toString()
				},
				datatype : "html",
				success : function(data) {
					location.reload();
					alert('删除成功');
				},
				error : function(data) {
					alert('删除失败');
				}
			})
		});
	});
</script>

<script>
	$(document).ready(function() {
		$(".show").click(function() {
			var isbn = $(this).attr("id");
			var dot = '.';
			dot = dot + isbn;
			$(dot).toggle();
		});
	});
</script>

<script>
	$(document).ready(function() {
		$('#myModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			var isbn = button.data('isbn') // Extract info from data-* attributes
			var title = button.data('title')
			var authorid = button.data('authorid')
			var publisher = button.data('publisher')
			var publishdate = button.data('publishdate')
			var price = button.data('price')
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this);
			modal.find('.modal-title').text('修改图书' + isbn)
			modal.find('.modal-body #isbn').val(isbn)
			modal.find('.modal-body #title').val(title)
			modal.find('.modal-body #authorid').val(authorid)
			modal.find('.modal-body #publisher').val(publisher)
			modal.find('.modal-body #publishdate').val(publishdate)
			modal.find('.modal-body #price').val(price)
		})
	});
</script>

<script>
	$(document).ready(function() {
		$("#changebtn").click(function() {
			var isbn0 = $("#myModalLabel").text();
			var isbn = $("#isbn").val();
			var title = $("#title").val();
			var authorid = $("#authorid").val();
			var publisher = $("#publisher").val();
			var publishdate = $("#publishdate").val();
			var price = $("#price").val();

			$.ajax({
				type : "POST",
				url : "change",
				data : {
					"changedata" : isbn0.toString()+","
									+isbn.toString()+","
									+title.toString()+","
									+authorid.toString()+","
									+publisher.toString()+","
									+publishdate.toString()+","
									+price.toString()
				},
				datatype : "html",
				success : function(data) {
					location.reload();
					alert('修改成功');
				},
				error : function(data) {
					alert('修改失败');
				}
			})
		});
	});
</script>
</head>
<body>
	<div>
		<table class="table table-striped" width="100%">
		<thead>
      <tr>
         <th>名称</th>
         <th>操作</th>
      </tr>
   </thead>
			<c:forEach items="${booklist.books}" var="book" varStatus="status">

				<tr>
					<td width="80%"><p class="show" id="${book.ISBN}">${book.title}</p></td>
					<td width="20%"><input type="button" value="删除" class="del"
						id="${book.ISBN}" /></td>
				</tr>
				<tr class="${book.ISBN}" style="display: none;">
					<td width="80%">
						<p>详细信息</p>
						<p>ISBN:${book.ISBN}</p>
						<p>title:${book.title}</p>
						<p>AuthorId:${book.id}</p>
						<p>Publisher:${book.publisher}</p>
						<p>PublishDate:${book.publishdate}</p>
						<p>Price:${book.price}</p>
						<p>AuthorName:${book.name}</p>
						<p>AuthorAge:${book.age}</p>
						<p>AuthorCountry:${book.country}</p>
						</td>
					<td width="20%"><button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#myModal"
							data-isbn="${book.ISBN}" data-title="${book.title}"
							data-authorid="${book.id}" data-publisher="${book.publisher}"
							data-publishdate="${book.publishdate}" data-price="${book.price}">修改</button></td>
				</tr>

			</c:forEach>
		</table>
		<br />
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body" style="padding: 100px 100px 10px;">
					<div class="input-group">
						<span class="input-group-addon">ISBN</span> <input type="number"
							class="form-control" id="isbn" required="required">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">Title</span> <input type="text"
							class="form-control" id="title" required="required">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">AuthorID</span> <input type="number"
							class="form-control" id="authorid" required="required">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">Publisher</span> <input
							type="text" class="form-control" id="publisher" required="required">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">PublishDate</span> <input
							type="date" class="form-control" id="publishdate" required="required">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">Price</span> <input type="number"
							class="form-control" id="price" required="required">
					</div>
					<br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="changebtn">提交更改</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>