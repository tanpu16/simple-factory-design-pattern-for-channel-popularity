package channelpopularity.state;

public class UltraPopularStateClass extends AbstractState {


	public String createOutputString(String vName)
	{
		String str = StateName.UNPOPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+vName;
		return str;
	}
}
