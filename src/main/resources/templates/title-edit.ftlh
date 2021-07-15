<#import "_base.ftlh" as b/>
<#import "/spring.ftl" as spring/>
<@b.page "Edit title">
    <@spring.bind "titleEditForm"/>
    <form action="${mvcUri("TMC#titleEdit", [titleEditForm.id])}" method="post"
          enctype="multipart/form-data">
        <label>Title name:</label>
        <@spring.formInput "titleEditForm.name"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label>Description:</label>
        <@spring.formTextarea "titleEditForm.description"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label>Upload title image:</label>
        <@spring.formInput "titleEditForm.image" "" "file"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <input type="submit" value="<@spring.message 'form.submit'/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@b.page>