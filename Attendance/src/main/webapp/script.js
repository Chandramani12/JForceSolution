

    document.getElementById("checkdatafil").addEventListener("input", function() {
        var password = this.value;
        var passwordError = document.getElementById("password-error");
        var username=document.getElementById("username-error");
        
        if (password.length < 8) {
            passwordError.style.display = "inline"; // Show error message
        } else if(username==null){
			passwordError
		}
        else {
            passwordError.style.display = "none"; // Hide error message
        }
    });
