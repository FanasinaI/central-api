package fanasina.group.fifacentralapi.service;

import fanasina.group.fifacentralapi.dto.ClubDto;
import fanasina.group.fifacentralapi.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ApiClientService {
    private final RestTemplate restTemplate;
    private final Map<String, String> apiKeys;

    @Autowired
    public ApiClientService(RestTemplate restTemplate,
                            @Value("#{${championship.api.keys}}") Map<String, String> apiKeys) {
        this.restTemplate = restTemplate;
        this.apiKeys = apiKeys;
    }

    public List<PlayerDto> fetchPlayers(String baseUrl, String championName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKeys.get(championName));

        ResponseEntity<PlayerDto[]> response = restTemplate.exchange(
                baseUrl + "/api/players",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                PlayerDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public List<ClubDto> fetchClubs(String baseUrl, String championName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKeys.get(championName));

        ResponseEntity<ClubDto[]> response = restTemplate.exchange(
                baseUrl + "/api/clubs",  // Correction de l'URL
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ClubDto[].class  // Correction du type de retour
        );
        return Arrays.asList(response.getBody());
    }
}