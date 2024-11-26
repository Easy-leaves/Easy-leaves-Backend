package api.easy_leaves.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.Statut;
import api.easy_leaves.enums.TypeAbsence;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.repository.AbsenceRepository;
import api.easy_leaves.repository.DepartementRepository;
import api.easy_leaves.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;

/**
 * Test d'intégration pour le service Absence.
 * Ce test vérifie la création d'une absence et sa persistance dans la base de données.
 * Il vérifie également les interactions avec les services associés comme l'utilisateur et le département.
 *
 * @author Driss
 */
@SpringBootTest
@Transactional
public class AbsenceServiceIntegrationTest {
    
    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private Utilisateur mockUtilisateur;
    
    @Autowired
    private DepartementRepository departementRepository;
    private Departement mockDepartement;

    private Absence absence;

    /**
     * Initialisation avant chaque test.
     * Crée des objets mock pour l'utilisateur, le département et l'absence.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        mockDepartement = new Departement();
        mockDepartement.setLibelle("Herault");
        
        mockUtilisateur = new Utilisateur();
        mockUtilisateur.setNom("Doe");
        mockUtilisateur.setPrenom("John");
        mockUtilisateur.setEmail("john.doe@example.com");
        mockUtilisateur.setRole(Role.EMPLOYE);
        mockUtilisateur.setDepartement(mockDepartement);
        mockUtilisateur.setCompteurUtilisateur(new ArrayList<>());
        mockUtilisateur.setAbsenceUtilisateur(new ArrayList<>());
        
        absence = new Absence(
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
     * Teste la création d'une absence et sa persistance dans la base de données.
     */
    @Test
    void testCreateAbsence() {
        departementRepository.save(mockDepartement);
        mockUtilisateur.setDepartement(mockDepartement);
        utilisateurRepository.save(mockUtilisateur);

        // Act
        Absence createdAbsence = absenceService.createAbsence(absence);

        // Assert
        assertNotNull(createdAbsence);
        assertEquals("Motif test", createdAbsence.getMotif());
        assertTrue(absenceRepository.findById(createdAbsence.getIdAbsence()).isPresent());
    }
}
