import java.util.concurrent.Semaphore;

/**
   * Класс, представляющий философа.
   */
class Philosopher implements Runnable {
    private int id;
    private Semaphore table;

    public Philosopher(int id, Semaphore table) {
      this.id = id;
      this.table = table;
    }

    /**
     * Метод, который философ использует для размышления.
     */
    private void think() {
      System.out.println("Philosopher " + id + " is thinking");
      try {
        Thread.sleep(500); // Размышляют 500 миллисекунд
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /**
     * Метод, который философ использует для приема пищи.
     */
    private void eat() {
      System.out.println("Philosopher " + id + " is eating");
      try {
        Thread.sleep(500); // Едят 500 миллисекунд
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      for (int i = 0; i < 3; i++) {
        try {
            table.acquire(); // Захват стола (не более чем для четырех философов)
            eat();
            } catch (InterruptedException e) {
              e.printStackTrace();
            } finally {
              table.release(); // Освобождение стола
            }
        think();

        // try {
        //   table.acquire(); // Захват стола (не более чем для четырех философов)
        //   think();
        // } catch (InterruptedException e) {
        //   e.printStackTrace();
        // } finally {
        //   table.release(); // Освобождение стола
        // }
      }
    }
  }