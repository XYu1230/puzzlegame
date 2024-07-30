package com.XYu1230.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        //设置界面的是否可视化
        this.setVisible(true);
    }



    String path = "image\\animal\\animal3\\";

    int[][] data = new int[4][4];

    //步数
    int step = 0;

    int girlIndex = 1,animalIndex = 1,sportIndex = 1;

    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };

    //空白图片所在位置
    int x =0,y = 0;

    //选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem CloseItem = new JMenuItem("关闭游戏");
    JMenuItem meItem = new JMenuItem("作者微信");
    JMenuItem rewordItem = new JMenuItem("打赏作者");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");







    //判断游戏是否胜利
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++){
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    //初始化数据
    private void initData(){
        int[] temp = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for (int i = 0; i < temp.length; i++) {
            int index = r.nextInt(16);
            int tem = temp[i];
            temp[i] = temp[index];
            temp[index] = tem;
        }
        for (int i = 0; i < temp.length; i++) {
            data[i/4][i%4] = temp[i];
        }
    }

    private void initImage() {
        //清除原有图片
        this.getContentPane().removeAll();

        if(victory()){
            //添加胜利图片
            JLabel vic = new JLabel(new ImageIcon("image\\win.png"));
            vic.setBounds(203,283,197,73);
            this.getContentPane().add(vic);
        }

        //添加步数
        JLabel stepLabel = new JLabel("步数" + step);
        stepLabel.setBounds(50,30,200,40);
        this.getContentPane().add(stepLabel);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int imageNumber = data[i][j];
                if(imageNumber == 0){
                    x = i;
                    y = j;
                }
                //管理容器,可以添加图片和文字  ImageIcon 图片对象
                JLabel jLabel = new JLabel(new ImageIcon(path + imageNumber + ".jpg"));

                //设置图片位置和大小
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);

                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

                imageNumber++;
            }
        }

        //添加背景图片 后添加的在下面
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603,680);

        //设置界面的标题
        this.setTitle("拼图小游戏 v1.0");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置退出模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消JLable默认的居中放置
        this.setLayout(null);

        this.addKeyListener(this);

    }

    private void initJMenuBar(){
        //整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //菜单上面的两个选项
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutUsMenu = new JMenu("关于我们");
        JMenu changeMenu = new JMenu("更换图片");



        //将条目目录添加到选项中
        functionMenu.add(changeMenu);
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(CloseItem);
        aboutUsMenu.add(meItem);
        aboutUsMenu.add(rewordItem);
        changeMenu.add(girl);
        changeMenu.add(animal);
        changeMenu.add(sport);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        CloseItem.addActionListener(this);
        meItem.addActionListener(this);
        rewordItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //将选项添加到菜单中
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutUsMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            //添加背景图片 后添加的在下面
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory())return ;

        int code = e.getKeyCode();
        if(code == 38){
            if(x == 3) return;
            System.out.println("向上移动");
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            step++;
            initImage();
        }
        else if(code == 39){
            if(y == 0) return;
            System.out.println("向右移动");
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            step++;
            initImage();
        }
        else if(code == 40){
            if(x == 0) return;
            System.out.println("向下移动");
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            step++;
            initImage();
        }
        else if(code == 37){
            if(y == 3) return;
            System.out.println("向左移动");
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            step++;
            initImage();
        }
        else if(code == 65){
            initImage();
        }
        else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == replayItem){
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();
        }
        else if(source == reLoginItem){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }
        else if(source == CloseItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }
        else if(source == meItem){
            //弹框对象
            JDialog jd = new JDialog();
            JLabel me = new JLabel(new ImageIcon("image\\me.jpg"));
            me.setBounds(0,0,258,296);
            jd.getContentPane().add(me);
            //设置弹框大小
            jd.setSize(344,344);
            //弹框置顶
            jd.setAlwaysOnTop(true);
            //弹框居中
            jd.setLocationRelativeTo(null);
            //弹框模式:不关闭无法操作下面的操作
            jd.setModal(true);
            //让弹框显示出来
            jd.setVisible(true);
        }
        else if(source == rewordItem){
            //弹框对象
            JDialog jd2 = new JDialog();
            JLabel me = new JLabel(new ImageIcon("image\\money.jpg"));
            me.setBounds(0,0,258,272);
            jd2.getContentPane().add(me);
            //设置弹框大小
            jd2.setSize(344,344);
            //弹框置顶
            jd2.setAlwaysOnTop(true);
            //弹框居中
            jd2.setLocationRelativeTo(null);
            //弹框模式:不关闭无法操作下面的操作
            jd2.setModal(true);
            //让弹框显示出来
            jd2.setVisible(true);
        }
        else if(source == girl){
            step = 0;
            path = "image\\girl\\girl"+ girlIndex +"\\";
            initData();
            initImage();
            girlIndex++;
            if(girlIndex == 14)girlIndex = 1;
        }
        else if(source == animal){
            step = 0;
            path = "image\\animal\\animal"+ animalIndex +"\\";
            initData();
            initImage();
            animalIndex++;
            if(animalIndex == 9)animalIndex = 1;
        }
        else if(source == sport){
            step = 0;
            path = "image\\sport\\sport"+ sportIndex +"\\";
            initData();
            initImage();
            sportIndex++;
            if(sportIndex == 11)sportIndex = 1;
        }
    }
}
