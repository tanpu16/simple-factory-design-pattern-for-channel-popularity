package channelpopularity.state;

public interface StateI {
	
	
	public int CalculatePopularityScore(int views,int likes,int dislikes,int vCount);
	public String createOutputString(String vName);
	
}
