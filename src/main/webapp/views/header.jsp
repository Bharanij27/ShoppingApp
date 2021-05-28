<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
	<header class="header">
    <nav class="navbar navbar-expand-lg py-3">
        <div class="container">
        	<a href="/home;jsessionid=<%=session.getId()%>" class="navbar-brand text-uppercase font-weight-bold">
        		<div class="headind">
				  <h1>SHOPPING APP</h1>
				</div>
        	</a>   
        	<form action="/logout;jsessionid=<%=session.getId()%>" method="post">
              <button type="submit" class="btn btn-secondary">Logout</button>
        	</form>
        </div>
    </nav>
</header>