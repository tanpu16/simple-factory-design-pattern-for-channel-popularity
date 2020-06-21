package channelpopularity.helper;
import channelpopularity.state.*;

import channelpopularity.state.StateName;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.state.factory.*;
import java.lang.Object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;

public class HelperClass {
	
	int videoCount = 0;
	int totalScore = 0;
	String videoName="";
	
	public void InputParser(FileProcessor fp,Results res)
	{
		try
		{
			String line = fp.poll();
			String outputString = null;
			List<StateName> stateNamesList = new ArrayList<StateName>();
			List<String> videoList = new ArrayList<String>();
			List<Integer> videoScore = new ArrayList<Integer>();
			
			HashMap<String, Integer> hmap = new HashMap<String,Integer>(); 
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
			StateI CurState = null;
			
			while(null != line)
			{
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
					videoName = line.split("::")[1];
					hmap.put(videoName,0);	
					videoCount++;
					
					
					if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
					{
						UnpopularStateClass UC = new UnpopularStateClass();
						outputString = UC.createOutputString(videoName,Operation.ADD_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
					{
						MildlyPopularStateClass MC = new MildlyPopularStateClass();
						outputString = MC.createOutputString(videoName,Operation.ADD_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
					{
						HighlyPopularStateClass HC = new HighlyPopularStateClass();
						outputString = HC.createOutputString(videoName,Operation.ADD_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
					{
						UltraPopularStateClass UTC = new UltraPopularStateClass();
						outputString = UTC.createOutputString(videoName,Operation.ADD_VIDEO);
					}
					
					System.out.println("   "+outputString);
					res.store(outputString);
					res.store("\n");
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
					//System.out.println("count before remove"+videoList.size());
					//System.out.println(" "+line);
					videoName = line.split("::")[1];
					videoCount--;
					hmap.remove(videoName);
					if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
					{
						UnpopularStateClass UC = new UnpopularStateClass();
						outputString = UC.createOutputString(videoName,Operation.REMOVE_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
					{
						MildlyPopularStateClass MC = new MildlyPopularStateClass();
						outputString = MC.createOutputString(videoName,Operation.REMOVE_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
					{
						HighlyPopularStateClass HC = new HighlyPopularStateClass();
						outputString = HC.createOutputString(videoName,Operation.REMOVE_VIDEO);
					}
					else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
					{
						UltraPopularStateClass UTC = new UltraPopularStateClass();
						outputString = UTC.createOutputString(videoName,Operation.REMOVE_VIDEO);
					}
					
					//System.out.println("video reomed "+videoName+" "+hmap);
					System.out.println("   "+outputString);
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					int views=Integer.parseInt(line.replaceAll("^.*VIEWS=|,.*$",""));
					int likes = Integer.parseInt(line.replaceAll("^.*,LIKES=|,.*$",""));
					int dislikes = Integer.parseInt(line.replaceAll("^.*DISLIKES=|].*$",""));
					
					//System.out.println("in metrics "+temp+" "+views+" "+likes+" "+dislikes);
					
					if(hmap.containsKey(temp))
					{
						//System.out.println("present");
						int score =0;
						if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
						{
							//System.out.println("unpopular metrics "+score);
							CurState = new UnpopularStateClass();
							int tempscore = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							
							if(hmap.get(temp) != 0)
							{
								tempscore += hmap.get(temp);
							}
							
							hmap.put(temp,tempscore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							
						
							//System.out.println(hmap);
							cc.setCurrentState(CurState.checkForUpdateState(score));
							//System.out.println("now state is "+CurState+" Score is "+score+" video is : "+temp);
							
						}
						else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
						{
							//System.out.println("mild metrics "+score);
							
							CurState = new MildlyPopularStateClass();
							int tempscore = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							
							if(hmap.get(temp) != 0)
							{
								tempscore += hmap.get(temp);
							}
							
							hmap.put(temp,tempscore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							//System.out.println(hmap);
							cc.setCurrentState(CurState.checkForUpdateState(score));
							//System.out.println("now state is "+CurState+" Score is "+score+" video is : "+temp);
							
						}
						else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
						{
							//System.out.println("high metrics "+score);
							
							CurState = new HighlyPopularStateClass();
							int tempscore = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							
							if(hmap.get(temp) != 0)
							{
								tempscore += hmap.get(temp);
							}
							
							hmap.put(temp,tempscore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							//System.out.println(hmap);
							cc.setCurrentState(CurState.checkForUpdateState(score));
							//System.out.println("now state is "+CurState+" Score is "+score+" video is : "+temp);
							
						}
						else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
						{
							//System.out.println("ultra metrics "+score);
							
							CurState = new UltraPopularStateClass();
							int tempscore = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							
							if(hmap.get(temp) != 0)
							{
								tempscore += hmap.get(temp);
							}
							
							hmap.put(temp,tempscore);
							
							for(int i: hmap.values() )
							{
								score +=i; 
							}
							//System.out.println(hmap);
							cc.setCurrentState(CurState.checkForUpdateState(score));
							//System.out.println("now state is "+CurState+" Score is "+score+" video is : "+temp);
							
						}
						outputString = CurState.createOutputStringMetrics(score);
						System.out.println(" "+outputString);
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
					
					if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
					{
						UnpopularStateClass UC = new UnpopularStateClass();
						outputString = UC.createOutputStringAdRequest(len);
					}
					else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
					{
						MildlyPopularStateClass MC = new MildlyPopularStateClass();
						outputString = MC.createOutputStringAdRequest(len);
					}
					else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
					{
						HighlyPopularStateClass HC = new HighlyPopularStateClass();
						outputString = HC.createOutputStringAdRequest(len);
					}
					else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
					{
						UltraPopularStateClass UTC = new UltraPopularStateClass();
						outputString = UTC.createOutputStringAdRequest(len);
					}
					
					System.out.println(" "+outputString);
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
