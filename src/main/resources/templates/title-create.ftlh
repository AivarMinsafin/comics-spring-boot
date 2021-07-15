<#import "_base.ftlh" as b/>
<#import "/spring.ftl" as spring/>
<@b.page "Create title">
    <@spring.bind "titleCreationForm"/>
    <form action="${mvcUri("TMC#createTitlePost")}" method="post" enctype="multipart/form-data">
        <label>Title name:</label>
        <@spring.formInput "titleCreationForm.name"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label>Description:</label>
        <@spring.formTextarea "titleCreationForm.description"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label>Upload title image:</label>
        <@spring.formInput "titleCreationForm.image" "" "file"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <input type="submit" value="<@spring.message 'form.submit'/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@b.page>