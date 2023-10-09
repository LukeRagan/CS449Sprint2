package guiguy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SOSProjectSprint1 {
    private static ButtonGroup buttonGroupRed;
    private static ButtonGroup buttonGroupBlue;
    private static boolean isRedSClicked = false; //tracks S is selected for red player
    private static boolean isRedOClicked = false; // tracks O is selected for red player
    private static boolean isBlueSClicked = false; //tracks S is selected for blue player
    private static boolean isBlueOClicked = false; // tracks O is selected for blue player
    private static int boardSize = 7; // Default board size
    private static boolean isRedTurn = true; // Red player's turn initially
    private static JLabel turnLabel; // Label to display the current player's turn

    public static void main(String[] args) {
        JFrame frame = new JFrame("SOS Game");

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JLabel blueLabel = new JLabel("Blue Player");
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(blueLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //blue button group
        buttonGroupBlue = new ButtonGroup();
        JRadioButton radioButtonBlue1 = new JRadioButton("S");
        JRadioButton radioButtonBlue2 = new JRadioButton("O");

        buttonGroupBlue.add(radioButtonBlue1);
        buttonGroupBlue.add(radioButtonBlue2);

        // makes the buttons stay in the middle of the right side of the screen
        leftPanel.add(radioButtonBlue1);
        leftPanel.add(radioButtonBlue2);
        leftPanel.add(Box.createVerticalGlue());

        JCheckBox checkBox = new JCheckBox("Record??");
        leftPanel.add(checkBox);

        //makes right panel and sets border
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        //title buttons
        JPanel horizontalRadioButtonsPanel = new JPanel();
        horizontalRadioButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // creates label for the title of the game
        JLabel label = new JLabel("SOS Game");
        horizontalRadioButtonsPanel.add(label);

        JRadioButton horizontalRadioButton1 = new JRadioButton("Simple Game");
        JRadioButton horizontalRadioButton2 = new JRadioButton("General Game");

        ButtonGroup horizontalButtonGroup = new ButtonGroup();
        horizontalButtonGroup.add(horizontalRadioButton1);
        horizontalButtonGroup.add(horizontalRadioButton2);

        horizontalRadioButtonsPanel.add(horizontalRadioButton1);
        horizontalRadioButtonsPanel.add(horizontalRadioButton2);

        // making panel for radio buttons on the right of the screen
        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.Y_AXIS));

        // label for board size adjuster
        JLabel boardSizeLabel = new JLabel("Board Size");
        radioButtonsPanel.add(boardSizeLabel);

        // making buttons for red player
        JLabel redLabel = new JLabel("Red Player");
        radioButtonsPanel.add(Box.createVerticalGlue());
        radioButtonsPanel.add(redLabel);
        radioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonGroupRed = new ButtonGroup(); // Changed from local variable to class variable
        JRadioButton radioButtonRed1 = new JRadioButton("S");
        JRadioButton radioButtonRed2 = new JRadioButton("O");

        buttonGroupRed.add(radioButtonRed1);
        buttonGroupRed.add(radioButtonRed2);

        // Add action listeners to radio buttons for red player
        radioButtonRed1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRedSClicked = true;
                isRedOClicked = false;
            }
        });

        radioButtonRed2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRedSClicked = false;
                isRedOClicked = true;
            }
        });
     // Add action listeners to radio buttons for blue player
        radioButtonBlue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBlueSClicked = true;
                isBlueOClicked = false;
            }
        });

        radioButtonBlue2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBlueSClicked = false;
                isBlueOClicked = true;
            }
        });

        // makes the buttons stay in the middle of the right side of the screen
        radioButtonsPanel.add(radioButtonRed1);
        radioButtonsPanel.add(radioButtonRed2);
        radioButtonsPanel.add(Box.createVerticalGlue());

        // making game board size
        final JPanel gameBoardPanel = new JPanel();
        updateGameBoard(gameBoardPanel, boardSize); // Initialize the game board

        // making board size adjuster
        final JSpinner boardSizeSpinner = new JSpinner();
        boardSizeSpinner.setModel(new SpinnerNumberModel(boardSize, 3, 8, 1)); //default, minimum, and maximum
        boardSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boardSize = (int) boardSizeSpinner.getValue();
                updateGameBoard(gameBoardPanel, boardSize);
            }
        });
        radioButtonsPanel.add(boardSizeSpinner);

        // Create a label to display the current player's turn
        turnLabel = new JLabel("Red Player's Turn");
        rightPanel.add(turnLabel, BorderLayout.SOUTH); // Add the label to the bottom of rightPanel

        rightPanel.add(radioButtonsPanel, BorderLayout.CENTER);
        rightPanel.add(gameBoardPanel, BorderLayout.CENTER);
        rightPanel.add(horizontalRadioButtonsPanel, BorderLayout.NORTH);

        // makes the panels move to the respective side of the screen
        JPanel mainPanel = new JPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        mainPanel.add(radioButtonsPanel, BorderLayout.EAST);

        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomLeftPanel.add(checkBox);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        containerPanel.add(bottomLeftPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(containerPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setVisible(true);
    }

    private static void updateGameBoard(JPanel gameBoardPanel, int size) {
        gameBoardPanel.removeAll(); // Clear previous components
        gameBoardPanel.setLayout(new GridLayout(size, size)); // Set new grid layout

        int cellSize = 50;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton button = new JButton();
                button.setPreferredSize(new Dimension(cellSize, cellSize));
                button.setBackground(Color.GREEN);
                button.setBorder(BorderFactory.createLineBorder(Color.black));
                gameBoardPanel.add(button);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isRedTurn) {
                        	button.setForeground(Color.RED);
                            if (isRedSClicked) {
                                button.setText("S");
                            } else if (isRedOClicked) {
                                button.setText("O");
                            }
                            turnLabel.setText("Blue Player's Turn"); // Set label text for Blue Player's Turn
                        } else {
                        	button.setForeground(Color.BLUE);
                            if (isBlueSClicked) {
                                button.setText("S");
                            } else if (isBlueOClicked) {
                                button.setText("O");
                            }
                            turnLabel.setText("Red Player's Turn"); // Set label text for Red Player's Turn
                        }
                        isRedTurn = !isRedTurn; // Switch turn
                    }
                });
            }
        }

        gameBoardPanel.revalidate();
        gameBoardPanel.repaint();
    }
}
