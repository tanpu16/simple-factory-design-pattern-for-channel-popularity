package channelpopularity.state;

import java.util.HashMap;

import channelpopularity.operation.Operation;

public interface StateI {
	
	
	public int CalculatePopularityScore(int views,int likes,int dislikes);
	public void addVideo(HashMap<String, Integer> hmap, String videoName, int popularity);
	public void removeVideo(HashMap<String, Integer> hmap, String videoName);
	public StateName checkForUpdateState(int popularity);
	public String createOutputString(String vName, Operation op);
	public String createOutputStringAdRequest(int len);
	public String createOutputStringMetrics(int score);
}
