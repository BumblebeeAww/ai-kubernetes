package org.tonality.dto;

public class TonalityResponse {
    private String sentiment;
    private double confidence;
    private String analyzedText;
    private String version = "1.0";

    public TonalityResponse() {}

    public TonalityResponse(String sentiment, double confidence, String analyzedText) {
        this.sentiment = sentiment;
        this.confidence = confidence;
        this.analyzedText = analyzedText;
    }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }

    public String getAnalyzedText() { return analyzedText; }
    public void setAnalyzedText(String analyzedText) { this.analyzedText = analyzedText; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}