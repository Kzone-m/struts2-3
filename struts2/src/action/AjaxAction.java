package action;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxAction extends ActionSupport{
	private int count;
	
	public String execute(){
		count += 1;
		return SUCCESS;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
