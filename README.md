# GameVault v1 · PR1 · MP0486 Accés a dades

Prova pràctica del RA1. Nom i cognoms: **\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_**

El projecte ja compila i té tota l'estructura feta. Hi falten trossos de codi marcats amb
`TODO PR1-01` … `TODO PR1-13`. Cada TODO explica què has de fer i et diu de quin exemple de classe surt.

## Com començar

1. Obre la carpeta `gamevault-pr1` amb **IntelliJ IDEA** (projecte Maven, Java 21).
2. Busca tots els forats: menú **View → Tool Windows → TODO**, o `Ctrl+Maj+F` i cerca `PR1-`.
3. Executa les proves: clic dret a `src/test/java` → **Run 'All Tests'** (o `mvn test`).
Al principi gairebé totes surten en vermell. Cada prova porta el codi del TODO al nom
(per exemple, `PR1-06 · quatre incidències…`): així saps quin forat t'ha fallat.
4. Quan tot estigui en verd, executa `GameVaultApp` (▶ al costat del `main`) i compara la sortida
amb la de l'enunciat.

## Normes

* **No modifiquis** cap classe de prova excepte `ProvesPropiesTest`, ni la classe `GameVaultApp`.
* No canviïs el nom ni els paràmetres dels mètodes que has de completar.
* Fes un commit cada vegada que tanquis un TODO (missatge: `PR1-06: llegeix el CSV amb incidències`).

## Forats que has de completar

|TODO|Classe|Què has de fer|Exemple de classe|
|-|-|-|-|
|PR1-01|`model/Joc`|Validar títol, hores i nota|Sessió 1|
|PR1-02|`fs/Magatzem`|Crear les carpetes import, export i backup|Sessió 2 · Ex3|
|PR1-03|`fs/Magatzem`|Còpia de seguretat amb marca de temps|Sessió 2 · Ex4|
|PR1-04|`fs/Magatzem`|Mida total amb `Files.walk`|Sessió 2 · Ex5|
|PR1-05|`io/RegistreOperacions`|Afegir una línia al log sense esborrar-lo|Sessió 5 · Ex1|
|PR1-06|`io/CsvJocDao`|Llegir el CSV brut amb incidències|Sessió 5 · Ex3|
|PR1-07|`io/CsvJocDao`|Escriure el catàleg en CSV|Sessió 5 · Ex4|
|PR1-08|`io/JsonJocDao`|Configurar Jackson i escriure JSON|Sessió 6 · Ex4|
|PR1-09|`io/JsonJocDao`|Llegir JSON|Sessió 6 · Ex4|
|PR1-10|`servei/Convertidor`|Desar per extensió i convertir|Sessions 5 i 6|
|PR1-11|`io/CauCataleg` i `model/Joc`|Serialitzar i desserialitzar|Sessió 6 · Ex1|
|PR1-12|`io/IndexHores`|Llegir les hores amb `seek`|Sessió 5 · Ex5|
|PR1-13|`io/IndexHores`|Sumar hores sense reescriure el fitxer|Sessió 5 · Ex5|
|PR1-14|`ProvesPropiesTest`|Escriure dues proves teves|—|
|PR1-15|Aquest README|Respondre les tres preguntes de sota|—|

## PR1-15 · Les meves respostes

Respon amb les teves paraules (mínim 4 línies cadascuna).

**1. Accés seqüencial i accés aleatori.** Per què `sumaHores` no necessita reescriure tot el fitxer
`hores.dat`? Què faries si haguessis de sumar hores a un joc dins de `catalog.csv`? Quan et compensa
cada forma d'accés?

> Resposta::L'accés seqüencial obliga a recórrer el fitxer des del principi fins a trobar la informació o reescriure el fitxer sencer per modificar una dada. En canvi, l'accés aleatori amb `RandomAccessFile` permet posicionar el punter directament en un byte determinat

**2. Triar format.** GameVault ha d'enviar el catàleg a una botiga web i també vol una cau per arrencar
més de pressa. Quin format faries servir per a cada cas (CSV, JSON o serialització Java) i per què?

> Resposta:

Cada format té uns avantatges i inconvenients clars segons les necessitats de l'aplicació:



\- CSV:És un format de text senzill que es pot obrir amb qualsevol full de càlcul (com Excel). És ideal per a la importació i exportació manual de dades humanament llegibles. En auant aspectes negatius, no preserva bé els tipus de dades complexos. 



\- JSON: És el format estàndard per a intercanvi de dades en APIs web. Proporciona una estructura clara,i fácil de fer servie amb diferents  llenguatges i sistemes.

&#x20;

\- Serialització binària :És un format propietari de Java molt ràpid d'escriure i llegir, ja que guarda l'estat exacte dels objectes de la memòria en disc. És la millor opció per a memòries cau d'accés intern ràpid, però no serveix com a format d'intercanvi perquè cap altre llenguatge ni versió incompatible de la classe el pot llegir.



**3. Excepcions.** Explica què passa en el teu programa quan el CSV té una línia amb la data mal escrita.
Per què el programa no s'atura? On es guarda el motiu?

> Resposta:

Quan el `CsvJocDao` processa una línia del CSV amb la data mal formada (com `25-09-2020` en lloc del format ISO `2020-09-25`), el mètode `LocalDate.parse()` llança una excepció `DateTimeParseException`. Dins del bucle de lectura de `CsvJocDao`, aquesta excepció és capturada per un bloc `catch`. En lloc de permetre que el programa peti o s'aturi el procés d'importació, es manté una llista d'incidències on s'afegeix un missatge explicatiu indicant el número de línia exacte i el motiu de l'error. Aquesta línia es salta i el bucle passa  a llegir la següent línia del fitxer CSV. 



Ús d'eines d'IA

Indica si has fet servir alguna eina d'IA, per a què i en quins TODO. Si no n'has fet servir, escriu-ho.

> Declaració: He fet servir IA a gairebé tots els TODO o bé per corregir si la sintaxis no era correcta, o bé si no ho conseguía resoldre per el meu compte. 

