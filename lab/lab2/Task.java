/**
 * @author Rachel Tai Ke Jia
 **/

class Task {
  private int task;

  public Task(int task) {
    this.task = task;
  }

  public String getTask() {
    if (task == 0) {
      return "deposit";
    } else {
      return "withdrawal";
    }
  }

  @Override
  public String toString() {
    return this.getTask();
  }
}

