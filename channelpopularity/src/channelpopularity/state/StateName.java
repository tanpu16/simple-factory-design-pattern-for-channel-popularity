package channelpopularity.state;

public enum StateName {

	UNPOPULAR(0,1000),
	MILDLY_POPULAR(1001,10000),
	HIGHLY_POPULAR(10001,100000),
	ULTRA_POPULAR(100001,Integer.MAX_VALUE);
	
	int minPopularity,maxPopularity;
	
	StateName(int minp,int maxp)
	{
		minPopularity = minp;
		maxPopularity = maxp;
	}
}
