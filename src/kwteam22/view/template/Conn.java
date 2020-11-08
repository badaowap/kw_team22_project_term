package kwteam22.view.template;

import java.util.List;

import javax.persistence.Query;

public interface Conn<T> {

	void getConnection();

	List<T> query(Query query, String sql);

	void add(T obj);

	void modify(Object ... obj);

	void remove(T obj , String primaryKey);

}
