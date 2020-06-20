package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface ContextI {
	public void setCurrentState(StateName nextState);
	public StateI getCurState();
}
