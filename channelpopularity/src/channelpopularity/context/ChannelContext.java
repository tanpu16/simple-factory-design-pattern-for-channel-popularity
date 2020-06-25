package channelpopularity.context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ContextI{
	private StateI curState;
	private Map<StateName, StateI> availableStates;
	
	//Constructor which initialize all 4 states and store StateName and StateI instance in map
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames)
	{
		availableStates = new HashMap<StateName, StateI>();
		availableStates.put(StateName.UNPOPULAR,stateFactoryIn.create(stateNames.get(0)));
		availableStates.put(StateName.MILDLY_POPULAR,stateFactoryIn.create(stateNames.get(1)));
		availableStates.put(StateName.HIGHLY_POPULAR,stateFactoryIn.create(stateNames.get(2)));
		availableStates.put(StateName.ULTRA_POPULAR,stateFactoryIn.create(stateNames.get(3)));
		curState = availableStates.get(StateName.UNPOPULAR);
	}
	
	@Override
	public String toString()
	{
		return "ChannelContext [Current State : "+curState+" Map : "+availableStates+"]"; 	
	}
	
	
	/*this method takes 1 parameter, which update the curState by checking if it is present in initialized map
	@param nextState -- next state needs to be update
	@return void
	@see just a function which updates the state
	*/
	public void setCurrentState(StateName nextState)
	{
		if(availableStates.containsKey(nextState))
		{
			curState = availableStates.get(nextState);
		}
	}

	/*this method returns the current state
	@param NA
	@return StateI -- next updated state
	@see just a function which returns te current state.
	*/
	public StateI getCurState() {
		return curState;
	}
	
	//descriptions of below methods provided in channelpopularity.state.AbstractState class
	
	public void addVideo(HashMap<String, List<Integer>> hmap, String videoName, List<Integer> list){ curState.addVideo(hmap, videoName, list);}
	
	public void removeVideo(HashMap<String, List<Integer>> hmap, String videoName){curState.removeVideo(hmap, videoName);}
	
	public int metrics(int views,int likes,int dislikes){ return curState.CalculatePopularityScore(views, likes, dislikes);}
	
	public String adRequest(int len) {return curState.adRequest(len);}
	
	
}
