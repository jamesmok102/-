function validateGid() {
    return validateEmpty("gid");
}

function validateTitle() {
    return validateEmpty("title");
}

function goUpdate(gid) {
    if (validateEmpty("title-"+gid)) {
        title = document.getElementById("title-"+gid).value;
        document.getElementById("gid").value = gid;
        document.getElementById("title").value = title;
        document.getElementById("itemForm").submit();
    }


}

function validateInsert() {
    return validateTitle();
}
