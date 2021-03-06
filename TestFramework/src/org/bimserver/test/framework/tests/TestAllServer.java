package org.bimserver.test.framework.tests;

/******************************************************************************
 * Copyright (C) 2009-2018  BIMserver.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see {@literal<http://www.gnu.org/licenses/>}.
 *****************************************************************************/

import java.nio.file.Paths;

import org.bimserver.plugins.OptionsParser;
import org.bimserver.test.framework.FolderWalker;
import org.bimserver.test.framework.RandomBimServerClientFactory;
import org.bimserver.test.framework.RandomBimServerClientFactory.Type;
import org.bimserver.test.framework.TestConfiguration;
import org.bimserver.test.framework.TestFramework;
import org.bimserver.test.framework.actions.AllActionsFactory;

public class TestAllServer {
	public static void main(String[] args) {
		TestConfiguration testConfiguration = new TestConfiguration();
		TestFramework testFramework = new TestFramework(testConfiguration, new OptionsParser(args).getPluginDirectories());

		testConfiguration.setHomeDir(Paths.get("/var/bimservertest/home"));
		testConfiguration.setActionFactory(new AllActionsFactory(testFramework));
		testConfiguration.setBimServerClientFactory(new RandomBimServerClientFactory(testFramework, 6060, Type.JSON));
		testConfiguration.setTestFileProvider(new FolderWalker(Paths.get("/var/ifc"), testFramework));
		testConfiguration.setOutputFolder(Paths.get("/var/bimservertestoutput"));
		testConfiguration.setNrVirtualUsers(8);
		testConfiguration.setStopNoVirtualUsers(false);
		testConfiguration.setStopOnServerException(false);
		testConfiguration.setNrEngineProcesses(8);
		testConfiguration.setCleanEnvironmentFirst(true);
		testConfiguration.setPort(6060);
		testConfiguration.setHost("thisisanexperimentalserver.com");

		testFramework.start();
	}
}
