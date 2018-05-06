package common.model.builder;

import common.model.User;

import java.util.ArrayList;
import java.util.List;

import common.model.Article;

public class ArticleBuilder {
	private int id;
	private String title;
	private String articleAbstract;
	private int author;
	private String body;
	private List<Integer> relatedArticles;

	private ArticleBuilder(){}
	
	public static ArticleBuilder anArticle(){ return new ArticleBuilder();}
	
	public ArticleBuilder withId(int id){
		this.id = id;
		return this;
	}
	public ArticleBuilder withTitle(String title){
		this.title = title;
		return this;
	}
	public ArticleBuilder withArticleAbstract(String articleAbstract){
		this.articleAbstract = articleAbstract;
		return this;
	}
	public ArticleBuilder withAuthor(int author){
		this.author = author;
		return this;
	}
	
	public ArticleBuilder withBody(String body){
		this.body = body;
		return this;
	}
	
	public ArticleBuilder withRelatedArticles(List<Integer> relatedArticles){
		this.relatedArticles = relatedArticles;
		return this;
	}
	public Article build(){
		Article article = new Article();
		article.setId(this.id);
		article.setArticleAbstract(this.articleAbstract);
		article.setAuthor(this.author);
		article.setTitle(this.title);
		article.setBody(this.body);
		article.setRelatedArticles(relatedArticles);
		return article;
	}
}
