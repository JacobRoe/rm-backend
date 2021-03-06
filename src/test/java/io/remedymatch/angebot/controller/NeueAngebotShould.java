package io.remedymatch.angebot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.remedymatch.TestApplication;
import io.remedymatch.WithMockJWT;
import io.remedymatch.engine.client.EngineClient;
import io.remedymatch.usercontext.TestUserContext;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"dbinit", "test", "disableexternaltasks"})
@DirtiesContext
@Tag("InMemory")
@Tag("SpringBoot")
public class NeueAngebotShould extends AngebotControllerTestBasis {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EngineClient engineClient;

    @BeforeEach
    void prepare() {

        prepareAngebotEntities();
    }

    @AfterEach
    void clear() {
        TestUserContext.clear();
    }

    @Test
    @Transactional
    @WithMockJWT(groupsClaim = {"testgroup"}, usernameClaim = SPENDER_USERNAME)
    public void neue_Angebot_anlegen() throws Exception {

        TestUserContext.setContextUser(spender);

        val artikelVariante = artikelRepository.findAll().get(0).getVarianten().get(0);


        val neuesAngebot = NeuesAngebotRequest.builder() //
                .artikelVarianteId(artikelVariante.getId()) //
                .anzahl(BigDecimal.valueOf(1000)) //
                .kommentar("ITest Angebot Kommentar") //
                .haltbarkeit(LocalDateTime.of(2020, 12, 24, 18, 0)) //
                .steril(true) //
                .build();

        MvcResult result = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
                .perform(MockMvcRequestBuilders //
                        .post("/angebot") //
                        .content(objectMapper.writeValueAsString(neuesAngebot)).contentType(MediaType.APPLICATION_JSON) //
                        .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(MockMvcResultMatchers.jsonPath("$.verfuegbareAnzahl").value(BigDecimal.valueOf(1000))) //
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty()) //
                .andReturn();

        AngebotRO angebot = objectMapper.readValue(result.getResponse().getContentAsString(), AngebotRO.class);

        assertEquals("ITest Angebot Kommentar", angebot.getKommentar());
    }
}
