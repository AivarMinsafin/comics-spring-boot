<#import "_base.ftlh" as b/>
<@b.page "Chapter manager">
    <h4>Chapters:</h4>
    <#list chapters as chapter>
        <p>
            ${chapter.name}
            <@b.defaultButton "Edit" '${mvcUri("CMC#chapterEdit", [chapter.id])}'/>
            <@b.defaultButton "Delete" '${mvcUri("CMC#chapterDelete", [chapter.id])}'/>
        </p>
    </#list>
</@b.page>