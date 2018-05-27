package server;

import java.util.List;
import java.util.Optional;

import common.model.Article;
import common.model.User;
import common.model.builder.UserBuilder;
import common.proxy.Server;
import server.repository.ArticleRepo;
import server.repository.UserRepo;

public class ServerImpl implements Server {

	private UserRepo    userRepo;
	private ArticleRepo articleRepo;
	
	public ServerImpl(UserRepo userRepo, ArticleRepo articleRepo){
		this.userRepo = userRepo;
		this.articleRepo = articleRepo;
	}
	
	@Override
	public User login(String username, String password) {
		Optional<User> optionalUser = userRepo.findByUsernameAndPassword(username, password);
		User response = UserBuilder.anUser().withId(-1).build();
		if (optionalUser.isPresent()){
			response = optionalUser.get();
		}
		System.out.println("[Server] login called with " + username + "s " + password);
		return response;
	}

	@Override
	public List<User> findAllUsers() {
		System.out.println("[Server] findall users called ");
		return userRepo.findAll();
	}

	@Override
	public List<Article> findAllArticles() {
		System.out.println("[Server] findall articles called ");
		return articleRepo.findAll();
	}

//	@Override
//	public void deleteById(int id) {
//		System.out.println("[Server] delete by ID called ");
//		userRepo.deleteById(id);
//	}

}
