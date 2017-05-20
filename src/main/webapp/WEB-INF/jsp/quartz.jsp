<%-- 
    Document   : quartz
    Created on : 10 Nov, 2016, 9:37:41 AM
    Author     : pravinkumbhar
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--<%@ taglib uri="http://www.joda.org/joda/time/tags"prefix="joda"  %>--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script src="jquery.js" type="text/javascript"></script>
        <script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

        <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
        <link href="${pageContext.request.contextPath}//resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <link href=" http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">

        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

        <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/excite-bike/jquery-ui.css" />
        <style type="text/css">

            /* @group Blink */
            .blink {
                -webkit-animation: blink .75s linear infinite;
                -moz-animation: blink .75s linear infinite;
                -ms-animation: blink .75s linear infinite;
                -o-animation: blink .75s linear infinite;
                animation: blink .75s linear infinite;
            }
            @-webkit-keyframes blink {
                0% { opacity: 1; }
                50% { opacity: 1; }
                50.01% { opacity: 0; }
                100% { opacity: 0; }
            }
            @-moz-keyframes blink {
                0% { opacity: 1; }
                50% { opacity: 1; }
                50.01% { opacity: 0; }
                100% { opacity: 0; }
            }
            @-ms-keyframes blink {
                0% { opacity: 1; }
                50% { opacity: 1; }
                50.01% { opacity: 0; }
                100% { opacity: 0; }
            }
            @-o-keyframes blink {
                0% { opacity: 1; }
                50% { opacity: 1; }
                50.01% { opacity: 0; }
                100% { opacity: 0; }
            }
            @keyframes blink {
                0% { opacity: 1; }
                50% { opacity: 1; }
                50.01% { opacity: 0; }
                100% { opacity: 0; }
            }
            /* @end */

        </style>
        
        
        <script>
            function myFunction() {
                alert('vanila javascript onclick');



            }

            $(document).ready(function () {

                CreateHtmlTable();
                setInterval(CreateHtmlTable, 10000);

            }
            );
        </script>

        <script>
            $(document).ready(function () {
                $("#but").click(function () {
                    alert("jquery clicked.");
                });
            });




        </script>


        <script>
            $(document).ready(function ()
            {
                $("#button").click(function ()
                {
                    var button = '<input type="button" id="dynamicButton" onclick="myFunction()" value="dynamic button">';
                    $('body').append(button);
                });


                $(document).on('click', '#dynamicButton', function ()
                {
                    alert("Dynamic button action On Event Jquery");
                });

            });




        </script>
        <script>


            function setHolidays(year) {
                $.ajax({
                    url: 'getList',
                    //data: {year: year},
                    dataType: 'json',
                    async: false, //while this is not generally good practice, it works here
                    success: function (data) {
                        //usHolidays.holidays = data;
                        alert(JSON.stringify(data));

                      
                    }
                });
            }










            $(function () {
                $("#myDatePicker").datepicker({

                    // The hidden field to receive the date
                    altField: "#dateHidden",
                    // The format you want
                    altFormat: "yy-mm-dd",
                    // The format the user actually sees
                    //  dateFormat: "dd/mm/yy",
                    numberOfMonths: [3, 4],
                    // numberOfMonths: 12,
                    stepMonths: 12,
                    defaultDate: new Date(new Date().getFullYear(), 0, 1),
                    //showCurrentAtPos: new Date().getMonth(),
                    onSelect: function (date) {

                        alert(date);
                        var aDate = new Date(date);

                        alert(">>>" + aDate.getFullYear());
                        var year = aDate.getFullYear();
                        setHolidays(year);

                        $(this).change();

                        var retVal = confirm("Do you want to add holiday ?");
                        if (retVal == true) {

                            alert("Holiday Added");
                            return true;
                        } else {
                            alert("Holiday NOT Added ");
                            return false;
                        }
                    },
                    beforeShowDay: function (date) {

                        var day = date.getDay();

                        week = Math.floor(date.getDate() / 7);

                        if (day == 0)
                        {
                            return [true, 'holiday', 'Sunday'];

                        } else if (day == 6 && ((week == 1 || week == 3))) {
                            return [true, 'holiday', 'Saturday'];
                        } else {
                            return [true, ''];
                        }



                    }
                });
            });


        </script>



        <script type="text/javascript">
            $(document).ready(function ()
            {
                $.ajax({
                    url: 'quartzList',
                    type: "GET",
                    dataType: "json",
                    success: function (response)
                    {
                        alert(response);
                        var trHTML = '';
                        $.each(response, function (key, value) {
                            trHTML +=
                                    '<tr><td>' + value.triggerName +
                                    '</td><td>' + value.prevFireTime +
                                    '</td><td>' + value.nextFireTime +
                                    '</td><td>' + value.startTime +
                                    '</td><td>' + value.endTime +
                                    '</td><td>' + value.triggerState +
                                    '</td><td>' + value.cronExpression +
                                    '</td></tr>';
                        });

                        $('#myData').append(trHTML);
                    }
                });


            });



            function CreateTableWithoutHeader() {

                $.ajax({
                    url: 'quartzList',
                    type: "GET",
                    dataType: "json",
                    success: function (response)
                    {

                        var trHTML = '';
                        $.each(response, function (key, value) {
                            trHTML +=
                                    '<tr><td>' + value.triggerName +
                                    '</td><td>' + value.prevFireTime +
                                    '</td><td>' + value.nextFireTime +
                                    '</td><td>' + value.startTime +
                                    '</td><td>' + value.endTime +
                                    '</td><td>' + value.triggerState +
                                    '</td><td>' + value.cronExpression +
                                    '</td></tr>';
                        });

                        $('#headerTable').append(trHTML);
                    }
                });

            }
        </script>



        <script type="text/javascript">


            $(document).ready(function () {

                //assign button click event to call function to create html table dynamically
                $("#btnCreateHtmlTable").click(function () {
                    CreateHtmlTable();
                });
            }
            );

            function CreateHtmlTable() {

                //Clear result div
                $("#ResultArea").html("");

                //Crate table html tag
                var table = $("<table id=DynamicTable class='table table-striped table-bordered'></table>").appendTo("#ResultArea");

                //Create table header row
                var rowHeader = $("<tr class='info'></tr>").appendTo(table);
                $("<th></th>").text("#").appendTo(rowHeader);
                $("<th></th>").text("Trigger Name").appendTo(rowHeader);
                $("<th></th>").text("Prev Fire Time").appendTo(rowHeader);
                $("<th></th>").text("Next Fire Time").appendTo(rowHeader)
                $("<th></th>").text("End Time").appendTo(rowHeader);
                $("<th></th>").text("Trigger State").appendTo(rowHeader);
                $("<th></th>").text("CronExpression").appendTo(rowHeader);

                //Get JSON data by calling action method in controller
                $.get('quartzList', function (data) {
                    $.each(data, function (i, value) {

                        //Create new row for each record


                        if (i % 2 == 0) {
                            var row = $("<tr class='active'></tr>").appendTo(table);
                        } else {
                            var row = $("<tr class='success'></tr>").appendTo(table);
                        }
                        $("<td></td>").text((i + 1)).appendTo(row);
                        $("<td></td>").text(value.triggerName).appendTo(row);
                        $("<td></td>").text(value.prevFireTime).appendTo(row);
                        $("<td></td>").text(value.nextFireTime).appendTo(row);
                        $("<td></td>").text(value.endTime).appendTo(row);
                        $("<td></td>").text(value.triggerState).appendTo(row);
                        $("<td class='expressinTextblock'></td>").text(value.cronExpression).appendTo(row);

                    });
                });
            }

        </script>

    <script type="text/javascript">
            $(document).ready(function () {
                $(".expressinTextblock").mouseover(function () {
                    var conExpression = $(this).text();
                    //(this).attr('title', conExpression);

                    var elemment = $(this);

                    $.ajax({
                        url: "getconExpressionText",
                        data: {conExpression: conExpression},
                        type: "GET",
                        success: function (data) {

                            elemment.attr('title', data);


                        }
                    });

                });
            });
        </script>


        <style type="text/css">
            .ui-datepicker td.holiday a, .ui-datepicker td.holiday a:hover {
                background: none #FFEBAF;
                border: 1px solid #BF5A0C;
                color:red;

            }
            .ui-datepicker {
                width: 900px !important;
            }


            .event a {
                background-color: #42B373 !important;
                background-image :none !important;
                color: #ffffff !important;
            }

            /*                                                .ui-datepicker-week-end, .ui-datepicker-week-end a.ui-state-default {color:red;}*/
        </style>
    </head>
    <body>
        <h1 class="tab blink">Sumukh !- With Header </h1>
        <div class="table-responsive" id="top" style="height:200px; width:1100px; margin:0 auto;" >
            <div id="ResultArea" style="width:1100px; margin:0 auto;">
            </div>  
        </div>







        <h1 >Sumukh !</h1>

        <table class="table table-striped table-bordered">
            <thead>
                <tr class="info">
                    <th>#</th>
                    <th>TriggerName</th>
                    <th>PrevFireTime</th>
                    <th>NextFireTime</th>

                    <th>Start Time</th>
                    <th>EndTime</th>
                    <th>Trigger State</th>
                    <th>Cron Expression</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach items="${triggers}" var="trigger" varStatus="counter">
                    <tr class="${counter.index % 2 == 0 ? 'active' : 'success'}">
                        <td>${counter.count}</td>
                        <td>${trigger.triggerName}</td>
                        <td>${trigger.prevFireTime}</td>
                        <td>${trigger.nextFireTime}</td>
                        <td>${trigger.startTime}</td>
                        <td>${trigger.endTime}</td>
                        <td>${trigger.triggerState}</td>
                        <td class="expressinTextblock ">${trigger.cronExpression}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="button" id="search"  value="search" class="btn btn-primary"/>
        <input type="button" id="but" onclick="myFunction()" value="button" class="btn btn-info"/>

        <input type="button" id="button"  value="Static Button"/>

        Enter Date: <div id="myDatePicker" style="width:800px; margin:0 auto;"></div>


        <table id="myData" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>TriggerName</th>
                    <th>PrevFireTime</th>
                    <th>NextFireTime</th>

                    <th>Start Time</th>
                    <th>EndTime</th>
                    <th>Trigger State</th>
                    <th>Cron Expression</th>

                </tr>
            </thead>
        </table>


        <input type="button" id="btnCreateHtmlTable" value="Create HTML table" />



        <div  id="holidayPicker" />     
    </body>
</html>
