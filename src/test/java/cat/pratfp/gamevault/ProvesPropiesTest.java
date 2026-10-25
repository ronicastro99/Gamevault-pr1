package cat.pratfp.gamevault;
import cat.pratfp.gamevault.io.IndexHores;
import cat.pratfp.gamevault.model.Joc;
import cat.pratfp.gamevault.servei.CatalegException;
import cat.pratfp.gamevault.servei.Convertidor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Les teves proves (PR1-14). Aquesta classe SÍ que l'has de modificar.
 * Escriu dues proves noves que no estiguin a les altres classes de prova.
 * Canvia el @DisplayName perquè digui què comproves i treu el fail().
 * Idees: l'índex amb un títol de més de 40 caràcters; una còpia de seguretat d'un fitxer sense extensió;
 * un CSV amb una línia en blanc; carregar un .json que no existeix amb el Convertidor;
 * sumaHores en dos registres seguits...
 */
class ProvesPropiesTest {

    @TempDir
    Path tmp;

    @Test
    @DisplayName("IndexHores gestiona i trunca un títol de més de 40 caràcters")
    void primeraProva() throws Exception {
        // Escriu aquí el teu codi (PR1-14)

        Path fitxerIndex = tmp.resolve("hores_llarg.dat");
        IndexHores index = new IndexHores(fitxerIndex);

        String titolMoltLlarg = "Super Mario Bros Wonder Deluxe Edition Extended Remastered";
        Joc jocLlarg = new Joc(1, titolMoltLlarg, "Switch", "Nintendo", LocalDate.of(2023, 10, 20), 30.0, 9.5);

        assertDoesNotThrow(() -> index.escriu(0, jocLlarg));
        assertEquals(30.0, index.llegeixHores(0));
        assertEquals(1, index.nombreRegistres());
    }

    @Test
    @DisplayName("PR1-14 · Convertidor llança CatalegException quan el format no és suportat (.xml)")
    void segonaProva() throws Exception {
        // Escriu aquí el teu codi (PR1-14)
        Convertidor convertidor = new Convertidor();
        Path fitxerXml = tmp.resolve("jocs.xml");

        // Carregar un format desconegut ha de llançar CatalegException amb el missatge adequat
        CatalegException ex = assertThrows(CatalegException.class, () -> convertidor.carrega(fitxerXml));
        assertTrue(ex.getMessage().contains("Format no suportat"));
    }
}

