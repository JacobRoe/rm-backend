package io.remedymatch.angebot.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.remedymatch.engine.client.EngineClient;
import io.remedymatch.engine.domain.BusinessKey;
import io.remedymatch.engine.domain.ProzessInstanzId;
import io.remedymatch.institution.domain.InstitutionTestFixtures;
import lombok.val;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = { //
		AngebotAnfrageProzessService.class, //
		EngineClient.class //
})
@Tag("Spring")
@DisplayName("AngebotAnfrageProzessService soll")
class AngebotAnfrageProzessServiceShould {

	@Autowired
	private AngebotAnfrageProzessService anfrageProzessService;

	@MockBean
	private EngineClient engineClient;

	@Test
	@DisplayName("Prozess starten koennen")
	void prozess_starten_koennen() {

		val angebotId = AngebotTestFixtures.beispielAngebotId();
		val anfrageId = AngebotAnfrageTestFixtures.beispielAngebotAnfrageId();
		val angebotInstitutionId = InstitutionTestFixtures.beispielInstitutionId();

		val businessKey = new BusinessKey(anfrageId.getValue());
		val expectedProzessInstanzId = new ProzessInstanzId("egal");

		given(engineClient.prozessStarten(eq(AngebotAnfrageProzessService.PROZESS_KEY), eq(businessKey), anyMap()))
				.willReturn(expectedProzessInstanzId);

		assertEquals(expectedProzessInstanzId,
				anfrageProzessService.prozessStarten(angebotId, anfrageId, angebotInstitutionId));

		then(engineClient).should().prozessStarten(eq(AngebotAnfrageProzessService.PROZESS_KEY), eq(businessKey), anyMap());
		then(engineClient).shouldHaveNoMoreInteractions();
	}

}