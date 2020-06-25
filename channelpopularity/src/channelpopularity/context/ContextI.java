package channelpopularity.context;

import java.util.HashMap;
import java.util.List;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface ContextI {
	public void setCurrentState(StateName nextState);
	public StateI getCurState();
	public void addVideo(HashMap<String, List<Integer>> hmap, String videoName, List<Integer> list);
	public void removeVideo(HashMap<String, List<Integer>> hmap, String videoName);
	public int metrics(int views,int likes,int dislikes);
	public String adRequest(int len);
}
