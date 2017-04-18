<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<title>SYM Main Page</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	
  	<link rel="stylesheet" type="text/css" href="css/header.css">
  	<link rel="stylesheet" type="text/css" href="css/main.css">
  	<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>
<body>

<s:include value="header_logged.jsp"/>

<!-- Tweet Start!-->
<div class="container" style="padding-top: 100px;">
	<div class="row">
		<div class="contents-wrapper-left">
            	<div class="string-wrapper">
					<h3><span class="contrast">最</span>新の<span class="contrast">ツ</span>イート!!!</h3>
				</div>
			<s:iterator value="topFiveTweets">
				<s:iterator>
					<div class="tweet-wrapper">
						<div class="image-wrapper">
							<img src="<s:property value="photoUrl"/>">
						</div>
						<div class="account-wrapper">
							<img src="<s:property value="iconUrl"/>" alt="title" >
							<p><s:property value="userName"/></p>
						</div>
						<div class="contents-wrapper">
							<p><s:property value="tweet"/></p>
						</div>
					</div>	
				</s:iterator>
			</s:iterator>
		</div>
		<div class="contents-wrapper-left">
			<div class="tweet-submit-wrapper">
				<div class="string-wrapper">
					<h3><span class="contrast">S</span>hare <span class="contrast">Y</span>our <span class="contrast">M</span>oment!!!</h3>
				</div>
				<form action="InsertTweetAction" method="post" enctype="multipart/form-data">
		            <div class="form-group">
		              <textarea name="tweet" id="comment" cols="50" rows="10" class="form-control" placeholder="Share your memory">
		              </textarea>
		              <input type="file" name="userImage" class="form-control form-file">
		              <input type="submit" value="送信" class="form-control form-submit"/>
		              <p class="counter"><span id="label">200</span> characters left</p>
		            </div>
	            </form>
            </div>
            <div class="user-tweet-wrapper">
            	<div class="string-wrapper">
					<h3><span class="contrast">Y</span>our <span class="contrast">T</span>ime <span class="contrast">L</span>ine!!!</h3>
				</div>
            	<s:iterator value="loginUserTweets">
            		<s:iterator>
						<div class="tweet-wrapper2">
							<div class="image-wrapper">
								<img src="<s:property value="photoUrl"/>">
							</div>
							<div class="account-wrapper">
								<img src="<s:property value="iconUrl"/>" alt="title" >
								<p><s:property value="userName"/></p>
							</div>
							<div class="contents-wrapper">
								<p><s:property value="tweet"/></p>
							</div>
						</div>	
					</s:iterator>
				</s:iterator>
            </div>
		</div>
	</div>
</div>

<s:include value="footer.jsp" />
</body>
</html>