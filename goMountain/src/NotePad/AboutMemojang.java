package NotePad;

import javax.management.openmbean.OpenType;
import javax.swing.JDialog;
import java.awt.Frame;



public class AboutMemojang extends JDialog {
	public AboutMemojang(Frame owner) {
		super(owner,"메모장정보",true);//modal (true) modaless(false)
		this.setSize(200,200);
		this.setLocationRelativeTo(owner);
		this.setVisible(true);
		//이름 아이콘 이미지 저작권정보 언제 누가 왜 버전정보.
	}
}
