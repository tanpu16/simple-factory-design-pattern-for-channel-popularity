package channelpopularity.state;

public class HighlyPopularStateClass extends AbstractState {

	
	public String createOutputString(String vName)
	{
		String str = StateName.HIGHLY_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+vName;
		return str;
	}

}
