package channelpopularity.context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import channelpopularity.state.*;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ContextI{
	private StateI curState;
	private Map<StateName, StateI> availableStates;
	
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames)
	{
		availableStates = new HashMap<StateName, StateI>();
		availableStates.put(StateName.UNPOPULAR,stateFactoryIn.create(stateNames.get(0)));
		availableStates.put(StateName.MILDLY_POPULAR,stateFactoryIn.create(stateNames.get(1)));
		availableStates.put(StateName.HIGHLY_POPULAR,stateFactoryIn.create(stateNames.get(2)));
		availableStates.put(StateName.ULTRA_POPULAR,stateFactoryIn.create(stateNames.get(3)));
		curState = availableStates.get(StateName.UNPOPULAR);
	}
	
	public void setCurrentState(StateName nextState)
	{
		if(availableStates.containsKey(nextState))
		{
			curState = availableStates.get(nextState);
		}
	}

	public StateI getCurState() {
		return curState;
	}
	
	public void addVideo(HashMap<String, Integer> hmap, String videoName, int popularity){ curState.addVideo(hmap, videoName, popularity);}
	
	public void removeVideo(HashMap<String, Integer> hmap, String videoName){curState.removeVideo(hmap, videoName);}
	
	
	
	
}
