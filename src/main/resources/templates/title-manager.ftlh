<#import "_base.ftlh" as b/>
<@b.page "Title manager">
    <h4>Titles:</h4>
    <#list titles as title>
        <p class="p-4">
            <a href="/title/${title.id}" class="text-muted text-decoration-none mr-2">
                <@b.image '${title.imagePath}' '70' '70' "rounded"/>
                <span class="mx-4">${title.name}</span>
            </a>
            <@b.defaultButton "Add chapter" '${mvcUri("CMC#chapterAdd", [title.id])}'/>
            <@b.defaultButton "Edit" '${mvcUri("TMC#titleEdit", [title.id])}'/>
            <@b.defaultButton "Chapters" '${mvcUri("CMC#chapterManager", [title.id])}'/>
            <@b.defaultButton "Delete" '${mvcUri("TMC#deleteTitle", [title.id])}'/>
        </p>
    </#list>
    <@b.defaultButton name="Create new Title" href='${mvcUri("TMC#createTitle")}'/>
</@b.page>