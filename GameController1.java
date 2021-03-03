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

import static xyz.chengzi.halma.StartChess.mainFrame;


public class GameController1 extends GameController implements InputListener {
    private ChessBoardComponent view;
    private ChessBoard model;

    public int getChessnumber() {
        return Chessnumber;
    }

    public void setChessnumber(int chessnumber) {
        Chessnumber = chessnumber;
    }

    private int Chessnumber = 38;

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


    public GameController1(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        super(chessBoardComponent, chessBoard);
        this.view = chessBoardComponent;
        this.model = chessBoard;
        view.registerListener(this);
        model.registerListener(view);
        model.placeInitialPieces1();

    }

    public boolean hasgWinner() {
        int k = 5;
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
            k = 4;
        } else if (i == 3) {
            k = 3;
        } else if (i == 4) {
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
    public boolean hasrWinner(){
        int m=14;
        for (int i=11;i<16;i++){
            if (i==13){
            m=12;
        }else if (i==12){
            m=13;
        }else if (i==14){
            m=11;
        }
            for (int j=m;j<16;j++){

                if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i,j)).getPiece()!=null) {
                    if (StartChess.controller.getModel().getGridAt(new ChessBoardLocation(i, j)).getPiece().getColor() != Color.RED) {
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(StartChess.mainFrame, "嗨皮的头 胜利！！！");

        return true;
    }

    public void restartGame(){
        model.placeInitialPieces1();
    }

    public Color nextPlayer() {
       StartChess.controller.hasrWinner();
       StartChess.controller.hasgWinner();
       if (GameFrame.a==0) {
           if (currentColor == Color.RED) {
               StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
               return currentColor = Color.GREEN;
           } else {
               StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
               return currentColor = Color.RED;
           }
       }else {
           if (currentColor == Color.RED) {
               StartChess.mainFrame.statusLabel.setText("happy leg");
               return currentColor = Color.GREEN;
           } else {
               StartChess.mainFrame.statusLabel.setText("happy head");
               return currentColor = Color.RED;
           }
       }
    }
    public Color regretPlayer() {
        if (StartChess.controller.isHasMoved()) {
            if (GameFrame.a==0) {
                if (currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
                    return currentColor = Color.RED;
                } else {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
                    return currentColor = Color.GREEN;
                }
            }else {
                if (currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("happy head");
                    return currentColor = Color.RED;
                } else {
                    StartChess.mainFrame.statusLabel.setText("happy leg");
                    return currentColor = Color.GREEN;
                }
            }
        } else {
            if (GameFrame.a == 0) {
                if (currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
                    return currentColor = Color.GREEN;
                } else {
                    StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
                    return currentColor = Color.RED;
                }
            }else {
                if (currentColor == Color.RED) {
                    StartChess.mainFrame.statusLabel.setText("happy leg");
                    return currentColor = Color.GREEN;
                } else {
                    StartChess.mainFrame.statusLabel.setText("happy head");
                    return currentColor = Color.RED;
                }
            }
        }
    }
}
