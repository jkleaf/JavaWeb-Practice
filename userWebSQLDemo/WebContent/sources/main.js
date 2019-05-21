window.onload = function () {
    var trans2Regit_btn = document.getElementById('trans2Regit');
    var trans2Login_btn = document.getElementById('trans2Login');
    var sign_submit = document.getElementById('sign-submit');
    var login_submit = document.getElementById('login-submit');
    trans2Regit_btn.onclick = function () {
        document.getElementById('pop1').style.display = "none";
        document.getElementById('pop2').style.display = "block";
    }
    trans2Login_btn.onclick = function () {
        document.getElementById('pop2').style.display = "none";
        document.getElementById('pop1').style.display = "block";
    }

    sign_submit.onsubmit = function () {
        var id = document.getElementById('id').value;
        var name = document.getElementById('name').value;
        var pwd = document.getElementById('pwd').value;
        var email = document.getElementById('email').value;
        var phonenum = document.getElementById('phonenum').value;
        var xhr = new XMLHttpRequest();
        xhr.open("post", "RegisterController", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = xhr.responseText;
                if (data == "true") {
                    alert("注册成功!");
                } else {
                    alert("注册失败,该用户已存在!");
                    // document.getElementById('pop1').style.display = "none";
                    // document.getElementById('pop2').style.display = "block";
                }
                window.location.href = "mainPage.jsp";
            }
        }
        xhr.send("id=" + id + "&name=" + name + "&pwd=" + pwd + "&email=" + email + "&phonenum=" + phonenum);
    }

    login_submit.onsubmit = function () {
        var name = document.getElementById('loginName').value;
        var pwd = document.getElementById('loginPwd').value;
        var xhr = new XMLHttpRequest();
        xhr.open("post", "LoginController", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = xhr.responseText;
                if (data == "false") {
                    alert("登录失败,用户名或密码错误!");
                    window.location.href = "mainPage.jsp";
                }
                else if (data == "true") {
                    window.location.href = "userManager.jsp";
                }
            }
        }
        xhr.send("name=" + name + "&pwd=" + pwd);
    }
}

// function checkRegister() {
//         var id = document.getElementById('id').value;
//         var name = document.getElementById('name').value;
//         var pwd = document.getElementById('pwd').value;
//         var email = document.getElementById('email').value;
//         var phonenum = document.getElementById('phonenum').value;
//         var xhr = new XMLHttpRequest();
//         var flag=false;
//         xhr.open("post", "RegisterController", true);
//         xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//         xhr.onreadystatechange = function () {
//             if (xhr.readyState == 4 && xhr.status == 200) {
//                 var data = xhr.responseText;
//                 if (data == "true") {
//                     alert("注册成功!");
//                 } else {
//                     alert("注册失败,该用户已存在!");
//                     // document.getElementById('pop1').style.display = "none";
//                     // document.getElementById('pop2').style.display = "block";
//                 }
//                 // window.location.href = "mainPage.jsp";
//                 flag=true;
//             }
//         }
//         xhr.send("id=" + id + "&name=" + name + "&pwd=" + pwd + "&email=" + email + "&phonenum=" + phonenum);
//         return flag;
// }

// function checkLogin() {
//     var name = document.getElementById('loginName').value;
//     var pwd = document.getElementById('loginPwd').value;
//     var xhr = new XMLHttpRequest();
//     var flag = false;
//     xhr.open("post", "LoginController", true);
//     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//     xhr.send("name=" + name + "&pwd=" + pwd);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState == 4 && xhr.status == 200) {
//             var data = xhr.responseText;
//             if (data == "false") {
//                 alert("登录失败,用户名或密码错误!");
//                 //window.location.href = "mainPage.jsp";
//                 flag = false;
//             }
//             else if (data == "true") {
//                 // window.location.href = "userManager.jsp";
//                 flag = true;
//             }
//         }
//     }
//     return flag;
// }

// $('#sign-btn').click(function () {
//     $.ajax({
//         type: "POST",
//         url: RegisterController,
//         data: [
//             id = $("#id").val(),
//             name = $('#name').val(),
//             pwd = $('pwd').val(),
//             email = $('email').val(),
//             phonenum = $('phonenum').val()
//         ],
//         success: function (result, _status) {
//             if (result == "true") {
//                 alert("注册成功!");
//             } else {
//                 alert("注册失败,该用户已存在!");
//             }
//         },
        // error: function (xhr, errorMsg, e) {
        //     alert("系统异常!");
        // }
//     })
// });