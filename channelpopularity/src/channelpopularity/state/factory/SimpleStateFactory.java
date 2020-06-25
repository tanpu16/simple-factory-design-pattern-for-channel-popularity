package channelpopularity.state.factory;
import channelpopularity.state.StateI;
import channelpopularity.state.HighlyPopularStateClass;
import channelpopularity.state.MildlyPopularStateClass;
import channelpopularity.state.UltraPopularStateClass;
import channelpopularity.state.UnpopularStateClass;
import channelpopularity.state.StateName;

public class SimpleStateFactory implements SimpleStateFactoryI {

	
	/*this method takes 1 parameter, used to initialize all states
	@param stateIn -- input state
	@return StateI -- initialized state out of 4
	@see just a function initialized all the states and return null if state does not present
	*/
	public StateI create(StateName stateIn)
	{
		StateI createState = null;
		
		if(stateIn == StateName.UNPOPULAR)
		{
			createState = new UnpopularStateClass();
		}
		else if(stateIn == StateName.MILDLY_POPULAR)
		{
			createState = new MildlyPopularStateClass();
		}
		else if(stateIn == StateName.HIGHLY_POPULAR)
		{
			createState = new HighlyPopularStateClass();
		}
		else if(stateIn == StateName.ULTRA_POPULAR)
		{
			createState = new UltraPopularStateClass();
		}	
		
		return createState;
	}
	
}


