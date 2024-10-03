function checkEmailExist() {
	const email = document.getElementById('email').value;
	const emailError = document.getElementById('email-error');

	// Validate the email format
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!emailRegex.test(email)) {
		emailError.textContent = 'Please enter a valid email address.';
		return Promise.resolve(false); // Return false if email is invalid
	}

	return new Promise((resolve) => {
		$.ajax({
			type: 'POST',
			url: '/user/check-email-exist',
			data: { email: email },
			success: function(result) {
				if (result) {
					emailError.textContent = 'This email is already in use';
					resolve(false);
				} else {
					emailError.textContent = '';
					resolve(true);
				}
			},
			error: function() {
				emailError.textContent = 'Error checking email. Please try again later.';
				resolve(false);
			}
		});
	});
}


$('#signUpForm').on('submit', async function(event) {
	event.preventDefault();
	if (await validateForm()) { 
		submitForm(); 
	}
});


async function validateForm() {
	const isPasswordValid = validatePassword();
	const isPhoneNumberValid = validatePhoneNumber();
	const isEmailValid = await checkEmailExist(); // Await the email validation result

	return isPasswordValid && isPhoneNumberValid && isEmailValid;
}


function submitForm() {
	// Get form values
	const name = document.getElementById("username").value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const phoneNumber = document.getElementById("phone-number").value;

	// Prepare form data
	const formData = {
		name: name,
		email: email,
		password: password,
		phoneNumber: phoneNumber
	};

	// Make an AJAX POST request to the backend to register the user
	$.ajax({
		type: "POST",
		url: "/user/register", // Your backend register endpoint
		data: formData,
		success: function(response) {
			alert("Registration successful");
			window.location.href = "/user/login"; // Redirect to login page after success
		},
		error: function(error) {
			alert("Error occurred: " + error.responseText);
		}
	});
}