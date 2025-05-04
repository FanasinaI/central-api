package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.config.ChampionshipConfig;
import fanasina.group.fifacentralapi.dto.ClubDto;
import fanasina.group.fifacentralapi.dto.PlayerDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiClientService {
    private final RestTemplate restTemplate;
    private final ChampionshipConfig config;

    public ApiClientService(RestTemplate restTemplate, ChampionshipConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<PlayerDto> fetchPlayers(String baseUrl, String championName) {
        String apiKey = config.getApiKeys().get(championName.toUpperCase());
        if (apiKey == null) {
            throw new RuntimeException("Clé API non trouvée pour le championnat: " + championName);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<PlayerDto[]> response = restTemplate.exchange(
                    baseUrl + "/api/players",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    PlayerDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Échec de récupération des joueurs depuis " + baseUrl, e);
        }
    }

    public List<ClubDto> fetchClubs(String baseUrl, String championName) {
        String apiKey = config.getApiKeys().get(championName.toUpperCase());
        if (apiKey == null) {
            throw new RuntimeException("Clé API non trouvée pour le championnat: " + championName);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<ClubDto[]> response = restTemplate.exchange(
                    baseUrl + "/api/clubs",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    ClubDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Échec de récupération des clubs depuis " + baseUrl, e);
        }
    }
}