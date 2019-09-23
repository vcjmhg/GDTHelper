package pojo;
/**
 * 滴答清单登陆时所获取的cookie
 * @author MichealZhao
 *
 */
public class DidaCookie {
	private String t;
	private String UM_distinctid;
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getUM_distinctid() {
		return UM_distinctid;
	}
	public void setUM_distinctid(String uM_distinctid) {
		UM_distinctid = uM_distinctid;
	}
	public DidaCookie(String t, String uM_distinctid) {
		super();
		this.t = t;
		UM_distinctid = uM_distinctid;
	}
	public DidaCookie() {
	}
	
}
