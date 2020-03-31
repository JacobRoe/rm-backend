package io.remedymatch.angebot.api;

import io.remedymatch.artikel.api.ArtikelDTO;
import io.remedymatch.institution.api.InstitutionStandortDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AngebotDTO {

    private UUID id;

    @NotNull
    private ArtikelDTO artikel;

    @NotNull
    private double anzahl;

    @NotNull
    private InstitutionStandortDTO standort;

    @NotNull
    private String kommentar;

    private double rest;

    private UUID institutionId;

    private LocalDateTime haltbarkeit;

    private boolean steril;

    private boolean originalverpackt;

    private boolean medizinisch;

    private boolean bedient;

    private double entfernung;

}
