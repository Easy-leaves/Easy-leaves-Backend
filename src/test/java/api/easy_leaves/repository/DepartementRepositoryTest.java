package api.easy_leaves.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.easy_leaves.model.Departement;
import api.easy_leaves.repository.DepartementRepository;

/**
 * Tests unitaires pour la gestion des départements dans le dépôt DepartementRepository.
 * Cette classe vérifie les opérations CRUD sur les départements.
 * 
 * @see DepartementRepository
 * 
 * @author Driss
 */
@SpringBootTest
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository departementRepository;

    /**
     * Test de création et de récupération d'un département.
     * Vérifie que le département est correctement sauvegardé et récupéré.
     */
    @Test
    void testCreateAndRetrieveDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setLibelle("IT");

        // Act
        Departement savedDepartement = departementRepository.save(departement);

        // Assert
        assertNotNull(savedDepartement);
        assertNotNull(savedDepartement.getIdDepartement());
        assertEquals("IT", savedDepartement.getLibelle());

        // Cleanup
        departementRepository.delete(savedDepartement);
    }

    /**
     * Test de mise à jour d'un département.
     * Vérifie que les modifications effectuées sur un département sont correctement sauvegardées.
     */
    @Test
    void testUpdateDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setLibelle("IT");
        departement = departementRepository.save(departement);

        // Act
        departement.setLibelle("Accounting");
        Departement updatedDepartement = departementRepository.save(departement);

        // Assert
        assertEquals("Accounting", updatedDepartement.getLibelle());

        // Cleanup
        departementRepository.delete(updatedDepartement);
    }

    /**
     * Test de suppression d'un département.
     * Vérifie que le département est bien supprimé du dépôt.
     */
    @Test
    void testDeleteDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setLibelle("Marketing");
        departement = departementRepository.save(departement);

        // Act
        departementRepository.delete(departement);

        // Assert
        assertTrue(departementRepository.findById(departement.getIdDepartement()).isEmpty());
    }
}
