public class ActivityLog{
  private String[] history;
  private int count;
  private static final int MAX_LOGS = 500;
  
  ActivityLog(){ //no parameters empty log
    history = new String[MAX_LOGS];
    count=0;
  }
  
  //add stuff to LOG
  public void addLog(String log) {
        if(count < MAX_LOGS) {
            history[count] = log;
            count++;
        }
  }
  
  public void viewLog(int howMuch) {
    if (count == 0) {
        System.out.println("No activities done recently !");
        return;
    }
    
    System.out.println("\n===== ACTIVITY LOG =====");
    int start = 0;
    //less than total just show x then
    if (howMuch<count && howMuch != 0) {
            start = count - howMuch;
    }
    
    for (int i = start; i < count; i++) {
            System.out.println(history[i]);
    }
  }
  
  public int getCount() {
    return count;
  }

  public String getLog(int index) {
    return history[index];
  }
}