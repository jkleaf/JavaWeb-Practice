package org.utils;

public class DaoFactory {

	private static final DaoFactory daoFactory = new DaoFactory();

	public static DaoFactory getInstance() {
		return daoFactory;
	}

	@SuppressWarnings("unchecked")
	public <T> T createDao(String className, Class<T> clazz) {
		try {
			T t = (T) Class.forName(className).newInstance();
			return t;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException();// no need to return NULL
		}
	}

}
