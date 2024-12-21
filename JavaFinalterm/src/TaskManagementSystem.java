import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Assignment {
    private String title;
    private String description;
    private String dueDate;
    private String status;
    private String feedback;

    public Assignment(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = "시작하지 않음";
        this.feedback = "";
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
        this.status = status;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "제목: " + title + "\n설명: " + description + "\n마감일: " + dueDate + "\n상태: " + status + "\n피드백: " + feedback;
    }
}

public class TaskManagementSystem {
    private static List<Assignment> assignments = new ArrayList<>();
    private static final String FILE_NAME = "assignments.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        loadAssignments();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            checkDueDates();
            System.out.println("\n과제 제출 시스템");
            System.out.println("1. 과제 추가");
            System.out.println("2. 과제 목록 보기");
            System.out.println("3. 과제 업데이트");
            System.out.println("4. 과제 제출");
            System.out.println("5. 피드백");
            System.out.println("6. 저장 후 종료");
            System.out.print("원하는 작업을 선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAssignment(scanner);
                    break;
                case 2:
                    viewAssignments();
                    break;
                case 3:
                    updateStatus(scanner);
                    break;
                case 4:
                    submitAssignment(scanner);
                    break;
                case 5:
                    provideFeedback(scanner);
                    break;
                case 6:
                    saveAssignments();
                    System.out.println("프로그램을 종료합니다. 안녕히 가세요!");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void addAssignment(Scanner scanner) {
        System.out.print("과제 제목을 입력하세요: ");
        String title = scanner.nextLine();
        System.out.print("과제 설명을 입력하세요: ");
        String description = scanner.nextLine();
        System.out.print("마감일을 입력하세요 (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        assignments.add(new Assignment(title, description, dueDate));
        System.out.println("과제가 성공적으로 추가되었습니다.");
    }

    private static void viewAssignments() {
        if (assignments.isEmpty()) {
            System.out.println("등록된 과제가 없습니다.");
        } else {
            for (int i = 0; i < assignments.size(); i++) {
                System.out.println("\n과제 " + (i + 1) + ":");
                System.out.println(assignments.get(i));
            }
        }
    }

    private static void updateStatus(Scanner scanner) {
        if (assignments.isEmpty()) {
            System.out.println("상태를 업데이트할 과제가 없습니다.");
            return;
        }

        System.out.print("상태를 업데이트할 과제 번호를 입력하세요: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < assignments.size()) {
            System.out.print("새 상태를 입력하세요 (예: 시작하지 않음, 진행 중, 제출 완료): ");
            String status = scanner.nextLine();
            assignments.get(index).setStatus(status);
            System.out.println("과제 상태가 업데이트되었습니다.");
        } else {
            System.out.println("잘못된 과제 번호입니다.");
        }
    }

    private static void submitAssignment(Scanner scanner) {
        if (assignments.isEmpty()) {
            System.out.println("제출할 과제가 없습니다.");
            return;
        }

        System.out.print("제출할 과제 번호를 입력하세요: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < assignments.size()) {
            assignments.get(index).setStatus("제출 완료");
            System.out.println("과제가 성공적으로 제출되었습니다.");
        } else {
            System.out.println("잘못된 과제 번호입니다.");
        }
    }

    private static void provideFeedback(Scanner scanner) {
        if (assignments.isEmpty()) {
            System.out.println("피드백을 제공할 과제가 없습니다.");
            return;
        }

        System.out.print("피드백을 제공할 과제 번호를 입력하세요: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < assignments.size()) {
            System.out.print("피드백 내용을 입력하세요: ");
            String feedback = scanner.nextLine();
            assignments.get(index).setFeedback(feedback);
            System.out.println("피드백이 성공적으로 제공되었습니다.");
        } else {
            System.out.println("잘못된 과제 번호입니다.");
        }
    }

    private static void saveAssignments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(assignments);
            System.out.println("과제가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("과제를 저장하는 동안 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadAssignments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            assignments = (List<Assignment>) ois.readObject();
            System.out.println("과제가 성공적으로 불러와졌습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("저장된 과제가 없습니다. 새로운 시작을 준비합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("과제를 불러오는 동안 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static void checkDueDates() {
        Date today = new Date();
        for (Assignment assignment : assignments) {
            try {
                Date dueDate = DATE_FORMAT.parse(assignment.getDueDate());
                if (dueDate.equals(today)) {
                    System.out.println("알림: 과제 '" + assignment.getTitle() + "'의 마감일이 오늘입니다!");
                }
            } catch (ParseException e) {
                System.out.println("과제의 날짜를 파싱하는 동안 오류가 발생했습니다: " + assignment.getTitle());
            }
        }
    }
}
