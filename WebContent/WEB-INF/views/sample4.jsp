<%@ page language="java" contentType="text/html; UTF-8"
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
</p>
<div id="conditionArea">
<form action="/book-master/book/upload" method="post" enctype="multipart/form-data">
<table border="1">
  <tr>
    <th>ファイル</th><td><input type="file" name="filename"></td>
  </tr>
</table>
<input type="submit" value="アップロード">
</form>
</div>
<p>
<a href="/book-master/book/select">一覧へ</a>
</p>
<h2>ファイルの内容</h2>
<p>
<pre>
${content}
</pre>
</p>
</body>
<<<<<<< HEAD
</html>
=======
</html>
>>>>>>> branch 'master' of git@github.com:furakz/sample-test.git
