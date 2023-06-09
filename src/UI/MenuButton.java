package UI;

import Gamestate.GameState;
import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;

public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffSetCenter = B_WIDTH / 2;
    private boolean mouseOver, mousePressed;
    private GameState state;
    BufferedImage [] img;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loading();
        initBounds();
    }

    // tạo 1 vùng bao quanh MenuButton
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffSetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    /*
        Xem cái ảnh button_atlas.png có 3 loại nút và 3 trạng thái của nó
        Khi khởi tạo đối tượng MenuButton sẽ có thuộc tính rowIndex
        getSubimage() là nó cắt cái ảnh cho từng nút thành 3 trạng thái thêm vào mảng img
        phải hiểu tham số trong getSubimage() nhé
     */

    private void loading() {
        img = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img[index], xPos - xOffSetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    /*
        ta có 3 trạng thái của nút sau khi cắt ảnh
        index = 0 là trạng thái mc định
        index = 1 là khi di chuột vào nút sẽ sáng lên (mouseOver)
        index = 2 là khi nhấn chuột nút sẽ ấn xuống (mousePress)
     */

    public void update(){
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {
        GameState.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public GameState getState() {
        return state;
    }
}
