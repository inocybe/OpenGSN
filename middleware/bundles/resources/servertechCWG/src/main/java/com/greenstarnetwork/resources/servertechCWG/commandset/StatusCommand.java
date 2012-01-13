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
package com.greenstarnetwork.resources.servertechCWG.commandset;

import java.util.ArrayList;
import java.util.List;

import com.greenstarnetwork.models.pdu.PDUElement;
import com.greenstarnetwork.models.pdu.PDUException;
import com.greenstarnetwork.models.pdu.PDUModel;
import com.iaasframework.capabilities.commandset.AbstractCommandWithProtocol;
import com.iaasframework.capabilities.commandset.CommandException;
import com.iaasframework.capabilities.model.IResourceModel;
import com.iaasframework.capabilities.protocol.api.ProtocolErrorMessage;
import com.iaasframework.capabilities.protocol.api.ProtocolResponseMessage;
import com.iaasframework.protocols.cli.message.CLIResponseMessage;
import com.iaasframework.resources.core.message.ICapabilityMessage;

/**
 * Status command handler. Show outlet status
 * @author K.-K.Nguyen <synchromedia.ca>
 *
 */
public class StatusCommand extends AbstractCommandWithProtocol {

	public static final String COMMAND = "StatusCommand";	//Query command ID
	public static final int Number_Fields = 6;	//number of fields in the response message

	public StatusCommand() {
		super(COMMAND);
	}
	
	@Override
	public void executeCommand() throws CommandException{
		if (!initialized) {
			initializeWithModel();
		}else
		{
			String command = CWGCommandList.STATUS;
			sendCommandToProtocol(command);
		}
	}

	@Override
	public void initializeCommand(IResourceModel model) throws CommandException{
    	initialized = true;
	}

	@Override
	public void parseResponse(IResourceModel model) throws CommandException 
	{
		CLIResponseMessage msg = (CLIResponseMessage) ((ProtocolResponseMessage) response).getProtocolMessage();
		
		List<List<String>> result = msg.getTableResult(" ");
		if (result.size() == 0)
			throw new CommandException("Invalid response!");
		
		if (result.get(1).size() < Number_Fields)
			throw new CommandException("Invalid response format!");
		
		if (result.size() < 4)	//only header, no return value
			return;

		
		ArrayList<PDUElement> elements = new ArrayList<PDUElement>();
		for (int i=3; i<result.size(); i++)
		{
			if (result.get(i).size() == 0)	//jump over empty lines
				continue;
			if (!result.get(i).get(0).startsWith("."))	//not a valid response line
				continue;
			try {
				//Set infeeds and their info here
				PDUElement elem = new PDUElement();
				elem.setID(result.get(i).get(0));
				elem.setName(result.get(i).get(1));
				elem.setStatusString(result.get(i).get(2));
				elem.setLoad(result.get(i).get(3));
				elem.setPower(result.get(i).get(4));
				//There are 6 fields, however, the last one could have the format like: "Idle On", 
				//so in that case the last two fields need to be merged
				if (result.get(i).size() > Number_Fields)
				{
					String lastfield = result.get(i).get(5);
					for (int j= Number_Fields; j<result.get(i).size(); j++)
						lastfield = lastfield + " " + result.get(i).get(j);
					elem.setControlState(lastfield);
				}else
					elem.setControlState(result.get(i).get(5));
				elements.add(elem);
			} catch (PDUException e) {
				throw new CommandException("Invalid Outlet status: " +  e.toString());
			}
		}

		if (elements.size() > 0)
			((PDUModel)model).setOutlets(elements);
	}

	@Override
	public void responseReceived(ICapabilityMessage response) throws CommandException{
		if (response instanceof ProtocolResponseMessage) {
			this.response = (ProtocolResponseMessage) response;
			this.requestEngineModel(false);
		}
		else if (response instanceof ProtocolErrorMessage) {
			this.errorMessage = (ProtocolErrorMessage) response;
			CommandException commandException = new CommandException("Error executing command "
					+ this.commandID, ((ProtocolErrorMessage) response)
					.getProtocolException());
			commandException.setName(this.commandID);
			commandException.setCommand(this);
			this.sendCommandErrorResponse(commandException);
		}
	}

}
