package org.robot.sshlibrary.keywords;

import org.apache.tools.ant.Project;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import org.robot.sshlibrary.SystemOutLogBuildListener;

@RobotKeywords
public class Scp {
	@RobotKeyword("Copy a local file to a remote machine using keybased auth scp.\n"
			+ "Example:\n"
			+ "| SCP | /home/myuser/.ssh/id_dsa | /home/myuser/README.txt | myuser@localhost:copied.txt |")
	@ArgumentNames({"keyfile", "localfile", "remotefile"})
	public void scp(String keyfile, String localfile, String remotefile) {
		org.apache.tools.ant.taskdefs.optional.ssh.Scp scpTask = new org.apache.tools.ant.taskdefs.optional.ssh.Scp();
		Project project = new Project();
		project.addBuildListener(new SystemOutLogBuildListener());
		scpTask.setProject(project);
		scpTask.setKeyfile(keyfile);
		scpTask.setLocalFile(localfile);
		scpTask.setRemoteTofile(remotefile);
		scpTask.setTrust(true);
		scpTask.execute();
	}
}
