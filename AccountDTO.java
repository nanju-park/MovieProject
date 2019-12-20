package movieproject;

public class AccountDTO {
	
	private String cId; //아이디
	private String cPwd; //비밀번호
	private String cName; //이름
	private String cTel; //전화번호
	private String checkid;
	private String checkpw;
	private int custom_pid;
	
	public int getCustom_pid() {
		return custom_pid;
	}
	public void setCustom_pid(int custom_pid) {
		this.custom_pid = custom_pid;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getCheckpw() {
		return checkpw;
	}
	public void setCheckpw(String checkpw) {
		this.checkpw = checkpw;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcPwd() {
		return cPwd;
	}
	public void setcPwd(String cPwd) {
		this.cPwd = cPwd;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcTel() {
		return cTel;
	}
	public void setcTel(String cTel) {
		this.cTel = cTel;
	}
	@Override
	public String toString() {
		//일단은 아이디와 비밀번호 출력?하는 형태
		String str = String.format("%10s %10s", cId, cPwd);
		return str;
	}
	
}
