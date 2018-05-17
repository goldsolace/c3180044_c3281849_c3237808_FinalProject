<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<ul class="list-group my-2">
	<li class="list-group-item text-dark py-3">
		<div class="d-flex justify-content-between">
			<h5>Ticket 1</h5>
			<h5 class="d-flex justify-content-between">
				<span id="stateBadge" class="mx-1 badge bg-danger text-light" >
					<span class="mx-1 fas fa-tasks"></span>New
				</span>
				<span id="stateBadge" class="mx-1 badge badge-secondary" >
					<span class="mx-1 fas fa-tag"></span>Software
				</span>
			</h5>
		</div>
		<h3 class="mb-1 text-left">How do I change my University Password</h3>
		<div class="d-flex justify-content-between">
			<p class="mr-2">Reported: <span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
			<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
		</div>
	</li>
	<li class="list-group-item text-dark py-3">
		<div class="input-group mb-3 ">
			<div class="input-group-prepend">
				<label id="state" class="input-group-text bg-danger text-light" for="stateSelect">State</label>
			</div>
			<select class="custom-select " id="stateSelect">
				<option value="1" selected>New</option>
				<option value="2">In Progess</option>
				<option value="3">Completed</option>
				<option value="4">Resolved</option>
			</select>
		</div>
	</li>
	<li class="list-group-item">
		<h5 class="mb-1">Question 1</h5>
		<p>Answer1</p>
		<h5 class="mb-1">Question 2</h5>
		<p>Answer2</p>
		<h5 class="mb-1">Question 3</h5>
		<p>Answer3</p>
		<h5 class="mb-1">Question 4</h5>
		<p>Answer4</p>
	</li>
	<li class="list-group-item">
		<h5 class="mb-1">Description</h5>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	</li>
	<li class="list-group-item">
		<h5 class="mb-1">Resolution Details</h5>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		<form class="form-inline my-2 my-lg-0">
			<button class="btn btn-success my-2 my-sm-0 m-2" type="submit">Accept</button>
			<button class="btn btn-danger my-2 my-sm-0 m-2" type="submit">Reject</button>
		</form>
	</li>
</ul>

<ul class="list-group my-2 mb-5">
	<h3 class="text-center m-3">Comments</h3>

	<li class="list-group-item">
		<div class="d-flex mb-1 justify-content-between">
			<h6>Brice Purton</h6>
			<small>9:37am 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
			<div class="list-group-item ml-5">
				<div class="d-flex mb-1 justify-content-between">
					<h6>Jono Williams</h6>
					<small>9:42am 14/05/2018</small>
				</div>
				<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
				<div class="list-group-item ml-5">
					<div class="d-flex mb-1 justify-content-between">
						<h6>Jono Williams</h6>
						<small>9:42am 14/05/2018</small>
					</div>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
				</div>
				<div class="list-group-item ml-5">
					<div class="d-flex mb-1 justify-content-between">
						<h6>Jono Williams</h6>
						<small>9:42am 14/05/2018</small>
					</div>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
				</div>
			</div>
		</div>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		</div>
	</li>

	<li class="list-group-item flex-column align-items-start">
		<div class="d-flex mb-1 justify-content-between">
			<h6>Jono Williams</h6>
			<small>9:42am 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	</li>

	<li class="list-group-item flex-column align-items-start">
		<div class="d-flex mb-1 justify-content-between">
			<h6>Wajdi Younes</h6>
			<small>12:01pm 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		</div>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
			<div class="list-group-item ml-5">
				<div class="d-flex mb-1 justify-content-between">
					<h6>Jono Williams</h6>
					<small>9:42am 14/05/2018</small>
				</div>
				<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
			</div>
		</div>
	</li>

	<li class="list-group-item">
		<form class="my-2 my-lg-0">
			<div class="form-group">
				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Comment text..."></textarea>
			</div>

			<button class="btn btn-success my-2 my-sm-0 m-2 float-right" type="submit">Post</button>
		</form>
	</li>
</ul>


<c:import url="/WEB-INF/view/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>