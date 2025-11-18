/**
 * Classe che rappresenta un cavallo partecipante alla gara.
 * Ogni cavallo è un Thread che compie 10 passi, simulati con sleep().
 */
public class Cavallo extends Thread {

    /** Nome del cavallo */
    private final String nome;

    /** Tempo di attesa (ritardo) tra un passo e l'altro */
    private int tempoAttesa;

    /**
     * Costruttore del cavallo.
     * @param nome nome del cavallo
     */
    public Cavallo(String nome) {
        super();
        this.nome = nome;
    }

    /**
     * Logica del thread: il cavallo compie 10 passi
     * e se è il primo a terminare imposta il vincitore.
     */
    @Override
    public void run() {

        System.out.println("Cavallo " + nome + " inizia la corsa!");

        for (int i = 1; i <= 10; i++) {
            try {
                sleep(tempoAttesa);
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato azzoppato!");
                return;
            }
            System.out.println(nome + " cavalca - passo " + i);
        }

        synchronized (Gara.class) {
            if (Gara.getPrimo() == null) {
                Gara.setPrimo(nome);
            }
        }
    }

    /**
     * Restituisce il ritardo del cavallo.
     * @return tempo di attesa
     */
    public int getTempoAttesa() {
        return tempoAttesa;
    }

    /**
     * Imposta il ritardo del cavallo.
     * @param t tempo di attesa
     */
    public void setTempoAttesa(int t) {
        this.tempoAttesa = t;
    }

    /**
     * Restituisce il nome del cavallo.
     * @return nome del cavallo
     */
    public String getNome() {
        return nome;
    }

    /**
     * Azzoppa il cavallo interrompendo il thread.
     */
    public void fermaCavallo() {
        interrupt();
    }
}
