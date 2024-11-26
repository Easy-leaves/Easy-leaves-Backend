package api.easy_leaves.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import api.easy_leaves.dto.CompteurDTO;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.services.CompteurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;


/**
 * @author Driss
 * Test unitaire pour CompteurController.
 */
@WebMvcTest(CompteurController.class)
public class CompteurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompteurService compteurService;

    @InjectMocks
    private CompteurController compteurController;

    private final ObjectMapper objectMapper = new ObjectMapper();

   
    /**
     * Test pour créer un compteur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    void testCreerCompteur() throws Exception {
        Compteur compteur = new Compteur();
        compteur.setIdCompteur(1);
        when(compteurService.createCompteur(any(Compteur.class))).thenReturn(compteur);

        mockMvc.perform(post("/compteurs/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(compteur)))
                .andExpect(status().isOk());

        verify(compteurService, times(1)).createCompteur(any(Compteur.class));
    }

    /**
     * Test pour mettre à jour un compteur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    void testMettreAJourCompteur() throws Exception {
        Compteur compteur = new Compteur();
        compteur.setIdCompteur(1);
        when(compteurService.updateCompteur(eq(1), any(Compteur.class))).thenReturn(compteur);

        mockMvc.perform(put("/compteurs/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(compteur)))
                .andExpect(status().isOk());

        verify(compteurService, times(1)).updateCompteur(eq(1), any(Compteur.class));
    }

    /**
     * Test pour supprimer un compteur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    void testSupprimerCompteur() throws Exception {
        doNothing().when(compteurService).deleteCompteur(1);

        mockMvc.perform(delete("/compteurs/delete/1"))
                .andExpect(status().isOk());

        verify(compteurService, times(1)).deleteCompteur(1);
    }
}
