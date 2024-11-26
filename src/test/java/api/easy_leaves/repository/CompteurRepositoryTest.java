package api.easy_leaves.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;

/**
 * Tests unitaires pour la gestion des compteurs dans le dépôt CompteurRepository.
 * Cette classe vérifie les opérations CRUD et les relations entre Compteurs et Utilisateurs.
 * 
 * @see CompteurRepository
 * @see UtilisateurRepository
 * @see DepartementRepository
 * 
 * @author Driss
 */
@SpringBootTest
public class CompteurRepositoryTest {

    @Autowired
    private CompteurRepository compteurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private DepartementRepository departementRepository;

    private Utilisateur utilisateur;

    /**
     * Préparation des données avant chaque test.
     * Un utilisateur et un département sont créés pour être utilisés dans les cas de test.
     */
    @BeforeEach
    public void setUp() {
        // Arrange
        Departement departement = new Departement();
        departement.setLibelle("Operations");
        departement = departementRepository.save(departement);

        utilisateur = new Utilisateur();
        utilisateur.setNom("Brown");
        utilisateur.setPrenom("Charlie");
        utilisateur.setEmail("charlie.brown@example.com");
        utilisateur.setRole(Role.EMPLOYE);
        utilisateur.setDepartement(departement);
        utilisateur = utilisateurRepository.save(utilisateur);
    }
    
    /**
     * Test de création d'un compteur associé à un utilisateur.
     * Ce test vérifie que le compteur est correctement sauvegardé dans la base de données
     * et que ses propriétés, ainsi que son association avec l'utilisateur, sont correctes.
     *
     * Étapes du test :
     * 1. Création d'un compteur pour un utilisateur existant.
     * 2. Sauvegarde du compteur dans le dépôt.
     * 3. Validation des propriétés sauvegardées.
     * 
     * Assertions :
     * - Le compteur sauvegardé n'est pas null.
     * - L'ID du compteur est généré.
     * - Les propriétés du compteur (année, type) sont correctement enregistrées.
     * - L'association avec l'utilisateur est correcte.
     */
    @Test
    public void testCreateCompteurForUtilisateur() {
        // Arrange: Créer un compteur lié à l'utilisateur
        Compteur compteur = new Compteur();
        compteur.setAnnee(2024);
        compteur.setTypeCompteur(TypeCompteur.CONGE_PAYE);
        compteur.setUtilisateur(utilisateur);

        // Act: Sauvegarder le compteur
        Compteur savedCompteur = compteurRepository.save(compteur);

        // Assert: Vérifier les valeurs
        assertNotNull(savedCompteur);
        assertNotNull(savedCompteur.getIdCompteur());
        assertEquals(2024, savedCompteur.getAnnee());
        assertEquals(TypeCompteur.CONGE_PAYE, savedCompteur.getTypeCompteur());
        assertEquals("Brown", savedCompteur.getUtilisateur().getNom());
    }
    
    /**
     * Test de suppression d'un compteur.
     * Vérifie que le compteur est correctement supprimé du dépôt.
     */
    @Test
    public void testDeleteCompteur() {
        // Arrange: Créer et sauvegarder un compteur
        Compteur compteur = new Compteur();
        compteur.setAnnee(2024);
        compteur.setTypeCompteur(TypeCompteur.CONGE_PAYE);
        compteur.setUtilisateur(utilisateur);
        compteur = compteurRepository.save(compteur);

        // Act: Supprimer le compteur
        compteurRepository.delete(compteur);

        // Assert: Vérifier qu'il est supprimé
        assertTrue(compteurRepository.findById(compteur.getIdCompteur()).isEmpty());
    }
}
