package cat.pratfp.gamevault.io;

import cat.pratfp.gamevault.model.Joc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Cau binària del catàleg: desa la llista sencera amb serialització Java per recuperar-la ràpidament.
 * Només la pot llegir Java: no és un format d'intercanvi.
 */
public class CauCataleg {

    /*
     * TODO PR1-11 · Serialitzar i desserialitzar el catàleg (CA 1.3 i 1.4)
     * desa:    escriu amb un ObjectOutputStream UN sol objecte: new ArrayList<>(jocs).
     *          (List.of(...) també és serialitzable, però així sempre desem el mateix tipus.)
     * carrega: llegeix l'objecte amb un ObjectInputStream i retorna'l com a List<Joc>.
     *          readObject pot llançar ClassNotFoundException: embolica-la en una IOException
     *          amb un missatge clar i la causa original.
     * Tots dos amb try-with-resources.
     * 2a part: perquè funcioni, el record Joc ha de ser serialitzable (fitxer Joc.java).
     * Pista: sessió 6, Ex1Serialitza.
     */
    public void desa(Path p, List<Joc> jocs) throws IOException {
        try (java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(java.nio.file.Files.newOutputStream(p))) {

            out.writeObject(new java.util.ArrayList<>(jocs));
        }
    }


    /**
     * Recupera el catàleg desat amb {@link #desa(Path, List)}.
     *
     * @param p fitxer .ser
     * @return la llista de jocs recuperada
     * @throws IOException si el fitxer no existeix, està corromput o la classe no es troba
     */
    @SuppressWarnings("unchecked")
    public List<Joc> carrega(Path p) throws IOException {
        // Escriu aquí el teu codi (PR1-11)
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(p))) {
            return (List<Joc>) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("No s'ha trobat la classe en llegir el fitxer binari", e);
        }
    }
}