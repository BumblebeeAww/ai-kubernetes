package org.tonality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.tonality.dto.*;
import org.tonality.service.TonalityAnalyzer;

@SpringBootApplication
@RestController
public class TonalityApp {
    private final TonalityAnalyzer analyzer;

    public TonalityApp(TonalityAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @GetMapping("/health")
    public HealthResponse health() {
        return new HealthResponse("OK", "AI_Tonality", "1.0");
    }

    @GetMapping("/api/tonality")
    public TonalityResponse analyzeTonality(@RequestParam String text) {
        return analyzer.analyze(text);
    }

    @PostMapping("/api/tonality")
    public TonalityResponse analyzeTonalityPost(@RequestBody AnalysisRequest request) {
        return analyzer.analyze(request.getText());
    }

    @GetMapping("/")
    public String home() {
        return "AI Tonality Analysis Service is running! Use /api/tonality?text=your_text";
    }

    @GetMapping("/test")
    public String test() {
        return "DIRECT TEST - " + new java.util.Date();
    }

    public static void main(String[] args) {
        SpringApplication.run(TonalityApp.class, args);
    }
}