package cqu.reptile;

import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;




import java.util.regex.*;
import java.io.*;
import java.net.*;

public class Httpc {
	public boolean getpage(String url)
	{
		try 
		{
			final Pattern pattern = Pattern.compile("href=\"/|news\\.cqu\\.edu\\.cn|202\\.202\\.0\\.35");
			URL pageURL = new URL(url);
			       Parser parser = new Parser(pageURL.openConnection());     
			       NodeList nodeList = parser
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
			            	   			System.out.println(node.getText());   	   		
				            	   		return true;
			            	   		}

			            	   }
			               return false;
			             }

			           });
			       // 打印
			       for (int i = 0; i < nodeList.size(); i++)
			       {
			         LinkTag n = (LinkTag) nodeList.elementAt(i);
			         System.out.print(n.getStringText() + " ==>> ");
			         System.out.println(n.extractLink());
			       }
		} 
		catch (IOException | ParserException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}	      
}
