/**
 *  Copyright 2017 SmartThings
 *
 *  Provides a virtual switch for a pet feeder indicator.
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
metadata {
    definition (name: "Pet Feeder Indicator Virtual Switch", namespace: "cvpcs", author: "Austen Dicken", executeCommandsLocally: true) {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: false){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'Fed', action:"switch.off", icon:"https://raw.githubusercontent.com/cvpcs/smartthings/master/devicetypes/cvpcs/pet-feeder-indicator-virtual-switch.src/pet-feeder-indicator-fed.png", backgroundColor:"#79b821", nextState:"turningOff"
                attributeState "off", label:'Not Fed', action:"switch.on", icon:"https://raw.githubusercontent.com/cvpcs/smartthings/master/devicetypes/cvpcs/pet-feeder-indicator-virtual-switch.src/pet-feeder-indicator-not-fed.png", backgroundColor:"#FFFFFF", nextState:"turningOn", defaultState: true
                attributeState "turningOn", label:'Fed', action:"switch.off", icon:"https://raw.githubusercontent.com/cvpcs/smartthings/master/devicetypes/cvpcs/pet-feeder-indicator-virtual-switch.src/pet-feeder-indicator-fed.png", backgroundColor:"#79b821", nextState:"turningOn"
                attributeState "turningOff", label:'Not Fed', action:"switch.on", icon:"https://raw.githubusercontent.com/cvpcs/smartthings/master/devicetypes/cvpcs/pet-feeder-indicator-virtual-switch.src/pet-feeder-indicator-not-fed.png", backgroundColor:"#FFFFFF", nextState:"turningOff"
            }
        }

        main(["switch"])
        details(["switch"])

    }
}

def parse(String description) {
}

def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def installed() {
    on()
}