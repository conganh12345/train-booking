function checkEmailExist() {
	const email = document.getElementById('email').value;
	const emailError = document.getElementById('email-error');
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	
	if (!emailRegex.test(email)) {
		emailError.textContent = 'Vui lòng nhập địa chỉ email.';
		return Promise.resolve(false);
	}

	return new Promise((resolve) => {
		$.ajax({
			type: 'POST',
			url: '/user/check-email-exist',
			data: { email: email },
			success: function(result) {
				if (result) {
					emailError.textContent = 'Email đã tồn tại';
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
	const isEmailValid = await checkEmailExist(); 

	return isPasswordValid && isPhoneNumberValid && isEmailValid;
}


function submitForm() {
	const name = document.getElementById("username").value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const phoneNumber = document.getElementById("phone-number").value;

	const formData = {
		name: name,
		email: email,
		password: password,
		phoneNumber: phoneNumber
	};

	$.ajax({
		type: "POST",
		url: "/user/register",
		data: formData,
		success: function(response) {
			alert("Đăng ký thành công");
			window.location.href = "/user/login"; 
		},
		error: function(error) {
			alert("Error occurred: " + error.responseText);
		}
	});
}