package fanasina.group.fifacentralapi.controller;

import fanasina.group.fifacentralapi.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
public class SyncController {
    private final SyncService syncService;

    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @PostMapping
    public ResponseEntity<String> synchronize() {
        syncService.syncFromAllChampionships();
        return ResponseEntity.ok("Synchronization completed");
    }
}