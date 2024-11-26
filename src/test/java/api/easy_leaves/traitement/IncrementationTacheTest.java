package api.easy_leaves.traitement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import api.easy_leaves.enums.Statut;
import api.easy_leaves.model.Absence;
import api.easy_leaves.repository.AbsenceRepository;

/**
 * Test unitaire de la classe IncrementationTache.
 * Ce test vérifie que la tâche planifiée updateAbsenceStatut met bien à jour les statuts des absences
 * de INITIALE à EN_ATTENTE_VALIDATION.
 * 
 * Les tests utilisent des mocks pour simuler les appels au repository et s'assurer que la logique de mise à jour fonctionne.
 * 
 * @author Driss
 */
public class IncrementationTacheTest {

    @Mock
    private AbsenceRepository absenceRepository;

    @InjectMocks
    private IncrementationTache incrementationTache;

    private List<Absence> absences;

    /**
     * Initialisation avant chaque test.
     * Crée des absences mock pour simuler l'environnement de test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Absence absence1 = new Absence();
        absence1.setStatut(Statut.INITIALE);

        Absence absence2 = new Absence();
        absence2.setStatut(Statut.INITIALE);

        absences = Arrays.asList(absence1, absence2);
    }

    /**
     * Teste la méthode updateAbsenceStatut.
     * Vérifie que les absences dont le statut est INITIALE sont bien mises à jour avec le statut EN_ATTENTE_VALIDATION.
     */
    @Test
    public void testUpdateAbsenceStatut() {
        // Arrange
        when(absenceRepository.findByStatut(Statut.INITIALE)).thenReturn(absences);

        // Act
        incrementationTache.updateAbsenceStatut();

        // Assert
        for (Absence absence : absences) {
            assertEquals(Statut.EN_ATTENTE_VALIDATION, absence.getStatut());
        }
        
        verify(absenceRepository, times(1)).findByStatut(Statut.INITIALE);
        verify(absenceRepository, times(1)).saveAll(absences);
    }

}
