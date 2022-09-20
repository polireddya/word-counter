package uk.co.synechron.wordcounter.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.synechron.wordcounter.service.WordCounter;

@ExtendWith(MockitoExtension.class)
class WordCounterControllerTest {

    @InjectMocks
    WordCounterController wordCounterController;

    @Mock
    WordCounter wordCounter;

    @Test
    public void testAddWordCounter() {
        String input ="synechron flower test counter test flower test";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(wordCounter.addWords(input)).thenReturn(getAddWordCounterResults());
        ResponseEntity responseEntity = wordCounterController.
                addWordCounter(input);

        Hashtable<String, Integer> wordsResults = (Hashtable<String, Integer>) responseEntity.getBody();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(wordsResults.size()).isEqualTo(4);


    }

    @Test
    public void testGetWordCount() {
        String input ="flower";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(wordCounter.getWordCounter(input)).thenReturn(2);
        int count = wordCounterController.getWordCount(input);

        assertThat(count).isEqualTo(2);


    }

    private Hashtable<String, Integer> getAddWordCounterResults() {
        Hashtable<String, Integer> results = new Hashtable<>();
        results.put("synechron", 1);
        results.put("flower", 2);
        results.put("test", 3);
        results.put("counter", 1);
        return results;
    }

}