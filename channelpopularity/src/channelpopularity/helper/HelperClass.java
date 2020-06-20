package channelpopularity.helper;
<<<<<<< HEAD
import channelpopularity.state.*;
=======
import channelpopularity.state.StateI;
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
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
<<<<<<< HEAD
			String outputString = null;
=======
			
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
			List<StateName> stateNamesList = new ArrayList<StateName>();
			List<String> videoList = new ArrayList<String>();
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
<<<<<<< HEAD
			StateI CurState;
=======
			StateI getTempCurState;
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
			
			while(null != line)
			{
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
<<<<<<< HEAD
=======
					//System.out.println(" "+line);
					
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
					videoName = line.split("::")[1];
					videoList.add(videoName);	
					videoCount++;
					
<<<<<<< HEAD
					
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
=======
					//System.out.println("in ADD video "+cc.getCurState());
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
<<<<<<< HEAD
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
=======
					System.out.println(" "+line);
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
<<<<<<< HEAD
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
					
					
					
=======
					
					System.out.println("in metrics "+temp);
					
					/*
					while(!videoList.isEmpty())
					{
						int i = 0;
						if(temp.equals(videoList.get(i)) == true)
						{
							System.out.println("Video present");
							break;
						}
						else
						{
							i++;
						}
			
					}
					*/
>>>>>>> de1ecfa9de996db55a7dc2a70bfc31379a5a8128
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
