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
package com.greenstarnetwork.resources.libvirt.actionset;

import java.util.ArrayList;
import java.util.Map;

import com.greenstarnetwork.models.vmm.VM;
import com.greenstarnetwork.models.vmm.VmHostModel;
import com.greenstarnetwork.resources.libvirt.commandset.CreateVMCommand;
import com.greenstarnetwork.resources.libvirt.commandset.GetMACCommand;
import com.greenstarnetwork.resources.libvirt.commandset.VMInfoCommand;
import com.iaasframework.capabilities.actionset.ActionException;
import com.iaasframework.capabilities.actionset.ActionState.State;
import com.iaasframework.capabilities.actionset.api.ActionResponseMessage;
import com.iaasframework.capabilities.model.ModelCapabilityClient;
import com.iaasframework.capabilities.model.RequestModelResponse;
import com.iaasframework.resources.core.capability.CapabilityException;

/**
 * Recreate a new VM on a given host.
 * Both VM image and XML template already exist in the storage. IP address is given by CloudManager.
 * This action simply call virsh create command to recreate the VM.
 * 
 * @author knguyen
 *
 */
public class ReCreateAction extends BasicAction{

	public static final String ACTION = "ReCreateAction";
	
	public ReCreateAction() {
		super(ACTION);
		initializeCommandsList();
	}
	
	private void initializeCommandsList(){
		commandsList = new ArrayList<String>();
		commandsList.add(CreateVMCommand.COMMAND);
		commandsList.add(GetMACCommand.COMMAND);
		commandsList.add(VMInfoCommand.COMMAND);
		state.setSteps(commandsList);
	}
	
	@Override
	public void executeAction() throws ActionException
	{
		extractResourceIdFromArgument();
		ActionResponseMessage responseMessage = new ActionResponseMessage();
		Map<Object, Object> args = this.actionRequest.getArguments();
		String newVMName = (String)args.get((String)"vmName");
		try {
			String nextCommand = state.getNextStep();
			if (state.getState() == State.INPROGRESS) 
			{
				if (nextCommand.compareTo(VMInfoCommand.COMMAND) == 0) 
				{
					ModelCapabilityClient modelClient = new ModelCapabilityClient(resourceID);
					RequestModelResponse reqModel = modelClient.requestModel(true);
					VM vm = ((VmHostModel)(reqModel.getResourceModel())).getVM(newVMName);
					if (vm != null) {
						String ip = vm.getIp();
						responseMessage.setMessage("VM: " + newVMName + " IP: " + ip + " created!");
						try {
							sendActionResponseMessage(responseMessage, this.requestor);
						}catch (CapabilityException e) {
							throw new ActionException("VM: " + newVMName + " failed!", e);
						}
					}else
						throw new ActionException("VM: " + newVMName + " failed!");
				}
					
				sendMessageToCommand(nextCommand);
			}
		} catch (CapabilityException e) {
			e.printStackTrace();
			throw new ActionException("VM: " + newVMName + " failed!", e);
		}
	}
}
	
