package com.demo.web.service;

import cc.cleverfan.web.annotation.Service;
import com.demo.web.entity.Person;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    public List<Person> getPersons(){
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setName("Zhang San");
        person.setTelephone("13565654747");
        person.setEmail("zsan@exmaple.com");
        persons.add(person);
        Person person1 = new Person();
        person1.setName("Li Si");
        person1.setTelephone("13589651478");
        person1.setEmail("lsi@exmaple.com");
        persons.add(person1);
        return persons;
    }
    
}
