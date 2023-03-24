import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class playSwing extends JFrame implements ActionListener{
	//JFrame 배치 방식은 BorderLayout 방식(동서남북센터, 기본:센터)
	JTextField tf_name,tf_phone,tf_email,tf_age;
	JTable jt;
	DefaultTableModel dtm;
	PreparedStatement ps =null;
	ResultSet rs = null;//select한  결과를 리턴받는 객체
	Connection con=null;
	public playSwing() {
		this.setTitle("first gui");
		this.setSize(500,800);
		this.setLocation(1420, 300);//음수 좌표 없음.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);//맨위줄, 중앙에 차례로 배치
		JPanel p1 =new JPanel();//배치방식 flowLayout
		JPanel p2 =new JPanel();
		
		
		
		String [] Title= {"이름","폰번호","이메일","나이"};
		Object [] [] row= new Object[0][4];
		dtm= new DefaultTableModel(row,Title);
		
		jt=new JTable(dtm);
		
		JScrollPane p3 =new JScrollPane(jt);
		JLabel name =new JLabel("이름:");
		JLabel phone =new JLabel("폰번호:");
		JLabel email =new JLabel("이메일:");
		JLabel age =new JLabel("나이:");
		tf_name=new JTextField(20);
		tf_phone=new JTextField(20);
		tf_email=new JTextField(20);
		tf_age=new JTextField(20);
		
		p1.add(name);p1.add(tf_name);
		p1.add(phone);p1.add(tf_phone);
		p1.add(email);p1.add(tf_email);
		p1.add(age);p1.add(tf_age);
		p1.setBackground(Color.pink);
		p2.setBackground(Color.YELLOW);
		JButton bt1= new JButton("전화번호");
		JButton bt2= new JButton("검색");
		JButton bt3= new JButton("수정");
		JButton bt4= new JButton("삭제");
		JButton bt5= new JButton("추가");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		p2.add(bt1);
		p2.add(bt2);
		p2.add(bt3);
		p2.add(bt4);
		p2.add(bt5);
		p1.setBounds(10, 10, 250, 100);
		p2.setBounds(10, 130, 400, 40);
		p3.setBounds(10, 180, 460, 570);
		p3.setBackground(Color.white);
		this.add(p1);this.add(p2);this.add(p3);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new playSwing();//new 생성자;
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//버튼에 써져있는 내용확인 e.getActionCommand()
	
		String cmd= e.getActionCommand();
		if(cmd.equals("전화번호")) {
			display();
		}
			
		else if(cmd.equals("검색")) {
			//tf.setBackground(Color.red);
			
		}
		else if(cmd.equals("수정")) {
			//tf.setFont(new Font("궁서체",Font.BOLD,20));
			
		}
		else if(cmd.equals("삭제")) {
			//tf.setText("");
			
	
		}
		else if(cmd.equals("추가")) {
			//tf.setText("");
			//tf.setBackground(Color.white);
			insert();
			
	
		}
		
			
	}
	public ResultSet select() {
		con=makeConnection();
		if(con != null) {
			String sql="select * from person";
			try {
				ps = con.prepareStatement(sql);//prepareStatement-> 메서드
				rs= ps.executeQuery();//select실행시 ->테이블 변화 없음
				//ps.executeUpdate();//insert,delete,update 실행시 ->테이블 변화줌
//				System.out.println("==========phonebook==========");
//				System.out.println("이름"+"\t"+"전화번호"+"\t"+"이메일"+"\t"+"나이"+"\t");
//				while(rs.next()==true)
//					System.out.println(rs.getString("name")+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt("age"));//첫번재부터 차례로 가리키는 메서드 sql 인덱스는 1번 부터
//					//System.out.println(rs.getRow());열번호만 나옴..
					
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();//추적정보출력
			}
			
			
		}
		return rs;
		
	}
	public void insert() {
		con=makeConnection();
		//Scanner in=new Scanner(System.in);
		if(con != null) {
			String sql;
			if(tf_age.getText().equals(""))
				sql="insert into person(name,phone,email) values(?,?,?)";
			else
				sql="insert into person values(?,?,?,?)";
				
//			
			
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1,tf_name.getText());
				ps.setString(2,tf_phone.getText());
				ps.setString(3,tf_email.getText());
				if(!tf_age.getText().equals(""))
					ps.setInt(4,Integer.parseInt(tf_age.getText()));
				ps.executeUpdate();
				
				
			}catch(SQLException ex){
				
				
				
				
			}
		}
		
		
	}
	public  void delete() {
		con=makeConnection();
		if(con != null) {
			Scanner in=new Scanner(System.in);
			System.out.println("전화번호 입력");
			String key=in.nextLine();
			//입력받은 key(전화번호) 값과 일치하는 회원이 존재여부 확인
			String sql="select * from person where phone = ? ";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, key);
				rs=ps.executeQuery();
				if(rs.next()) {//일치하는 번호가 있을때
					
					sql="delete from person where phone = ?";
					ps=con.prepareStatement(sql);
					ps.setString(1, key);
					ps.executeUpdate();
					
				}
				else {
					System.out.println("전화번호가 일치하는 회원이 없습니다.");
				}
				
			}catch(SQLException e) {
				
			}
			
		}
		
	}
	
	public  void update() {
		con=makeConnection();
		if(con != null) {
			Scanner in=new Scanner(System.in);
			System.out.println("전화번호 입력");
			String key=in.nextLine();
			//입력받은 key(전화번호) 값과 일치하는 회원이 존재여부 확인
			String sql="select * from person where phone = ? ";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, key);
				rs=ps.executeQuery();
				if(rs.next()) {//일치하는 번호가 있을때
					System.out.println("이메일 입력");
					String e=in.nextLine();
					System.out.println("나이입력");
					int n=in.nextInt();
					sql="update person set email=?,age=? where phone=?";
					ps=con.prepareStatement(sql);
					ps.setString(1, e);
					ps.setInt(2, n);
					ps.setString(3, key);
					ps.executeUpdate();
					
				}
				else {
					System.out.println("전화번호가 일치하는 회원이 없습니다.");
				}
				
			}catch(SQLException e) {
				
			}
			
		}
		
	}
	public void display() {
		
		rs=select();
		try {
			String info[]=new String[4];
			dtm.setRowCount(0);//행개수를 0이되게 설정
			while(rs.next()) {
				info[0]=rs.getString(1);
				info[1]=rs.getString(2);
				info[2]=rs.getString(3);
				info[3]=rs.getString(4);
				dtm.addRow(info);
				
			}
		}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	
	public Connection makeConnection() {
		String url=
				"jdbc:mysql://localhost:3306/myapp?serverTimezone=Asia/Seoul";
		String id="root";
		String pw="1234";
		Connection con=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//드라이버로딩
			System.out.println("데이터베이스연결중");
			con=DriverManager.getConnection(url,id,pw);//db연결명령
			System.out.println("데이터베이스 연결성공");
		}catch(ClassNotFoundException e) {//com.mysql.cj.jdbc.Driver 로딩시 예외가 발생할때(위치에 없거나..) 실행할 구문
			System.out.println("jdbc 드라이버를 찾지 못했습니다");
			
		}catch(SQLException e){//SQLException e ->SQLException를 줄여서 e로 쓰기
			System.out.println("데이터베이스 연결실패");
			System.out.println(e.getMessage());
		}
		return con;
	}
					

}