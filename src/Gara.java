import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*; // JFileChooser

/**
 * Classe principale che gestisce la gara tra cavalli.
 * Permette di aggiungere cavalli, impostare ritardi, avviare la gara,
 * azzoppare un cavallo e salvare i risultati.
 */
public class Gara {

    /** Nome del primo cavallo arrivato */
    static String primoArrivato;

    /**
     * Metodo principale che gestisce il menu utente.
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;
        ArrayList<Cavallo> listaCavalli = new ArrayList<>();
        int scelta;

        while (continua) {

            System.out.println("1.Inserisci Cavallo \n2.Inserisci ritardo del Cavallo \n3.Fai cominciare la Gara\n4.Azzoppa un cavallo\n5.Esci");
            System.out.println("Inserisci la tua opzione:");
            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {

                case 1:
                    System.out.println("Inserisci il nome del Cavallo:");
                    String nomeCavallo = scanner.nextLine();
                    listaCavalli.add(new Cavallo(nomeCavallo));
                    break;

                case 2:
                    int i = 1;
                    System.out.println("Cavalli in Gara:");
                    for (Cavallo c : listaCavalli) {
                        System.out.println(i++ + " " + c.getNome());
                    }

                    System.out.println("Inserisci il nome del Cavallo:");
                    String nomeRicerca = scanner.nextLine();
                    System.out.println("Inserisci il tempo di ritardo del cavallo:");
                    int tempo = scanner.nextInt();
                    scanner.nextLine();

                    boolean trovato = false;
                    for (Cavallo c : listaCavalli) {
                        if (c.getNome().equals(nomeRicerca)) {
                            c.setTempoAttesa(tempo);
                            trovato = true;
                            break;
                        }
                    }
                    if (!trovato) {
                        System.out.println("Cavallo non trovato. Riprova!.");
                    }
                    break;

                case 3:
                    if (listaCavalli.isEmpty()) {
                        System.out.println("Non sono ancora presenti cavalli nella corsa");
                        break;
                    }

                    Gara.primoArrivato = null;

                    ArrayList<Cavallo> gara = new ArrayList<>();
                    for (Cavallo c : listaCavalli) {
                        gara.add(new Cavallo(c.getNome()));
                        gara.get(gara.size() - 1).setTempoAttesa(c.getTempoAttesa());
                    }

                    for (Cavallo c : gara) c.start();

                    for (Cavallo c : gara) {
                        try {
                            c.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Il vincitore è " + primoArrivato);

                    salvaRisultato("Il vincitore è " + primoArrivato);
                    break;

                case 4:
                    if (!listaCavalli.isEmpty()) {
                        int rand = (int) (Math.random() * listaCavalli.size());
                        listaCavalli.get(rand).fermaCavallo();
                        System.out.println(listaCavalli.get(rand).getNome() + " è stato azzoppato!");
                    }
                    break;

                case 5:
                    continua = false;
                    break;

                default:
                    System.out.println("Opzione non valida!");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Imposta il nome del primo cavallo arrivato.
     * @param n nome del cavallo vincitore
     */
    public static synchronized void setPrimo(String n) {
        primoArrivato = n;
    }

    /**
     * Restituisce il nome del cavallo vincitore.
     * @return nome del primo cavallo arrivato
     */
    public static synchronized String getPrimo() {
        return primoArrivato;
    }

    /**
     * Salva il risultato della gara in un file scelto dall'utente tramite JFileChooser.
     * @param testo contenuto da salvare
     */
    public static void salvaRisultato(String testo) {
        try {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Dove vuoi salvare il risultato:");

            int ritorno = fc.showSaveDialog(null);

            if (ritorno == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(testo);
                    System.out.println("Risultato salvato in: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Errore nel salvataggio: " + e.getMessage());
                }
            } else {
                System.out.println("Salvataggio annullato.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
