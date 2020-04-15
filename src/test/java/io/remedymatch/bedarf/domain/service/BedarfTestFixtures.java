package io.remedymatch.bedarf.domain.service;

import java.math.BigDecimal;
import java.util.UUID;

import io.remedymatch.artikel.domain.service.ArtikelTestFixtures;
import io.remedymatch.bedarf.domain.model.Bedarf;
import io.remedymatch.bedarf.domain.model.BedarfId;
import io.remedymatch.bedarf.infrastructure.BedarfEntity;
import io.remedymatch.institution.domain.model.Institution;
import io.remedymatch.institution.domain.model.InstitutionStandort;
import io.remedymatch.institution.domain.service.InstitutionTestFixtures;
import io.remedymatch.institution.infrastructure.InstitutionEntity;
import io.remedymatch.institution.infrastructure.InstitutionStandortEntity;

public final class BedarfTestFixtures {

	public static final BedarfId BEDARF_ID = new BedarfId(UUID.randomUUID());
	public static final BigDecimal BEDARF_ANZAHL = BigDecimal.valueOf(120.0);
	public static final BigDecimal BEDARF_REST = BigDecimal.valueOf(120.0);
	public static final Institution BEDARF_INSTITUTION = InstitutionTestFixtures.beispielInstitution();
	public static final InstitutionEntity BEDARF_INSTITUTION_ENTITY = InstitutionTestFixtures
			.beispielInstitutionEntity();
	public static final InstitutionStandort BEDARF_STANDORT = InstitutionTestFixtures.beispielHaupstandort();
	public static final InstitutionStandortEntity BEDARF_STANDORT_ENTITY = InstitutionTestFixtures
			.beispielHaupstandortEntity();
	public static final boolean BEDARF_STERIL = true;
	public static final boolean BEDARF_MEDIZINISCH = true;
	public static final String BEDARF_KOMMENTAR = "Kommentar";
	public static final boolean BEDARF_BEDIENT = false;

	private static final BedarfId BEDARF_OHNE_ANFRAGEN_ID = new BedarfId(UUID.randomUUID());

	public static BedarfId beispielBedarfId() {
		return BEDARF_ID;
	}

	public static Bedarf beispielBedarf() {
		return Bedarf.builder() //
				.id(beispielBedarfId()) //
				.artikel(ArtikelTestFixtures.beispielArtikel()) //
				.artikelVariante(ArtikelTestFixtures.beispielArtikelVariante1()) //
				.anzahl(BEDARF_ANZAHL) //
				.rest(BEDARF_REST) //
				.institution(BEDARF_INSTITUTION) //
				.standort(BEDARF_STANDORT) //
				.steril(BEDARF_STERIL) //
				.medizinisch(BEDARF_MEDIZINISCH) //
				.kommentar(BEDARF_KOMMENTAR) //
				.bedient(BEDARF_BEDIENT) //
				.build();
	}

	public static BedarfEntity beispielBedarfEntity() {
		return BedarfEntity.builder() //
				.id(beispielBedarfId().getValue()) //
				.artikel(ArtikelTestFixtures.beispielArtikelEntity()) //
				.artikelVariante(ArtikelTestFixtures.beispielArtikelVariante1Entity()) //
				.anzahl(BEDARF_ANZAHL) //
				.rest(BEDARF_REST) //
				.institution(BEDARF_INSTITUTION_ENTITY) //
				.standort(BEDARF_STANDORT_ENTITY) //
				.steril(BEDARF_STERIL) //
				.medizinisch(BEDARF_MEDIZINISCH) //
				.kommentar(BEDARF_KOMMENTAR) //
				.bedient(BEDARF_BEDIENT) //
				.build();
	}

	static Bedarf beispielBedarfOhneAnfragen() {
		return Bedarf.builder() //
				.id(BEDARF_OHNE_ANFRAGEN_ID) //
				.artikel(ArtikelTestFixtures.beispielArtikel()) //
				.artikelVariante(ArtikelTestFixtures.beispielArtikelVariante1()) //
				.anzahl(BEDARF_ANZAHL) //
				.rest(BEDARF_REST) //
				.institution(BEDARF_INSTITUTION) //
				.standort(BEDARF_STANDORT) //
				.steril(BEDARF_STERIL) //
				.medizinisch(BEDARF_MEDIZINISCH) //
				.kommentar(BEDARF_KOMMENTAR) //
				.bedient(BEDARF_BEDIENT) //
				.build();
	}

	static BedarfEntity beispielBedarfOhneAnfragenEntity() {
		return BedarfEntity.builder() //
				.id(BEDARF_OHNE_ANFRAGEN_ID.getValue()) //
				.artikel(ArtikelTestFixtures.beispielArtikelEntity()) //
				.artikelVariante(ArtikelTestFixtures.beispielArtikelVariante1Entity()) //
				.anzahl(BEDARF_ANZAHL) //
				.rest(BEDARF_REST) //
				.institution(BEDARF_INSTITUTION_ENTITY) //
				.standort(BEDARF_STANDORT_ENTITY) //
				.steril(BEDARF_STERIL) //
				.medizinisch(BEDARF_MEDIZINISCH) //
				.kommentar(BEDARF_KOMMENTAR) //
				.bedient(BEDARF_BEDIENT) //
				.build();
	}
}
