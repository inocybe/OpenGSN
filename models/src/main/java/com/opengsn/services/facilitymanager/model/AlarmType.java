/**
 * Copyright 2009-2011 École de technologie supérieure,
 * Communication Research Centre Canada,
 * Inocybe Technologies Inc. and 6837247 CANADA Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opengsn.services.facilitymanager.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum AlarmType {
	
	TEMPERATURE_HIGH,
	HUMIDITY_HIGH,
	TEMPERATURE_LOW,
	HUMIDITY_LOW,
	Lower_Critical_going_low,
	Lower_Non_critical_going_high,
	Lower_Critical_going_high,
	Lower_Non_recoverable_going_low,
	Lower_Non_recoverable_going_high,
	Upper_Critical_going_low,
	Upper_Critical_going_high,
	Upper_Non_recoverable_going_low,
	Upper_Non_recoverable_going_high


}
