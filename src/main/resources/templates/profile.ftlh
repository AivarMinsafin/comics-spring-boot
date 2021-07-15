<#import "_base.ftlh" as b/>
<@b.page title="Profile">
    <div class="row top-div">
        <div class="col"></div>
        <div class="col-10 px-4 pt-4 border border-bottom-0 rounded-top">
            <h4 class="text-muted">username: ${user.username}</h4>
        </div>
        <div class="col"></div>
    </div>
    <div class="row mid-div my-4">
        <div class="col"></div>
        <div class="titles-list col-10 border rounded p-4" style="background-color: lightgray">
            <#if titles?size != 0>
                <#list titles as title>
                    <div class="row align-items-center">
                        <div class="col">
                            <p>
                                <a href="${mvcUri("TC#titlePage", [title.id])}" class="text-muted text-decoration-none mr-2">
                                    <@b.image '${title.imagePath}' '70' '70' "rounded"/>
                                    <span class="mx-4">${title.name}</span>
                                </a>
                            </p>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="row align-items-center justify-content-center">
                    <div class="col text-muted">
                        <p style="text-align: center">titles list is empty</p>
                    </div>
                </div>
            </#if>
        </div>
        <div class="col"></div>
    </div>
    <div class="row bot-div justify-content-center">
        <div class="col"></div>
        <div class="col">
            <#if (user.author)!false>
                <@b.defaultButton name="Title manager" href='${mvcUri("TMC#titleManager")}'/>
                <@b.defaultButton name="Author page" href='${mvcUri("AC#authorPage")}'/>
            <#else>
                <@b.defaultButton name="Become an author" href='${mvcUri("AC#becomeAuthor")}'/>
            </#if>
            <@b.defaultButton name="Edit profile" href='${mvcUri("PC#getProfileEditPage")}'/>
        </div>
        <div class="col"></div>
    </div>
</@b.page>