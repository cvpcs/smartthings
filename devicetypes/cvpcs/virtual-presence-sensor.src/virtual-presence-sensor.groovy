/**
 *  DD-WRT MAC Address Presence Sensor
 *
 *  Copyright 2018 Austen Dicken
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

import groovy.json.JsonSlurper

metadata {
	definition (name: "Virtual Presence Sensor", namespace: "cvpcs", author: "Austen Dicken", executeCommandsLocally: true) {
    	capability "Actuator"
		capability "Presence Sensor"
		capability "Sensor"

		command "setPresence", ["string"]
	}

	simulator {
		status "present": "presence: 1"
		status "not present": "presence: 0"
	}

	tiles {
		standardTile("presence", "device.presence", width: 2, height: 2, canChangeBackground: true) {
			state("present", labelIcon:"st.presence.tile.present", backgroundColor:"#00A0DC")
			state("not present", labelIcon:"st.presence.tile.not-present", backgroundColor:"#ffffff")
		}
        
		main "presence"
		details (["presence"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def installed() {
	sendEvent(name: "presence", value: "not present")
}

def setPresence(status) {
	log.debug "Status is $status"
	sendEvent(name: "presence", value: status)
}
