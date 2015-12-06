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
<div id="conditionArea">
<form action="/book-master/book/download" method="post">
<table border="1">
  <tr>
    <th>名称</th><td><input type="text" name="name" value="${conditionForm.name[0]}"></td>
  </tr>
  <tr>
    <th>名称リスト</th>
    <td><input type="text" name="nameList" value="${conditionForm.nameList[0]}"><input type="text" name="nameList" value="${conditionForm.nameList[1]}">
    </td>
  </tr>
  <tr>
    <th>定価</th><td><input type="text" name="price" value="${conditionForm.price[0]}"></td>
  </tr>
  <tr>
    <th>ページ数</th><td><input type="text" name="nbOfPage" value="${conditionForm.nbOfPage[0]}"></td>
  </tr>
</table>
<input type="submit" value="ダウンロード">
</form>
</div>
<p>
<a href="/book-master/book/select">一覧へ</a>
</p>
</body>
</html>