<#import "_base.ftlh" as b/>
<#import "spring.ftl" as spring/>
<@b.page "Chapter">
    <div class="pages">
        <#list pages as page>
            <p class="m-0">
                <@b.image href="${page.path}" width="100%"/>
            </p>
        </#list>
    </div>
    <div class="row justify-content-between my-2">
        <div class="col d-flex justify-content-center">
            <#if chapter.previousChapterId??>
                <@b.defaultButton name="<- Previous chapter" href='${mvcUri("CC#chapter", [prevChapter])}'/>
            </#if>
        </div>
        <div class="col d-flex justify-content-center">
            <@b.defaultButton "Back to the title" '${mvcUri("TC#titlePage", [chapter.titleId])}'/>
        </div>
        <div class="col d-flex justify-content-center">
            <#if chapter.nextChapterId??>
                <@b.defaultButton name="Next chapter ->" href='${mvcUri("CC#chapter", [nextChapter])}'/>
            </#if>
        </div>
    </div>
</@b.page>