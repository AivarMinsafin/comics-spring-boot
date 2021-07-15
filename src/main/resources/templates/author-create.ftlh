<#import "_base.ftlh" as b/>
<#import "/spring.ftl" as spring/>
<@b.page title="Become an author">
    <@spring.bind "authorCreationForm"/>
<#--    <form action="/become-author" method="post">-->
<#--        <label>Firstname</label>-->
<#--        <@spring.formInput "authorCreationForm.firstname"/>-->
<#--        <@spring.showErrors "<br>" "error"/>-->
<#--        <label>Lastname</label>-->
<#--        <@spring.formInput "authorCreationForm.lastname"/>-->
<#--        <@spring.showErrors "<br>" "error"/>-->
<#--        <label>Phone number</label>-->
<#--        <@spring.formInput "authorCreationForm.phoneNumber"/>-->
<#--        <@spring.showErrors "<br>" "error"/>-->
<#--        <input type="submit" value="<@spring.message 'form.submit'/>">-->
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
<#--    </form>-->
    <form action="${mvcUri("AC#becomeAuthorPost")}" method="post">
        <div class="form-group">
            <label>Firstname</label>
            <@spring.formInput "authorCreationForm.firstname" 'class="form-control'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label>Lastname</label>
            <@spring.formInput "authorCreationForm.lastname" 'class="form-control'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <label>Phone number</label>
            <@spring.formInput "authorCreationForm.phoneNumber" 'class="form-control'/>
            <@spring.showErrors "<br>" "error"/>
        </div>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</@b.page>