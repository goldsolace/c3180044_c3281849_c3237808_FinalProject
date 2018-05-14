/*
 * File Name: validation.js
 * Author: Brice Purton
 * StudentID: 3180044
 * Last Modified: 14/04/2018
 */

// Validation prototype constructor
function Validation(input) {
	// Array to store validity tests
	this.validityChecks = [];
	// Validates form when it is submitted
	this.checkValidity = function(input) {
		// Iterate all validity checks for the specific input
		for (let i = 0; i < this.validityChecks.length; i++) {
			// True if invalid input
			if (this.validityChecks[i].isInvalid(input)) {
				// Display error message of validityCheck
				input.classList.add('invalid');
				this.validityChecks[i].errorMessage.style.display = 'inline';
				return false;
			}
		}
		// Passed all checks
		return true;
	};
	// Removes error messages
	this.removeErrorMessage = function(input) {
		for (let i = 0; i< errors.length; i++) {
			if (errors[i] != null) {
				errors[i].style.display = 'none';
			}
		}
		// Check if input has an error displayed
		if (input.classList.contains('invalid')) {
			input.classList.remove('invalid');
			for (let i = 0; i < this.validityChecks.length; i++) {
				this.validityChecks[i].errorMessage.style.display = 'none';
			}
		}
	};
}

// Declare global variables
let usernameChecks;
let usernameInput;
let inputs;
let errors
let activeUserError;
let autoSaveError;
let noGameError;
let invalidCase;
let invalidUsername;

// Initialise global variables on page load
window.onload = function () {
	/*
	 * Validity check arrays for each input
	 * isInvalid(): tests input for invalidity
	 * errorMessage: the span element that will show appropriate error message
	 */
	usernameValidityChecks = [
		{
			// Check if username entered
			isInvalid: function(input) {
				return input.value.length < 3 || input.value.length > 12;
			},
			errorMessage: document.getElementById('eUsername1')
		},
		{
			// Check only letters & numbers used
			isInvalid: function(input) {
				return input.value.match(/[^a-zA-Z0-9\-\/]/) ? true : false;
			},
			errorMessage: document.getElementById('eUsername2')
		}
	];

	// Get inputs to be validated
	usernameInput = document.getElementById('username');
	
	inputs = [];
	inputs.push(usernameInput);
	
	if (usernameInput != null) {
		usernameInput.Validation = new Validation();
		usernameInput.Validation.validityChecks = usernameValidityChecks;

		// Remove error messages if input changes after invalid information submitted
		for (let i = 0; i< inputs.length; i++) {
			if (inputs[i] != null) {
				inputs[i].addEventListener('keyup', function(e) {
					if ((e.keyCode || e.which) != 13) {
						this.Validation.removeErrorMessage(this);
					}
				})
			}
		}
	}

	activeUserError =  document.getElementById('eActiveUser');
	autoSaveError =  document.getElementById('eAutoSave');
	noGameError =  document.getElementById('eNoGame');
	invalidCase =  document.getElementById('eInvalidCase');
	invalidUsername =  document.getElementById('eInvalidUsername');

	errors = []
	errors.push(activeUserError);
	errors.push(autoSaveError);
	errors.push(noGameError);
	errors.push(invalidCase);
	errors.push(invalidUsername);
	// Display error message if element isn't null
	for (let i = 0; i< errors.length; i++) {
		if (errors[i] != null) {
			errors[i].style.display = 'inline';
		}
	}

	var state =  document.getElementById('state');
	var stateSelect =  document.getElementById('stateSelect');
	if (state != null) {
		SetState(state, stateSelect);
		stateSelect.addEventListener('change', function(e) {
			SetState(state, stateSelect);
		});
	}
	


}

/*
 * Method called when form is submitted to validate information
 */
function handleSubmit() {
	for (let i = 0; i< errors.length; i++) {
		if (errors[i] != null) {
			errors[i].style.display = 'none';
		}
	}
	let isValid = true;
	// Interate through inputs
	for (let i = 0; i < inputs.length; i++) {
		if (inputs[i] != null) {
			let inputValidity = inputs[i].Validation.checkValidity(inputs[i]);
			// Only true if all inputs valid
			isValid = isValid && inputValidity;
		}
	}
	// True to submit form or False to preventing submission of form
	return isValid;
}

function SetState(state, stateSelect) {
	RemoveStateClass(state);
	switch (stateSelect.value) {
		case "1":
			state.classList.add('bg-danger');
			break;
		case "2":
			state.classList.add('bg-progress');
			break;
		case "3":
			state.classList.add('bg-primary');
			break;
		case "4":
			state.classList.add('bg-success');
			break;
	}
}

function RemoveStateClass(state) {
	state.classList.remove('bg-danger');
	state.classList.remove('bg-progress');
	state.classList.remove('bg-primary');
	state.classList.remove('bg-success');
}