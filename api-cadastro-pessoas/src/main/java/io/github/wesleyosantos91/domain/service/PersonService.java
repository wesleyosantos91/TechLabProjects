package io.github.wesleyosantos91.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.core.mapper.PersonMapper;
import io.github.wesleyosantos91.domain.entity.Person;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.repository.PersonRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public Person save(Person person) {
        return repository.save(person);
    }

    @Transactional
    public Person update(String id, Person person) {

        final Person personSaved = findById(id);
        return repository.save(mapper.toEntity(person, personSaved));
    }

    @Transactional
    public void delete(String id) {

        final Person person = findById(id);
        repository.delete(person);
    }

    public Page<Person> search(Person entity, Pageable page) {

        final Example<Person> personExample = Example.of(entity);
        return repository.findAll(personExample, page);
    }

    public Person findById(String id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found {0} registry with code {1}", Person.class.getSimpleName(), id)));
    }
}
