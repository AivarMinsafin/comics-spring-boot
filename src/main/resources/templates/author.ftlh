<#import "_base.ftlh" as b/>
<@b.page title="Author page">
    <h4>Personal information</h4>
    <p>Firstname: ${author.firstname}</p>
    <p>Lastname: ${author.lastname}</p>
    <p>Readers count: ${readersCount}</p>
    <h4>Titles list:</h4>
    <#list titles as title>
        <p>
            <a href="${mvcUri("TC#titlePage", [title.id])}" class="text-muted text-decoration-none mr-2">
                <@b.image '${title.imagePath}' '70' '70' "rounded"/>
                <span class="mx-4">${title.name}</span>
            </a>
        </p>
    </#list>
</@b.page>