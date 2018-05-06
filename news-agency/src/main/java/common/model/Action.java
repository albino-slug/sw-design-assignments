package common.model;

public class Action {//TODO rename as Request
	@Override
	public String toString() {
		return "Action [type=" + type + ", arg=" + arg + "]";
	}
	private ActionType type;
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	private Article article;
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	public Action(ActionType type) {
		super();
		this.type = type;
	}

	public Object getArg() {
		return arg;
	}
	public void setArg(Object arg) {
		this.arg = arg;
	}
	private Object arg;
	
}
