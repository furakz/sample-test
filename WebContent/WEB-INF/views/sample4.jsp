<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書籍マスタ-アップロード</title>
</head>
<body>
<p>
書籍情報<br />
<form action="/book-master/book/upload" method="post" enctype="multipart/form-data">
<table border="1">
  <tr>
    <th>ファイル</th>
    <td><input type="file" name="dataFile"></td>
  </tr>
</table>
  <input type="submit" value="登録">
</form>
</p>
<p>
<a href="/book-master/book/select">一覧へ</a>
</p>
</body>
</html>