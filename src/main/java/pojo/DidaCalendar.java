package pojo;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 封装滴答清单获取的Json所解析的对象
 * @author MichealZhao
 *
 */
public class DidaCalendar implements Comparable<DidaCalendar>{
	private String id;
	private String projectId;
	private String sortOrder;
	private String title;
	private String content;
	private String desc;
	private String startDate;
	private String dueDate;
	private String timeZone;
	private boolean isAllDay;
	private String reminder;
	private String reminders;
	private String repeatFirstDate;
	private String exDate;
	private String completedTime;
	private String completedUserId;
	private String repeatTaskId;
	private int priority;
	private int status;
	private String items;
	private int progress;
	private String modifiedTime;
	private String etag;
	private int deleted;
	private String createdTime;
	private String creator;
	private String remindTime;
	private String repeatFrom;
	private String pomodoroSummaries;
	private String kind;

	private float score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isAllDay() {
		return isAllDay;
	}

	public void setAllDay(boolean allDay) {
		isAllDay = allDay;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getReminders() {
		return reminders;
	}

	public void setReminders(String reminders) {
		this.reminders = reminders;
	}

	public String getRepeatFirstDate() {
		return repeatFirstDate;
	}

	public void setRepeatFirstDate(String repeatFirstDate) {
		this.repeatFirstDate = repeatFirstDate;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

	public String getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	public String getCompletedUserId() {
		return completedUserId;
	}

	public void setCompletedUserId(String completedUserId) {
		this.completedUserId = completedUserId;
	}

	public String getRepeatTaskId() {
		return repeatTaskId;
	}

	public void setRepeatTaskId(String repeatTaskId) {
		this.repeatTaskId = repeatTaskId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}

	public String getRepeatFrom() {
		return repeatFrom;
	}

	public void setRepeatFrom(String repeatFrom) {
		this.repeatFrom = repeatFrom;
	}

	public String getPomodoroSummaries() {
		return pomodoroSummaries;
	}

	public void setPomodoroSummaries(String pomodoroSummaries) {
		this.pomodoroSummaries = pomodoroSummaries;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "DidaCalendar{" +
				"title='" + title + '\'' +
				", dueDate='" + dueDate + '\'' +
				", score=" + score +
				'}';
	}

	@Override
	public int compareTo(@NotNull DidaCalendar dc) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date thatTime=null;
		Date thisTime=null;
		try {
			thisTime=sf.parse(this.getDueDate().substring(0,10));
			thatTime=sf.parse(dc.getDueDate().substring(0,10));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(thisTime.after(thatTime)) return 1;
		else
			return -1;
	}
}
