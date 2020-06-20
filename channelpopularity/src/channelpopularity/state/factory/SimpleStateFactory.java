package channelpopularity.state.factory;
import channelpopularity.state.*;

public class SimpleStateFactory implements SimpleStateFactoryI {

	
	public StateI create(StateName stateIn)
	{
		StateI createState = null;
		
		if(stateIn == StateName.UNPOPULAR)
		{
<<<<<<< HEAD
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
=======
			createState = new UnpopularClass();
		}
		else if(stateIn == StateName.MILDLY_POPULAR)
		{
			createState = new MildlyPopularClass();
		}
		else if(stateIn == StateName.HIGHLY_POPULAR)
		{
			createState = new HighlyPopularClass();
		}
		else if(stateIn == StateName.ULTRA_POPULAR)
		{
			createState = new UltraPopularClass();
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
		}	
		//System.out.println("in create "+createState);
		return createState;
	}
	
}


