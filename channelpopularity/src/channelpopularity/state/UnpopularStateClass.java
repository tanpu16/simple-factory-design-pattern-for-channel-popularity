package channelpopularity.state;

public class UnpopularStateClass extends AbstractState  {


	public UnpopularStateClass()
	{
		popularityScore = 0;
	}


	
	public String createOutputString(String vName)
	{
		String str = StateName.UNPOPULAR.toString()+"__VIDEO_ADDED::"+vName;
		return str;
	}
	
}
