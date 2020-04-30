package io.remedymatch.person.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.remedymatch.institution.controller.InstitutionRO;
import io.remedymatch.institution.controller.InstitutionStandortRO;
import io.remedymatch.institution.domain.model.Institution;
import io.remedymatch.institution.domain.model.InstitutionId;
import io.remedymatch.institution.domain.model.InstitutionStandort;
import io.remedymatch.institution.domain.model.InstitutionStandortId;
import io.remedymatch.institution.domain.model.InstitutionTyp;
import io.remedymatch.person.domain.model.Person;
import io.remedymatch.person.domain.model.PersonId;
import io.remedymatch.person.domain.model.PersonInstitution;
import io.remedymatch.person.domain.model.PersonInstitutionId;

@ExtendWith(SpringExtension.class)
@DisplayName("PersonMapper soll")
public class PersonMapperShould {
	private static final PersonId PERSON_ID = new PersonId(UUID.randomUUID());
	private static final String USERNAME = "username";
	private static final String VORNAME = "Vorname";
	private static final String NACHNAME = "Nachname";
	private static final String EMAIL = "EMail";
	private static final String TELEFON = "012345";

	public static final PersonInstitutionId AKTUELLE_INSTITUTION_ID = new PersonInstitutionId(UUID.randomUUID());

	public static final InstitutionId INSTITUTION_ID = new InstitutionId(UUID.randomUUID());
	public static final String INSTITUTION_NAME = "Institution Name";
	public static final String INSTITUTION_KEY = "institutionKey";
	public static final InstitutionTyp INSTITUTION_TYP = InstitutionTyp.KRANKENHAUS;

	public static final InstitutionStandortId STANDORT_ID = new InstitutionStandortId(UUID.randomUUID());
	public static final String STANDORT_NAME = "Standort Name";
	public static final String STANDORT_PLZ = "Standort PLZ";
	public static final String STANDORT_ORT = "Standort Ort";
	public static final String STANDORT_STRASSE = "Standort Strasse";
	public static final String STANDORT_HAUSNUMMER = "Standort 10a";
	public static final String STANDORT_LAND = "Standort Land";
	public static final BigDecimal STANDORT_LONGITUDE = BigDecimal.valueOf(1000);
	public static final BigDecimal STANDORT_LATITUDE = BigDecimal.valueOf(2000);

	@Test
	@DisplayName("Domain Objekt in RO konvertieren")
	void domain_Objekt_in_RO_konvertieren() {
		assertEquals(personRO(), PersonControllerMapper.mapToPersonRO(person()));
	}

	private Person person() {
		return Person.builder() //
				.id(PERSON_ID)//
				.username(USERNAME) //
				.vorname(VORNAME) //
				.nachname(NACHNAME) //
				.email(EMAIL) //
				.telefon(TELEFON) //
				.aktuelleInstitution(aktuelleInstitution()) //
				.institutionen(new ArrayList<>(Arrays.asList(aktuelleInstitution())))
				.build();
	}

	private PersonRO personRO() {
		return PersonRO.builder() //
				.id(PERSON_ID.getValue())//
				.username(USERNAME) //
				.vorname(VORNAME) //
				.nachname(NACHNAME) //
				.email(EMAIL) //
				.telefon(TELEFON) //
				.aktuelleInstitution(aktuelleInstitutionRO()) //
				.institutionen(new ArrayList<>(Arrays.asList(aktuelleInstitutionRO())))
				.build();
	}

	private PersonInstitution aktuelleInstitution() {
		return PersonInstitution.builder() //
				.id(AKTUELLE_INSTITUTION_ID) //
				.institution(institution()) //
				.standort(standort()) //
				.build();
	}
	
	private PersonInstitutionRO aktuelleInstitutionRO() {
		return PersonInstitutionRO.builder() //
				.id(AKTUELLE_INSTITUTION_ID.getValue()) //
				.institution(institutionRO()) //
				.standort(standortRO()) //
				.build();
	}

	private Institution institution() {
		return Institution.builder() //
				.id(INSTITUTION_ID) //
				.name(INSTITUTION_NAME) //
				.institutionKey(INSTITUTION_KEY) //
				.typ(INSTITUTION_TYP) //
				.hauptstandort(standort()) //
				.standorte(new ArrayList<>(Arrays.asList(standort()))) //
				.build();
	}

	private InstitutionRO institutionRO() {
		return InstitutionRO.builder() //
				.id(INSTITUTION_ID.getValue()) //
				.name(INSTITUTION_NAME) //
				.institutionKey(INSTITUTION_KEY) //
				.typ(INSTITUTION_TYP) //
				.hauptstandort(standortRO()) //
				.standorte(new ArrayList<>(Arrays.asList(standortRO()))) //
				.build();
	}

	private InstitutionStandort standort() {
		return InstitutionStandort.builder() //
				.id(STANDORT_ID) //
				.name(STANDORT_NAME) //
				.plz(STANDORT_PLZ) //
				.ort(STANDORT_ORT) //
				.strasse(STANDORT_STRASSE) //
				.strasse(STANDORT_HAUSNUMMER) //
				.land(STANDORT_LAND) //
				.longitude(STANDORT_LONGITUDE) //
				.latitude(STANDORT_LATITUDE) //
				.build();
	}

	private InstitutionStandortRO standortRO() {
		return InstitutionStandortRO.builder() //
				.id(STANDORT_ID.getValue()) //
				.name(STANDORT_NAME) //
				.plz(STANDORT_PLZ) //
				.ort(STANDORT_ORT) //
				.strasse(STANDORT_STRASSE) //
				.strasse(STANDORT_HAUSNUMMER) //
				.land(STANDORT_LAND) //
				.longitude(STANDORT_LONGITUDE) //
				.latitude(STANDORT_LATITUDE) //
				.build();
	}
}