package channelpopularity.state;

import channelpopularity.operation.Operation;

public class HighlyPopularStateClass extends AbstractState {

	
	public String createOutputString(String vName)
	{
		String str = StateName.HIGHLY_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+vName;
		return str;
	}
	
	public String createOutputString(String vName, Operation op)
	{
		String str = null;
		if(op == Operation.ADD_VIDEO)
		{
			str = StateName.HIGHLY_POPULAR.toString()+"__VIDEO_ADDED::"+vName;
		}
		else if(op == Operation.REMOVE_VIDEO)
		{
			str = StateName.HIGHLY_POPULAR.toString()+"__VIDEO_REMOVED::"+vName;
		}

		
		return str;
	}
	
	public String createOutputStringAdRequest(int len)
	{
		String str = null;
		if(len > 1 && len <= 30)
		{
			str = StateName.HIGHLY_POPULAR.toString()+"__AD_REQUEST::"+"APPROVED";
		}
		else
		{
			str = StateName.HIGHLY_POPULAR.toString()+"__AD_REQUEST::"+"REJECTED";
		}
		return str;
	}

	public String createOutputStringMetrics(int score)
	{
		String str = StateName.HIGHLY_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+score;
		return str;
	}

}
