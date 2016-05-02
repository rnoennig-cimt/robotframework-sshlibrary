package org.robot.sshlibrary.keywords;

import org.apache.tools.ant.Project;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import org.robot.sshlibrary.SystemOutLogBuildListener;

@RobotKeywords
public class SSHExec {
	@RobotKeyword("Execute a command via ssh using key based auth.\n"
			+ "Example:\n"
			+ "| SSH Exec | myuser | /home/myuser/.ssh/id_dsa | example.com | ls -lsa |")
	@ArgumentNames({ "user", "keyfile", "host", "command" })
	public int sshExec(String user, String keyfile, String host, String command) {
		org.apache.tools.ant.taskdefs.optional.ssh.SSHExec sshExecTask = new org.apache.tools.ant.taskdefs.optional.ssh.SSHExec();
		Project project = new Project();
		project.addBuildListener(new SystemOutLogBuildListener());
		sshExecTask.setProject(project);
		sshExecTask.setUsername(user);
		sshExecTask.setKeyfile(keyfile);
		sshExecTask.setHost(host);
		sshExecTask.setTrust(true);
		sshExecTask.setCommand(command);
		sshExecTask.setFailonerror(false);
		sshExecTask.setResultproperty("sshexec.returncode");

		sshExecTask.execute();
		int retcode = -1;
		String retcodeStr = project.getProperty("sshexec.returncode");
		if (retcodeStr != null) {
			retcode = Integer.parseInt(retcodeStr);
		}

		return retcode;
	}

	@RobotKeyword("Execute a command via ssh using key based auth.\n"
			+ "The retcode and the output will be returned in an object."
			+ "Example:\n"
			+ "| ${result}= | SSH Exec With Output | myuser | /home/myuser/.ssh/id_dsa | example.com | ls -lsa |\n"
			+ "| Log | Return code: ${result.retcode}, Output: ${result.output} |")
	@ArgumentNames({ "user", "keyfile", "host", "command" })
	public SSHExecReturn sshExecWithOutput(String user, String keyfile,
			String host, String command) {
		org.apache.tools.ant.taskdefs.optional.ssh.SSHExec sshExecTask = new org.apache.tools.ant.taskdefs.optional.ssh.SSHExec();
		Project project = new Project();
		project.addBuildListener(new SystemOutLogBuildListener());
		sshExecTask.setProject(project);
		sshExecTask.setUsername(user);
		sshExecTask.setKeyfile(keyfile);
		sshExecTask.setHost(host);
		sshExecTask.setTrust(true);
		sshExecTask.setCommand(command);
		sshExecTask.setFailonerror(false);
		sshExecTask.setSuppressSystemOut(true);
		sshExecTask.setSuppressSystemErr(true);
		sshExecTask.setResultproperty("sshexec.returncode");
		sshExecTask.setOutputproperty("sshexec.output");
		sshExecTask.setErrorproperty("sshexec.error");
		sshExecTask.execute();
		int retcode = -1;
		String retcodeStr = project.getProperty("sshexec.returncode");
		if (retcodeStr != null) {
			retcode = Integer.parseInt(retcodeStr);
		}
		String output = project.getProperty("sshexec.output");
		String error = project.getProperty("sshexec.error");
		SSHExecReturn execReturn = new SSHExecReturn();
		execReturn.retcode = retcode;
		execReturn.output = output;
		execReturn.error = error;
		return execReturn;
	}

	public class SSHExecReturn {
		public int retcode;
		public int getRetcode() {
			return retcode;
		}
		public void setRetcode(int retcode) {
			this.retcode = retcode;
		}

		public String output;
		public String getOutput() {
			return output;
		}
		public void setOutput(String output) {
			this.output = output;
		}
		
		public String error;
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
	}
}
