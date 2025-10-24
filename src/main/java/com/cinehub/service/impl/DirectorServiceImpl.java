package com.cinehub.service.impl;

import com.cinehub.model.Director;
import com.cinehub.repository.DirectorRepository;
import com.cinehub.service.DirectorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class DirectorServiceImpl implements DirectorService {

    DirectorRepository directorRepository;
    public DirectorServiceImpl(DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    @Override
    @Transactional
    public Director save(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new  NoSuchElementException("There is no Director with the Id: "+ id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!directorRepository.existsById(id)){
            throw new NoSuchElementException("There is no director found with the id: " + id +" to be deleted");
        }

        directorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Director update(Long id, Director newDirector){
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no Director with the following id:" + id  + " to be updated!"));

        director.setFirstName(newDirector.getFirstName());
        director.setLastName(newDirector.getLastName());
        director.setNationality(newDirector.getNationality());
        director.setBirthDate(newDirector.getBirthDate());
        director.setBiography(newDirector.getBiography());

        //now saving the new information for the director
        return directorRepository.save(director);
    }

    @Override
    public Director findByName(String name){
        //todo : this method mzl madertch l code dyalha
        //categoryRepository.findByName(name);
        return null ;
    }
}
