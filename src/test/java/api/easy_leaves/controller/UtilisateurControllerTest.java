package api.easy_leaves.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import api.easy_leaves.dto.UtilisateurDTO;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.services.UtilisateurService;
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
 * Test unitaire pour UtilisateurController.
 */
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    

    /**
     * Test pour créer un utilisateur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    public void testCreerUtilisateur() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1);
        when(utilisateurService.createUtilisateur(any(Utilisateur.class))).thenReturn(utilisateur);

        mockMvc.perform(post("/utilisateurs/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utilisateur)))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).createUtilisateur(any(Utilisateur.class));
    }

    /**
     * Test pour mettre à jour un utilisateur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    public void testMettreAJourUtilisateur() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1);
        when(utilisateurService.updateUtilisateur(eq(1), any(Utilisateur.class))).thenReturn(utilisateur);

        mockMvc.perform(put("/utilisateurs/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utilisateur)))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).updateUtilisateur(eq(1), any(Utilisateur.class));
    }

    /**
     * Test pour supprimer un utilisateur.
     * @throws Exception en cas d'erreur MockMvc
     */
    @Test
    public void testSupprimerUtilisateur() throws Exception {
        doNothing().when(utilisateurService).deleteUtilisateur(1);

        mockMvc.perform(delete("/utilisateurs/delete/1"))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).deleteUtilisateur(1);
    }
}
