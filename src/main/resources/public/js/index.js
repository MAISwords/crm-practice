layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 表单submit提交
     * form.on('submit按钮的lay-filter属性值')，在index.ftl里面去看
     */
    form.on('submit(login)', function (data){

        console.log(data.field)//当前容器的全部表单字段,就是前端输入的参数
        //第一步 做表单元素的非空判断  lay-ui里面已经自带了，在这里可以不用写了

        //发送ajax请求，传递用户姓名与密码，执行用户登录操作
        $.ajax({
            type:"post",
            url:ctx + "/user/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            success:function (result){ //result是回调函数，用来接收后端返回的数据
                console.log(result);
                //判断是否登录成功，如果code等于200表示成功，否则表示失败
                if(result.code == 200){
                    //登陆成功，设置用户是一个登录状态
                    //方法一：利用session会话保存用户信息,如果会话存在，则用户是登录状态
                    //缺点：服务器关闭或浏览器关闭，会话就失效了

                    //方法二：利用cookie存储，保存用户信息
                    //只要cookie时间未失效，则用户是登录状态
                    layer.msg("登陆成功! ", function (){
                        //将用户信息设置到cookie中
                        $.cookie("userId",result.result.userId);
                        $.cookie("userName",result.result.userName);
                        $.cookie("trueName",result.result.trueName);

                        //登录成功后跳转到首页
                        window.location.href = ctx + "/main";





                    });

                }else{
                    //登陆失败
                    layer.msg(result.msg, {icon:5});
                }
            }

        });


        return false;//阻止表单跳转  不加的话表单地址会被提交走
    });

});