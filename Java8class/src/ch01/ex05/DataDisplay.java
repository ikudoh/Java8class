// 3メソッドのListenerをラムダ式に変更、8行削除。



package ch01.ex05;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

/**
 * クラス/作成したインスタンスの持つデータの表示に使用する画面部品操作クラスです。
 * 指定されたクラス/インスタンスの情報を表示します。
 *
 * @author i.kudoh
 *
 */
public class DataDisplay {

	/**
	 * ユーザーが指定/生成したクラス情報を保持するクラス
	 */
	private ClassInfo classInfo = new ClassInfo();

	/**
	 * ユーザーが入力したクラス名の情報を表示するボタン
	 */
	JButton showInfoButton = new JButton("表示");

	/**
	 * パッケージ情報表示部品
	 */
	private JTextField packageNameField = new JTextField();

	/**
	 * クラス宣言表示部品
	 */
	private JTextField classDeclaredField= new JTextField();

	/**
	 * フィールド情報
	 */
	Field[] fields = null;

	/**ｄ
	 * フィールド一覧表示部品
	 */
	private String[] fieldColumnNames = {"フィールド名", "値"};
	private DefaultTableModel fieldModel = new DefaultTableModel(fieldColumnNames, 0) {
		@Override
		public boolean isCellEditable(int row, int column) {
			if(column == 1) {
				// フィールドの型を取得し、直接編集可能か確認する
				String fullType = fieldModel.getValueAt(row, 0).toString();
				String[] strs = fullType.split(" ");
				String type = strs[strs.length-2];	// フィールド定義文字列から変数の型を取得
				if(/* !fullType.contains("private") && */
						!(fullType.contains("final") && fullType.contains("static")) &&
						DataUtility.canDirectUpdate(type)) {
					return true;
				}
			}
			return false;
		};
	};

	/**
	 * ユーザーが作成したインスタンスを表示します
	 * @param targetInstance
	 */
	public void setInstance(Object targetInstance) {
		Class<?> cls = targetInstance.getClass();

		packageNameField.setText(cls.getPackage().getName());
		classDeclaredField.setText(classInfo.getClassDeclared(cls));
		constructorDisplay.showInfo(cls, targetInstance);
		fieldDisplay.showInfo(cls, targetInstance);
		methodDisplay.showInfo(cls, targetInstance);
	}

	/**
	 * メインフレーム
	 */
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * インスタンス情報を表示するタブ
	 */
	private JTabbedPane tabPane = new JTabbedPane();
	public void updateTabPanel(Map<String, JPanel> tabPanels) {
		tabPane.removeAll();
		for(Map.Entry<String, JPanel> tab : tabPanels.entrySet()) {
			tabPane.add(tab.getKey(), tab.getValue());
		}
	}

	/**
	 * ユーザー入力クラス名
	 */
	JTextField classNameField = new JTextField();

	private FieldDisplay fieldDisplay;
	private MethodDisplay methodDisplay;
	private ConstructorDisplay constructorDisplay;
	private UserInstances userInstances = new UserInstances();

	public DataDisplay() {
		fieldDisplay = new FieldDisplay(userInstances, this);
		methodDisplay = new MethodDisplay(userInstances, this);
		constructorDisplay = new ConstructorDisplay(userInstances, fieldDisplay, methodDisplay, this);
	}

	/**
	 * データ情報画面を表示します
	 */
	public void show() {
		frame = createFrame();
		JPanel panel = new JPanel();
		//		panel.setBackground(Color.BLUE);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JPanel otherDisplayButtonPanel = createSelectInstanceButtonPanel();
		layout.putConstraint(SpringLayout.NORTH, otherDisplayButtonPanel, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, otherDisplayButtonPanel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, otherDisplayButtonPanel, -10, SpringLayout.EAST, panel);
		JPanel inputPanel = createInputPanel();
		layout.putConstraint(SpringLayout.NORTH, inputPanel, 10, SpringLayout.SOUTH, otherDisplayButtonPanel);
		layout.putConstraint(SpringLayout.WEST, inputPanel, 0, SpringLayout.WEST, otherDisplayButtonPanel);
		layout.putConstraint(SpringLayout.EAST, inputPanel, 0, SpringLayout.EAST, otherDisplayButtonPanel);
		JLabel infoTitleLabel = new JLabel("クラス情報");
		layout.putConstraint(SpringLayout.NORTH, infoTitleLabel, 10, SpringLayout.SOUTH, inputPanel);
		layout.putConstraint(SpringLayout.WEST, infoTitleLabel, 0, SpringLayout.WEST, inputPanel);
		layout.putConstraint(SpringLayout.EAST, infoTitleLabel, 0, SpringLayout.EAST, inputPanel);
		JPanel packagePanel = createSimpleInfoPanel("パッケージ", packageNameField);
		layout.putConstraint(SpringLayout.NORTH, packagePanel, 10, SpringLayout.SOUTH, infoTitleLabel);
		layout.putConstraint(SpringLayout.WEST, packagePanel, 0, SpringLayout.WEST, infoTitleLabel);
		layout.putConstraint(SpringLayout.EAST, packagePanel, 0, SpringLayout.EAST, infoTitleLabel);
		JPanel classPanel = createSimpleInfoPanel("クラス", classDeclaredField);
		layout.putConstraint(SpringLayout.NORTH, classPanel, 10, SpringLayout.SOUTH, packagePanel);
		layout.putConstraint(SpringLayout.WEST, classPanel, 0, SpringLayout.WEST, packagePanel);
		layout.putConstraint(SpringLayout.EAST, classPanel, 0, SpringLayout.EAST, packagePanel);
		tabPane = new JTabbedPane();
		layout.putConstraint(SpringLayout.NORTH, tabPane, 10, SpringLayout.SOUTH, classPanel);
		layout.putConstraint(SpringLayout.WEST, tabPane, 0, SpringLayout.WEST, classPanel);
		layout.putConstraint(SpringLayout.EAST, tabPane, 0, SpringLayout.EAST, classPanel);
		String[][] datas = new String[1][];
		tabPane.add("コンストラクタ", constructorDisplay.get(datas));
		tabPane.add("フィールド",  fieldDisplay.get(datas));
		tabPane.add("メソッド", methodDisplay.get(datas));
		panel.add(otherDisplayButtonPanel);
		panel.add(inputPanel);
		panel.add(infoTitleLabel);
		panel.add(packagePanel);
		panel.add(classPanel);
		panel.add(tabPane);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/////////////////////////// クラス情報を表示するための部品を生成 ///////////////////////////
	/**
	 * フレームを作成します
	 * @return frame フレーム
	 */
	private JFrame createFrame() {
		JFrame frame = new JFrame("Interpret");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(DisplayUtility.DISPLAY_SIZE_WIDTH, DisplayUtility.DISPLAY_SIZE_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	/**
	 * 他の画面を表示するボタンを配置するパネルを作成します
	 * @return
	 */
	private JPanel createSelectInstanceButtonPanel() {
		JPanel otherDisplayButtonPanel = new JPanel();
		otherDisplayButtonPanel.setLayout(DisplayUtility.getRightishFlowLayout());
		JButton createArrayButton = new JButton("配列を作成");
		createArrayButton.addActionListener((e) -> {
				new ArrayDialog(classInfo.getCls(), -1, userInstances, DataDisplay.this).open();
			}
		);
		JButton selectInstanceButton = new JButton("作成したインスタンスを表示");
		selectInstanceButton.addActionListener((e) -> {
				new UserInstanceDialog(userInstances, DataDisplay.this).show();
			}
		);
		otherDisplayButtonPanel.add(createArrayButton);
		otherDisplayButtonPanel.add(selectInstanceButton);
		return otherDisplayButtonPanel;
	}

	/**
	 * ユーザー入力パネルを作成します
	 * @return
	 */
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		JPanel classNamePanel = new JPanel();
		classNamePanel.setLayout(DisplayUtility.getLeftishFlowLayout());
		JLabel classNameLabel = new JLabel("表示するクラス名");
		classNamePanel.add(classNameLabel);
		classNameField = new JTextField("interpret.Interpret");
		classNameField.setSize(500, 30);
		showInfoButton.addActionListener((e) -> {
				showInfo(classNameField.getText(), false);
			}
		);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(DisplayUtility.getRightishFlowLayout());
		buttonPanel.add(showInfoButton);
		inputPanel.add(classNamePanel);
		inputPanel.add(classNameField);
		inputPanel.add(buttonPanel);
		return inputPanel;
	}

	/**
	 * 出力結果表示エリアをクリアします
	 * ユーザーが入力する「表示するクラス名」はクリアしません
	 */
	private void clearOutputData() {
		packageNameField.setText("");
		classDeclaredField.setText("");
		fieldModel.setRowCount(0);
		constructorDisplay.clear();
		fieldDisplay.clear();
		methodDisplay.clear();
	}

	/**
	 * 表示専用title/valueのみのシンプルな情報を表示するパネルを作成します
	 * @param title
	 * @return
	 */
	private JPanel createSimpleInfoPanel(String title, JTextField target) {
		JPanel panel = new JPanel();
		panel.setLayout(DisplayUtility.getLeftishFlowLayout());
		JLabel label = new JLabel(title + " : ");
		JTextField field = target;
		field.setPreferredSize(new Dimension(600, 25));
		//		field.setEnabled(false);
		//		field.setForeground(Color.red);
		panel.add(label);
		panel.add(field);
		return panel;
	}

	/**
	 * タブで表示するクラス情報のパネルを作成します(Table)
	 * @return
	 */
	public JPanel createTabPanel(String[][] datas, DefaultTableModel dataModel, JPanel settingPanel) {
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setPreferredSize(new Dimension(DisplayUtility.DISPLAY_SIZE_WIDTH, DisplayUtility.DISPLAY_SIZE_HEIGHT/3));
		dataModel.setRowCount(0);
		JTable outputTable = new JTable(dataModel);
		outputTable.setColumnSelectionAllowed(true);
		outputTable.setRowSelectionAllowed(true);
		for(int i = 0; i < datas.length; i++) {
			dataModel.addRow(datas[i]);
		}
		JScrollPane scrollPane = new JScrollPane(outputTable);
		scrollPane.setPreferredSize(new Dimension(750, 450));
		outputPanel.add(scrollPane);
		outputPanel.add(settingPanel);
		return outputPanel;
	}

	/**
	 * クラス情報を表示します
	 * @param className
	 * @param isUserInstance
	 */
	protected boolean showInfo(String className, boolean isUserInstance) {
		clearOutputData();
		try {
			classInfo.setClassInfo(className, isUserInstance);
			setPackage(classInfo.getPackage());
			setClassDeclared(classInfo.getClassDeclared());
			//			constructors = classInfo.getConstructors();
			//			setConstructors(getConstructorInfos(constructors));
			constructorDisplay.showInfo(classInfo.getCls(), null);
			// フィールドとメソッドはコンストラクタ画面でインスタンスを生成してから操作可能。
			//			fields = classInfo.getFields();
			//			setFields(getFieldInfos(classInfo.getFields(), classInfo.getTargetInstance()));
			//			setMethods(classInfo.getMethods());
			//			setInnerClasses(classInfo.getInnerClasses());
			//			setTmpName(classInfo.getTargetInstanceName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			classInfo.setTargetInstance(null);
			clearOutputData();
			ErrorDialog.showErrorDialog(e);
			return false;
		} catch (InvocationTargetException e) {
			classInfo.setTargetInstance(null);
			clearOutputData();
			ErrorDialog.showErrorDialog(e.getCause());
			return false;
		}
		return true;
	}



	//	/**
	//	 * エラー情報を設定します
	//	 * @param errorInfo
	//	 */
	//	private void setErrorInfo(String errorInfo) {
	//		errorField.setText(errorInfo);
	//	}

	/**
	 * パッケージ情報を設定します
	 * @param packageName
	 */
	public void setPackage(String packageName) {
		packageNameField.setText(packageName);
	}

	/**
	 * クラス宣言を設定します
	 * @param classDeclared
	 */
	private void setClassDeclared(String classDeclared) {
		classDeclaredField.setText(classDeclared);
	}

	/**
	 * フィールド一覧情報を取得します
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public Map<String, String> getFieldInfos(Field[] fields, Object instance) throws IllegalArgumentException, IllegalAccessException {
		int count = fields.length;
		Map<String, String> fieldInfos = new HashMap<>();
		for(int i = 0; i < count; i++) {
			fields[i].setAccessible(true);	// フィールドへのアクセス許可
			String modifierStr = Modifier.toString(fields[i].getModifiers());
			StringBuilder sb = new StringBuilder();
			sb.append(modifierStr.length() > 0 ? modifierStr + " " : "");
			//			sb.append(omitPackagePath(fields[i].getGenericType().getTypeName()) + SPACE + fields[i].getName());
			sb.append(fields[i].getGenericType().getTypeName() + " " + fields[i].getName());
			String value = fields[i].get(instance) != null ? fields[i].get(instance).toString() : "null";
			fieldInfos.put(sb.toString(), value);
		}
		return fieldInfos;
	}
}
