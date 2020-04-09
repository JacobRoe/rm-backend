package io.remedymatch.artikel.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import io.remedymatch.artikel.domain.model.Artikel;
import io.remedymatch.artikel.domain.model.ArtikelId;
import io.remedymatch.artikel.domain.model.ArtikelKategorieId;
import io.remedymatch.artikel.domain.model.ArtikelVariante;
import io.remedymatch.artikel.domain.model.ArtikelVarianteId;
import io.remedymatch.artikel.infrastructure.ArtikelEntity;
import io.remedymatch.artikel.infrastructure.ArtikelVarianteEntity;

public class ArtikelEntityConverter {
	private ArtikelEntityConverter() {
	}

	static List<Artikel> convertArtikel(final List<ArtikelEntity> entities) {
		return entities.stream().map(ArtikelEntityConverter::convertArtikel).collect(Collectors.toList());
	}

	public static Artikel convertArtikel(final ArtikelEntity entity) {
		return Artikel.builder() //
				.id(new ArtikelId(entity.getId())) //
				.artikelKategorieId(new ArtikelKategorieId(entity.getArtikelKategorie())) //
				.name(entity.getName()) //
				.beschreibung(entity.getBeschreibung()) //
				.varianten(convertVarianten(entity.getVarianten())) //
				.build();
	}

	public static ArtikelEntity convertArtikel(final Artikel artikel) {
		return ArtikelEntity.builder() //
				.id(artikel.getId().getValue()) //
				.artikelKategorie(artikel.getArtikelKategorieId().getValue()) //
				.name(artikel.getName()) //
				.beschreibung(artikel.getBeschreibung()) //
				.varianten(convertVarianteEntities(artikel.getVarianten())) //
				.build();
	}

	static List<ArtikelVariante> convertVarianten(final List<ArtikelVarianteEntity> entities) {
		return entities.stream().map(ArtikelEntityConverter::convertVariante).collect(Collectors.toList());
	}

	public static ArtikelVariante convertVariante(final ArtikelVarianteEntity entity) {
		return ArtikelVariante.builder() //
				.id(new ArtikelVarianteId(entity.getId())) //
				.artikelId(new ArtikelId(entity.getArtikel())) //
				.variante(entity.getVariante()) //
				.norm(entity.getNorm()) //
				.beschreibung(entity.getBeschreibung()) //
				.medizinischAuswaehlbar(entity.isMedizinischAuswaehlbar()) //
				.build();
	}

	static List<ArtikelVarianteEntity> convertVarianteEntities(final List<ArtikelVariante> varianten) {
		return varianten.stream().map(ArtikelEntityConverter::convertVariante).collect(Collectors.toList());
	}

	public static ArtikelVarianteEntity convertVariante(final ArtikelVariante variante) {
		return ArtikelVarianteEntity.builder() //
				.id(variante.getId().getValue()) //
				.artikel(variante.getArtikelId().getValue()) //
				.variante(variante.getVariante()) //
				.norm(variante.getNorm()) //
				.beschreibung(variante.getBeschreibung()) //
				.medizinischAuswaehlbar(variante.isMedizinischAuswaehlbar()) //
				.build();
	}
}
