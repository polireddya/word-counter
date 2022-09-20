package uk.co.synechron.wordcounter.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Hashtable;


@ExtendWith(MockitoExtension.class)
class WordCounterTest {

    @InjectMocks
    WordCounter wordCounter;

    @Test
    public void testAddWords() {
       String input ="synechron flower test counter test flower test";
        Hashtable<String,Integer> wordsList = wordCounter.addWords(input);
        assertThat(wordsList.size()).isEqualTo(4);
    }


    @Test
    public void testGetWordCounter() {
        String input ="test";
        int count = wordCounter.getWordCounter(input);
        assertThat(count).isEqualTo(3);
    }

}