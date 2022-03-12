package com.example.demo.service;

import com.example.demo.exception.ServiceException;
import com.example.demo.model.Demo;
import com.example.demo.repository.DemoRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.exception.ErrorType.NOT_FOUND;


@Service
@AllArgsConstructor
public class DemoService {

    private final DemoRepository demoRepository;

    public Demo create(Demo demo) {
        return demoRepository.save(demo);
    }

    @Transactional
    public Demo update(Demo demo) {
        getById(demo.getId());
        return demoRepository.save(demo);
    }

    @Transactional
    public void deleteById(Long id) {
        getById(id);
        demoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Demo getById(Long id) {
        return demoRepository.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND, "exception.not-found.service.demo-object-not-found", id));
    }

    @Transactional(readOnly = true)
    public List<Demo> getAll() {
        return demoRepository.findAll();
    }


}
