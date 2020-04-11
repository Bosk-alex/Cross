package cross;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    GameManager gm = new GameManager();

    // 24.1 чтобы заполнить поле
    public static int[][] field;
    public static int mode;
    public static int fieldSizeX;
    public static int fieldSizeY;
    public static int winLength;
    // набор переменных для игры 2 человек
    int playerDot = 1;
    int temp = 2;
    int tmp;
    String player = "Player";
    String tempPl = "Player 2";
    String tmpPl;
    // 25 высота и ширина каждоый ячейки
    int cellHeight = 1;
    int cellWidth = 1;

    // 27 если ничего не нарисовано
    boolean isInitialized = false;

    // 10 создаем конструктор и задаем цвет поля
    Map() {
        setBackground(new Color(150,255,255));
        // 30 создаем слушателя шелчка мышки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });

    }

    // 31 создаем метод который определяем куда челкнули
    void update(MouseEvent e) {
        // пиксели делим на ширину и высоту
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.println("x: " + cellX + " y: " + cellY);
        // роверка победы до хода
        if (gm.checkWin(playerDot)) {
            System.out.println("Player WIN!");
            JOptionPane.showMessageDialog(this, player + " WIN!", "Dialog message", JOptionPane.INFORMATION_MESSAGE);

            return;
        }
        if (gm.checkWin(2)) {
            System.out.println("SkyNet WIN!");
            JOptionPane.showMessageDialog(this, "SkyNet WIN!", "Dialog message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // запись хода игрока, проверки после хода
        if (gm.isCellValid(cellY, cellX)) {
            field[cellY][cellX] = playerDot;
            repaint();
        } else return;

        if (gm.checkWin(playerDot)) {
            System.out.println("Player WIN!");
            JOptionPane.showMessageDialog(this, player + " WIN!", "Dialog message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (gm.isFieldFull()) {
            System.out.println("DRAW");
            JOptionPane.showMessageDialog(this, "DRAW", "Dialog message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // если два игрока - меняем названия игроков и крестик на нолик
        if (mode == 1) {
            tmpPl = player;
            player = tempPl;
            tempPl = tmpPl;
            tmp = playerDot;
            playerDot = temp;
            temp = tmp;
            return;
        }
        // ходит компьютер, проверки после хода
        gm.aiStep();
        repaint();
        if (gm.checkWin(2)) {
            System.out.println("SkyNet WIN!");
            JOptionPane.showMessageDialog(this, "SkyNet WIN!", "Dialog message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (gm.isFieldFull()) {
            System.out.println("DRAW");
            JOptionPane.showMessageDialog(this, "DRAW", "Dialog message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for(int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 24 метод для рисования нашего поля в целом
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    // 11 создаем метод который говорит о типе игры, размеры поля, и выиграшная длина
    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength){
        // заглушка
        System.out.println("mode = " + mode +
                " fsX = " + fieldSizeX +
                " fsy = " + fieldSizeY +
                " winLen = " + winLength);
        //25 запоняем поля при старте новой игры
        this.mode = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        field = new int[fieldSizeY][fieldSizeX];
        if (mode == 1) player = "Player 1";
        tempPl = "Player 2";
        playerDot = 1;
        temp = 2;

        isInitialized = true;
        // 28 говорим панели перерисоваться
        repaint();
    }

    // 24.1 метод для рисование
    void render(Graphics g) {
        if(!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int margin = cellHeight / 10;
        // узнаем размер ечеек
        cellHeight = panelHeight /fieldSizeX;
        cellWidth = panelWidth / fieldSizeY;

        // 26 отрисовываем по Y (горизонтальные полоски)
        for(int i = 1; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        // 29 отрисовываем по X (вертикальные полоски)
        for(int i = 1; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for(int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == 1) {
                    g.drawLine(j * cellHeight + margin, i * cellWidth + margin, (j + 1) * cellHeight - margin, (i + 1) * cellWidth - margin);
                    g.drawLine((j + 1) * cellHeight - margin, i * cellWidth + margin, j * cellHeight + margin, (i + 1) * cellWidth - margin);
                } else if (field[i][j] == 2) {
                    g.drawOval(j * cellHeight + margin, i * cellWidth + margin, cellHeight - margin * 2, cellWidth - margin * 2);
                }
            }
        }
    }


}