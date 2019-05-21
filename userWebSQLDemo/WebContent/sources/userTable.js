window.onload = function () {
    var all_a2 = document.getElementsByClassName("a2");
    var add_btn = document.getElementById("add-btn");
    var modify_btns = document.getElementsByClassName("a1");
    var logout_btn = document.getElementById("logout-btn");
    var update_form = document.getElementById("update-form");
    for (var i = 0; i < all_a2.length; i++) {
        all_a2[i].onclick = function () {
            var tr = this.parentNode.parentNode;
            var name = tr.children[1].innerHTML;
            if (confirm("确定要删除" + name + "的信息吗?")) {
                // tr.parentNode.removeChild(tr);
                return true;
            } else {
                return false;
            }
        };
    }
    logout_btn.onclick = function () {
        if (confirm("您确定要退出吗?")) {
            return true;
        }
        return false;
    }
    add_btn.onclick = function () {
        document.getElementById('popDiv1').style.display = 'block';
        document.getElementById('fade').style.display = 'block';
    };
    for (var i = 0; i < modify_btns.length; i++) {
        modify_btns[i].onclick = function () {
            document.getElementById('popDiv2').style.display = "block";
            document.getElementById('fade').style.display = 'block';
            var all_textbox = document.getElementsByClassName("textbox2");
            var tr = this.parentNode.parentNode;
            for (var i = 0; i < all_textbox.length; i++) {
                all_textbox[i].children[0].value = tr.children[i].innerHTML;
            }
        }
    }
    update_form.onsubmit = function () {
        var all_textbox2 = document.getElementsByClassName("textbox2");
        var id = all_textbox2[0].children[0].value;
        var name = all_textbox2[1].children[0].value;
        var pwd = all_textbox2[2].children[0].value;
        var email = all_textbox2[3].children[0].value;
        var phonenum = all_textbox2[4].children[0].value;
        var xhr = new XMLHttpRequest();
        xhr.open("post", "UpdateUserController", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = xhr.responseText;
                if (data == "true") {
                    alert("更新成功!");
                } else {
                    alert("更新失败!");
                }
                window.location.href = "userManager.jsp";
            }
        }
        xhr.send("id=" + id + "&name=" + name + "&pwd=" + pwd + "&email=" + email + "&phonenum=" + phonenum);
    }
};
function closeDiv() {
    document.getElementById('popDiv1').style.display = 'none';
    document.getElementById('popDiv2').style.display = 'none';
    document.getElementById('fade').style.display = 'none';
}
function checkInfo() {
    var all_textbox = document.getElementsByClassName("textbox1");
    for (var i = 0; i < all_textbox.length; i++) {
        if (all_textbox[i].children[0].value == "") {
            alert("必须提交完整信息!");
            return false;
        }
    }
    return true;
}