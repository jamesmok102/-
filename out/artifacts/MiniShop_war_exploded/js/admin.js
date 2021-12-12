function validateAid() {
    return validateEmpty("aid");
}

function validatePassword() {
    return validateEmpty("password");
}

function validateLogin() {
    return validateAid() && validatePassword();
}