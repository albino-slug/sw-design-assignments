package common.model;

import java.util.ArrayList;
import java.util.List;

public class Article{
	private int id;
	private String title;
	private String articleAbstract;
	private int author;
	private String body;
	private List<Integer> relatedArticles = new ArrayList<Integer>();

	public Article(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public String getBody() {
		return body.toString();
	}


	public void setBody(String body) {
		this.body = body;
	}


	public List<Integer> getRelatedArticles() {
		return relatedArticles;
	}
	
	public List<Integer> setRelatedArticles(List<Integer> relatedArticles) {
		return relatedArticles = relatedArticles;
	}


}
