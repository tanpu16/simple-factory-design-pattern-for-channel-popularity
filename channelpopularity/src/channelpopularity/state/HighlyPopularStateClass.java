package channelpopularity.state;

import channelpopularity.operation.Operation;

public class HighlyPopularStateClass extends AbstractState {


	/*this method takes 2 parameters, used to create final output string
	@param vName -- Video name
	@param videoName - operation performed on video
	@return String -- final output string needs to store in result class
	@see just a function which creates respective operation related output string.
	*/
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
	
	/*this method takes 1 parameter1, used to create final output string for adRequest by checking range
	@param len -- length of ad in input file
	@return String -- final output string needs to store in result class
	@see just a function which creates ad related output string by checking ad length
	*/
	public String adRequest(int len)
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

	/*this method takes 1 parameter, used to create final output string for metrics
	@param score -- score of channel popularity
	@return String -- final output string needs to store in result class
	@see just a function which creates metrics related output string
	*/
	public String createOutputStringMetrics(int score)
	{
		String str = StateName.HIGHLY_POPULAR.toString()+"__POPULARITY_SCORE_UPDATE::"+score;
		return str;
	}


}
