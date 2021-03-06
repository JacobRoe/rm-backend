package io.remedymatch.bedarf.domain.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.remedymatch.artikel.domain.model.ArtikelId;
import io.remedymatch.artikel.domain.model.ArtikelVarianteId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class NeuerBedarf {

	@NotNull
	@Positive
	private BigDecimal anzahl;

	@Valid
	private ArtikelId artikelId;

	@Valid
	private ArtikelVarianteId artikelVarianteId;

	private boolean steril;

	private boolean medizinisch;

	private boolean oeffentlich;

	@NotBlank
	private String kommentar;
}
