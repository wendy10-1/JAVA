package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.InputListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.SquareComponent;
import xyz.chengzi.halma.topMenu;
import xyz.chengzi.halma.GameFrame;
import xyz.chengzi.halma.StartChess;


import javax.swing.*;

import static xyz.chengzi.halma.StartChess.controller;
import static xyz.chengzi.halma.StartChess.mainFrame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class GameController implements InputListener {
    private boolean hasMoved=false;
    public int count;

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    private ChessBoardComponent view;
    private ChessBoard model;
    private ChessBoardLocation selectedLocation;
    private int Chessnumber;

    public int getChessnumber() {
        return Chessnumber;
    }

    public void setChessnumber(int chessnumber) {
        Chessnumber = chessnumber;
    }
    public ChessBoardComponent getView() {
        return view;
    }

    public void setView(ChessBoardComponent view) {
        this.view = view;
    }

    public ChessBoard getModel() {
        return model;
    }

    public void setModel(ChessBoard model) {
        this.model = model;
    }

    public  Color currentColor;

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        this.view = chessBoardComponent;
        this.model = chessBoard;


    }
    public ChessBoardLocation getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(ChessBoardLocation location) {
        this.selectedLocation = location;
    }

    public void resetSelectedLocation() {
        setSelectedLocation(null);
    }
    public void restartGame(){

    }
    public boolean hasrWinner(){
        return false;
    }
    public boolean hasgWinner(){
        return false;
    }
    public boolean haswWinner(){
        return false;
    }
    public boolean hasbWinner(){
        return false;
    }


    public boolean hasSelectedLocation() {
        return selectedLocation != null;
    }

    public Color nextPlayer() {
        controller.hasrWinner();
        if (currentColor==Color.RED){
            mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
        }else {
            mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
        }
        return (StartChess.controller.currentColor==Color.RED)?Color.GREEN:Color.RED;
    }
    public Color regretPlayer(){
        if (currentColor == Color.RED) {
            StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的头");
            return currentColor = Color.RED;
        }else{
            StartChess.mainFrame.statusLabel.setText("当前玩家：嗨皮的腿");
            return currentColor = Color.GREEN;
        }
    }
    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (StartChess.controller.hasrWinner()||StartChess.controller.hasgWinner()||StartChess.controller.hasbWinner()||StartChess.controller.haswWinner()){
            URL url=null;

            URI uri;

            File f=new File("胜利.wav");

            uri=f.toURI();

            try {
                url=uri.toURL();

            } catch (MalformedURLException ex) {

                ex.printStackTrace();

            }

            AudioClip aau;

            aau= Applet.newAudioClip(url);

            aau.play();
        }
        if(selectedLocation!=null){
            URL url=null;

            URI uri;

            File f=new File("bo.wav");

            uri=f.toURI();

            try {

                url=uri.toURL();

            } catch (MalformedURLException ex) {

                ex.printStackTrace();

            }

            AudioClip aau;

            aau= Applet.newAudioClip(url);

            aau.play();
            GameFrame.list3.add(selectedLocation);
            if(Math.abs(selectedLocation.getRow()-location.getRow())==1||Math.abs(selectedLocation.getColumn()-location.getColumn())==1){
                if(isHasMoved()){
                    return;
                }else {
                    if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)){
                        GameFrame.list1.add(location);
                        model.moveChessPiece(selectedLocation,location);
                        GameFrame.list2.add(StartChess.controller.getModel().getGridAt(location).getPiece());
                        setHasMoved(true);
                        for (int i=0;i<16;i++) {
                            for (int j = 0; j < 16; j++) {
                                controller.getView().getGridComponents()[i][j].canMoveTo= false;
                                controller.getView().repaint();
                            }
                        }
                        resetSelectedLocation();
                        return;
                    }
                }
            }
            if(Math.abs(selectedLocation.getRow()-location.getRow())==2||Math.abs(selectedLocation.getColumn()-location.getColumn())==2){
                if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)){
                    GameFrame.list1.add(location);
                    model.moveChessPiece(selectedLocation,location);
                    GameFrame.list2.add(StartChess.controller.getModel().getGridAt(location).getPiece());
                    setHasMoved(true);
                    for (int i=0;i<16;i++) {
                        for (int j = 0; j < 16; j++) {
                            controller.getView().getGridComponents()[i][j].canMoveTo= false;
                            controller.getView().repaint();
                        }
                    }
                    resetSelectedLocation();
                    setSelectedLocation(location);
                }
            }
        }

    }
    public void helpMove(ChessBoardLocation src){
        for (int i=-2;i<3;i++) {
            for (int j = -2; j < 3; j++) {
                if (isHasMoved()) {
                    if (Math.pow(i,2)+Math.pow(j,2)<=2){
                        continue;
                    }
                    if (src.getRow() + i >= 0 && src.getColumn() + j >= 0 && src.getRow() + i <= 15 && src.getColumn() + j <= 15) {
                        if (StartChess.controller.getModel().isValidMove(src, new ChessBoardLocation(src.getRow() + i, src.getColumn() + j))) {
                            controller.getView().getGridComponents()[src.getRow() + i][src.getColumn() + j].canMoveTo = true;
                            controller.getView().repaint();
                        }
                    }
                } else {
                    if (src.getRow() + i >= 0 && src.getColumn() + j >= 0 && src.getRow() + i <= 15 && src.getColumn() + j <= 15) {
                        if (StartChess.controller.getModel().isValidMove(src, new ChessBoardLocation(src.getRow() + i, src.getColumn() + j))) {
                            controller.getView().getGridComponents()[src.getRow() + i][src.getColumn() + j].canMoveTo = true;
                            controller.getView().repaint();
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        if(location==null){
            return;
        }
        ChessPiece piece = model.getChessPieceAt(location);
        if (piece.getColor() == StartChess.controller.currentColor && (!hasSelectedLocation() || location.equals(getSelectedLocation()))) {
            helpMove(location);
            if (!hasSelectedLocation()) {
                setSelectedLocation(location);
            } else {
                resetSelectedLocation();
            }
            component.setSelected(!component.isSelected());
            component.repaint();
        }
    }


    /*@Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)) {
            model.moveChessPiece(selectedLocation, location);
            resetSelectedLocation();
           currentColor=nextPlayer();
        }
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = model.getChessPieceAt(location);
        if (piece.getColor() == currentColor && (!hasSelectedLocation() || location.equals(getSelectedLocation()))) {
            if (!hasSelectedLocation()) {
                setSelectedLocation(location);
            } else {
                resetSelectedLocation();
            }
            component.setSelected(!component.isSelected());
            component.repaint();
        }
    }

     */
}

