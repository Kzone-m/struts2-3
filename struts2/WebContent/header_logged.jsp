<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Header Start!!! -->
  <header>
    <div class="container">
      <div class="row">
          <div class="header-left">
            <a href="main.jsp">SYM</a>
          </div>
          <div class="header-right">
            <ul>
              <li>
  				<form action="GoMyPageAction">
					<input type="submit" value="マイページ"/>  					
  				</form>
              </li>
              <li>
  				<form action="LogoutAction">
					<input type="submit" value="ログアウト"/>  					
  				</form>
              </li>
            </ul>
          </div>
      </div>
    </div>
  </header>