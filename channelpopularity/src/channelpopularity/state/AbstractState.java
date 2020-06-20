package channelpopularity.state;

public abstract class AbstractState implements StateI {
	
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount)
	{
		int popularityScore = (views + 2*(likes-dislikes))/vCount;
		return popularityScore;
	}
	
	
}
