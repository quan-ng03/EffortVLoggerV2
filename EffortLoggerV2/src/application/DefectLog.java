package application;

public class DefectLog {
	private int index;
	private String name;
	private String detail;
	private int injected;
	private int removed;
	private int category;
	private int status;
	private int fix;
	private String project;
	
	public DefectLog(int index, String name, String detail, int injected, int removed, int category, int status, int fix, String project) {
		this.index = index;
		this.name = name;
		this.detail = detail;
		this.injected = injected;
		this.removed = removed;
		this.category = category;
		this.status = status;
		this.fix = fix;
		this.project = project;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getInjected() {
		return injected;
	}
	public void setInjected(int injected) {
		this.injected = injected;
	}
	public int getRemoved() {
		return removed;
	}
	public void setRemoved(int removed) {
		this.removed = removed;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFix() {
		return fix;
	}
	public void setFix(int fix) {
		this.fix = fix;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
}