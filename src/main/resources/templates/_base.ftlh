<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "spring.ftl" as spring/>
<#macro page title>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script
                src="https://code.jquery.com/jquery-3.6.0.min.js"
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <title>${title}</title>
    </head>
    <body>
    <div class="container-fluid">
        <div class="container">

            <nav class="navbar navbar-dark navbar-expand fixed-top bg-dark">
                <div class="container-fluid">
                    <div class="container row">
                        <div class="col-1">
                            <a class="navbar-brand" href="#">comics lib</a>
                        </div>
                        <div class="col">
                            <ul class="navbar-nav me-auto mb-2 mb-md-0 ml-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="<@spring.url "/"/>">home</a>
                                </li>
                                <@sec.authorize access="isAuthenticated()">
                                    <li class="nav-item">
                                        <a class="nav-link" href="${mvcUri("PC#getProfile")}">profile</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<@spring.url "/logout"/>">logout</a>
                                    </li>
                                </@sec.authorize>
                                <@sec.authorize access="isAnonymous()">
                                    <li class="nav-item">
                                        <a class="nav-link" href="${mvcUri("SIC#getSingInPage")}">sign in</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${mvcUri("SUC#getSignUpPage")}">sign up</a>
                                    </li>
                                </@sec.authorize>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>

            <!-- header section -->

            <div class="container" style="margin-top: 66px">
                <#nested/>
            </div>

            <!-- footer section -->


        </div>
    </div>
    <#--JavaScript-->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
            integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>
<#macro defaultButton name href type="dark">
    <a href="${href}" class="btn btn-${type}" role="button">
        ${name}
    </a>
</#macro>
<#macro js path>
    <script src="${path}"></script>
</#macro>
<#macro image href height="" width="" classes="">
    <img class="${classes}" src="<@spring.url "/images/${href}"/>" alt="" height="${height}" width="${width}">
</#macro>