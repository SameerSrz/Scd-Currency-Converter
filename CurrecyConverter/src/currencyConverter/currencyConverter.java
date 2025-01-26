package currencyConverter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class currencyConverter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;
	private JTextField convertedAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					currencyConverter frame = new currencyConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public currencyConverter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Currency Converter");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		lblNewLabel.setBounds(128, 0, 135, 36);
		contentPane.add(lblNewLabel);
		
		amount = new JTextField();
		amount.setFont(new Font("Tahoma", Font.BOLD, 13));
		amount.setBounds(175, 47, 121, 20);
		contentPane.add(amount);
		amount.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Amount :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(78, 47, 70, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("From :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(78, 99, 52, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox cFrom = new JComboBox();
		cFrom.setModel(new DefaultComboBoxModel(new String[] {"USD ", "PKR ", "EUR ", "GBP "}));
		cFrom.setBounds(128, 99, 70, 22);
		contentPane.add(cFrom);
		
		JComboBox cTo = new JComboBox();
		cTo.setModel(new DefaultComboBoxModel(new String[] {"USD ", "PKR ", "EUR ", "GBP"}));
		cTo.setBounds(265, 99, 70, 22);
		contentPane.add(cTo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("To :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(223, 99, 32, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Converted Amount :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(43, 157, 135, 20);
		contentPane.add(lblNewLabel_1_2);
		
		convertedAmount = new JTextField();
		convertedAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		convertedAmount.setColumns(10);
		convertedAmount.setBounds(193, 158, 121, 20);
		contentPane.add(convertedAmount);
		
		JButton convertBtn = new JButton("Convert");
		convertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            double givenAmount = Double.parseDouble(amount.getText().trim());

		            String fromCurrency = (String) cFrom.getSelectedItem();
		            String toCurrency = (String) cTo.getSelectedItem();

		            double result = converter(givenAmount, fromCurrency, toCurrency);

		            convertedAmount.setText(String.format("%.2f", result));
		        } catch (NumberFormatException ex) {
		            convertedAmount.setText("Invalid Input");
		        }
			}
		});
		convertBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		convertBtn.setBounds(78, 206, 89, 23);
		contentPane.add(convertBtn);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount.setText("");
				convertedAmount.setText("");
			}
		});
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		clearBtn.setBounds(225, 207, 89, 23);
		contentPane.add(clearBtn);
	}
	
	public double converter(double givenAmount, String fromCurrency, String toCurrency) {
		double usdToPkr = 278.0;
	    double usdToEur = 0.95;
	    double usdToGbp = 0.80;
	    
	    double conversionRate = 1.0;
	    
	    if (fromCurrency.trim().equals("USD")) {
	        if (toCurrency.trim().equals("PKR")) {
	            conversionRate = usdToPkr;
	        } else if (toCurrency.trim().equals("EUR")) {
	            conversionRate = usdToEur;
	        } else if (toCurrency.trim().equals("GBP")) {
	            conversionRate = usdToGbp;
	        }
	    } else if (fromCurrency.trim().equals("PKR")) {
	        if (toCurrency.trim().equals("USD")) {
	            conversionRate = 1 / usdToPkr;
	        } else if (toCurrency.trim().equals("EUR")) {
	            conversionRate = usdToEur / usdToPkr;
	        } else if (toCurrency.trim().equals("GBP")) {
	            conversionRate = usdToGbp / usdToPkr;
	        }
	    } else if (fromCurrency.trim().equals("EUR")) {
	        if (toCurrency.trim().equals("USD")) {
	            conversionRate = 1 / usdToEur;
	        } else if (toCurrency.trim().equals("PKR")) {
	            conversionRate = usdToPkr / usdToEur;
	        } else if (toCurrency.trim().equals("GBP")) {
	            conversionRate = usdToGbp / usdToEur;
	        }
	    } else if (fromCurrency.trim().equals("GBP")) {
	        if (toCurrency.trim().equals("USD")) {
	            conversionRate = 1 / usdToGbp;
	        } else if (toCurrency.trim().equals("PKR")) {
	            conversionRate = usdToPkr / usdToGbp;
	        } else if (toCurrency.trim().equals("EUR")) {
	            conversionRate = usdToEur / usdToGbp;
	        }
	    }
	    
	    return givenAmount * conversionRate;
	}
}
