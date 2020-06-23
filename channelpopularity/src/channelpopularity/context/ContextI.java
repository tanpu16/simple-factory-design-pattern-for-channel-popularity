package channelpopularity.context;

import java.util.HashMap;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface ContextI {
	public void setCurrentState(StateName nextState);
	public StateI getCurState();
	public void addVideo(HashMap<String, Integer> hmap, String videoName, int popularity);
	public void removeVideo(HashMap<String, Integer> hmap, String videoName);
}
