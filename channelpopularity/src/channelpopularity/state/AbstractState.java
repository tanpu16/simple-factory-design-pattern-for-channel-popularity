package channelpopularity.state;

import channelpopularity.operation.Operation;

public abstract class AbstractState implements StateI {
	
	public int popularityScore = 0;
	
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount)
	{
		//System.out.println("in AbstractState score before is "+popularityScore);
		popularityScore = (views + 2*(likes-dislikes));
		//System.out.println("in AbstractState score is "+popularityScore);
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
	
	public abstract String createOutputString(String vName, Operation op);
	
	public abstract String createOutputStringAdRequest(int len);
	
	public abstract String createOutputStringMetrics(int score);
}
