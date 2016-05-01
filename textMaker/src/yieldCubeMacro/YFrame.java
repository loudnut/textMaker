package yieldCubeMacro;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class YFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textProcessStepName_15;
	private JTextField textProcessStepName_16;
	private JTextField textMeaStepName_15;
	private JTextField textMeaStepName_16;

	private int sigmaItemCount = 0;
	private JTextField textFilterRecipe;
	private JTextField textTitle;
	private JTextField textThkMean_15;
	private JTextField textThkRange_15;
	private JTextField textThkMeanRefLine;
	private JTextField textThkRangeRefLine;
	private JTextField textThkMean_16;
	private JTextField textThkRange_16;
	final JTextArea textMacro = new JTextArea();
	final JLabel clipBoardStatus = new JLabel("");
	JCheckBox chckbxXp8Filter = new JCheckBox("XP8");
	private JTextField textProcessStepNameProd;
	private JTextField textMeaStepNameProd;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textCustomItem;
	
	final JTextArea textMacroProd = new JTextArea();
	final JLabel lblCopied = new JLabel("");
	List<JTextField> sigmaItem = new ArrayList<JTextField>();
	List<JComboBox> sigmaItemComboBox = new ArrayList<JComboBox>();
	List<ButtonGroup> sigmaItemComboBoxGroup = new ArrayList<ButtonGroup>();
	List<JTextField> sigmaItemOther = new ArrayList<JTextField>();
	private String[] sigmaItemMenu = {"THK Mean","THK Range", "CD Mean", "CD StdDev", "Other"};
	private JTextField textPatchString;

	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public YFrame() {
		super("YieldCube Macro Maker v2.1 - PEE2 Diffusion");
		System.out.println("LOG: v1.1 Implemented demo and clearAll btn");
		System.out.println("LOG: v1.2 Implemented copyToClipbaord btn\n\tnot suitable for 4R,4L");
		System.out.println("LOG: v2.0 Setup Prod tab");
		System.out.println("LOG: v2.1 Change the position of clearAll btn");
		
//		test to input system fuction in y3
//		StringBuffer test = new StringBuffer("CD1 Space Bot DF - DG_CV (Mean)");
//		System.out.println(test);
//		test.replace(test.indexOf("("), test.indexOf("(")+1, "\\(");
//		test.replace(test.indexOf(")"), test.indexOf(")")+1, "\\)");
//		System.out.println(test);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		final JPanel panel = new JPanel();
		tabbedPane.addTab("Qual THK", null, panel, null);

		
		
		
		
	    JPopupMenu popup = new JPopupMenu();
	    JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	    item.setText("Cut");
	    popup.add(item);
	    item = new JMenuItem(new DefaultEditorKit.CopyAction());
	    item.setText("Copy");
	    popup.add(item);
	    item = new JMenuItem(new DefaultEditorKit.PasteAction());
	    item.setText("Paste");
	    popup.add(item);

	    item = new JMenuItem("Select All");
	    item.setEnabled(true);
//	    item.setAccelerator(KeyStroke.getKeyStroke("control A"));
	    item.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
            	textMacro.selectAll();
            }
        });
	    popup.add(item);
//	    Add this menu to target textField
//	    target.setComponentPopupMenu(popup);
		
		JLabel lblProcessStep = new JLabel("Process Step");
		panel.setLayout(new MigLayout("", "[77px][179.00,grow][131.00px,grow][46.00px][][][275.00]", "[][20px][20px][][][35.00][][29.00][][][][][55.00][][23.00px]"));
		
		JLabel lblTitle = new JLabel("Title");
		panel.add(lblTitle, "cell 0 0,alignx left");
		
		textTitle = new JTextField();
		panel.add(textTitle, "cell 1 0 2 1,growx");
		textTitle.setColumns(10);
		textTitle.setComponentPopupMenu(popup);

		JLabel lblF = new JLabel("F15");
		panel.add(lblF, "cell 1 1,alignx center,aligny bottom");

		JLabel lblF_1 = new JLabel("F16");
		panel.add(lblF_1, "cell 2 1,alignx center,aligny bottom");

		JLabel lblMacro = new JLabel("Macro");
		panel.add(lblMacro, "flowx,cell 6 1,alignx left,growy");

		textProcessStepName_15 = new JTextField();
		panel.add(textProcessStepName_15, "cell 1 2,growx");
		textProcessStepName_15.setColumns(10);
		textProcessStepName_15.setComponentPopupMenu(popup);

		textProcessStepName_16 = new JTextField();
		panel.add(textProcessStepName_16, "cell 2 2,growx");
		textProcessStepName_16.setColumns(10);
		textProcessStepName_16.setComponentPopupMenu(popup);

		
		textMacro.setComponentPopupMenu(popup);
		panel.add(textMacro, "cell 6 2 1 13,grow");
		panel.add(lblProcessStep, "cell 0 2,alignx left,growy");

		JLabel lblMeasurementStep = new JLabel("Measurement Step");
		panel.add(lblMeasurementStep, "cell 0 3,alignx left");

		JButton btnMakeMacro = new JButton("Make Macro");
		btnMakeMacro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				textMacro.setText("btn is pressed");
//				String fuck = new String("8401-DF FQDPLYFLM7 QUAL - fuck you");
//				textMacro.setText("GlobalQuery {");
				textMacro.setText("");
				TextProcessor p = new TextProcessor(textProcessStepName_15.getText(),textProcessStepName_16.getText(),textMeaStepName_15.getText(),textMeaStepName_16.getText(),textFilterRecipe.getText(),textThkMean_15.getText(),textThkMean_16.getText(),textThkMeanRefLine.getText(),textThkRange_15.getText(),textThkRange_16.getText(),textThkRangeRefLine.getText(),textTitle.getText(), chckbxXp8Filter.isSelected());
				textMacro.setText(p.makeMacro());
			}
		});

		textMeaStepName_15 = new JTextField();
		panel.add(textMeaStepName_15, "cell 1 3,growx");
		textMeaStepName_15.setColumns(10);
		textMeaStepName_15.setComponentPopupMenu(popup);

		textMeaStepName_16 = new JTextField();
		panel.add(textMeaStepName_16, "cell 2 3,growx");
		textMeaStepName_16.setColumns(10);
		textMeaStepName_16.setComponentPopupMenu(popup);
		
		JLabel lblFilterRecipe = new JLabel("Filter Recipe (ÃöÁä¦r§Y¥i)");
		panel.add(lblFilterRecipe, "flowy,cell 0 4,alignx left");
		
		textFilterRecipe = new JTextField();
		panel.add(textFilterRecipe, "cell 1 4 2 1,growx");
		textFilterRecipe.setColumns(10);
		textFilterRecipe.setComponentPopupMenu(popup);
		
		JButton btnNewButton = new JButton("demo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				demo();
			}
		});
		panel.add(btnNewButton, "cell 4 4,growx,aligny center");

		JLabel lblYieldItemName = new JLabel("Yield3 Item Name");
		panel.add(lblYieldItemName, "cell 1 5,alignx center,aligny bottom");

		JLabel lblReferenceLinesctrl = new JLabel("Reference Lines (Ctrl Limits)");
		panel.add(lblReferenceLinesctrl, "cell 2 5,alignx center,aligny bottom");

//		JLabel lblSigmaItem = new JLabel("Sigma Item");
		JLabel lblThkMean = new JLabel("THK Mean   F15");
		panel.add(lblThkMean, "flowx,cell 0 6,alignx left");
		
		textThkMean_15 = new JTextField();
		panel.add(textThkMean_15, "cell 1 6,growx");
		textThkMean_15.setColumns(10);
		textThkMean_15.setComponentPopupMenu(popup);
		
		textThkMeanRefLine = new JTextField();
		panel.add(textThkMeanRefLine, "cell 2 6 1 2,growx");
		textThkMeanRefLine.setColumns(10);
		textThkMeanRefLine.setComponentPopupMenu(popup);
		
		panel.add(btnMakeMacro, "cell 4 6 1 3,grow");

		
//		HashMap<JTextField, JTextField> sigmaItem;
//		above line is related to the unused HashMap method

//		below block is currently hidden to users
/*		JButton btnAddSigmaItem = new JButton("+");
		btnAddSigmaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sigmaItemCount < 2) {

//					below comment is discarded. issue solved by using List
//					dynamically add new fields like this:
//					     .add(y3Item_x = new JTextField(),
//					or, use a HashMap
//					http://stackoverflow.com/questions/18275360/create-fields-and-methods-dynamically

					sigmaItem.add(new JTextField());
					sigmaItem.add(new JTextField());
					panel.add(sigmaItem.get(++sigmaItemCount * 2 - 2), "cell 1 " + (sigmaItemCount + 3) + ",growx");
					panel.add(sigmaItem.get(sigmaItemCount * 2 - 1), "cell 2 " + (sigmaItemCount + 3) + ",growx");
					panel.revalidate();
					validate();
				}
			}
		});
		panel.add(btnAddSigmaItem, "cell 0 6");
*/		
		
		JLabel lblThkMeanF = new JLabel("THK Mean   F16");
		panel.add(lblThkMeanF, "cell 0 7,alignx left,aligny top");
		
		textThkMean_16 = new JTextField();
		panel.add(textThkMean_16, "cell 1 7,growx,aligny top");
		textThkMean_16.setColumns(10);
		textThkMean_16.setComponentPopupMenu(popup);
	
		
		JLabel lblThkRange = new JLabel("THK Range  F15");
		panel.add(lblThkRange, "cell 0 8,alignx left");
		
		textThkRange_15 = new JTextField();
		panel.add(textThkRange_15, "cell 1 8,growx");
		textThkRange_15.setColumns(10);
		textThkRange_15.setComponentPopupMenu(popup);
		
		textThkRangeRefLine = new JTextField();
		panel.add(textThkRangeRefLine, "cell 2 8 1 2,growx");
		textThkRangeRefLine.setColumns(10);
		textThkRangeRefLine.setComponentPopupMenu(popup);
		
		JLabel lblThkRangeF = new JLabel("THK Range  F16");
		panel.add(lblThkRangeF, "cell 0 9,alignx left");
		
		textThkRange_16 = new JTextField();
		panel.add(textThkRange_16, "cell 1 9,growx");
		textThkRange_16.setColumns(10);
		textThkRange_16.setComponentPopupMenu(popup);
		
		JButton btnCopyToClipboard = new JButton("Copy to Clipboard");
		btnCopyToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textMacro.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				clipBoardStatus.setText("copied...");
			}
		});
		panel.add(btnCopyToClipboard, "cell 4 9");
		
		JLabel lblRctrlLimit = new JLabel("R\u503C\u82E5\u7121ctrl limit\u8ACB\u7C97\u4F30\u6700\u5927\u503C");
		panel.add(lblRctrlLimit, "cell 2 10");
		panel.add(clipBoardStatus, "cell 4 10,alignx right");
		
		panel.add(chckbxXp8Filter, "cell 0 4");
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAll();
			}
		});
		panel.add(btnClearAll, "cell 6 1,alignx left,aligny top");

		final JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Prod", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[368.00][89.00][300.00][225.00][98.00][323.00][66.00,grow]", "[][][baseline][][][][][][][][baseline][]"));
		
		JLabel label = new JLabel("...");
		panel_1.add(label, "cell 1 0");
		label.setVisible(false);
		
		JLabel lblProcessStep_1 = new JLabel("Process Step");
		panel_1.add(lblProcessStep_1, "cell 2 0,alignx center");
		
		JLabel lblMeasurementStep_1 = new JLabel("Measurement Step");
		panel_1.add(lblMeasurementStep_1, "cell 3 0,alignx center");
		
		JLabel lblMacro_1 = new JLabel("Macro");
		panel_1.add(lblMacro_1, "flowx,cell 5 0,alignx left");
		
		textProcessStepNameProd = new JTextField();
		panel_1.add(textProcessStepNameProd, "cell 2 1,growx");
		textProcessStepNameProd.setColumns(10);
		
		textMeaStepNameProd = new JTextField();
		panel_1.add(textMeaStepNameProd, "cell 3 1,growx");
		textMeaStepNameProd.setColumns(10);
		
		JButton btnGlobalQuery = new JButton("Global Query");
		btnGlobalQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMacroProd.setText("");
				TextProcessor pp = new TextProcessor(textProcessStepNameProd.getText(), textMeaStepNameProd.getText(), sigmaItemCount,
			sigmaItem, sigmaItemComboBox, sigmaItemOther, textPatchString.getText());
				textMacroProd.setText(pp.makeMacroProdGq());
			}
		});
		panel_1.add(btnGlobalQuery, "cell 4 1,growx");
		
		
		textMacroProd.setText("");
		panel_1.add(textMacroProd, "cell 5 1 1 11,grow");
		
		JLabel lblPatchString = new JLabel("patch string");
		panel_1.add(lblPatchString, "flowx,cell 4 2");
		
		JLabel lblYItem = new JLabel("Yield3 Item Name");
		panel_1.add(lblYItem, "flowx,cell 2 3,alignx center");
		
		JLabel lblReferenceLinesctrl_1 = new JLabel("Reference Lines (Ctrl Limits)");
		panel_1.add(lblReferenceLinesctrl_1, "cell 3 3,alignx center");
		
		
		JButton btnAddSigmaItem = new JButton("+");
		btnAddSigmaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sigmaItemCount < 8) {
					sigmaItem.add(new JTextField());
					sigmaItem.add(new JTextField());
					panel_1.add(sigmaItem.get(++sigmaItemCount * 2 - 2), "cell 2 " + (sigmaItemCount + 3) + ",growx");
					panel_1.add(sigmaItem.get(sigmaItemCount * 2 - 1), "cell 3 " + (sigmaItemCount + 3) + ",growx");

					sigmaItemComboBox.add(new JComboBox(sigmaItemMenu));
					sigmaItemOther.add(new JTextField());
					sigmaItemOther.get(sigmaItemCount - 1).setVisible(false);
					panel_1.add(sigmaItemComboBox.get(sigmaItemCount - 1), "cell 0 " + (sigmaItemCount + 3));
					panel_1.add(sigmaItemOther.get(sigmaItemCount - 1), "cell 0 " + (sigmaItemCount + 3) + ",growx");
					sigmaItemComboBox.get(sigmaItemCount - 1).addActionListener(new ComBoxListener(sigmaItemComboBox,sigmaItemOther,sigmaItemCount));

					panel_1.revalidate();
					validate();
				}
			}
		});
		panel_1.add(btnAddSigmaItem, "cell 2 3");
		
		JButton btnMakeMacro_1 = new JButton("Make Macro");
		btnMakeMacro_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMacroProd.setText("");
				TextProcessor pp = new TextProcessor(textProcessStepNameProd.getText(), textMeaStepNameProd.getText(), sigmaItemCount,
			sigmaItem, sigmaItemComboBox, sigmaItemOther, textPatchString.getText());
				textMacroProd.setText(pp.makeMacroProd());
			}
		});
		panel_1.add(btnMakeMacro_1, "cell 4 3 1 4,grow");
		
		panel_1.add(lblCopied, "cell 4 8,alignx right");
		JButton btnCopyToClipboard_1 = new JButton("Copy to Clipboard");
		btnCopyToClipboard_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textMacroProd.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				lblCopied.setText("copied...");
			}
		});
		panel_1.add(btnCopyToClipboard_1, "cell 4 7");
		
		
		JButton btnClearAll_1 = new JButton("Clear All");
		btnClearAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllProd(panel_1);
			}
		});
		panel_1.add(btnClearAll_1, "cell 5 0,alignx left");
		
		textPatchString = new JTextField();
		panel_1.add(textPatchString, "cell 4 2");
		textPatchString.setColumns(10);
		
		
		
		
/*		JButton btnAddSigmaItem = new JButton("+");
		btnAddSigmaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sigmaItemCount < 2) {

//					below comment is discarded. issue solved by using List
//					dynamically add new fields like this:
//					     .add(y3Item_x = new JTextField(),
//					or, use a HashMap
//					http://stackoverflow.com/questions/18275360/create-fields-and-methods-dynamically

					sigmaItem.add(new JTextField());
					sigmaItem.add(new JTextField());
					panel.add(sigmaItem.get(++sigmaItemCount * 2 - 2), "cell 1 " + (sigmaItemCount + 3) + ",growx");
					panel.add(sigmaItem.get(sigmaItemCount * 2 - 1), "cell 2 " + (sigmaItemCount + 3) + ",growx");
					panel.revalidate();
					validate();
				}
			}
		});
		panel.add(btnAddSigmaItem, "cell 0 6");
*/		

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		setVisible(true);
	}
	
	private void demo(){
		textTitle.setText("42 CONTAINER LINER POLY");

		textProcessStepName_15.setText("8401-DF FQPOLYFLM4 QUAL");
		textMeaStepName_15.setText("8120-DF PST THK FQPOLYFLM4 MEA");
		textProcessStepName_16.setText("8401-DF FQDPLYFLM9 QUAL");
		textMeaStepName_16.setText("8120-DF PST THK FQDPLYFLM9 MEA");
		textFilterRecipe.setText("BKM50A42LINER-E");

		textThkMean_15.setText("THK L02 - Thickness - RAW_WAFER (Mean)");
		textThkMean_16.setText("Layer2 THK - Thickness - RAW_WAFER (Mean)");
		textThkMeanRefLine.setText("65;50;35");

		textThkRange_15.setText("THK L02 - Thickness - RAW_WAFER (Range)");
		textThkRange_16.setText("Layer2 THK - Thickness - RAW_WAFER (Range)");
		textThkRangeRefLine.setText("10");
	}
	
	private void clearAll(){
		textTitle.setText("");
		textProcessStepName_15.setText("");
		textProcessStepName_16.setText("");
		textMeaStepName_15.setText("");
		textMeaStepName_16.setText("");
		textFilterRecipe.setText("");
		textThkMean_15.setText("");
		textThkMean_16.setText("");
		textThkMeanRefLine.setText("");
		textThkRange_15.setText("");
		textThkRange_16.setText("");
		textThkRangeRefLine.setText("");
		textMacro.setText("");
		clipBoardStatus.setText("");
	}
	
	private void clearAllProd(JPanel panel_1){
		textTitle.setText("");
		textProcessStepNameProd.setText("");
		textMeaStepNameProd.setText("");
		textMacroProd.setText("");
		lblCopied.setText("");
		
		while (sigmaItemCount > 0) {
			panel_1.remove(sigmaItem.get(sigmaItemCount * 2 - 2));
			panel_1.remove(sigmaItem.get(sigmaItemCount * 2 - 1));
			panel_1.remove(sigmaItemComboBox.get(sigmaItemCount - 1));
			panel_1.remove(sigmaItemOther.get(sigmaItemCount-- - 1));
		}
		panel_1.repaint();
		panel_1.revalidate();
		validate();
		
		sigmaItem = new ArrayList<JTextField>();
		sigmaItemComboBox = new ArrayList<JComboBox>();
		sigmaItemComboBoxGroup = new ArrayList<ButtonGroup>();
		sigmaItemOther = new ArrayList<JTextField>();
	}
}
