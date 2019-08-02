package interfaztictactoe;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.*;
import javax.xml.ws.BindingProvider;

public final class Window {
    private final ecci_tictactoe.ECCITictactoePort ticTacToe;
    
    JFrame frame = null;
    JPanel panel = null;
    JPanel panelMatriz = null;
    JButton[][] buttons = null;
    JButton game = null;
    JButton restart = null;
    
    int[] posJugada = null;
    boolean ganador = false;
    int blancos = 9;
    
    /** Constructor. Se encarga de inicializar los componentes. */
    Window() {
        ecci_tictactoe.ECCITictactoe tictac = new ecci_tictactoe.ECCITictactoe();
        ticTacToe = tictac.getECCITictactoePort();
        ((BindingProvider)ticTacToe).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        
        posJugada = new int[2];
        addButtons();
        addGRButton();
        
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(panelMatriz);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    /** Este método se encarga de inicializar los botones correspondientes a la matriz del juego y los agrega a la ventana principal. */
    public void addButtons() {
        panelMatriz = new JPanel();
        panelMatriz.setBounds(100, 50, 300, 250);
        panelMatriz.setLayout(new GridLayout(3,3));
        
        buttons = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                JButton newButton = new JButton();
                
                newButton.setToolTipText(Integer.toString(x) + "," + Integer.toString(y));
                newButton.addActionListener((ActionEvent e) -> {
                    matriz(newButton);
                });
                buttons[x][y] = newButton;
                panelMatriz.add(buttons[x][y]);
            }
        }
    }
    
    /** Inicializa los botones de Jugar y Reiniciar Juego y los agrega a la ventana principal. */
    public void addGRButton() {
        panel = new JPanel();
        panel.setBounds(210, 350, 85, 70);
        panel.setLayout(new FlowLayout());
        
        game = new JButton("Jugar");
        game.addActionListener((ActionEvent e) -> {
            jugar();
        });
        
        restart = new JButton("Reiniciar");
        restart.addActionListener((ActionEvent e) -> {
            reiniciar();
        });
        panel.add(game);
        panel.add(restart);
    }
    
    /**
     * Método que se ejecuta al presionar un botón de la matriz.
     * Escribe una ‘X’ (jugada actual) en el botón
     * y limpia los demás botones en la matriz para que no haya opciones repetidas.
     * 
     * @param btn Boton que fue presionado.
     */
    private void matriz(JButton btn) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].isEnabled()) {
                    buttons[x][y].setText(" ");
                }
            }
        }
        
        btn.setText("X");
        
        String aux = btn.getToolTipText();
        String[] auxSplit = aux.split(",");
        
        posJugada[0] = Integer.parseInt(auxSplit[0]);
        posJugada[1] = Integer.parseInt(auxSplit[1]);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
    }
    
    /** 
     * Método que se ejecuta al presionar el botón de jugar.
     * Se encarga de ejecutar toda la lógica del juego, buscar ganadores y dar por finalizado el juego.
     */
    private void jugar() {
        ticTacToe.setJugada(posJugada[0], posJugada[1], "X");
        blancos--;
        
        if(!ticTacToe.verificarGane("X")) {
            if (blancos > 0) {
                Random r = new Random();
                int a = r.nextInt(3);
                int b = r.nextInt(3);
                
                while (!ticTacToe.getIndiceMatriz(a, b).equals(" ") || ticTacToe.getIndiceMatriz(a, b).equals("")) {
                    a = r.nextInt(3);
                    b = r.nextInt(3);
                }
                
                ticTacToe.setJugada(a, b, "O");
                blancos--;
                
                if (ticTacToe.verificarGane("O")) {
                    ganador = true;
                    actualizarLayoutFinal();
                    JOptionPane.showMessageDialog(null, "PERDISTE! 'O' GANÓ");
                } else {
                    actualizarLayout();
                }
            } else if (!ganador) {
                actualizarLayout();
                JOptionPane.showMessageDialog(null, "ES UN EMPATE!");
            }
        } else {
            ganador = true;
            actualizarLayoutFinal();
            JOptionPane.showMessageDialog(null, "GANASTE!");
        }
    }
    
    /** Actualiza la interfaz luego de cada jugada. */
    private void actualizarLayout() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!ticTacToe.getIndiceMatriz(x, y).equals(" ")) {
                    buttons[x][y].setText(ticTacToe.getIndiceMatriz(x, y));
                    buttons[x][y].setFont(new Font("Comic Sans MS", Font.BOLD, 50));
                    buttons[x][y].setEnabled(false);
                }
            }
        }
    }
    
    /** Actualiza la interfaz al final del juego. Desactiva los botones de la matriz y el botón jugar. */
    private void actualizarLayoutFinal() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setEnabled(false);
                buttons[x][y].setText(ticTacToe.getIndiceMatriz(x, y));
                buttons[x][y].setFont(new Font("Comic Sans MS", Font.BOLD, 50));
            }
        }
        game.setEnabled(false);
    }
    
    /** Limpia la interfaz, las estructuras de datos y permite iniciar un juego nuevo desde cero. */
    private void reiniciar() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                ticTacToe.setJugada(x, y, "_");
                buttons[x][y].setText(" ");
                buttons[x][y].setEnabled(true);
                game.setEnabled(true);
                blancos = 9;
                ganador = false;
            }
        }
    }
}