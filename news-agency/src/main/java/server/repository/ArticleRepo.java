package server.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import common.model.Article;
import storage.ArticleStorageManager;

public class ArticleRepo {
	private List<Article> articles = new ArrayList<Article>();
	private final ArticleStorageManager articleStorageManager = new ArticleStorageManager();
	
	public ArticleRepo(String storagePath){
		loadArticles(storagePath);
	}


	public void loadArticles(String storagePath) {
		try {
			articles = articleStorageManager.loadArticlesFromFile(storagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void storeArticles(String storagePath){
		try {
			articleStorageManager.updateArticleFile(articles, storagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// WRITE 1 ARTICLE TO FILE
	public boolean add(Article article){
		articles.add(article);
		return true;
	}
	
	public Optional<Article> findById(int id){
		List<Article> matches = articles.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		if (matches.isEmpty()){
			return Optional.empty();
		}
		if (matches.size() > 1){
			System.out.println("[ArticleRepo] Multiple articles with the same id: " + id);
		}
		else{
			return Optional.of(matches.get(0));
		}
		return Optional.empty();
	}
	
	public List<Article> findByTitle(String title){
		return articles.stream().filter(u -> u.getTitle().equals(title)).collect(Collectors.toList());
	}
	
	public boolean deleteById(int id){
		List<Article> matches = articles.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		if (matches.isEmpty()){
			return false;
		}
		if (matches.size() > 1){
			System.out.println("[ArticleRepo] Multiple articles with the same title");
		}
		else{
			articles.removeIf(u -> u.getId() == id);
		}
		return false;
	}

	public List<Article> findAll() {
		return articles;
	}
}
