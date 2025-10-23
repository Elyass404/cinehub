package com.cinehub.service;

import com.cinehub.model.Director;
import com.cinehub.respository.DirectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class DirectorService implements DirectorServiceInterface{

    DirectorRepository directorRepository;
    public DirectorService (DirectorRepository directorRepository){
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
    }

    @Override
    public Director findByName(String name){
        //todo : this method mzl madertch l code dyalha
        //categoryRepository.findByName(name);
        return null ;
    }
}
