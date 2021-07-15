<#import "_base.ftlh" as b/>
<#import "/spring.ftl" as spring/>
<@b.page "Sing up">
    <h4><a href="?lang=ru">RU</a></h4>
    <h4><a href="?lang=en">EN</a></h4>
    <h1><@spring.message 'sign-up-page.registration.title'/></h1>
    <@spring.bind "userSignUpForm"/>
    <#if passwordsErrorMessage??>
        <p class="error">${passwordsErrorMessage}</p>
    </#if>
    <form action="${mvcUri("SUC#signUp")}" method="post">
        <div class="form-group">
            <label><@spring.message 'form.label.email'/></label>
            <@spring.formInput "userSignUpForm.email"/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label><@spring.message 'form.label.username'/></label>
            <@spring.formInput "userSignUpForm.username"/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label><@spring.message 'form.label.password'/></label>
            <@spring.formPasswordInput "userSignUpForm.password"/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label><@spring.message 'form.label.password.confirmation'/></label>
            <@spring.formPasswordInput "userSignUpForm.passwordVerification"/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </div>
        <input type="submit" value="<@spring.message 'form.submit'/>">
    </form>
</@b.page>