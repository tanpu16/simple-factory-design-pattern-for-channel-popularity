package channelpopularity.state;

import channelpopularity.operation.Operation;

public interface StateI {
	
	
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount);
	public StateName checkForUpdateState(int popularity);
	public String createOutputString(String vName, Operation op);
	public String createOutputStringAdRequest(int len);
	public String createOutputStringMetrics(int score);
}
