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
package com.opengsn.services.cloudmanager;

import java.util.Comparator;

import com.opengsn.services.cloudmanager.model.Host;
/**
 * Comparator Class used to sort the host instances in order of priority
 * 
 * @author Mathieu Lemay (IT)
 *
 */
public class HostPriorityComparator implements Comparator<Host> {

	@Override
	public int compare(Host host0, Host host1) {
		return host0.getHostPriority() - host1.getHostPriority(); 
	}

}
