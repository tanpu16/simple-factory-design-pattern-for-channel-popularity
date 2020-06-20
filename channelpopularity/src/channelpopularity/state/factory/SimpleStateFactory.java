package channelpopularity.state.factory;
import channelpopularity.state.*;

public class SimpleStateFactory implements SimpleStateFactoryI {

	
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
		//System.out.println("in create "+createState);
		return createState;
	}
	
}


