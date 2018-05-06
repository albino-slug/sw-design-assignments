package storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.model.Article;

public class ArticleStorageManager {
	
	// LOAD ALL ARTICLES FROM FILE
	public ArrayList<Article> loadArticlesFromFile(String filePath) throws IOException{
		FileReader file = new FileReader("articles.json");
		try{
			String articleJsons = new String(Files.readAllBytes(Paths.get(filePath)));
			
			Gson gson = new Gson();
			ArrayList<Article> articles = new ArrayList(Arrays.asList(gson.fromJson(articleJsons, Article[].class))); 
			System.out.println("[SUCCESS] read whole article file.");
			
			return articles;
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			file.close();
		}
		return null;
	}
	
	public void updateArticleFile(List<Article> articles, String filePath) throws IOException{
		FileWriter file = new FileWriter(filePath);
		try{
			Gson gson = new Gson();
			String uJson = gson.toJson(articles);
			file.write(uJson);
			System.out.println("[SUCCESS] updated article file.");

		} catch (IOException e){
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
	}

}
