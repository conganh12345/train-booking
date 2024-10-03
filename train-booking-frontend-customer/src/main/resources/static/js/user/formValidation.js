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
	        errorMessage.push('Mật khẩu phải có ít nhất 8 ký tự');
	    }
	    if(!uppercaseRegex.test(password)){
	        errorMessage.push('ít nhất một chữ cái viết hoa');
	    }
	    if(!lowercaseRegex.test(password)){
	        errorMessage.push('ít nhất một chữ cái viết thường');
	    }
	    if(!numberRegex.test(password)){
	        errorMessage.push('ít nhất một chữ số');
	    }
	    if(!specialCharRegex.test(password)){
	        errorMessage.push('ít nhất một ký tự đặc biệt');
	    }
	    
	    passwordError.textContent = errorMessage.join(', ');
	    
	    return errorMessage.length === 0;
	}else{
	    passwordError.textContent = 'Mật khẩu không được để trống';
	    return false;
	}
}

function validatePhoneNumber(){
    const phoneNumber = document.getElementById("phone-number").value;
    const phoneNumberError = document.getElementById("phone-number-error");
    const phoneRegex = /^0\d{9}$/;
    if(phoneNumber.length > 0){
        if(!phoneRegex.test(phoneNumber)){
            phoneNumberError.textContent = 'Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0';
            return false;
        }
        phoneNumberError.textContent = '';
        return true;
    }
    else{
        phoneNumberError.textContent = 'Số điện thoại không được để trống';
        return false;
    }
}






















