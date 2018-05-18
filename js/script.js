/*
 * File Name: script.js
 * Author: Brice Purton
 * Last Modified: 18/05/2018
 */

// Time it takes for a session to become invalid
const sessionMaxTime = 15 * 60 * 1000;

const indexPage = "/";
const loginPage = "/Login";

window.onload = function () {
	var state =  document.getElementById('state');
	var stateSelect =  document.getElementById('stateSelect');
	if (state != null) {
		SetState(state, stateSelect);
		stateSelect.addEventListener('change', function(e) {
			SetState(state, stateSelect);
		});
	}

	var states =  document.getElementsByClassName('state');
	if (states != null) {
		for (var i = 0; i < states.length; i++) {
			SetState(states[i]);
		} 
	}
	
	var urlStr = window.location.href;
	var urlPattern = urlStr.substring(urlStr.lastIndexOf("/"));
	if (urlPattern != indexPage && urlPattern != loginPage) {
		// Display session timeout warning message 1 minute from timeout
		window.setTimeout(AlertSessionTimeout, sessionMaxTime - 60 * 1000);
		// Auto redirect user 5 seconds after timeout
		window.setTimeout(SessionTimeout, sessionMaxTime+5000);
	}
}

// Method to change color appearence of state element
function SetState(state) {
	switch (state.innerText) {
		case "New":
			// New = red
			state.classList.add('badge-danger');
			break;
		case "In Progress":
			// In Progress = orange
			state.classList.add('badge-progress');
			break;
		case "Completed":
			// Completed = blue
			state.classList.add('badge-primary');
			break;
		case "Resolved":
			// Resolved = green
			state.classList.add('badge-success');
			break;
	}
}



// Method to remove color appearence of state element
function RemoveStateClass(state) {
	state.classList.remove('bg-danger');
	state.classList.remove('bg-progress');
	state.classList.remove('bg-primary');
	state.classList.remove('bg-success');
}

// Method to warn user of session timeout
function AlertSessionTimeout() {
	CreateMessage("info", "Your session will expire in 1 minute.");
}

// Method to redirect user if session has timed out
function SessionTimeout() {
	// Form used to redirect user and display error message
	var errorForm = document.createElement("form");
	errorForm.setAttribute('method',"post");
	errorForm.setAttribute('action',"Timeout");
	document.body.appendChild(errorForm);

	var errorInput = document.createElement("input");
	errorInput.setAttribute('type',"text");
	errorInput.setAttribute('name',"errorMessage");
	errorInput.setAttribute('value', "Your session has timed out.");
	errorForm.appendChild(errorInput);

	var errorSubmit = document.createElement("input");
	errorSubmit.setAttribute('type',"submit");
	errorSubmit.setAttribute('value',"Submit");
	errorForm.appendChild(errorSubmit);

	errorForm.submit();
}

// Form used to redirect user and display error message
function CreateMessage(type, message) {
	switch (type) {
		case "error":
			type = "alert-error";
			break;
		case "warning":
			type = "alert-warning";
			break;
		case "info":
			type = "alert-info";
			break;
		case "success":
			type = "alert-success";
			break;
	}

	var containerDiv = document.createElement("div");
	containerDiv.setAttribute('class',"container pt-2");
	var navBar = document.getElementById("navBar");
	navBar.parentNode.insertBefore(containerDiv, navBar.nextSibling);

	var alertDiv = document.createElement("div");
	alertDiv.setAttribute('class',"alert " + type + " alert-dismissible fade show text-center");
	alertDiv.setAttribute('role',"alert");
	alertDiv.innerHTML = message;
	containerDiv.appendChild(alertDiv);

	var alertButton = document.createElement("button");
	alertButton.setAttribute('type',"button");
	alertButton.setAttribute('class',"close");
	alertButton.setAttribute('data-dismiss',"alert");
	alertButton.setAttribute('aria-label',"Close");
	alertButton.innerHTML = "<span aria-hidden='true'>&times;</span>";
	alertDiv.appendChild(alertButton);
}