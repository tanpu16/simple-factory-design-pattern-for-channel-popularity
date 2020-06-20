package channelpopularity.helper;
import channelpopularity.state.*;
import channelpopularity.state.StateName;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.state.factory.*;
import java.lang.Object;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;

public class HelperClass {
	
	int videoCount=0;
	String videoName="";
	
	public void InputParser(FileProcessor fp,Results res)
	{
		try
		{
			String line = fp.poll();
			String outputString = null;
			List<StateName> stateNamesList = new ArrayList<StateName>();
			List<String> videoList = new ArrayList<String>();
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
			StateI CurState;
			
			while(null != line)
			{
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
					videoName = line.split("::")[1];
					videoList.add(videoName);	
					videoCount++;
					
					
					if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
					{
						UnpopularStateClass UC = new UnpopularStateClass();
						outputString = UC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
					{
						MildlyPopularStateClass MC = new MildlyPopularStateClass();
						outputString = MC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
					{
						HighlyPopularStateClass HC = new HighlyPopularStateClass();
						outputString = HC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
					{
						UltraPopularStateClass UTC = new UltraPopularStateClass();
						outputString = UTC.createOutputString(videoName);
					}
					
					res.store(outputString);
					res.store("\n");
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
					//System.out.println("count before remove"+videoList.size());
					//System.out.println(" "+line);
					videoName = line.split("::")[1];
					videoCount--;
					videoList.remove(videoName);
					if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
					{
						UnpopularStateClass UC = new UnpopularStateClass();
						outputString = UC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
					{
						MildlyPopularStateClass MC = new MildlyPopularStateClass();
						outputString = MC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
					{
						HighlyPopularStateClass HC = new HighlyPopularStateClass();
						outputString = HC.createOutputString(videoName);
					}
					else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
					{
						UltraPopularStateClass UTC = new UltraPopularStateClass();
						outputString = UTC.createOutputString(videoName);
					}
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					int views=Integer.parseInt(line.replaceAll("^.*VIEWS=|,.*$",""));
					int likes = Integer.parseInt(line.replaceAll("^.*,LIKES=|,.*$",""));
					int dislikes = Integer.parseInt(line.replaceAll("^.*DISLIKES=|].*$",""));
					
					//System.out.println("in metrics "+temp+" "+views+" "+likes+" "+dislikes);
					
					if(videoList.contains(temp))
					{
						System.out.println("present");
						if(cc.getCurState().toString().matches("^.*UnpopularState.*$"))
						{
							CurState = new UnpopularStateClass();
							int count = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							//outputString = UC.createOutputString(videoName);
						}
						else if(cc.getCurState().toString().matches("^.*MildlyPopularState.*$"))
						{
							CurState = new MildlyPopularStateClass();
							int count = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							//outputString = MC.createOutputString(videoName);
						}
						else if(cc.getCurState().toString().matches("^.*HighlyPopularState.*$"))
						{
							CurState = new HighlyPopularStateClass();
							int count = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							//outputString = HC.createOutputString(videoName);
						}
						else if(cc.getCurState().toString().matches("^.*UltraPopularState.*$"))
						{
							CurState = new UltraPopularStateClass();
							int count = CurState.CalculatePopularityScore(views,likes,dislikes,videoCount);
							//outputString = UTC.createOutputString(videoName);
						}
					}
					else
					{
						System.out.println("Video does not present");
						System.exit(0);
					}
					
					
					
				}
				else if(line.matches("^AD_REQUEST__[a-zA-Z0-9]+::LEN=[0-9]+$"))
				{
					System.out.println(" "+line);
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
