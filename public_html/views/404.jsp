<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404</title>
    <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700">-->
    <style>
        html, body {
            height: 100%;
            padding: 0;
            margin: 0;
        }
        body {
            background: #1f3649;
            background: -moz-radial-gradient(center, ellipse cover, #1f3649 0%, #17253d 44%, #040d11 100%);
            background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%, #1f3649), color-stop(44%, #17253d), color-stop(100%, #040d11));
            background: -webkit-radial-gradient(center, ellipse cover, #1f3649 0%, #17253d 44%, #040d11 100%);
            background: -o-radial-gradient(center, ellipse cover, #1f3649 0%, #17253d 44%, #040d11 100%);
            background: -ms-radial-gradient(center, ellipse cover, #1f3649 0%, #17253d 44%, #040d11 100%);
            background: radial-gradient(ellipse at center, #1f3649 0%, #17253d 44%, #040d11 100%);
            color: #f0f0f0;
            font-family: "Open Sans",Arial,Helvetica,sans-serif;
            font-size: 19px;
            text-align: center;
        }
        .container {
            height: 400px;
            left: 50%;
            margin: -200px 0 0 -250px;
            position: absolute;
            top: 50%;
            width: 500px;
        }
        h1 {
            background: transparent url("img/anb.png") no-repeat scroll 0 center;
            font-size: 100px;
            font-weight: 300;
            line-height: 90px;
            margin: 0 auto;
            text-align: right;
            width: 300px;
        }
        .link {
            background-color: #fbb351;
            border-radius: 3px;
            color: white;
            display: block;
            font-size: 14px;
            margin: 50px auto 0;
            padding: 10px;
            text-decoration: none;
            width: 160px;
        }
        .link:hover, link:focus {
            background-color: #FFA32D;
        }
        p {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>404</h1>
        <p>No se puede encontrar la página.</p>
        <a class="link" href="${empty sessionScope.USER ? 'index' : 'dashboard'}.do">Ir a la página de inicio</a>
    </div>
</body>
</html>