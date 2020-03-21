package io.remedymatch.angebot.api;

import io.remedymatch.angebot.domain.AngebotService;
import io.remedymatch.person.domain.PersonRepository;
import io.remedymatch.web.UserNameProvider;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.remedymatch.angebot.api.AngebotMapper.mapToEntity;


@RestController
@AllArgsConstructor
@RequestMapping("/angebot")
public class AngebotController {

    private final AngebotService angebotService;
    private final PersonRepository personRepository;
    private final UserNameProvider userNameProvider;

    @GetMapping
    public ResponseEntity<List<AngebotDTO>> getAll() {
        val angebote = StreamSupport.stream(angebotService.alleAngeboteLaden().spliterator(), false)
                .map(AngebotMapper::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(angebote);
    }

    @PostMapping("/melden")
    public ResponseEntity<Void> angebotMelden(@RequestBody AngebotDTO angebot) {
        val user = personRepository.findByUserName(userNameProvider.getUserName());
        angebotService.angebotMelden(mapToEntity(angebot), user.getInstitution());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> angebotLoeschen(@PathVariable("id") String angebotId) {
        angebotService.angebotLoeschen(UUID.fromString(angebotId));
        return ResponseEntity.ok().build();
    }

}