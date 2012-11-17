package cqu.reptile;
public class Reptile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Httpc httpc1=new Httpc("http://news.cqu.edu.cn/news/article/article_48156.html");
		Httpc httpc2=new Httpc("http://news.cqu.edu.cn/news/article/article_48156.html");
		Thread t1 = new Thread(httpc1);
		Thread t2 = new Thread(httpc2);
		//Repdatadao re=new Repdatadao();
		//re.inurl("aa", "bb");
		t1.start();
		t2.start();
	}

}
