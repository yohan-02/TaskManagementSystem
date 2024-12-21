import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Assignment {
	private String title;
	private String description;
	private String dueDate;
	private String status;
	private String feedback;
	
	public Assignment(String title, String description, String dueDate) {
		title = title;
		description = description;
		dueDate = dueDate;
		status = "시작하지 않음";
		feedback = "";
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	 public String getDueDate() {
		 return dueDate; 

	}
	 public String getStatus() {
		 return status;
	}
	 public String getFeedback() {
		 return feedback;
	 }
	 public void setStatus(String status) {
		 status = status;
	 }
	 public void setFeedback(String feedback) {
		 feedback = feedback;
	 }
	 @Override
	 public String toString() {
		 return "제목: " + title + "\n설명: " + description + "\n마감일: " + dueDate + "\n상태: " + status + "\n피드백: " + feedback;
	 }
}

class Student {
	
}

class Professor {
	
}

public class TaskManagementSystem {
    private List<Assignment> assignments = new ArrayList<>();
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Professor> professors = new HashMap<>();



    public static void main(String[] args) {


    	
    }
}