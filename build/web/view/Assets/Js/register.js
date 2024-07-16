/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById('registrationForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Ngăn chặn form gửi đi

    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const messageDiv = document.getElementById('message');

    const hasUpperCase = /[A-Z]/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const hasNumber = /[0-9]/.test(password);


    if(!hasUpperCase || !hasSpecialChar || !hasNumber)
    {
        messageDiv.innerHTML = "Password must contain at least uppercase letter, special character, and number.";
        return;
    }
    
    if (password !== confirmPassword) {
        messageDiv.innerHTML = "Passwords do not match. Please try again.";
        return;
    }

    this.submit();
});