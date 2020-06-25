package channelpopularity.helper;
import channelpopularity.state.StateI;

import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.context.ChannelContext;
import channelpopularity.operation.Operation;

public class HelperClass {
	
	int videoCount;
	int popularity;
	int totalScore;
	String videoName;
	boolean invalidInput, isVideoPresentAdd, isVideoPresentR, isDecrease, isEmptyFile; 
	
	public HelperClass()
	{
		videoCount = 0;
		popularity = 0;
		totalScore = 0;
		videoName="";
		invalidInput = false;
		isVideoPresentAdd = false;
		isVideoPresentR = false;
		isDecrease = false;
		isEmptyFile = false;
	}
	
	@Override
	public String toString()
	{
		return "HelperClass [videoCount : "+videoCount+" popularity : "+popularity+" totalScore : "+totalScore+" videoName : "+videoName+" invalidInput : "+invalidInput+" isVideoPresentAdd : "+isVideoPresentAdd+
			" isVideoPresentR : "+isVideoPresentR+" isDecrease : "+isDecrease+" isEmptyFile : "+isEmptyFile+"]"; 	
	}
	
	/*this is a helper method which takes instances of 2 classes used to perform operations on input.txt file.
	operations like fetching line by using poll() method of FileProcessor class, Store final result in
	store method of Result class.
	@param fp an instance of FileProcessor.java class from util package
	@param res an instance of Results.java class from util package
	@return void
	@see just an helper function for performing different operations on input.txt file like parse the input
	store videos, calculate metrics then store output and display exceptions
	*/
	public void InputParser(FileProcessor fp,Results res) throws IOException
	{
		try
		{
			String line = fp.poll();
			String outputString = null;
			List<StateName> stateNamesList = new ArrayList<StateName>();
			
			HashMap<String, List<Integer>> hmap = new HashMap<String,List<Integer>>(); 
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
			StateI CurState = cc.getCurState();
			
			if(null == line)
			{
				isEmptyFile = true;
			}
			
			while(null != line)
			{
				
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
					isVideoPresentAdd = false;
					videoName = line.split("::")[1];
					
					if(hmap.containsKey(videoName))
					{
						isVideoPresentAdd = true;
						break;
					}
					else
					{
						List<Integer> list = new ArrayList<Integer>();
						list.add(0);
						list.add(0);
						list.add(0);
						list.add(0);
						
						cc.addVideo(hmap, videoName, list);
						videoCount++;
						int score = 0;
				
						outputString = CurState.createOutputString(videoName,Operation.ADD_VIDEO);

						for(Map.Entry<String, List<Integer>> entry : hmap.entrySet())
						{
							
							List<Integer> lt = entry.getValue();
							score += lt.get(3);
						}
						
						popularity = score/videoCount;
						cc.setCurrentState(CurState.checkForUpdateState(popularity));
						CurState = cc.getCurState();
					
						res.store(outputString);
						res.store("\n");
					}
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
					isVideoPresentR = false;
					videoName = line.split("::")[1];
					videoCount--;
					
					int score = 0;
					
					
					if(hmap.containsKey(videoName))
					{
						cc.removeVideo(hmap, videoName);
					
						outputString = CurState.createOutputString(videoName,Operation.REMOVE_VIDEO);
						res.store(outputString);
						res.store("\n");
						for(Map.Entry<String, List<Integer>> entry : hmap.entrySet())
						{
							
							List<Integer> lt = entry.getValue();
							score += lt.get(3);
						}
						if(videoCount != 0)
						{
							popularity = score/videoCount;
						}
						cc.setCurrentState(CurState.checkForUpdateState(popularity));
						CurState = cc.getCurState();
					}
					else
					{
						isVideoPresentR = true;
						break;
					}
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					int views=Integer.parseInt(line.replaceAll("^.*VIEWS=|,.*$",""));
					int likes = Integer.parseInt(line.replaceAll("^.*,LIKES=|,.*$",""));
					int dislikes = Integer.parseInt(line.replaceAll("^.*DISLIKES=|].*$",""));
					isVideoPresentR = false;
					isDecrease = false;
					
					CurState = cc.getCurState();
					
					if(hmap.containsKey(temp))
					{
						
						int score = 0;
						
						List<Integer> values = hmap.get(temp);

						if(0 > (values.get(1) + likes) || 0 > (values.get(2) + dislikes))
						{
							isDecrease = true;
							break;
						}
						
						else
						{
							views += values.get(0);
							likes += values.get(1);
							dislikes += values.get(2);
							totalScore += values.get(3);
						
							totalScore = cc.metrics(views,likes,dislikes);
							
							List<Integer> list = new ArrayList<Integer>();
							list.add(views);
							list.add(likes);
							list.add(dislikes);
							list.add(totalScore);
								
							cc.addVideo(hmap, temp, list);
							
							for(Map.Entry<String, List<Integer>> entry : hmap.entrySet())
							{
								
								List<Integer> lt = entry.getValue();
								score += lt.get(3);
							}					
							
							popularity = score/videoCount;
							
							outputString = CurState.createOutputStringMetrics(score/videoCount);
						
							res.store(outputString);
							res.store("\n");
						
							cc.setCurrentState(CurState.checkForUpdateState(popularity));
							CurState = cc.getCurState();
						
						}
					}
					else
					{
						isVideoPresentR = true;
						break;
					}
					
					
				}
				else if(line.matches("^AD_REQUEST__[a-zA-Z0-9]+::LEN=[0-9]+$"))
				{
					isVideoPresentR = false;
					String temp=line.replaceAll("^.*__|::.*$","");
					int len=Integer.parseInt(line.replaceAll("^.*LEN=|$",""));
					
					if(hmap.containsKey(temp))
					{
						outputString = cc.adRequest(len);
						res.store(outputString);
						res.store("\n");
					}
					else
					{
						isVideoPresentR = true;
						break;
					}
				}
				else
				{
					invalidInput = true;
					break;
				}
				line = fp.poll();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
		try
		{
			if(isVideoPresentAdd)
			{
				throw new Exception("Video Already present at the time of Add video! Exiting!!!");
			}
			else if(isVideoPresentR)
			{
				throw new Exception("Video does not exist! Exiting!!!");
			}
			else if(invalidInput)
			{
				throw new Exception("Invalid Input! Exiting!!! 1.check if the views,likes and dislikes are Integer only or 2. check if views are not negative or 3.check if Add/Remove/Metrics/Ad formats in input file are valid ");
			}
			else if(isDecrease)
			{
				throw new Exception("Decrease in the number of likes or dislikes cannot be more than the total number of likes or dislikes received so far for the video! Exiting!!!");
			}
			else if(isEmptyFile)
			{
				throw new Exception("File is Empty! Exiting!!!");
			}
			
		}
		catch(Exception ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}
	

}
