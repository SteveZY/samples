#!/bin/bash
#path_to_logs reportSuffix timeToGetOnReport
FILE_PATH=$1
FILE_NAME=application.log
CSV_RETAILER="$2.csv"
MINS=$3 #The minutes taken to generate one report
PATTERN_TO_SEARCH="FTP upload.*$CSV_RETAILER"

#echo file path is $PATTERN_TO_SEARCH 
DATE_LAST_LOG=`awk -v pat="$PATTERN_TO_SEARCH" '$0 ~ pat {k=$3}END{print k}' $FILE_PATH/$FILE_NAME` #grab date
TIME_LAST_LOG=`awk -v pat="$PATTERN_TO_SEARCH" '$0 ~ pat {k=$4}END{print k}' $FILE_PATH/$FILE_NAME` # grab time

if [ -n "$DATE_LAST_LOG" ]; then
#    echo $DATE_LAST_LOG $TIME_LAST_LOG
   GOT_LAST_DATE=true
else
#    echo 'WARN: no file uploaded today' # set to start of the day
    DATE_LAST_LOG=`date '+%F'`
    TIME_LAST_LOG="00:00:00"
fi
#Mac date conversion
#LAST_LOG_UPLOADED_TS=`date -j -f %Y-%m-%d:%H:%M:%S "$DATE_LAST_LOG:$TIME_LAST_LOG" +%s`
#Linux date conversion
LAST_LOG_UPLOADED_TS=`date -d "$DATE_LAST_LOG $TIME_LAST_LOG" +%s`
#`date -d "$DATE_LAST_LOG $TIME_LAST_LOG" +%s`
CUR_TS=`date +%s`
#echo uploaded time is $LAST_LOG_UPLOADED_TS, cur time is $CUR_TS
TWO_HOURS_IN_SEC=$(($MINS*60))

#echo secs in 2 hours are $TWO_HOURS_IN_SEC

TIME_DIFF=$(($CUR_TS-$LAST_LOG_UPLOADED_TS))
#echo the diff is $TIME_DIFF

if [[ $TIME_DIFF -ge $TWO_HOURS_IN_SEC ]] 
then
#sending alerts
 #   echo greater and sending alert here
    response=$( curl -s -o /dev/null -w "%{http_code}" -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: GenieKey {xxxxx}" \
    -d '{"message": "some message "}' https://api.opsgenie.com/v2/alerts
)


if [[ $response -ne 202 ]] 
then 
  #  echo "Could create alert correctly"
    exit 1
fi

fi
