package cqu.reptile;

import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;




import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class Httpc {
	public static HashSet urlset;
	public Httpc()
	{
		urlset=new HashSet();
	}
	public NodeList getli(String url,final Pattern pattern )
	{
	   
		NodeList nodeList=null;
		  try {
				URL pageURL = new URL(url);
			     Parser parser = new Parser(pageURL.openConnection());  
			 nodeList = parser
			           .extractAllNodesThatMatch(new NodeFilter()
			           {
			             // 实现该方法,用以过滤标签
			             public boolean accept(Node node)
			             {
			               if (node instanceof LinkTag)// 标记
			            	   {  
			            	   		Matcher matcher = pattern.matcher(node.getText());
			            	   		if(matcher.find())
			            	   		{	   		
				            	   		return true;
			            	   		}
			            	   }
			               return false;
			             }

			           });
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return nodeList;
	}
	public boolean startpage(String url)
	{
	
			if(urlset.size()>=100)
			{
				return false;
			}
			 
			       // 打印
			       List urlli=new ArrayList();
			       urlli.add(url);
			       getpage(urlli);
			      
		return true;
	}
	public List getpage(List urlli)
	{
		if(urlset.size()>=1000)
		{
			return null;
		}
		final Pattern pattern = Pattern.compile("href=\"/|news\\.cqu\\.edu\\.cn|202\\.202\\.0\\.35");  
		List reurlli=new ArrayList();
		for(int i=0;i<urlli.size();i++)
		{
			 NodeList nodeList = getli((String)urlli.get(i),pattern);
			 for (int j = 0; j < nodeList.size(); j++)
		       {
		         LinkTag n = (LinkTag) nodeList.elementAt(j);
		         String nexturl=n.extractLink();
		         if(urlset.add(nexturl))
		         {
		        	 System.out.print(n.getStringText() + " ==>> ");
			         System.out.println(nexturl);
			         reurlli.add(nexturl);
		         }
		       }
		}
		return getpage(reurlli);
	}
}
