package channelpopularity.helper;
import channelpopularity.state.StateI;
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
			
			List<StateName> stateNamesList = new ArrayList<StateName>();
			List<String> videoList = new ArrayList<String>();
			
			stateNamesList.add(StateName.UNPOPULAR);
			stateNamesList.add(StateName.MILDLY_POPULAR);
			stateNamesList.add(StateName.HIGHLY_POPULAR);
			stateNamesList.add(StateName.ULTRA_POPULAR);
			
			SimpleStateFactoryI ssf = new SimpleStateFactory();
			ChannelContext cc = new ChannelContext(ssf,stateNamesList);
			StateI getTempCurState;
			
			while(null != line)
			{
				if(line.matches("^ADD_VIDEO::[a-zA-Z0-9]+$"))
				{
					//System.out.println(" "+line);
					
					videoName = line.split("::")[1];
					videoList.add(videoName);	
					videoCount++;
					
					//System.out.println("in ADD video "+cc.getCurState());
					
				}
				else if(line.matches("^REMOVE_VIDEO::[a-zA-Z0-9]+$"))
				{
					System.out.println(" "+line);
				}
				else if(line.matches("^METRICS__[a-zA-Z0-9]+::\\[VIEWS=[0-9]+,LIKES=-?[0-9]+,DISLIKES=-?[0-9]+\\]$"))
				{
					String temp=line.replaceAll("^.*__|::.*$","");
					
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
