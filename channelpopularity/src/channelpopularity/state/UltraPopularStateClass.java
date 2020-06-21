package channelpopularity.state;

import channelpopularity.operation.Operation;

public class UltraPopularStateClass extends AbstractState {


	public String createOutputString(String vName)
	{
		String str = StateName.UNPOPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+vName;
		return str;
	}
	
	public String createOutputString(String vName, Operation op)
	{
		String str = null;
		if(op == Operation.ADD_VIDEO)
		{
			str = StateName.ULTRA_POPULAR.toString()+"__VIDEO_ADDED::"+vName;
		}
		else if(op == Operation.REMOVE_VIDEO)
		{
			str = StateName.ULTRA_POPULAR.toString()+"__VIDEO_REMOVED::"+vName;
		}


		return str;
	}	
	
	public String createOutputStringAdRequest(int len)
	{
		String str = null;
		if(len > 1 && len <= 40)
		{
			str = StateName.ULTRA_POPULAR.toString()+"__AD_REQUEST::"+"APPROVED";
		}
		else
		{
			str = StateName.ULTRA_POPULAR.toString()+"__AD_REQUEST::"+"REJECTED";
		}
		return str;
	}
	
	public String createOutputStringMetrics(int score)
	{
		String str = StateName.ULTRA_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+score;
		return str;
	}
}
