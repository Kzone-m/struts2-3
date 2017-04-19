<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	
	
<!DOCTYPE html>
<html lang="ja">
<head>
	<title>SYM</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	
  	<link rel="stylesheet" type="text/css" href="css/header.css">
  	<link rel="stylesheet" type="text/css" href="css/top.css">
  	<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>
<body>

<!-- Header Start!!! -->
<s:include value="header.jsp" />


<!-- Modal for Login Start!!! -->
  <div class="modal fade" id="myModal1">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body">
          <button type="button" class="close" data-dismiss="modal"><span>&times;</span><br></button>
            <h2>Login</h2>
            <s:form action="LoginAction" method="post">
              <div class="form-wrapper">
          		<s:textfield name ="userName" cssClass="form-content" placeholder="Username" theme="css_xhtml"/>
                <s:password name="password" cssClass="form-content" placeholder="Password" theme="css_xhtml" />
                <s:submit type ="button" cssClass="form-content-submit" value="Submit" theme="css_xhtml" />
              </div>
            </s:form>
        </div>
      </div>
    </div>
  </div>


 <!-- Modal for Register Start!!! -->
    <div class="modal fade" id="myModal2">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-body">
            <button type="button" class="close" data-dismiss="modal"><span>&times;</span><br></button>
              <h2>Register</h2>
              <form action="">
                <div class="form-wrapper">
                  <s:textfield name ="name" cssClass="form-content" placeholder="Enter your user name" theme="css_xhtml"/>
                  <s:textfield cssClass="form-content" placeholder="Enter your email-address" theme="css_xhtml"/>
                  <s:textfield cssClass="form-content" placeholder="Confirm your email-address" theme="css_xhtml"/>
                  <s:password name="password" cssClass="form-content" placeholder="Enter your email-address" theme="css_xhtml"/>
                  <s:password cssClass="form-content" placeholder="Enter your email-address" theme="css_xhtml"/>
                  <s:submit type ="button" cssClass="form-content-submit" value="Submit" theme="css_xhtml" />
                </div>
              </form>
          </div>
        </div>
      </div>
    </div>



<!-- Landing Page Start!!!-->
  <div class="main-wrapper">
      <div class="container">
        <h1>Your Memory Lives on with us.</h1>
        <div class="main-msg">
          <p>Do you remember what you ate last night?</p>
          <p>Do you remember who you saw and what you talked? </p>
          <p>It is easy to forget anything which you did </p>
          <p>But it is so hard to remember what you have done</p>
          <p>However, there is sometimes something important for you among you forgot</p>
          <p>So, let's share your memory now!!!</p>
        </div>
        <button type="button" class="btn btn-danger register" data-toggle="modal" data-target="#myModal2">
          登録
        </button>
      </div>
  </div>

<!-- Contents Page Start!!!-->
<div class="container">
	<div class="tweet-header">
		<h3>最新のツイート</h3>
	</div>
	<s:iterator value="topFourTweets">
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
<!-- Contents Page End!!!-->


<s:include value="footer.jsp" />

<script src="js/top.js"></script>
</body>
</html>