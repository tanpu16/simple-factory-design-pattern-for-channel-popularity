package channelpopularity.helper;
import channelpopularity.state.*;

import channelpopularity.state.StateName;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.state.factory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import channelpopularity.context.ChannelContext;
import channelpopularity.operation.Operation;

public class HelperClass {
	
	int videoCount = 0;
	int popularity = 0;
	String videoName="";
	
	public void InputParser(FileProcessor fp,Results res)
	{
		try
		{
			String line = fp.poll();
			String outputString = null;
			List<StateName> stateNamesList = new ArrayList<StateName>();
			
			HashMap<String, Integer> hmap = new HashMap<String,Integer>(); 
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
			StateI CurState = cc.getCurState();
			
			while(null != line)
			{
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
					videoName = line.split("::")[1];
					
					//hmap.put(videoName,0);	
					cc.addVideo(hmap, videoName, 0);
					videoCount++;
					int score = 0;
					
					
					outputString = CurState.createOutputString(videoName,Operation.ADD_VIDEO);
					for(int i: hmap.values() )
					{
						score +=i; 
					}
					popularity = score/videoCount;
					cc.setCurrentState(CurState.checkForUpdateState(popularity));
					CurState = cc.getCurState();
					
					res.store(outputString);
					res.store("\n");
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
					videoName = line.split("::")[1];
					videoCount--;
					
					int score = 0;
					
					//hmap.remove(videoName);
					
					cc.removeVideo(hmap, videoName);
					
					outputString = CurState.createOutputString(videoName,Operation.ADD_VIDEO);
					res.store(outputString);
					res.store("\n");
					for(int i: hmap.values() )
					{
						score +=i; 
					}
					if(videoCount != 0)
					{
						popularity = score/videoCount;
					}
					cc.setCurrentState(CurState.checkForUpdateState(popularity));
					CurState = cc.getCurState();
					
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					int views=Integer.parseInt(line.replaceAll("^.*VIEWS=|,.*$",""));
					int likes = Integer.parseInt(line.replaceAll("^.*,LIKES=|,.*$",""));
					int dislikes = Integer.parseInt(line.replaceAll("^.*DISLIKES=|].*$",""));
					
					
					
					if(hmap.containsKey(temp))
					{
						
						int score = 0;
						if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
						{
							
							CurState = new UnpopularStateClass();
							
							int totalScore = CurState.CalculatePopularityScore(views,likes,dislikes);
							
							if(hmap.get(temp) != 0)
							{
								totalScore += hmap.get(temp);
							}
							
							//hmap.put(temp,totalScore);
							
							cc.addVideo(hmap, temp, totalScore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							
						
							popularity = score/videoCount;
							
							
						}
						else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
						{
							
							
							CurState = new MildlyPopularStateClass();
							int totalScore = CurState.CalculatePopularityScore(views,likes,dislikes);
							
							if(hmap.get(temp) != 0)
							{
								totalScore += hmap.get(temp);
							}
							
							//hmap.put(temp,totalScore);
							cc.addVideo(hmap, temp, totalScore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}

							popularity = score/videoCount;
							
						}
						else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
						{
							
							
							CurState = new HighlyPopularStateClass();
							int totalScore = CurState.CalculatePopularityScore(views,likes,dislikes);
							
							if(hmap.get(temp) != 0)
							{
								totalScore += hmap.get(temp);
							}
							
							//hmap.put(temp,totalScore);
							cc.addVideo(hmap, temp, totalScore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							
							popularity = score/videoCount;
							
						}
						else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
						{
							
							
							CurState = new UltraPopularStateClass();
							int totalScore = CurState.CalculatePopularityScore(views,likes,dislikes);
							
							if(hmap.get(temp) != 0)
							{
								totalScore += hmap.get(temp);
							}
							
							//hmap.put(temp,totalScore);
							cc.addVideo(hmap, temp, totalScore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							
							popularity = score/videoCount;

							
						}
						outputString = CurState.createOutputStringMetrics(score/videoCount);
						cc.setCurrentState(CurState.checkForUpdateState(popularity));
						CurState = cc.getCurState();
						res.store(outputString);
						res.store("\n");
					}
					else
					{
						
						System.out.println("Video does not present");
						System.exit(0);
					}
					
					
					
				}
				else if(line.matches("^AD_REQUEST__[a-zA-Z0-9]+::LEN=[0-9]+$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					int len=Integer.parseInt(line.replaceAll("^.*LEN=|$",""));
					
					outputString = CurState.createOutputStringAdRequest(len);
					res.store(outputString);
					res.store("\n");
				}
				else
				{
					System.out.println("Format is invalid");
					System.exit(0);
				}
				line = fp.poll();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception!!!");
			e.printStackTrace();
		}
	}

}
