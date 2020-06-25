package channelpopularity.state;

import java.util.HashMap;
import java.util.List;
import channelpopularity.operation.Operation;

public abstract class AbstractState implements StateI {
	
	
	/*this is a metrics method which takes 3 parameters, which in turn calculate the popularity score of each video,
	 and if the popularity score is negative then it will assign 0.
	@param views - views on video
	@param likes - likes on video
	@param dislikes - dislikes on video
	@return int - the popularity score calculated for video
	@see just metrics function which returns popularity score of a video.
	*/
	public int CalculatePopularityScore(int views,int likes,int dislikes)
	{
		int popularityScore = (views + 2*(likes-dislikes));
		
		if(popularityScore < 0)
		{
			popularityScore = 0;
		}
		
		return popularityScore;
	}
	
	/*this method takes 1 parameters, popularity of channel which checks which state need to assign next 
	 depending upon provided popularity ranges.
	@param popularity -- popularity score of channel
	@return StateName - state next need to update
	@see just a function which update the state.
	*/
	public StateName checkForUpdateState(int popularity)
	{
		StateName updatedState = null;
		if(popularity >= StateName.UNPOPULAR.minPopularity && popularity <= StateName.UNPOPULAR.maxPopularity)
		{
			updatedState = StateName.UNPOPULAR;
		}
		else if(popularity >= StateName.MILDLY_POPULAR.minPopularity  && popularity <= StateName.MILDLY_POPULAR.maxPopularity)
		{
			updatedState = StateName.MILDLY_POPULAR;
		}
		else if(popularity >= StateName.HIGHLY_POPULAR.minPopularity && popularity <= StateName.HIGHLY_POPULAR.maxPopularity)
		{
			updatedState = StateName.HIGHLY_POPULAR;
		}
		else if(popularity >= StateName.ULTRA_POPULAR.minPopularity && popularity <= StateName.ULTRA_POPULAR.maxPopularity)
		{
			updatedState = StateName.ULTRA_POPULAR;
		}
		
		return updatedState;
	}
	
	/*this method takes 3 parameters, just store the video name, views, likes, dislikes, and total popularity score of a video so far in a map
	@param hmap -- map in which values need to store
	@param videoName - name of the video 
	@param list - list containing views, likes, dislikes and total popularity score of a video so far
	@return void
	@see just a function which stores video related data
	*/
	public void addVideo(HashMap<String, List<Integer>> hmap, String videoName, List<Integer> list)
	{
		hmap.put(videoName,list);
	}
	
	/*this method takes 2 parameters, just remove the video from map
	@param hmap -- map from which video needs to remove
	@param videoName - name of the video 
	@return void
	@see just a function which remove video from map.
	*/
	public void removeVideo(HashMap<String, List<Integer>> hmap, String videoName)
	{
		hmap.remove(videoName);
	}
	
	//abstract functions implemented in each state class and read description there.
	
	public abstract String createOutputString(String vName, Operation op);
	
	public abstract String adRequest(int len);
	
	public abstract String createOutputStringMetrics(int score);
}
