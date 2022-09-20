package uk.co.synechron.wordcounter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.synechron.wordcounter.service.WordCounter;

import java.util.Hashtable;

@RestController
@Slf4j
@RequestMapping("/wordCounter")
public class WordCounterController {

    @Autowired
    WordCounter wordCounter;

    @PostMapping("/addWords")
    public ResponseEntity addWordCounter(@RequestParam String input) {
        log.info("addWordCounter method started and input words are {}", input);
        try {
            Hashtable<String, Integer> wordsResults = wordCounter.addWords(input);
            return ResponseEntity.ok(wordsResults);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/getCount")
    public int getWordCount(@RequestParam String input) {
        log.info("getWordCount method started and input words are {}", input);
        return wordCounter.getWordCounter(input);

    }
}
