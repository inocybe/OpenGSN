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
package com.greenstarnetwork.resources.libvirt.commandset;

/**
*
* @author Fereydoun Farrahi Moghaddam (ffarrahi@synchromedia.ca) - Synchromedia lab. 
*
*/
public class LibvirtCommandList 
{ 

	public static final String CREATE = "virsh create";
	public static final String DELETE = "virsh destroy";
	public static final String START = "virsh resume";
	public static final String STOP = "virsh suspend";
	//public static final String UPDATE = "Update";
	public static final String MIGRATE = "virsh migrate";
	//public static final String REFRESH = "Refresh";
}
