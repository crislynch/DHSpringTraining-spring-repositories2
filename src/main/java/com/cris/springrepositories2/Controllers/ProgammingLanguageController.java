package com.cris.springrepositories2.Controllers;


import com.cris.springrepositories2.Entities.ProgrammingLanguage;
import com.cris.springrepositories2.Repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProgammingLanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @PostMapping
    public ResponseEntity<ProgrammingLanguage> create(@RequestBody ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguage savedProg = languageRepository.save(programmingLanguage);
        return ResponseEntity.ok().body(savedProg);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getAll() {
        return languageRepository.count() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(languageRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> getProgLangById(@PathVariable(name = "id") Long id) {

        return languageRepository.findById(id).map(progLang -> ResponseEntity.ok().body(progLang))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> updateProgLang(@PathVariable(name = "id") Long id, @RequestParam String invetor) {
        return languageRepository.findById(id)
                .map(progLang -> {
                    progLang.setInventor(invetor);
                    ProgrammingLanguage updatedCar = languageRepository.save(progLang);
                    return ResponseEntity.ok().body(updatedCar);})
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgLang(@PathVariable Long id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProgLangs() {
        languageRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}