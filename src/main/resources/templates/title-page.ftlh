<#import "_base.ftlh" as b/>
<#import "spring.ftl" as spring/>

<@b.page "Title">

    <div class="description row">
        <div class="col"></div>
        <div class="col-10 px-4 pt-4 border border-bottom-0 rounded-top">
            <div class="row">
                <div class="col-4">
                    <@b.image '${title.imagePath}' '480' '300' "rounded"/>
                </div>
                <div class="col">
                    <h3 class="p-2">${title.name}</h3>
                    <p class="p-2">Description: ${title.description}</p>
                    <p class="p-2">Readers count: ${readersCount}</p>
                    <a href="${mvcUri("AC#authorPageId", [author.id])}"><p class="p-2">Author: ${author.firstname} ${author.lastname}</p></a>
                    <#if isReader = false>
                        <@b.defaultButton "+ Add" '${mvcUri("UC#addTitleToList", [title.id])}'/>
                    </#if>
                </div>
            </div>
        </div>
        <div class="col"></div>
    </div>
    <div class="chapters row">
        <div class="col"></div>
        <div class="col-10 border rounded p-4" style="background-color: lightgray">
            <#if chapters?size != 0>
                <#list chapters as chapter>
                    <div class="row align-items-center">
                        <div class="col">
                            <a href="${mvcUri("CC#chapter", [chapter])}">${chapter.name}</a>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="row align-items-center justify-content-center">
                    <div class="col text-muted">
                        <p style="text-align: center">chapters list is empty</p>
                    </div>
                </div>
            </#if>
        </div>
        <div class="col"></div>
    </div>

</@b.page>
