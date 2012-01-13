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
package com.greenstarnetwork.services.controller.core.testharness.vmmodel;

import com.greenstarnetwork.models.vmm.VM;

/**
 * @author hzhang
 *
 */
public abstract class VMHarness extends VM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218350753583643917L;

	//consumption in amp hours
	abstract public double getConsumption(long start, long end); 
	
	//peak current during period
	abstract public double getPeak(long start, long end); 
	
	//average current during period
	abstract public double getAverage(long start, long end); 
}
