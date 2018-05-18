/*
 * File Name: script.js
 * Author: Brice Purton
 * Last Modified: 18/05/2018
 */

// Time it takes for a session to become invalid
const sessionMaxTime = 15 * 60 * 1000;

const indexPage = "http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/";
const loginPage = "http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/Login";

window.onload = function () {
	var state =  document.getElementById('state');
	var stateSelect =  document.getElementById('stateSelect');
	if (state != null) {
		SetState(state, stateSelect);
		stateSelect.addEventListener('change', function(e) {
			SetState(state, stateSelect);
		});
	}

	// Display session timeout warning message 1 minute from timeout
	window.setTimeout(AlertSessionTimeout, sessionMaxTime - 60 * 1000);
	// Auto redirect user 1 second after timeout
	window.setTimeout(SessionTimeout, sessionMaxTime+1000);
}

// Method to change color appearence of state element
function SetState(state, stateSelect) {
	RemoveStateClass(state);
	switch (stateSelect.value) {
		case "1":
			// New = red
			state.classList.add('bg-danger');
			break;
		case "2":
			// In Progress = orange
			state.classList.add('bg-progress');
			break;
		case "3":
			// Complete = blue
			state.classList.add('bg-primary');
			break;
		case "4":
			// Resolved = green
			state.classList.add('bg-success');
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
	if (window.location.href != indexPage && window.location.href != loginPage) {
		CreateMessage("info", "Your session will expire in 1 minute.");
	}
}

// Method to redirect user if session has timed out
function SessionTimeout() {
	if (window.location.href != indexPage && window.location.href != loginPage) {
		CreateTimeoutForm();
	}
}

// Form used to redirect user and display error message
function CreateTimeoutForm() {
	var errorForm = document.createElement("form");
	errorForm.setAttribute('method',"post");
	errorForm.setAttribute('action',"http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/Timeout");
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