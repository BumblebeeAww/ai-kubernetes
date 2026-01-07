package org.tonality.service;

import org.springframework.stereotype.Service;
import org.tonality.dto.TonalityResponse;

@Service
public class TonalityAnalyzer {
    public TonalityResponse analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new TonalityResponse("neutral", 0.5, text != null ? text : "");
        }

        String sentiment = classifySentiment(text.toLowerCase());
        double confidence = calculateConfidence(text, sentiment);

        return new TonalityResponse(sentiment, confidence, text);
    }

    private String classifySentiment(String text) {
        String[] positiveWords = {"happy", "good", "excellent", "great", "awesome", "perfect", "wonderful", "fantastic", "amazing", "love", "like"};
        String[] negativeWords = {"sad", "bad", "terrible", "awful", "horrible", "hate", "dislike", "angry", "upset", "worst", "disappointing"};

        int positiveScore = countKeywords(text, positiveWords);
        int negativeScore = countKeywords(text, negativeWords);

        if (positiveScore > negativeScore) {
            return "positive";
        } else if (negativeScore > positiveScore) {
            return "negative";
        } else {
            return "neutral";
        }
    }

    private int countKeywords(String text, String[] keywords) {
        int count = 0;
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                count++;
            }
        }
        return count;
    }

    private double calculateConfidence(String text, String sentiment) {
        double baseConfidence = 0.5;

        if (sentiment.equals("positive") || sentiment.equals("negative")) {
            baseConfidence += 0.3;
        }

        double lengthBonus = Math.min(text.length() / 100.0, 0.2);
        return Math.min(baseConfidence + lengthBonus + (Math.random() * 0.1), 1.0);
    }
}