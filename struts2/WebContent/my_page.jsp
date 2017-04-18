<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<title>My Page</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	
  	<link rel="stylesheet" type="text/css" href="css/header.css">
  	<link rel="stylesheet" type="text/css" href="css/my_page.css">
    <link rel="stylesheet" type="text/css" href="css/footer.css">
</head>
<body>

<s:include value="header_my_page.jsp"/>

<div class="body-wrapper">
	<div class="contents-wrapper">
		<s:iterator value="dtoLst">
			<s:iterator>
				<h3><s:property value="userName"/>のお部屋</h3>
				<p>現在のアイコン</p>
				<img src="<s:property value="iconUrl"/>"></img>
				<form action="ChangeIconAction" method="post" enctype="multipart/form-data">
				    <input type="hidden" value="<s:property value="iconFlg"/>" name="iconFlg">
				    <input type="file" name="userImage" class="form-file">
					<input type="submit" value="写真を選んでアイコンを変更する" class="form-submit">
				</form>
			</s:iterator>
		</s:iterator>
	</div>
	<s:include value="footer.jsp"/>
</div>

</body>
</html>