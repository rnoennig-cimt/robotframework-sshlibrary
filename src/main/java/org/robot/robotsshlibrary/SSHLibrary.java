package org.robot.sshlibrary;

import org.robotframework.javalib.library.AnnotationLibrary;


public class SSHLibrary extends AnnotationLibrary {
	private static final String KEYWORD_PATTERN = "org/robot/sshlibrary/keywords/**/*.class";

	public SSHLibrary() {
		addKeywordPattern(KEYWORD_PATTERN);
	}
}
