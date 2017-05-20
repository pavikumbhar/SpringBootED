
<section id="main-content">
    <section class="wrapper site-min-height">
        <h3><i class="fa fa-angle-right"></i> Holiday</h3>
        <div class="row mt">
            <div class="col-lg-12">
                
                <!--          		<p>Place your content here.</p>-->
               
                  <div id="myDatePicker" > </div>
                
                
            </div>
        </div>

    </section><! --/wrapper -->
</section>



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

<script type="text/javascript">


            $(function () {

                holidays();

            });


            function holidays() {

                var holidaylist = new Array();

                function dataInvoke() {
                    alert('Inside ajax get call>>>'),
                            $.ajax({
                                type: 'GET',
                                url: '/FormApp/getdates',
                                // data:{'date':date},
                                contentType: 'application/json; charset=utf-8',
                                async: false, //while this is not generally good practice, it works here
                                success: function (data) {

                                    //var holidaylist = new Array();
                                    alert('holidays marked----->' + data);
                                    for (i = 0; i < data.length; i++)
                                    {
                                        holidaylist.push(new Date((data[i])));

                                    }
                                    alert('dates in array of holidays---->' + holidaylist);

                                }

                            });
                }


                $("#myDatePicker").datepicker({
                    // The hidden field to receive the date
                    altField: "#dateHidden",
                    // The format you want
                    //  altFormat: "yyyy-mm-dd",
                    // The format the user actually sees
                    dateFormat: "mm/dd/yy",
                    numberOfMonths: [3, 4],
                    // numberOfMonths: 12,
                    stepMonths: 12,
                    defaultDate: new Date(new Date().getFullYear(), 0, 1),
                    // showCurrentAtPos: new Date().getMonth
                    // 
                    beforeShow: dataInvoke(),
                    onSelect: function (date) {



                        alert('selected date--- ' + date);


                        var aDate = new Date(date);

                        $(this).change();
                        //var retVal = confirm("Do you want to add holiday ?");
                        // if (retVal === true)
                        //  {
                        var cnt = 0;

                        for (i = 0; i < holidaylist.length; i++)
                        {

                            if (holidaylist[i].getTime() === aDate.getTime())
                            {


                               // holidaylist.pop(aDate);
                                cnt++;
                            }

                        }

                        if (cnt === 0)
                        {
                            var retVal = confirm("Do you want to add holiday ?");
                            if (retVal === true)
                            {
                                setHolidays(date);
                                alert("Holiday Added " + date);
                            }
                            else
                            {
                                alert("Holiday NOT Added ");
                            }
                        }
                        else
                        {
                            alert("It's Holiday...");
                            var result = confirm("Do you want to remove holiday ?");
                            if (result === true)
                            {

                                for (i = 0; i < holidaylist.length; i++)
                                {

                                    if (holidaylist[i].getTime() === aDate.getTime())
                                    {


                                        holidaylist.pop(aDate);
                                       // cnt++;
                                    }

                                }
                                updateHolidays(date);
                                alert("Date Updated " + date);
                            }
                        }
                        return true;




//                        } else
//                        {
//                            alert("Holiday NOT Added ");
//                            
//                            return false;
//                        }

                    },
                    beforeShowDay: getHolidays

                });

                // $("#myDatepicker").datepicker("refresh");
                //holidays();


                function getHolidays(date) {


                    var day = date.getDay();
                    week = Math.floor(date.getDate() / 7);

                    for (i = 0; i < holidaylist.length; i++)
                    {

                        if (holidaylist[i].getTime() === date.getTime())
                        {

                            return [true, 'holiday', 'Holiday'];
                        }

                    }





                    if (day === 0)
                    {
                        //alert("inside conditon 2....");
                        return [true, 'holiday', 'Sunday'];

                    }
                    else if (day === 6 && ((week === 1 || week === 3)))
                    {
                        // alert("inside conditon 3....");
                        return [true, 'holiday', 'Saturday'];
                    }
                    else
                    {
                        // alert("inside conditon final....");
                        return [true, ''];
                    }



                }



                function setHolidays(date) {

                    alert('Inside ajax set call function >>>' + date);

                    $.ajax({
                        type: 'GET',
                        url: 'setdate',
                        data: {'date': date, 'flag': 100},
                        contentType: 'application/json; charset=utf-8',
                        async: false, //while this is not generally good practice, it works here                       
                        success: function (resp) {

                            dataInvoke();

                        }

                    });



                }

                function updateHolidays(date) {

                    alert('Inside ajax update call function >>>' + date);

                    $.ajax({
                        type: 'GET',
                        url: '/FormApp/updateDate',
                        data: {'date': date},
                        contentType: 'application/json; charset=utf-8',
                        async: false, //while this is not generally good practice, it works here                       
                        success: function (resp) {

                            dataInvoke();
                            // getHolidays(resp);

                        }

                    });



                }


            }



        </script>



