package fanasina.group.fifacentralapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApiConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Map<String, String> championshipUrls(
            @Value("${championship.urls.premier_league}") String plUrl,
            @Value("${championship.urls.la_liga}") String lligaUrl,
            @Value("${championship.urls.bundesliga}") String blUrl,
            @Value("${championship.urls.seria_a}") String saUrl,
            @Value("${championship.urls.ligue_1}") String l1Url) {

        Map<String, String> urls = new HashMap<>();
        urls.put("PREMIER_LEAGUE", plUrl);
        urls.put("LA_LIGA", lligaUrl);
        urls.put("BUNDESLIGA", blUrl);
        urls.put("SERIA_A", saUrl);
        urls.put("LIGUE_1", l1Url);
        return urls;
    }

    @Bean
    public Map<String, String> apiKeys(
            @Value("${championship.api.keys.premier_league}") String plKey,
            @Value("${championship.api.keys.la_liga}") String lligaKey,
            @Value("${championship.api.keys.bundesliga}") String blKey,
            @Value("${championship.api.keys.seria_a}") String saKey,
            @Value("${championship.api.keys.ligue_1}") String l1Key) {

        Map<String, String> keys = new HashMap<>();
        keys.put("PREMIER_LEAGUE", plKey);
        keys.put("LA_LIGA", lligaKey);
        keys.put("BUNDESLIGA", blKey);
        keys.put("SERIA_A", saKey);
        keys.put("LIGUE_1", l1Key);
        return keys;
    }
}