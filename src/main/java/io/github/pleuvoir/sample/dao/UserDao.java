package io.github.pleuvoir.sample.dao;

import io.github.pleuvoir.annotation.Param;
import io.github.pleuvoir.sample.model.User;

public interface UserDao {

	User selectByName(@Param("ename") String name);
}
