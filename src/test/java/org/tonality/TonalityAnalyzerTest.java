package org.tonality;

import org.junit.jupiter.api.Test;
import org.tonality.dto.TonalityResponse;
import org.tonality.service.TonalityAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

class TonalityAnalyzerTest {

    private final TonalityAnalyzer analyzer = new TonalityAnalyzer();

    @Test
    void testPositiveSentiment() {
        TonalityResponse response = analyzer.analyze("I love this amazing service!");
        assertEquals("positive", response.getSentiment());
        assertTrue(response.getConfidence() > 0.5);
        assertEquals("I love this amazing service!", response.getAnalyzedText());
    }

    @Test
    void testNegativeSentiment() {
        TonalityResponse response = analyzer.analyze("I hate bad weather and terrible service");
        assertEquals("negative", response.getSentiment());
    }

    @Test
    void testNeutralSentiment() {
        TonalityResponse response = analyzer.analyze("The weather is ok today");
        assertEquals("neutral", response.getSentiment());
    }

    @Test
    void testEmptyText() {
        TonalityResponse response = analyzer.analyze("");
        assertNotNull(response.getSentiment());
    }

    @Test
    void testConfidenceRange() {
        TonalityResponse response = analyzer.analyze("Great!");
        double confidence = response.getConfidence();
        assertTrue(confidence >= 0.5 && confidence <= 1.0);
    }
}