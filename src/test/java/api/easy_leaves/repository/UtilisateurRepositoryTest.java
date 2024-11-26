package api.easy_leaves.repository;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.easy_leaves.enums.Role;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;

/**
 * Tests unitaires pour la gestion des utilisateurs dans le dépôt UtilisateurRepository.
 * Cette classe vérifie les opérations CRUD et les relations entre Utilisateurs et Départements.
 * 
 * @see UtilisateurRepository
 * @see DepartementRepository
 * 
 * @autor Driss
 */
@SpringBootTest
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private DepartementRepository departementRepository;

    private Departement departement;

    /**
     * Préparation des données avant chaque test.
     * Un département est créé pour être utilisé dans les cas de test.
     */
    @BeforeEach
    public void setUp() {
        departement = new Departement();
        departement.setLibelle("HR");
        departement = departementRepository.save(departement);
    }

    /**
     * Test de création d'un utilisateur avec un département.
     * Vérifie que l'utilisateur est correctement sauvegardé et lié au département.
     */
    @Test
    public void testCreateUtilisateurWithDepartement() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Smith");
        utilisateur.setPrenom("Alice");
        utilisateur.setEmail("alice.smith@example.com");
        utilisateur.setRole(Role.EMPLOYE);
        utilisateur.setDepartement(departement);

        // Act
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);

        // Assert
        assertNotNull(savedUtilisateur);
        assertNotNull(savedUtilisateur.getIdUtilisateur());
        assertEquals("Alice", savedUtilisateur.getPrenom());
        assertEquals("HR", savedUtilisateur.getDepartement().getLibelle());
    }
}

