package com.dao;

import com.domain.Person;

/**
 * @author xsy
 * @create 2018-12-21 13:47
 * @desc
 **/
public interface PersonDao {

    Person getPerson(int id);

    boolean  update(Person person);
}
