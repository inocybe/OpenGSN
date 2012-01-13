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
package com.greenstarnetwork.resources.facilityResource.actionset;

/**
 * 
 * Refresh action for facility resource: to update the energy data of a facility resource.
 * made for poller
 * 
 * @author Fereydoun Farrahi Moghaddam (ffarrahi@synchromedia.ca) - Synchromedia lab.
 * 
 */


import java.util.ArrayList;

import com.greenstarnetwork.resources.facilityResource.commandset.CalculateOPSpecsCommand;
import com.greenstarnetwork.resources.facilityResource.commandset.CheckPowerCommand;
import com.greenstarnetwork.resources.facilityResource.commandset.InitializeCommand;
import com.greenstarnetwork.resources.facilityResource.commandset.RefreshClimateCommand;
import com.iaasframework.capabilities.actionset.ActionException;
import com.iaasframework.capabilities.actionset.ActionState.State;

public class PollerAction extends BasicAction{

	public static final String ACTION = "PollerAction";

	public PollerAction() {
		super(ACTION);
		initializeCommandsList();
	}

	private void initializeCommandsList(){
		commandsList = new ArrayList<String>();
		commandsList.add(InitializeCommand.COMMAND);
		commandsList.add(RefreshClimateCommand.COMMAND);
		commandsList.add(CalculateOPSpecsCommand.COMMAND);
		commandsList.add(CheckPowerCommand.COMMAND);
		state.setSteps(commandsList);
	}

	@Override
	public void executeAction() throws ActionException
	{
		try {
			String nextCommand = state.getNextStep();
			if (state.getState() == State.INPROGRESS) 
			{
				sendMessageToCommand(nextCommand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionException("except from refresh ::", e);
		}
	}
}

