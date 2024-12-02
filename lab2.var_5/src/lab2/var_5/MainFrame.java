package lab2.var_5;
// Импортируются классы, используемые в приложении
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    private Formula sum =new Formula();//!!
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldSum;//!!
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    private JPanel imagePane; //!!

    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow((Math.log((1+x)*(1+x))+ Math.cos(Math.PI*z*z*z)),Math.sin(y))+
                Math.pow ((Math.pow(Math.E,x*x)+ Math.cos(Math.pow(Math.E,z)) + Math.pow(1/y,0.5)),1/x);
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y,  Double z) {
        return x*((Math.cos(y*y)*Math.cos(y*y)*Math.cos(y*y)/ Math.pow(z,x)));
    }
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        // Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // Который будет устанавливать идентификатор выбранной
// формулы в классе Formula равным formulaId
                MainFrame.this.formulaId = formulaId;
                //imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        // Установить выделенной 1-ую кнопку из группы
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        JLabel labelForZ = new JLabel("Z:"); //!!
        textFieldZ = new JTextField("0", 10);//!!
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize()); //!!
// Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        // источник события, регистрация обработчика, обработчик события
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    //Метод parseDouble из класса Double используется для преобразования строк, полученных из текстовых полей, в числа типа Double.
                    //Если введенное значение не является допустимым числом (например, содержит буквы), этот метод выбросит исключение NumberFormatException
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x,y,z);
                    else
                        result = calculate2(x,y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        // Создать область для кнопок
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x,y,z)+sum.getSum();
                    else
                        result = calculate2(x,y,z)+sum.getSum();
                    sum.setSum(+result);
                    textFieldSum.setText(result.toString());
                    //textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldSum.setText("0");
                sum.setSum(0);
            }
        });
        Box hboxButtonsMMC = Box.createHorizontalBox();
        hboxButtonsMMC.add(Box.createHorizontalGlue());
        hboxButtonsMMC.add(buttonM);
        hboxButtonsMMC.add(Box.createHorizontalStrut(30));
        hboxButtonsMMC.add(buttonMC);
        hboxButtonsMMC.add(Box.createHorizontalGlue());
        hboxButtonsMMC.setBorder(
                BorderFactory.createLineBorder(Color.ORANGE));

        JLabel labelForSum = new JLabel("Cумма:");
//labelResult = new JLabel("0");
        textFieldSum = new JTextField("0", 25);
        textFieldSum.setMaximumSize(
                textFieldSum.getPreferredSize());
        Box hboxSum = Box.createHorizontalBox();
        hboxSum.add(Box.createHorizontalGlue());
        hboxSum.add(labelForSum);
        hboxSum.add(Box.createHorizontalStrut(10));
        hboxSum.add(textFieldSum);
        hboxSum.add(Box.createHorizontalGlue());
        hboxSum.setBorder(BorderFactory.createLineBorder(Color.PINK));


// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxSum);
        contentBox.add(hboxButtons);
        contentBox.add(hboxButtonsMMC);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
