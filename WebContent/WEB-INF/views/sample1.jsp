<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world Page</title>
</head>
<body>
<!--
<h1>Hello World!</h1>
<p>
Book title is ${book.title}
</p>
 -->
<p>
<a href="/book-master/book/edit_new">新規登録</a>
</p>
<div id="conditionArea">
<form action="/book-master/book/search" method="post">
<table border="1">
  <tr>
    <th>名称</th><td><input type="text" name="name"></td>
  </tr>
  <tr>
    <th>名称リスト</th><td><input type="text" name="nameList"><input type="text" name="nameList"></td>
  </tr>
  <tr>
    <th>定価</th><td><input type="text" name="price"></td>
  </tr>
</table>
<input type="submit" value="検索">
</form>
</div>
<p>
<table border="1">
<c:forEach var="book" items="${bookList}" varStatus="status">
  <tr>
    <td><a href="/book-master/book/select/${book.id}">${book.id}</a>
    </td><td>${book.title}</td>
    <td>${book.price}</td>
    <td>${book.nbOfPage}</td>
  </tr>
</c:forEach>
</table>
</p>
</body>
</html>