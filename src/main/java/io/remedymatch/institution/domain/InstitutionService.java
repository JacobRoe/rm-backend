package io.remedymatch.institution.domain;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.remedymatch.geodaten.api.StandortService;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionStandortRepository institutionStandortRepository;
    private final StandortService standortService;


    public InstitutionEntity updateInstitution(InstitutionEntity institution) {
        val savedInstitution = institutionRepository.findById(institution.getId()).get();
        savedInstitution.setName(institution.getName());
        savedInstitution.setTyp(institution.getTyp());
        return institutionRepository.save(savedInstitution);
    }

    public InstitutionEntity updateHauptstandort(InstitutionEntity institution, InstitutionStandort  standort) {

        var longlatList = standortService.findePointsByAdressString(standort.getAdresse());

        if (longlatList == null || longlatList.size() == 0) {
            throw new IllegalArgumentException("Die Adresse konnte nicht aufgelöst werden");
        }

        standort.setLatitude(BigDecimal.valueOf(longlatList.get(0).getLatitude()));
        standort.setLongitude(BigDecimal.valueOf(longlatList.get(0).getLongitude()));

        standort = institutionStandortRepository.update(standort);
        institution.setHauptstandort(InstitutionStandortEntityConverter.convert(standort));
        return institutionRepository.save(institution);
    }

    public InstitutionEntity standortHinzufuegen(InstitutionEntity institution, InstitutionStandort  standort) {

        var longlatList = standortService.findePointsByAdressString(standort.getAdresse());

        if (longlatList == null || longlatList.size() == 0) {
            throw new IllegalArgumentException("Die Adresse konnte nicht aufgelöst werden");
        }

        standort.setLatitude(BigDecimal.valueOf(longlatList.get(0).getLatitude()));
        standort.setLongitude(BigDecimal.valueOf(longlatList.get(0).getLongitude()));

        standort = institutionStandortRepository.update(standort);
        institution.getStandorte().add(InstitutionStandortEntityConverter.convert(standort));
        return institutionRepository.save(institution);
    }

    public InstitutionEntity standortEntfernen(InstitutionEntity institution, UUID standortId) {
        var standort = institution.getStandorte().stream().filter(s -> s.getId().equals(standortId)).findFirst();
        if (standort.isEmpty()) {
            throw new IllegalArgumentException("Standort kann nicht gelöscht werden, da dieser nicht vorhanden ist.");
        }

        //TODO prüfen ob dieser Standort in Anfrage / Angebot / Bedarf verwendet wird. Wenn ja -> kann er nicht gelöscht werden

        institution.getStandorte().remove(standort.get());
        val inst = institutionRepository.save(institution);
        institutionStandortRepository.delete(new InstitutionStandortId(standort.get().getId()));
        return inst;
    }


}
