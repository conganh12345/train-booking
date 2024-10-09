function validatePassword() {
	const password = document.getElementById("password").value;
	const passwordError = document.getElementById("password-error");

	const lengthRegex = /.{8,}/;
	const uppercaseRegex = /[A-Z]/;
	const lowercaseRegex = /[a-z]/;
	const numberRegex = /[0-9]/;
	const specialCharRegex = /[!@#$\^\&*\)\(+=._-]/;

	let errorMessage = [];
	if(password.length > 0){
		if (!lengthRegex.test(password)){
			errorMessage.push('Password must be at least 8 characters long');
		}
		if(!uppercaseRegex.test(password)){
			errorMessage.push('at least one uppercase letter');
		}
		if(!lowercaseRegex.test(password)){
			errorMessage.push('at least one lowercase letter');
		}
		if(!numberRegex.test(password)){
			errorMessage.push('at least one number');
		}
		if(!specialCharRegex.test(password)){
			errorMessage.push('at least on special letter');
		}
		
		passwordError.textContent = errorMessage.join(', ');
		
		return errorMessage.length === 0;
	}else{
		passwordError.textContent = 'Password cannot be empty';
		return false;
	}
	
}

function validatePhoneNumber(){
	const phoneNumber = document.getElementById("phone-number").value;
	const phoneNumberError = document.getElementById("phone-number-error");
	const phoneRegex = /^0\d{9}$/;
	if(phoneNumber.length > 0){
		if(!phoneRegex.test(phoneNumber)){
			phoneNumberError.textContent = 'Phone number must be 10 digits and start with 0';
			return false;
		}
		phoneNumberError.textContent = '';
		return true;
	}
	else{
		phoneNumberError.textContent = 'Phone number cannot be empty';
		return false;
	}
	
}





















