package com.XYu1230.ui;

import javax.swing.*;
import java.util.ResourceBundle;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame(){
        this.setSize(488,500);

        //设置界面的标题
        this.setTitle("拼图小游戏 --注册");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置退出模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
