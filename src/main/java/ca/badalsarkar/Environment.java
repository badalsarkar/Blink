package ca.badalsarkar;


import java.util.Map;

/**
 * This is an utility class which encapsulates process of environmental variables. All the
 * environmental variables are managed by this class. Any other class/methods needing environmental
 * variable consults with this class.
 */
public final class Environment {
  private static Map<String, String> env;
  // All supported variables
  private static boolean cliColor; // Used to print URL status in color

  public static void extractAllVariables() {
    env = System.getenv();
    setCliColor();
  }

  private static void setCliColor() {
    if (env.get("CLICOLOR") != null) {
      try {
        cliColor = Integer.parseInt(env.get("CLICOLOR")) == 1 ? true : false;
      } catch (NumberFormatException e) {
        cliColor = false;
      }
    }
  }

  public static boolean getCliColor() {
    return cliColor;
  }
}
