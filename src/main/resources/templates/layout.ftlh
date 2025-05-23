<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <title>Document</title>
        <link rel="stylesheet" href="/static/css/style.css" />
    </head>
    <body>

    <style>
        .navbar {
            position: sticky;
            top: 0;
            z-index: 1000;
        }
    </style>

    <#assign known = SPRING_SECURITY_CONTEXT?? >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >
    </#if>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">NavBar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li class="nav-item">
                            <a class="nav-link" href="/posts/all">All posts</a>
                        </li>
                    <#if authenticated??>
                        <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/posts">Create Post</a>
                        </li>
                    </#if>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/users/search">Search</a>
                    </li>


                </ul>



                <#if authenticated??>
                    <a href="/users/profile" class="btn btn-outline-info m-2">Profile</a>
                    <a href="/logout" class="btn btn-outline-info m-2"
                       id="logout">Log out</a>
                <#else>
                    <a href="/auth/login"
                       class="btn btn-outline-info m-2">Login</a>
                    <a href="/auth/registration"
                       class="btn btn-outline-info m-2">Register</a>
                </#if>
            </div>
        </div>
    </nav>


    <div class="container">

        <#assign known = SPRING_SECURITY_CONTEXT?? >
        <#if known>
            <#assign
            user = SPRING_SECURITY_CONTEXT.authentication.principal
            name = user.getUsername()
            >
        </#if>
    </div>

    </body>
    </html>

</#macro>