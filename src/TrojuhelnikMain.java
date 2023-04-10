import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class TrojuhelnikMain extends JFrame{
    private JPanel panel;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JButton lzeSestrojitButton;
    private JButton kopirujButton;

    private JFileChooser chooser = new JFileChooser(".");

    public TrojuhelnikMain() {
        setTitle("Trojúhelník");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuSoubor = new JMenu("Soubor");
        JMenu menuAkce = new JMenu("Akce");
        menuBar.add(menuSoubor);
        menuBar.add(menuAkce);

        JMenuItem nactiItem = new JMenuItem("Načti...");
        nactiItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vyberSoubor();
            }
        });
        JMenuItem ulozItem = new JMenuItem("Ulož...");
        ulozItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vyberUkladanySoubor();
            }
        });
        menuSoubor.add(nactiItem);
        menuSoubor.add(ulozItem);

        JMenuItem kopirujItem = new JMenuItem("Kopíruj A do B i C");
        kopirujItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kopiruj();
            }
        });
        JMenuItem lzeSestrojitItem = new JMenuItem("Lze sestrojit trojúhelník?");
        lzeSestrojitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lzeSestrojit();
            }
        });
        menuAkce.add(kopirujItem);
        menuAkce.add(lzeSestrojitItem);


        kopirujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kopiruj();
            }
        });

        lzeSestrojitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lzeSestrojit();
            }
        });
    }

    private void kopiruj() {
        String cislo = textFieldA.getText();
        textFieldB.setText(cislo);
        textFieldC.setText(cislo);
    }

    private void lzeSestrojit() {
        int a = Integer.parseInt(textFieldA.getText());
        int b = Integer.parseInt(textFieldB.getText());
        int c = Integer.parseInt(textFieldC.getText());
        if(a+b > c){
            JOptionPane.showMessageDialog(null, "Ze stran délek A: "+a+", B: "+b+" a C: "+c+" lze sestrojit trojúhelník");
        }
        else {
            JOptionPane.showMessageDialog(null, "Ze stran délek A: "+a+", B: "+b+" a C: "+c+" nelze sestrojit trojúhelník");
        }

    }
    private void vyberSoubor() {
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            nactiSoubor(selectedFile);
        }
    }

    private void nactiSoubor(File selectedFile){
        try (
                Scanner scanner =
                        new Scanner(new BufferedReader(
                                new FileReader(selectedFile)))
        ) {
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                String[] polozky = radek.split(";");
                textFieldA.setText(polozky[0]);
                textFieldB.setText(polozky[1]);
                textFieldC.setText(polozky[2]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void vyberUkladanySoubor() {
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            ulozSoubor(selectedFile);
        }
    }

    private void ulozSoubor(File soubor) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(soubor)))
        ) {
            writer.print(""+textFieldA.getText()+";"+textFieldB.getText()+";"+textFieldC.getText());

        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


    public static void main(String[] args) {
        TrojuhelnikMain t = new TrojuhelnikMain();
        t.setContentPane(t.panel);
        t.pack();
        t.setResizable(false);
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
