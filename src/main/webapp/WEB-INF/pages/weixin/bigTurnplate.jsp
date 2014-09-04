<%--
  Created by IntelliJ IDEA.
  User: Gavin
  Date: 9/4/2014
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<!doctype html>
<html lang="en" style="height: 100%">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>大转盘抽奖</title>
    <m:require src="jquery,jQueryRotate,bootstrap"></m:require>
    <style>
        body{
            background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #101920), color-stop(1, #E4E7F1));
            background-image: -moz-linear-gradient(top, #101920, #E4E7F1); /* Firefox */
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#101920', endColorstr='#E4E7F1', GradientType='0'); /* IE*/
        }
    </style>
</head>
<body>
<script>
    $(function(){
        function rotate(n){
            $("#id").rotate({
                angle: 0,
                animateTo: (7-n)*60+360*9,
                easing:$.easing.easeInOutElastic,
                duration:8000
            });
        }

        $("#1").click(function(){
            rotate(1);
        });
        $("#2").click(function(){
            rotate(2);
        });
        $("#3").click(function(){
            rotate(3);
        });
        $("#4").click(function(){
            rotate(4);
        });
        $("#5").click(function(){
            rotate(5);
        });
        $("#6").click(function(){
            rotate(6);
        });
        $("#img").click(function(){
            rotate(parseInt(Math.random()*6));
        });
    });
</script>


<div style="position: relative;box-sizing: border-box;margin-top: 100px;">
    <img id="id" style="width: 70%;margin-left: 15%;" src="../css/images/1.png"/>
    <img id="img" src="../css/images/2.png" style="position: absolute;left: 14%;top: 1%;width: 71%;"/>
</div>

<div style="margin: 38px auto;text-align: center;">
    <button id="1" class="btn btn-primary  btn-large ">我想选1</button>

    <button id="2" class="btn btn-warning  btn-large ">我想选2</button>

    <button id="3" class="btn btn-danger  btn-large ">我想选3</button>

    <button id="4" class="btn btn-info  btn-large ">我想选4</button>

    <button id="5" class="btn btn-inverse  btn-large ">我想选5</button>

    <button id="6" class="btn btn-success  btn-large ">我想选6</button>
</div>
</body>
</html>
