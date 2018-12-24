/**
 *  Generic Video Camera Connect
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
 *  Based on the original work by Patrick Stuart (pstuart)
 */
definition(
    name: "ReoLink RLN16-410 NVR",
    namespace: "cvpcs",
    author: "Austen Dicken",
    description: "This smartapp installs the NVR app for adding cameras connected to a ReoLink RLN16-410 NVR.",
    category: "Safety & Security",
    iconUrl: "https://raw.githubusercontent.com/cvpcs/smartthings/master/smartapps/cvpcs/reolink-rln16-410-nvr.src/Reolink.png",
    iconX2Url: "https://raw.githubusercontent.com/cvpcs/smartthings/master/smartapps/cvpcs/reolink-rln16-410-nvr.src/Reolink@2x.png",
    iconX3Url: "https://raw.githubusercontent.com/cvpcs/smartthings/master/smartapps/cvpcs/reolink-rln16-410-nvr.src/Reolink@2x.png",
    singleInstance: true)


preferences {
    page(name: "mainPage", title: "Existing cameras", install: true, uninstall: true) {
        if(state?.installed) {
            section("Add a new camera") {
                app "ReoLink RLN16-410 NVR Camera", "cvpcs", "ReoLink RLN16-410 NVR Camera", title: "New Camera", page: "mainPage", multiple: true, install: true
            }
        } else {
            section("Initial Install") {
                paragraph "This smartapp installs the NVR app so you can add multiple child video cameras. Click install / done then go to smartapps in the flyout menu and add new cameras or edit existing cameras."
            }
        }
        section("LAN configuration") {
            input("host", "string", title: "IP/Hostname", required: true)
            input("port", "number", title: "Port", required: true)
            input("user", "string", title: "Username", required: true)
            input("pass", "password", title: "Password", required: true)
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"

    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"

    unsubscribe()
    initialize()
}

def initialize() {
    state.installed = true
}
