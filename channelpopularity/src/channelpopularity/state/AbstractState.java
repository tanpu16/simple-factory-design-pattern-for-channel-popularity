package channelpopularity.state;

public abstract class AbstractState implements StateI {
	
<<<<<<< HEAD
	public int popularityScore;
	
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount)
	{
		int popularityScore = (views + 2*(likes-dislikes))/vCount;
		System.out.println("in AbstractState "+popularityScore);
		return popularityScore;
	}
	
	public abstract String createOutputString(String vName);
	
=======
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount)
	{
		int popularityScore = (views + 2*(likes-dislikes))/vCount;
		return popularityScore;
	}
	
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
	
}
