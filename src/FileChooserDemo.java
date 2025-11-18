import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe che dimostra l'uso di JFileChooser per aprire e salvare file.
 * Mostra un'interfaccia semplice con due pulsanti e un'area di log.
 */
public class FileChooserDemo extends JPanel implements ActionListener {

    /** Carattere per andare a capo nelle stampe */
    static private final String newline = "\n";

    /** Pulsante per aprire file */
    JButton apriBtn;

    /** Pulsante per salvare file */
    JButton salvaBtn;

    /** Area di testo che mostra i log delle operazioni */
    JTextArea area;

    /** Selettore grafico dei file */
    JFileChooser chooser;

    /**
     * Costruttore: inizializza pulsanti, area log e JFileChooser.
     */
    public FileChooserDemo() {
        super(new BorderLayout());

        area = new JTextArea(5,20);
        area.setMargin(new Insets(5,5,5,5));
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        chooser = new JFileChooser();

        apriBtn = new JButton("Apri File...");
        apriBtn.addActionListener(this);

        salvaBtn = new JButton("Salva File...");
        salvaBtn.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(apriBtn);
        panel.add(salvaBtn);

        add(panel, BorderLayout.PAGE_START);
        add(scroll, BorderLayout.CENTER);
    }

    /**
     * Gestisce le azioni dei pulsanti: apertura o salvataggio file.
     * @param e evento generato dal pulsante
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == apriBtn) {

            int returnVal = chooser.showOpenDialog(FileChooserDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                area.append("Apertura: " + file.getName() + "." + newline);
            } else {
                area.append("Apertura cancellata." + newline);
            }

            area.setCaretPosition(area.getDocument().getLength());

        } else if (e.getSource() == salvaBtn) {

            int returnVal = chooser.showSaveDialog(FileChooserDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                area.append("Salvataggio: " + file.getName() + "." + newline);
            } else {
                area.append("Salvataggio cancellato." + newline);
            }

            area.setCaretPosition(area.getDocument().getLength());
        }
    }

    /**
     * Avvia la GUI creando una finestra Swing.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FileChooserDemo());
        frame.pack();
        frame.setVisible(true);
    }
}
