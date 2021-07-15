<#import "_base.ftlh" as b>
<@b.page 'Welcome!'>
    <div class="row">
        <div class="col"></div>
        <div class="titles col-10 border rounded p-4" style="background-color: lightgray">

        </div>
        <div class="col"></div>
    </div>
    <script>
        $(document).ready(function () {

            let titlePath = "${pathPlaceholder("TC#titlePage", ["%ph%"])}";

            let apiPath = "${pathPlaceholder("TRC#getTitlesPageable", ["%ph1%", "%ph2%"])}";

            console.log("start");

            let pageVar = 0;
            let countVar = 8;
            let $ajaxFunc = function (){
                $.getJSON( apiPath.replace("%ph1%", countVar).replace("%ph2%", pageVar), function( data ) {
                    $.each( data, function( key, val ) {
                        $(
                            "<p><a href='"+titlePath.replace("%ph%", val.id)+"' class='text-muted text-decoration-none mr-2'><img src='/images/"+val.imagePath+"' alt='' height='70' width='70' class='rounded'><span class='mx-4'>"+val.name+"</span></a></p>"
                        ).appendTo(".titles");
                    });
                    pageVar++;
                });
            };

            $ajaxFunc();

            $(window).scroll(function () {
                if ($(window).scrollTop() + $(window).height() === $(document).height()){
                    $ajaxFunc();
                }
            });

            console.log("end");

        });
    </script>
</@b.page>