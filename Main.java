//import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Main{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Space Restaurant");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2000,1000);
		frame.setVisible(true);
		JTabbedPane tabs = new JTabbedPane();
		JPanel panel1 = new JPanel(new GridLayout(0,2));
		JPanel panel2 = new JPanel(new GridLayout(0,2));
		JPanel panel3 = new JPanel(new GridLayout(0,1));
		
		String[] items = {"1. Sun Lava Cake - $11.00", "2. Mercury Macarons - $2.50", "3. Venus Vanilla Ice Cream - $2.00", "4. Earth Eclair - $5.75", "5. Mars Mug Cake - $4.25", "6. Jupiter Jam Donut - $1.50", "7. Saturn Shortcake - $9.00", "8. Uranus Upside-down Cake - $8.50", "9. Neptune Nutella Crepes - $6.50", "10. Pluto Cake Pops - $1.75", "11. Moon Mint Choco Ice Cream - $2.00", "12. Halle's Hotcakes - $4.50", "13. Comet Cookie - $0.75"};
	    double[] pricing = {11.00, 2.50, 2.00, 5.75, 4.25, 1.50, 9.00, 8.50, 6.50, 1.75, 2.00, 4.50, .75};
		JComboBox<String> menu = new JComboBox<String>(items);
	    menu.setSelectedIndex(0);

	    NumberFormat amountFormat = NumberFormat.getNumberInstance();
	    JFormattedTextField qty = new JFormattedTextField(amountFormat);
	    qty.setColumns(5);

	    JButton add = new JButton("Add Item");
	    final JLabel menuLabel = new JLabel("Menu:");
	    //I got the code for the borders off of the oracle tutorial
	    menuLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
	    final JLabel qtyLabel = new JLabel("Quantity:");
	    qtyLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
	    final JTextArea outcome = new JTextArea();

	    //tab 1 contents
	    panel1.add(menuLabel);
	    panel1.add(menu);
	    panel1.add(qtyLabel);
	    panel1.add(qty);
	    panel1.add(add);
	    panel1.add(outcome);
	    
		tabs.addTab("Order Here", panel1);
		tabs.addTab("Your Cart", panel2);
		tabs.addTab("Checkout", panel3);
		frame.add(tabs);
		
		//tab 2 contents
		ArrayList<String> cartList = new ArrayList<String>();
		final JLabel cartLabel = new JLabel("Cart:");
		cartLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
		final JTextArea cart = new JTextArea();
		final JLabel itemLabel = new JLabel("ID of Item Being Removed:");
		JFormattedTextField itemNum = new JFormattedTextField(amountFormat);
		itemLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton remove = new JButton("Remove Item");
		final JTextArea removed = new JTextArea();
		
		panel2.add(cartLabel);
		panel2.add(cart);
		panel2.add(itemLabel);
		panel2.add(itemNum);
		panel2.add(remove);
		panel2.add(removed);
		
		//tab 3 contents
		ArrayList<Double> cartPrices = new ArrayList<Double>();
		final JTextArea finalCart = new JTextArea();
		JButton checkout = new JButton("Checkout");
		
		panel3.add(finalCart);
		panel3.add(checkout);
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outcome.setText(" ");
				String prod = (String)menu.getSelectedItem();
		        outcome.append("Added " + qty.getText() + " order(s) of " + prod.substring(prod.indexOf(" ")+1, prod.lastIndexOf("-")-1) + " to your cart!");
		        String temp = prod.substring(prod.indexOf(".")+2, prod.lastIndexOf("-")-1) + " (Qty: " + qty.getText() + ")";
		        cartList.add(temp);
		        Double price = Integer.parseInt(qty.getText()) * pricing[Integer.parseInt(prod.substring(0, prod.indexOf("."))) - 1];
		        cartPrices.add(price);
		        DecimalFormat df = new DecimalFormat("0.00##");
		        cart.setText(" ");
		        int i = 1;
		        for (String added : cartList) {
		        	cart.append(i + ". " + added + " - $" + df.format(cartPrices.get(i-1)));
		        	cart.append("\n");
		        	i++;
		        }
			}
		});
		
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				removed.setText(" ");
				int temp = Integer.parseInt(itemNum.getText())-1;
				removed.append("Removed " + cartList.remove(temp) + " from your cart.");
				cart.setText(" ");
		        int i = 1;
		        for (String added : cartList) {
		        	cart.append(i + ". " + added);
		        	cart.append("\n");
		        	i++;
		        }
		    }
		});}
	
}