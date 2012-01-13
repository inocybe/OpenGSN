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

import com.iaasframework.capabilities.commandset.CommandException;
import com.iaasframework.capabilities.model.IResourceModel;

/**
 * Execute a null command
 * 
 * @author knguyen
 *
 */
public class EchoCommand extends BasicCommand {

	public static final String COMMAND = "EchoCommand";	//Query command ID

	public EchoCommand() {
		super(COMMAND);
	}
	
	@Override
	public void executeCommand() throws CommandException 
	{
			String command1 = "echo";
			sendCommandToProtocol(command1);
	}
	
	@Override
	public void parseResponse(IResourceModel model) throws CommandException {
		if (response == null) {
			return;
		}
	}
}
