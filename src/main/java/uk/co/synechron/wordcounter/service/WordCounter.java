package uk.co.synechron.wordcounter.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

@Service
@Slf4j
public class WordCounter {

    private static final Hashtable<String, Integer> worldCountTable = new Hashtable<>();

    private static final String nonAlphanumeric = "^[a-zA-Z0-9]*$";


    public Hashtable<String, Integer> addWords(String words) {
        log.info("addWords method started and input is {}",words);

        List<String> wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(words.split("\\W+")));

        wordsList.forEach(word -> {
            if (word.matches(nonAlphanumeric)) {
                //we are storing the words into hash table, requirement is not clear
                // about case-sensitive results need to display or not so converting as lower case
                String englishWord = new Translator().translate(word).toLowerCase();

                // worldCountTable ia the static because we are storing the values ,
                // requirement say we need to expose as rest service so if we need to search any specific word we need to store it
                // so i have created as static it will return if any word is added earlier
                if (worldCountTable.containsKey(englishWord)) {
                    worldCountTable.put(englishWord, worldCountTable.get(englishWord) + 1);
                } else {
                    worldCountTable.put(englishWord, 1);
                }
            }
        });
        log.info("addWords method finished and output is {}",worldCountTable);
        return worldCountTable;
    }

    public int getWordCounter(String word) {
        log.info("getWordCounter method started and output is {}",word);
        String englishWord = new Translator().translate(word).toLowerCase();
        Integer wordCount = worldCountTable.get(englishWord);
        log.info("getWordCounter method finished");
        return wordCount != null? wordCount : 0;
    }

}
