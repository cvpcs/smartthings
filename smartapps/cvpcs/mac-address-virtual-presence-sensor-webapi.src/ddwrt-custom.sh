#!/bin/sh

WATCHDOG_SLEEP_SEC=2
WATCHDOG_DISCONNECT_TRY=30 # give not responding devices 1 minute to respond
CURL="curl -k"

MAC_ADDRESS="$1"
APP_TOKEN="$2"
ACCESS_TOKEN="$3"
last_state="0"
PHONEON="https://graph.api.smartthings.com/api/smartapps/installations/${APP_TOKEN}/${MAC_ADDRESS}/present?access_token=${ACCESS_TOKEN}"
PHONEOFF="https://graph.api.smartthings.com/api/smartapps/installations/${APP_TOKEN}/${MAC_ADDRESS}/absent?access_token=${ACCESS_TOKEN}"
offcount=0

[ -z "$MAC_ADDRESS" -o -z "$APP_TOKEN" -o -z "$ACCESS_TOKEN" ] && exit 1

while sleep $WATCHDOG_SLEEP_SEC; do
    new_state=`arp | grep " $MAC_ADDRESS "`
    [ -n "$new_state" ] && new_state=on || new_state=off
    echo $new_state > /tmp/$MAC_ADDRESS.state
    if [ "$new_state" != "$last_state" ] ; then
        if [ "$new_state" == "on" ]; then
            last_state="$new_state"
            ${CURL} "${PHONEON}"
            offcount=0
        else
            offcount=$(( $offcount + 1 ))
        fi
    fi

    if [ $offcount -ge $WATCHDOG_DISCONNECT_TRY ]; then
        last_state="$new_state"
        ${CURL} "${PHONEOFF}"
        offcount=0
    fi
done