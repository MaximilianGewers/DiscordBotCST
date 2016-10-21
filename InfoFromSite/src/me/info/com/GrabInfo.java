package me.info.com;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GrabInfo {

	public static void GetList(String url, String source) throws Exception{
        Document document = Jsoup.connect(url).get();
        Elements titles = null, prices = null;
        if(source.equalsIgnoreCase("steam")){
	        titles = document.select(".game_area_purchase_game h1");
	        if(document.select(".discount_final_price").size() <= 0){
	        	prices = document.select(".price");
	        }else{
	        	prices = document.select(".discount_final_price");
	        }
        }
		int i = 0;
        for (Element title : titles) {
                System.out.println(title.text() + ": " + prices.get(i).text());
                i++;
        }
		return;
	}
	
    public static void main(String[] args) throws Exception {
	        String url = "http://store.steampowered.com/app/440/";
	        GetList(url, "steam");
	      /* Elements obj = Jsoup.connect("http://store.steampowered.com/app/289070/").get().select(".game_purchase_action_bg");
	        for (Element objs : obj){
	        	//checks if text contains $
	        	if(objs.text().contains("$")){
	        		// prints lines that contain $
	        		System.out.println(objs.text());
	        	}
	        }*/
    }

}