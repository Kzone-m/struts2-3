<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
	
	
<!DOCTYPE html>
<html lang="ja">

<sj:head/>

<head>
	<title>SYM</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	<style>
  		.form-wrapper{
  			width: 30%;
  			margin: 100px auto;
  		}
  		.form-image {
  			width:25px;
  			height:25px;
  			float:left;
  		}
  	</style>
</head>
<body>
	

	<div class="form-wrapper">
		<form action="AjaxAction" method="post" id="form1">
			<input type="hidden" value="<s:property value="count"/>" name="count">
			<sj:submit
				formIds="form1" targets="result" value="Enter" type="image" src="img/good_icon.png" cssClass="form-image"/>
		</form>
		<div id="result">
			<p><s:property value="count"/></p>
		</div>
	</div>

</body>
</html>