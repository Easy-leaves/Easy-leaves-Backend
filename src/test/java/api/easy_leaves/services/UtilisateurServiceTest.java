package api.easy_leaves.services;

import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test unitaire pour le service Utilisateur.
 * Ce test vérifie les méthodes de récupération et de création d'un utilisateur.
 * Les tests sont effectués avec des mocks pour simuler les appels à la base de données.
 * 
 * @author Driss
 */
class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;

    /**
     * Initialisation avant chaque test.
     * Crée un mock d'utilisateur pour les tests.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1);
        utilisateur.setNom("Doe");
        utilisateur.setPrenom("John");
        utilisateur.setEmail("john.doe@example.com");
    }

    /**
     * Teste la récupération de tous les utilisateurs.
     * Vérifie que le service retourne correctement les utilisateurs et que l'appel au repository est effectué.
     */
    @Test
    void testGetAllUtilisateurs() {
        when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(utilisateur));
        assertEquals(1, utilisateurService.getAllUtilisateurs().size());
        verify(utilisateurRepository, times(1)).findAll();
    }

    /**
     * Teste la récupération d'un utilisateur par son ID.
     * Vérifie que l'utilisateur est trouvé et ses détails sont corrects.
     */
    @Test
    void testGetUtilisateurById() {
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(utilisateur));
        assertNotNull(utilisateurService.getUtilisateurById(1));
        assertEquals("Doe", utilisateurService.getUtilisateurById(1).getNom());
        verify(utilisateurRepository, times(2)).findById(1);
    }

    /**
     * Teste la création d'un utilisateur.
     * Vérifie que l'utilisateur est correctement créé et sauvegardé.
     */
    @Test
    void testCreateUtilisateur() {
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);
        assertNotNull(utilisateurService.createUtilisateur(utilisateur));
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }

    /**
     * Teste la suppression d'un utilisateur par son ID.
     * Vérifie que l'utilisateur est supprimé correctement du repository.
     */
    @Test
    void testDeleteUtilisateur() {
        doNothing().when(utilisateurRepository).deleteById(1);
        utilisateurService.deleteUtilisateur(1);
        verify(utilisateurRepository, times(1)).deleteById(1);
    }
}
