package pojo;
/**
 * storage the status of parameters
 * @author MichealZhao
 *
 */
public class Parameters {
	private String net;
	//Is transform into tick format?
	private boolean tick;
	private boolean tdl;
	private boolean excel;
	//only support one tag and judge by '#tag'
	private String tag;
	//get full path of selected file
	private String in;
	public Parameters() {
		super();
	}
	public Parameters(String net, boolean tick, boolean tdl, boolean excel, String tag, String in, String out,
			String mail) {
		super();
		this.net = net;
		this.tick = tick;
		this.tdl = tdl;
		this.excel = excel;
		this.tag = tag;
		this.in = in;
		this.out = out;
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Patameters [net=" + net + ", tick=" + tick + ", tdl=" + tdl + ", excel=" + excel + ", tag=" + tag
				+ ", in=" + in + ", out=" + out + ", mail=" + mail + "]";
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public boolean isTick() {
		return tick;
	}
	public void setTick(boolean tick) {
		this.tick = tick;
	}
	public boolean isTdl() {
		return tdl;
	}
	public void setTdl(boolean tdl) {
		this.tdl = tdl;
	}
	public boolean isExcel() {
		return excel;
	}
	public void setExcel(boolean excel) {
		this.excel = excel;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	private String out;
	private String mail;
}
