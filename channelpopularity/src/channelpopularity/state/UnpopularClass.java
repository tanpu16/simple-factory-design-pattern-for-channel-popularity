package channelpopularity.state;

public class UnpopularClass extends AbstractState  {


	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount)
	{
		int popularityScore = (views + 2*(likes-dislikes))/vCount;
		return popularityScore;
	}
	@Override
	public StateI calculatePopularityScore() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
