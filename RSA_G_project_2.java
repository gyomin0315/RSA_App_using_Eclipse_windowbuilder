package myPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class RSA_G_project_2 {

	private JFrame frame;
	private JTextField plaintext_field;
	private JTextField Ciphertext_field;
	private JTextField decryptedtext_field;
	private JLabel log;
	
	private JRadioButton bt_e;

	final Random myRandom = new Random(); 

	int p,q,e1,d,n;
	
	static int enline[]= {0,0,0,0,0,0,0,0,0,0,0};
	
	int indx;
	boolean bin_X[] = new boolean[100];
	private JTextField p_field;
	private JTextField q_field;
	private JTextField e_field;
	private final ButtonGroup buttonGroup = new ButtonGroup();
																																																																																																																																																																																																																																																			
	//int converted_text_1[],converted_text_2[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSA_G_project_2 window = new RSA_G_project_2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RSA_G_project_2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 916, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Plaintext:");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 142, 126, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblCiphertext = new JLabel("Your message will be transferred as...");
		lblCiphertext.setFont(new Font("굴림", Font.BOLD, 20));
		lblCiphertext.setBounds(12, 243, 420, 35);
		frame.getContentPane().add(lblCiphertext);

		JLabel lblNewLabel_1_1 = new JLabel("Decrypted text:");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 342, 187, 35);
		frame.getContentPane().add(lblNewLabel_1_1);

		plaintext_field = new JTextField();
		plaintext_field.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 20));
		plaintext_field.setText("gyomin");
		plaintext_field.setBounds(12, 187, 327, 42);
		frame.getContentPane().add(plaintext_field);
		plaintext_field.setColumns(10);

		Ciphertext_field = new JTextField();
		Ciphertext_field.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 20));
		Ciphertext_field.setColumns(10);
		Ciphertext_field.setBounds(12, 290, 327, 42);
		frame.getContentPane().add(Ciphertext_field);

		decryptedtext_field = new JTextField();
		decryptedtext_field.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 20));
		decryptedtext_field.setColumns(10);
		decryptedtext_field.setBounds(12, 386, 327, 42);
		frame.getContentPane().add(decryptedtext_field);

		JButton bt_encrypt = new JButton("Encrypt");
		bt_encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				
				String plaintext = plaintext_field.getText().toString();
				DefineRSA();
				
				int converted_text[] = new int[plaintext.length()];
				
				int result[] = new int [plaintext.length()];

				char ch;
		
				for(int i=0;i<plaintext.length();i++)
				{
					ch = plaintext.charAt(i);
					converted_text[i] = (int) ch;
					result[i] = Modulo_a_x_p(converted_text[i], e1, n);
					enline[i] = result[i];
					
					Ciphertext_field.setText(Ciphertext_field.getText() +enline[i] + ",");
					

					
				}
			
				

			}
		});
		bt_encrypt.setFont(new Font("굴림", Font.ITALIC, 20));
		bt_encrypt.setBounds(497, 63, 187, 64);
		frame.getContentPane().add(bt_encrypt);

		JButton bt_decrypt = new JButton("Decrypt");
		bt_decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String plaintext = plaintext_field.getText().toString();
				
				char convert_text [] = new char[plaintext.length()];

				int result[] = new int[plaintext.length()];
				
				for(int i=0;i<plaintext.length();i++)
				{
					result[i] = Modulo_a_x_p(enline[i], d, n);
					convert_text[i] = (char) result[i];
					
					decryptedtext_field.setText(decryptedtext_field.getText() + convert_text[i]  );
				}
				
				
			}
		});
		bt_decrypt.setFont(new Font("굴림", Font.ITALIC, 20));
		bt_decrypt.setBounds(497, 176, 187, 64);
		frame.getContentPane().add(bt_decrypt);

		log = new JLabel("Notice");
		log.setVerticalAlignment(SwingConstants.TOP);
		log.setBounds(12, 449, 672, 35);
		frame.getContentPane().add(log);
		
		JLabel lblNewLabel_1 = new JLabel("p:");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1.setBounds(12, 25, 52, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		p_field = new JTextField();
		p_field.setBounds(32, 26, 106, 21);
		frame.getContentPane().add(p_field);
		p_field.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("q:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(164, 25, 52, 28);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		q_field = new JTextField();
		q_field.setColumns(10);
		q_field.setBounds(191, 26, 106, 21);
		frame.getContentPane().add(q_field);
		
		bt_e = new JRadioButton("Press_bt_for_Random_e");
		bt_e.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		bt_e.setBounds(12, 86, 285, 42);
		frame.getContentPane().add(bt_e);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("e:");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1_2_1.setBounds(320, 25, 72, 28);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		e_field = new JTextField();
		e_field.setColumns(10);
		e_field.setBounds(357, 26, 106, 21);
		frame.getContentPane().add(e_field);
		
		JButton bt_clear = new JButton("Clear");
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				p_field.setText("");
				q_field.setText("");
				bt_e.setSelected(false);
				plaintext_field.setText("");
				Ciphertext_field.setText("");
				decryptedtext_field.setText("");
				
				
			}
		});
		bt_clear.setFont(new Font("굴림", Font.ITALIC, 20));
		bt_clear.setBounds(497, 290, 187, 64);
		frame.getContentPane().add(bt_clear);
		
		JLabel lblPAndQ = new JLabel("1) p and q must be prime number. ");
		lblPAndQ.setVerticalAlignment(SwingConstants.TOP);
		lblPAndQ.setBounds(12, 482, 672, 35);
		frame.getContentPane().add(lblPAndQ);
		
		JLabel lblIfYou = new JLabel("2) If you want to take Random e, press the Radiobutton or you can type your own e in textfield ");
		lblIfYou.setVerticalAlignment(SwingConstants.TOP);
		lblIfYou.setBounds(12, 527, 672, 35);
		frame.getContentPane().add(lblIfYou);
	}
	 public int Modulo_a_x_p(int num_a, int num_x, int num_p)
	    {
	        indx = 0;
	        //// Convert decimal X into binary
	        Convert_to_binary(num_x,bin_X);

	        ///// Make exponential table ///
	        int res = 1;
	        for (int i=0;i<indx;i++)
	        {
	            if (bin_X[i])
	            {
	                res = (res * num_a) % num_p;

	            }
	            num_a = (num_a * num_a) % num_p;
	        }
	        return res;
	    }

	    public void Convert_to_binary(int x, boolean bin_x[])
	    {
	        while(x>=1)
	        {
	            if (x % 2 ==1) {
	                bin_x[indx] = true;
	            }
	            else
	            {
	                bin_x[indx] = false;
	            }
	            indx++;
	            x = x/2;
	        }
	    }



	public void DefineRSA() {
		//p = 47;
		//q = 71;
		
		p = Integer.parseInt(p_field.getText().toString());
		
		q = Integer.parseInt(q_field.getText().toString());
		n = p*q;
		int Phi = (p-1)*(q-1);
		///// Pick e /////
		//e = PickE(Phi);
		//e1 = 17;
		
		if(bt_e.isSelected())
		{
			e1 = PickE(Phi);
		}
		else
		{
			e1 = Integer.parseInt(e_field.getText().toString());
		}
		System.out.println(e1);
		d = ComputeD(e1,Phi);
		//log.setText(log.getText().toString() + "RSA:"+" p="+ p + ", q=" + q + ", n=" + n + ", phi="+Phi + ", e=" + e1+ ", d="+d);
	}
	public int PickE(int phi)
	{
		int E;
		E = myRandom.nextInt(phi-1);
		while (GCD(E,phi))
		{
			E = myRandom.nextInt(phi-1);
		}
		return E;
	}

	public int ComputeD(int E, int phi)
	{
		int q,r_1,r_2,r,t_1 = 0,t_2=1,t;
		r_1 = phi; r_2 = E;
		r = r_1 % r_2;
		q = r_1/r_2;
		t = t_1 - t_2*q;
		while(r!=0)
		{
			r_1 = r_2;
			r_2 = r;
			r = r_1 % r_2;
			q = r_1/r_2;
			t_1 = t_2;
			t_2 = t;
			t = t_1 - t_2*q;
		}
		if (t_2<0)
		{
			t_2 = t_2 + phi;
		}
		return t_2;
	}


	public boolean GCD(int E, int phi) {
		int q,r_1,r_2,r;
		r_1 = phi; r_2 = E;
		r = r_1 % r_2;
		q = r_1/r_2;

		while(r!=0)
		{
			r_1 = r_2;
			r_2 = r;
			r = r_1 % r_2;
			q = r_1/r_2;
		}
		if(r_2==1) {
			return false;
		}
		else {
			return true;
		}
	}
}

