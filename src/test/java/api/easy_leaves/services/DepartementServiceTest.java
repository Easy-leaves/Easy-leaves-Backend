package api.easy_leaves.services;

import api.easy_leaves.model.Departement;
import api.easy_leaves.repository.DepartementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test unitaire pour le service Departement.
 * Ce test vérifie les méthodes de récupération et de création des départements en utilisant des mocks.
 * 
 * @author Driss
 */
public class DepartementServiceTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementService departementService;

    private Departement departement;

    /**
     * Initialisation avant chaque test.
     * Crée un mock de département pour les tests.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        departement = new Departement();
        departement.setIdDepartement(1);
        departement.setLibelle("IT");
    }

    /**
     * Teste la récupération d'un département par son ID.
     * Vérifie que le département est trouvé et que le libellé est correct.
     */
    @Test
    public void testGetDepartementById() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        assertEquals("IT", departementService.getDepartementById(1).getLibelle());
        verify(departementRepository, times(1)).findById(1);
    }

    /**
     * Teste la création d'un département.
     * Vérifie que le département est correctement créé et sauvegardé.
     */
    @Test
    public void testCreateDepartement() {
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);
        assertNotNull(departementService.createDepartement(departement));
        verify(departementRepository, times(1)).save(departement);
    }
}
