package NotePad;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class MemoJang extends JFrame implements ActionListener{
	JTextArea ta=new JTextArea();
	public MemoJang() {
		this.setTitle("jsk memo");
		this.setBounds(100,500,500,500);
		makeMenu();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jp=new JScrollPane(ta);
		this.add(jp);
		this.setVisible(true);
	}
	public void makeMenu() {
		JMenuBar mb=new JMenuBar();
		//파일메뉴
		JMenu file=new JMenu("파일");
		JMenuItem file_new= new JMenuItem("새파일");
		JMenuItem file_open= new JMenuItem("열기");
		JMenuItem file_save= new JMenuItem("저장");
		JMenuItem file_saveAs= new JMenuItem("다른이름으로 저장");
		JMenuItem file_exit= new JMenuItem("끝내기");
		file.add(file_new);	file.add(file_open);
		file.addSeparator();
		file.add(file_save);file.add(file_saveAs);
		file.addSeparator();
		file.add(file_exit);
		///편집메뉴
		JMenu edit=new JMenu("편집");
		JMenuItem edit_cut= new JMenuItem("잘라내기");
		JMenuItem edit_copy= new JMenuItem("복사");
		JMenuItem edit_paste= new JMenuItem("붙여넣기");
		JMenuItem edit_find= new JMenuItem("찾기");
		JMenuItem edit_change= new JMenuItem("바꾸기");
		JMenuItem edit_datetime= new JMenuItem("시간/날짜");
		edit.add(edit_cut);
		edit.add(edit_copy);edit.add(edit_paste);
		edit.addSeparator();
		edit.add(edit_find);edit.add(edit_change);
		//서식메뉴
		JMenu form=new JMenu("서식");
		JMenuItem form_font= new JMenuItem("글꼴");
		JMenuItem form_fontColor= new JMenuItem("글자색");
		JMenuItem form_backColor= new JMenuItem("배경색");
		form.add(form_font);form.add(form_fontColor);form.add(form_backColor);
		//보기 메뉴
		JMenu view=new JMenu("보기");
		JMenuItem view_zoom= new JMenuItem("확대/축소");
		JMenuItem view_statusBar= new JMenuItem("상태표시줄");
		view.add(view_zoom);view.add(view_statusBar);
		
		JMenu help=new JMenu("도움말");
		JMenuItem help_info= new JMenuItem("도움말 정보");
		JMenuItem help_about= new JMenuItem("메모장 정보");
		help_about.addActionListener(this);
		help.add(help_info);help.add(help_about);
		
		mb.add(file);mb.add(edit);mb.add(form);mb.add(view);mb.add(help);
		this.setJMenuBar(mb);//메뉴바 거는 방법
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd= e.getActionCommand();
		if(cmd.equals("메모장 정보")) {
			new AboutMemojang(this);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemoJang();

	}

}
