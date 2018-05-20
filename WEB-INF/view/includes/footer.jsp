<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${context}/js/bootstrap.min.js"></script>
		<script src="${context}/js/script.js"></script>
	</body>
</html>

<%-- Add removeMessages to the end of each page to make messages non persistent once displayed --%>
<c:import url="/WEB-INF/view/includes/removeMessages.jsp"/>