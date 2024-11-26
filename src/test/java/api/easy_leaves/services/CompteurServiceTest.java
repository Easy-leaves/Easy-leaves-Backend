package api.easy_leaves.services;

import api.easy_leaves.model.Compteur;
import api.easy_leaves.repository.CompteurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test unitaire pour le service Compteur.
 * Ce test vérifie les méthodes liées à la gestion des compteurs, y compris la récupération et la création d'un compteur.
 * Les tests sont basés sur des mocks afin de simuler les interactions avec le repository.
 *
 * @author Driss
 */
public class CompteurServiceTest {

    @Mock
    private CompteurRepository compteurRepository;

    @InjectMocks
    private CompteurService compteurService;

    private Compteur compteur;

    /**
     * Initialisation avant chaque test.
     * Crée un mock de compteur pour les tests.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        compteur = new Compteur();
        compteur.setIdCompteur(1);
        compteur.setAnnee(2023);
    }

    /**
     * Teste la récupération d'un compteur par son ID.
     * Vérifie que le compteur est trouvé et que l'année est correcte.
     */
    @Test
    public void testGetCompteurById() {
        when(compteurRepository.findById(1)).thenReturn(Optional.of(compteur));
        assertEquals(2023, compteurService.getCompteurById(1).getAnnee());
        verify(compteurRepository, times(1)).findById(1);
    }

    /**
     * Teste la création d'un compteur.
     * Vérifie que le compteur est correctement créé et sauvegardé.
     */
    @Test
    public void testCreateCompteur() {
        when(compteurRepository.save(any(Compteur.class))).thenReturn(compteur);
        assertNotNull(compteurService.createCompteur(compteur));
        verify(compteurRepository, times(1)).save(compteur);
    }
}
