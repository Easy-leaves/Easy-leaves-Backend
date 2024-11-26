package api.easy_leaves.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.Statut;
import api.easy_leaves.enums.TypeAbsence;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;

/**
 * Tests unitaires pour la gestion des absences dans le dépôt AbsenceRepository.
 * Cette classe vérifie les opérations CRUD et les relations entre Absences et Utilisateurs.
 * 
 * @see AbsenceRepository
 * @see UtilisateurRepository
 * @see DepartementRepository
 * 
 * @author Driss
 */
@SpringBootTest
public class AbsenceRepositoryTest {
	
	/**
     * Référence au dépôt des absences.
     */
    @Autowired
    private AbsenceRepository absenceRepository;

    /**
     * Référence au dépôt des utilisateurs.
     */
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    /**
     * Référence au dépôt des départements.
     */
    @Autowired
    private DepartementRepository departementRepository;
   
    /**
     * Utilisateur utilisé pour les tests.
     */
    private Utilisateur utilisateur;
    
    /**
     * Département utilisé pour les tests.
     */
    private Departement departement;

    /**
     * Préparation des données avant chaque test.
     * Un utilisateur et un département sont créés pour être utilisés dans les cas de test.
     */
    @BeforeEach
    public void setUp() {
        // Initialiser un département
        departement = new Departement();
        departement.setLibelle("Finaces");
        departement = departementRepository.save(departement);

        // Initialiser un utilisateur
        utilisateur = new Utilisateur();
        utilisateur.setNom("LOLO");
        utilisateur.setPrenom("Jit");
        utilisateur.setEmail("jilo2.doe@example.com");
        utilisateur.setRole(Role.EMPLOYE);
        utilisateur.setDepartement(departement);
        utilisateur.setCompteurUtilisateur(new ArrayList<>());
        utilisateur.setAbsenceUtilisateur(new ArrayList<>());
        utilisateur = utilisateurRepository.save(utilisateur);
    }
    
    /**
     * Test de création et de sauvegarde d'une absence.
     * Vérifie que l'absence est correctement sauvegardée et que les champs sont bien renseignés.
     */
    @Test
    public void testCreateAbsence() {
        // Arrange
        Absence absence = new Absence();
        absence.setDateDebut(new Date());
        absence.setDateFin(new Date());
        absence.setType(TypeAbsence.CONGE_PAYE);
        absence.setStatut(Statut.EN_ATTENTE_VALIDATION);
        absence.setMotif("Vacance au Maroc");
        absence.setUtilisateur(utilisateur);

        // Act
        Absence savedAbsence = absenceRepository.save(absence);

        // Assert
        assertNotNull(savedAbsence);
        assertNotNull(savedAbsence.getIdAbsence());
        assertEquals("Vacance au Maroc", savedAbsence.getMotif());
        assertEquals(utilisateur.getIdUtilisateur(), savedAbsence.getUtilisateur().getIdUtilisateur());
    }

    /**
     * Test de récupération d'une absence par son identifiant.
     * Vérifie que l'absence récupérée correspond bien à celle sauvegardée.
     */
    @Test
    public void testFindAbsenceById() {
        // Arrange
        Absence absence = new Absence();
        absence.setDateDebut(new Date());
        absence.setDateFin(new Date());
        absence.setType(TypeAbsence.RTT_EMPLOYE);
        absence.setStatut(Statut.VALIDEE);
        absence.setMotif("Medical leave");
        absence.setUtilisateur(utilisateur);
        Absence savedAbsence = absenceRepository.save(absence);

        // Act
        Absence foundAbsence = absenceRepository.findById(savedAbsence.getIdAbsence()).orElse(null);

        // Assert
        assertNotNull(foundAbsence);
        assertEquals(savedAbsence.getIdAbsence(), foundAbsence.getIdAbsence());
        assertEquals("Medical leave", foundAbsence.getMotif());
    }

    /**
     * Test de récupération des absences d'un utilisateur.
     * Vérifie que toutes les absences associées à un utilisateur sont bien retournées.
     */
    @Test
    public void testFindAbsencesByUtilisateur() {
        // Arrange
        Absence absence1 = new Absence();
        absence1.setDateDebut(new Date());
        absence1.setDateFin(new Date());
        absence1.setType(TypeAbsence.CONGE_PAYE);
        absence1.setStatut(Statut.EN_ATTENTE_VALIDATION);
        absence1.setMotif("Vacation");
        absence1.setUtilisateur(utilisateur);

        Absence absence2 = new Absence();
        absence2.setDateDebut(new Date());
        absence2.setDateFin(new Date());
        absence2.setType(TypeAbsence.RTT_EMPLOYE);
        absence2.setStatut(Statut.INITIALE);
        absence2.setMotif("Medical leave");
        absence2.setUtilisateur(utilisateur);

        absenceRepository.save(absence1);
        absenceRepository.save(absence2);

        // Act
        List<Absence> absences = absenceRepository.findAll(); // Custom query can be added if needed

        // Assert
        assertEquals(2, absences.size());
    }

    /**
     * Test de mise à jour d'une absence.
     * Vérifie que les modifications effectuées sur une absence sont correctement sauvegardées.
     */
    @Test
    public void testUpdateAbsence() {
        // Arrange
        Absence absence = new Absence();
        absence.setDateDebut(new Date());
        absence.setDateFin(new Date());
        absence.setType(TypeAbsence.CONGE_PAYE);
        absence.setStatut(Statut.EN_ATTENTE_VALIDATION);
        absence.setMotif("Vacation");
        absence.setUtilisateur(utilisateur);
        Absence savedAbsence = absenceRepository.save(absence);

        // Act
        savedAbsence.setStatut(Statut.VALIDEE);
        savedAbsence.setMotif("Updated vacation");
        Absence updatedAbsence = absenceRepository.save(savedAbsence);

        // Assert
        assertEquals(Statut.VALIDEE, updatedAbsence.getStatut());
        assertEquals("Updated vacation", updatedAbsence.getMotif());
    }

    /**
     * Test de suppression d'une absence.
     * Vérifie que l'absence est bien supprimée du dépôt.
     */
    @Test
    public void testDeleteAbsence() {
        // Arrange
        Absence absence = new Absence();
        absence.setDateDebut(new Date());
        absence.setDateFin(new Date());
        absence.setType(TypeAbsence.RTT_EMPLOYEUR);
        absence.setStatut(Statut.EN_ATTENTE_VALIDATION);
        absence.setMotif("Conference");
        absence.setUtilisateur(utilisateur);
        Absence savedAbsence = absenceRepository.save(absence);

        // Act
        absenceRepository.delete(savedAbsence);

        // Assert
        assertTrue(absenceRepository.findById(savedAbsence.getIdAbsence()).isEmpty());
    }
}
