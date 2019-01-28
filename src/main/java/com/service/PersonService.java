package com.service;

import com.dao.PersonDao;
import com.domain.Person;

/**
 * @author xsy
 * @create 2018-12-21 13:47
 * @desc
 **/
public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean update(int id, String name) {
        Person person = personDao.getPerson(id);
        if (person == null) {
            return false;
        }

        Person personUpdate = new Person(person.getId(), name);
        return personDao.update(personUpdate);
    }
}
