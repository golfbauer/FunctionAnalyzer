package de.hhn.it.pp.components.mathtrainer;

public class Watch implements Runnable {
  private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(BiKrMathTrainer.class);
  private boolean timeisup = false;
  private int seconds = 0;

  public void setTimeisup(boolean value) {
    timeisup = value;
  }

  public boolean getTimeisup() {
    return timeisup;
  }

  public int getSeconds() {
    return seconds;
  }

  @Override
  public void run() {
    while (seconds != 5) {
      try {
        Thread.sleep(1000);
        seconds++;

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    logger.info("5 seconds passed");
    setTimeisup(true);
  }
}
