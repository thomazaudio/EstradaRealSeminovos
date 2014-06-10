/*
 * Copyright 2004-2012 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.icemobile.samples;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "navigationBean")
@ViewScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String selectedTabPane = "panelt1";

	public String getSelectedTabPane() {
		return selectedTabPane;
	}

	public void setSelectedTabPane(String selectedTabPane) {
		this.selectedTabPane = selectedTabPane;
	}
}
