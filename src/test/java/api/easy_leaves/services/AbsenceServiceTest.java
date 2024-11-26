package api.easy_leaves.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.Statut;
import api.easy_leaves.enums.TypeAbsence;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.repository.AbsenceRepository;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * Test unitaire pour le service Absence.
 * Ce test vérifie les méthodes de récupération et de création d'absences en simulant les interactions avec les composants nécessaires.
 * Les tests utilisent des mocks pour simuler les appels à la base de données.
 *
 * @author Driss
 */
@SpringBootTest
@Transactional
class AbsenceServiceTest {

    @Mock
    private AbsenceRepository absenceRepository;

    @InjectMocks
    private AbsenceService absenceService;

    private Absence mockAbsence;
    private Utilisateur mockUtilisateur;

    /**
     * Initialisation avant chaque test.
     * Crée des objets mock pour l'utilisateur et l'absence.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Departement mockDepartement = new Departement();
        mockDepartement.setLibelle("Herault");

        mockUtilisateur = new Utilisateur();
        mockUtilisateur.setNom("Doe");
        mockUtilisateur.setPrenom("John");
        mockUtilisateur.setEmail("john.doe@example.com");
        mockUtilisateur.setRole(Role.EMPLOYE);
        mockUtilisateur.setDepartement(mockDepartement);
        mockUtilisateur.setCompteurUtilisateur(new ArrayList<>());
        mockUtilisateur.setAbsenceUtilisateur(new ArrayList<>());

        mockAbsence = new Absence(
            1,
            new Date(),
            new Date(),
            TypeAbsence.CONGE_PAYE,
            Statut.EN_ATTENTE_VALIDATION,
            "Motif test",
            mockUtilisateur
        );
    }

    /**
     * Teste la récupération de toutes les absences.
     * Vérifie que le service retourne correctement les absences et que l'appel au repository est effectué.
     */
    @Test
    void testGetAllAbsences() {
        when(absenceRepository.findAll()).thenReturn(Arrays.asList(mockAbsence));

        var absences = absenceService.getAllAbsences();

        assertNotNull(absences);
        assertEquals(1, absences.size());
        assertEquals("Motif test", absences.get(0).getMotif());
        assertEquals("John", absences.get(0).getUtilisateur().getPrenom());
        verify(absenceRepository, times(1)).findAll();
    }

    /**
     * Teste la récupération d'une absence par son ID lorsque l'absence est trouvée.
     * Vérifie la présence de l'absence et ses détails dans le service.
     */
    @Test
    void testGetAbsenceById_Found() {
        when(absenceRepository.findById(1)).thenReturn(Optional.of(mockAbsence));

        var absence = absenceService.getAbsenceById(1);

        assertNotNull(absence);
        assertEquals(1, absence.getIdAbsence());
        assertEquals("Doe", absence.getUtilisateur().getNom());
        verify(absenceRepository, times(1)).findById(1);
    }

    /**
     * Teste la création d'une absence.
     * Vérifie que l'absence est correctement enregistrée et retournée.
     */
    @Test
    void testCreateAbsence() {
        when(absenceRepository.save(any(Absence.class))).thenReturn(mockAbsence);

        var createdAbsence = absenceService.createAbsence(mockAbsence);

        assertNotNull(createdAbsence);
        assertEquals("Motif test", createdAbsence.getMotif());
        assertEquals("john.doe@example.com", createdAbsence.getUtilisateur().getEmail());
        verify(absenceRepository, times(1)).save(mockAbsence);
    }
}
