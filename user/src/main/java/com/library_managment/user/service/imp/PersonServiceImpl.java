package com.library_managment.user.service.imp;

import com.library_managment.user.model.entity.Person;
import com.library_managment.user.repository.PersonRepository;
import com.library_managment.user.service.api.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
