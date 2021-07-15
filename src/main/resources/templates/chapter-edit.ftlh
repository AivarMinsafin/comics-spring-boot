<#import "_base.ftlh" as b/>
<#import "/spring.ftl" as spring>
<@b.page "Edit chapter">
    <@spring.bind "chapterEditForm"/>
    <form action="${mvcUri("CMC#chapterEdit", [chapterEditForm.id])}" method="post" enctype="multipart/form-data">
        <label>Chapter name:</label>
        <@spring.formInput "chapterEditForm.name"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label>Upload pages:</label>
        <@spring.formInput "chapterEditForm.pages" "multiple" "file"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <input type="submit" value="<@spring.message 'form.submit'/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@b.page>