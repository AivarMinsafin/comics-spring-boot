<#import "_base.ftlh" as b/>
<#import "spring.ftl" as spring/>
<@b.page "Profile edit">
    <@spring.bind "userEditForm"/>
    <#if passwordsErrorMessage??>
        <p class="error">${passwordsErrorMessage}</p>
    </#if>
<#--<form action="/profile-edit" method="post">-->
<#--    <label>New Email</label>-->
<#--    <@spring.formInput "userEditForm.newEmail"/>-->
<#--    <@spring.showErrors "<br>" "error"/>-->
<#--    <label>New username</label>-->
<#--    <@spring.formInput "userEditForm.newUsername"/>-->
<#--    <@spring.showErrors "<br>" "error"/>-->
<#--    <label>Old password to confirm changes</label>-->
<#--    <@spring.formPasswordInput "userEditForm.passwordForConfirmAction"/>-->
<#--    <@spring.showErrors "<br>" "error"/>-->
<#--    <input type="submit" value="<@spring.message 'form.submit'/>">-->
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
<#--</form>-->
    <form action="${mvcUri("PC#profileEdit")}" method="post">
        <div class="form-group">
            <label>Email address</label>
            <@spring.formInput "userEditForm.newEmail" 'class="form-control"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label>New username</label>
            <@spring.formInput "userEditForm.newUsername" 'class="form-control"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label>Old password to confirm changes</label>
            <@spring.formPasswordInput "userEditForm.passwordForConfirmAction" 'class="form-control"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </div>
    </form>
</@b.page>
