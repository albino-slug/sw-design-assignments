package storage;

import common.model.Role;
import common.model.builder.ArticleBuilder;
import common.model.builder.UserBuilder;
import server.repository.ArticleRepo;
import server.repository.UserRepo;

public class PopulateFiles {

	public static void main(String[] args) {
		UserRepo userRepo = new UserRepo("users.json");
		ArticleRepo articleRepo = new ArticleRepo("articles.json");
		userRepo.add(UserBuilder.anUser()
				.withId(1)
				.withPassword("asdf")
				.withRole(Role.READER)
				.withUsername("user1")
				.build());
		userRepo.add(UserBuilder.anUser()
				.withId(4)
				.withPassword("asdf")
				.withRole(Role.WRITER)
				.withUsername("user4")
				.build());
		userRepo.add(UserBuilder.anUser()
				.withId(5)
				.withPassword("asdf")
				.withRole(Role.ADMIN)
				.build());
		userRepo.storeUsers("users.json");
		
//		articleRepo.add(ArticleBuilder.anArticle()
//				.withId(1)
//				.withArticleAbstract("abstract for article 1")
//				.withAuthor(2)
//				.withBody("body for article 1")
//				.withTitle("title for article 1")
//				.build());
//		articleRepo.add(ArticleBuilder.anArticle()
//				.withId(2)
//				.withArticleAbstract("abstract for article 2")
//				.withAuthor(2)
//				.withBody("body for article 2")
//				.withTitle("title for article 2")
//				.build());
//		articleRepo.add(ArticleBuilder.anArticle()
//				.withId(3)
//				.withArticleAbstract("abstract for article 3")
//				.withAuthor(4)
//				.withBody("body for article 3")
//				.withTitle("title for article 3")
//				.build());
//		articleRepo.add(ArticleBuilder.anArticle()
//				.withId(4)
//				.withArticleAbstract("abstract for article 4")
//				.withAuthor(4)
//				.withBody("body for article 4")
//				.withTitle("title for article 4")
//				.build());
//		articleRepo.storeArticles("articles.json");
	}

}
