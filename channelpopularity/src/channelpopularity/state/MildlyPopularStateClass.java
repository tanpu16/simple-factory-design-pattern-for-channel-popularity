package channelpopularity.state;

public  class MildlyPopularStateClass  extends AbstractState {


	
	public String createOutputString(String vName)
	{
		String str = StateName.MILDLY_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+vName;
		return str;
	}
}
