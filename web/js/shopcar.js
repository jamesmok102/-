var allPrice = 0.0;

window.onload = function() {
    document.getElementById("result").innerHTML = "<font>总金额:" + allPrice +"</font>";
}

function calCommodity(cid) {
    var price = parseFloat(document.getElementById("price-"+cid).innerHTML);
    var count = parseInt(document.getElementById(cid).value);
    allPrice += (price * count);
    document.getElementById("cal-"+cid).innerHTML = "<font>"+(price * count)+"</font>";
    if (document.getElementById("result") != undefined) {
        document.getElementById("result").innerHTML = "<font>" + allPrice +"</font>";
    }
}

function addBut(cid) {
    var price = parseFloat(document.getElementById("price-"+cid).innerHTML);
    var count = parseInt(document.getElementById(cid).value);
    allPrice += (price * count);
    count++;
    document.getElementById(cid).value = count;
    calCommodity(cid);
}

function subBut(cid) {

    var count = parseInt(document.getElementById(cid).value);
    if (count != 0){
        count--;var price = parseFloat(document.getElementById("price-"+cid).innerHTML);
        document.getElementById(cid).value = count;
        allPrice -= (price * count);
        calCommodity(cid);
    }

}

function calResult() {

}