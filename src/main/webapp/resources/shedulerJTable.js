/* 
 * Javascript to manage the JTable
 */

$(document).ready(function () {
    //setup the jtable that will display the results
    $('#jobSchedulerList').jtable({
        title: 'Job Scheduler List',
        selecting: true, //Enable selecting 
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default: 10)
        sorting: true, //Enable sorting
        actions: {
            listAction: 'jobSchedulerList',
            createAction: 'saveJobScheduler',
            updateAction: 'updateJobScheduler',
            deleteAction: 'deleteJobScheduler'
        },
        fields: {
            jobId: {
                title: 'Job Id',
                key: true,
                list: true,
                create: false,
                edit: false,
                 width: '5%'
            },
            triggerName: {
                title: 'Trigger Name',
                width: '15%',
               
                
            },
            jobName: {
                title: 'Job Name',
                width: '15%'
                
            },
            cronExpression: {
                title: 'Cron Expression',
                width: '15%'
            }


        },
        //Register to selectionChanged event to hanlde events                                     
        recordAdded: function (event, data) {
            //after record insertion, reload the records
            $('#jobSchedulerList').jtable('load');
        },
        recordUpdated: function (event, data) {
            //after record updation, reload the records
            $('#jobSchedulerList').jtable('load');
        }
    });
    $('#jobSchedulerList').jtable('load');

});
