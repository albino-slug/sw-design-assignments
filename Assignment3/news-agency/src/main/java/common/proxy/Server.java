package common.proxy;

import java.util.List;
import java.util.Optional;

import common.model.Article;
import common.model.User;

public interface Server {
	public User login(String username, String password);
	public List<User> findAllUsers();
	//public void deleteById(int id);
	public List<Article> findAllArticles();
}
