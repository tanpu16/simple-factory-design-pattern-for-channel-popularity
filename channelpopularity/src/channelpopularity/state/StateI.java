package channelpopularity.state;

import java.util.HashMap;
import java.util.List;

import channelpopularity.operation.Operation;

public interface StateI {
	
	
	public int CalculatePopularityScore(int views,int likes,int dislikes);
	public void addVideo(HashMap<String, List<Integer>> hmap, String videoName, List<Integer> list);
	public void removeVideo(HashMap<String, List<Integer>> hmap, String videoName);
	public StateName checkForUpdateState(int popularity);
	public String createOutputString(String vName, Operation op);
	public String adRequest(int len);
	public String createOutputStringMetrics(int score);
}
