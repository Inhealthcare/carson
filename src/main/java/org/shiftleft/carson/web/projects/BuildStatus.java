package org.shiftleft.carson.web.projects;

import javax.persistence.Embeddable;

@Embeddable
public class BuildStatus {

	public enum State {
		UNKNOWN, OK, FAILING;
	}

	private State state;

	public BuildStatus() {
		state = State.UNKNOWN;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
