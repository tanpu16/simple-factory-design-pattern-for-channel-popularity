package channelpopularity.state;

import java.util.HashMap;

import channelpopularity.operation.Operation;

public abstract class AbstractState implements StateI {
	
	public int popularityScore = 0;
	
	public int CalculatePopularityScore(int views,int likes,int dislikes)
	{
		popularityScore = (views + 2*(likes-dislikes));
		return popularityScore;
	}
	
	public StateName checkForUpdateState(int popularity)
	{
		StateName updatedState = null;
		if(popularity >= 0 && popularity <= 1000)
		{
			updatedState = StateName.UNPOPULAR;
		}
		else if(popularity >= 1001 && popularity <= 10000)
		{
			updatedState = StateName.MILDLY_POPULAR;
		}
		else if(popularity >= 10001 && popularity <= 100000)
		{
			updatedState = StateName.HIGHLY_POPULAR;
		}
		else if(popularity >= 100001 && popularity <= Integer.MAX_VALUE)
		{
			updatedState = StateName.ULTRA_POPULAR;
		}
		
		return updatedState;
	}
	
	public void addVideo(HashMap<String, Integer> hmap, String videoName, int popularity)
	{
		hmap.put(videoName,popularity);
	}
	
	public void removeVideo(HashMap<String, Integer> hmap, String videoName)
	{
		hmap.remove(videoName);
	}
	
	public abstract String createOutputString(String vName, Operation op);
	
	public abstract String createOutputStringAdRequest(int len);
	
	public abstract String createOutputStringMetrics(int score);
}
