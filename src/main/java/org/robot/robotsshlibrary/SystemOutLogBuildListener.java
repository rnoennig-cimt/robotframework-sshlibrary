package org.robot.sshlibrary;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;

public class SystemOutLogBuildListener implements BuildListener {
	@Override
	public void taskStarted(BuildEvent arg0) {}
	
	@Override
	public void taskFinished(BuildEvent arg0) {}
	
	@Override
	public void targetStarted(BuildEvent arg0) {}
	
	@Override
	public void targetFinished(BuildEvent arg0) {}
	
	@Override
	public void messageLogged(BuildEvent buildEvent) {
		System.out.println(buildEvent.getMessage());
	}
	
	@Override
	public void buildStarted(BuildEvent arg0) {}
	
	@Override
	public void buildFinished(BuildEvent arg0) {}
}
