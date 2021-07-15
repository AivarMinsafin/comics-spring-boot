<#import "_base.ftlh" as b/>
<#import "spring.ftl" as spring/>
<@b.page title="Sign in">
<#--    <form action="/signIn" method="post">-->
<#--        <input type="email" name="email" placeholder="EMAIL">-->
<#--        <input type="password" name="password" placeholder="PASSWORD">-->
<#--        <label>-->
<#--            <input type="checkbox" name="remember-me">Remember me-->
<#--        </label>-->
<#--        <input type="submit" value="SING IN">-->
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
<#--    </form>-->
    <form action="<@spring.url "/signIn"/>" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <div class="form-check">
            <input name="remember-me" type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <a href="${prop("oauth.github.authorize")}/?client_id=${prop("oauth.github.client.id")}" class="btn btn-primary">Enter via GitHub</a>
    </form>
<#--    <a href="${prop("oauth.github.authorize")}/?client_id=${prop("oauth.github.client.id")}" class="btn btn-primary">Enter via GitHub</a>-->
</@b.page>