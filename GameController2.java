package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.GameFrame;
import xyz.chengzi.halma.listener.InputListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.SquareComponent;
import xyz.chengzi.halma.StartChess;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class GameController2 extends GameController implements InputListener {
    private ChessBoardComponent view;
    private ChessBoard model;

    public int getChessnumber() {
        return Chessnumber;
    }

    public void setChessnumber(int chessnumber) {
        Chessnumber = chessnumber;
    }

    private int Chessnumber=52;


    @Override
    public ChessBoardComponent getView() {
        return view;
    }

    @Override
    public void setView(ChessBoardComponent view) {
        this.view = view;
    }

    @Override
    public ChessBoard getModel() {
        return model;
    }

    @Override
    public void setModel(ChessBoard model) {
        this.model = model;
    }
    public Color currentColor;

    public GameController2(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        super(chessBoardComponent,chessBoard);
        this.view = chessBoardComponent;
        this.model = chessBoard;
        view.registerListener(this);
        model.registerListener(view);
        model.placeInitialPieces2();
    }
    public void restartGame(){
        model.placeInitialPieces2();
    }
    public boolean hasgWinner() {
        int k = 4;
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
            k = 3;
        } else if (i == 3) {
            k = 2;
        }
            for (int j = 0; j < k; j++) {

                if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece() != null) {
                    if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece().getColor() != Color.GREEN) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(StartChess.mainFrame, "嗨皮的腿 胜利！！！");

        return true;
    }
    public boolean hasrWinner() {
        int m = 12;
        for (int i = 15; i > 11; i--) {
            if (i == 13) {
            m = 13;
        } else if (i == 12) {
            m = 14;
        }
            for (int j = m; j < 16; j++) {

                if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece() != null) {
                    if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece().getColor() != Color.RED) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(StartChess.mainFrame, "嗨皮的头 胜利！！！");

        return true;

    }
    public boolean hasbWinner() {
        int n = 2;
        for (int i = 12; i < 16; i++) {
            if (i == 13) {
            n = 3;
        } else if (i == 14 || i == 15) {
            n = 4;
        }
            for (int j = 0; j < n; j++) {

                if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece() != null) {
                    if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece().getColor() != Color.BLACK) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(StartChess.mainFrame, "嗨皮的脖子 胜利！！！");

        return true;
    }
    public boolean haswWinner() {
        int x = 12;
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
            x = 13;
        } else if (i == 3) {
            x = 14;
        }
            for (int j = x; j < 16; j++) {

                if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece() != null) {
                    if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece().getColor() != Color.WHITE) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(StartChess.mainFrame, "嗨皮的辫子 胜利！！！");
        return true;
    }
    public Color nextPlayer() {
        StartChess.controller.hasrWinner();
        StartChess.controller.hasgWinner();
        StartChess.controller.hasbWinner();
        StartChess.controller.haswWinner();
            if (StartChess.controller.currentColor == Color.RED) {
                StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的脖子");
                return StartChess.controller.currentColor = Color.BLACK;
            } else if (StartChess.controller.currentColor == Color.BLACK) {
                StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
                return StartChess.controller.currentColor = Color.GREEN;
            } else if (StartChess.controller.currentColor == Color.GREEN) {
                StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的辫子");
                return StartChess.controller.currentColor = Color.WHITE;
            } else {
                StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
                return StartChess.controller.currentColor = Color.RED;
            }
    }
    public Color regretPlayer() {
        if (StartChess.controller.isHasMoved()) {
                if (StartChess.controller.currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
                    return StartChess.controller.currentColor = Color.RED;
                } else if (StartChess.controller.currentColor == Color.BLACK) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的脖子");
                    return StartChess.controller.currentColor = Color.BLACK;
                } else if (StartChess.controller.currentColor == Color.GREEN) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
                    return StartChess.controller.currentColor = Color.GREEN;
                } else {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的辫子");
                    return StartChess.controller.currentColor = Color.WHITE;
                }
            } else {
                if (StartChess.controller.currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的辫子");
                    return StartChess.controller.currentColor = Color.white;
                } else if (StartChess.controller.currentColor == Color.BLACK) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
                    return StartChess.controller.currentColor = Color.RED;
                } else if (StartChess.controller.currentColor == Color.GREEN) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的脖子");
                    return StartChess.controller.currentColor = Color.BLACK;
                } else {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
                    return StartChess.controller.currentColor = Color.green;
                }
            }
    }
}
