package pojo;

import java.util.Date;

/**
 * 封装开始时间和截止时间
 * @author MichealZhao
 *
 */
public class TimeParam {
	private Date from;
	private Date to;
	private int limit=100;
	public TimeParam(Date from, Date to, int limit) {
		super();
		this.from = from;
		this.to = to;
		this.limit = limit;
	}
	public TimeParam() {
		super();
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "TimeParam [from=" + from + ", to=" + to + ", limit=" + limit + "]";
	}
}
