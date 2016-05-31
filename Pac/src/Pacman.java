import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pacman extends JFrame {
    public Pacman() {
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null); // 視窗位置置中
        this.getContentPane().setLayout(new BorderLayout()); 
        this.getContentPane().add(new GamePanel());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Pacman();
    }

    public class GamePanel extends JPanel {
        private Image pmImage; // 小精靈圖片 (64*64 pixels)
        private int x = 200; // 小精靈初始位置
        private int y = 200;
        boolean right, left, down, up; // Input booleans

        URL urlForImage;
        ImageIcon usFlag;

        public GamePanel() {
            loadImage("C");
            this.setFocusable(true); // 取得焦點, 否則KeyListener會無作用
            addKeyListener(new GameInput()); // Add it to the JPanel
        }

        // 讀取圖片
        public void loadImage(String img) {
            urlForImage = getClass().getResource("./images/"+img+".gif");
            usFlag = new ImageIcon(urlForImage);
            pmImage = usFlag.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.white); // 底色白色
            g.drawImage(pmImage, x, y, this); // 繪圖

            // 往下
            if (down) { loadImage("D"); y++; }

            // 往上
            if (up) { loadImage("U"); y--; }

            // 往右
            if (right) { loadImage("R"); x++; }

            // 左
            if (left) { loadImage("L"); x--; }

            // 延遲 - 小精靈跑的速度 
            for (int index = 0; index < 10000000; index++) {}

            repaint();
        }

        private class GameInput implements KeyListener {
            public void keyTyped(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_DOWN) down = false;
                if (e.getKeyCode() == e.VK_UP) up = false;
                if (e.getKeyCode() == e.VK_RIGHT) right = false;
                if (e.getKeyCode() == e.VK_LEFT) left = false;
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_DOWN) down = true;
                if (e.getKeyCode() == e.VK_UP) up = true;
                if (e.getKeyCode() == e.VK_RIGHT) right = true;
                if (e.getKeyCode() == e.VK_LEFT) left = true;
            }
        }
    }
}