import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        // prompt user for encode or decode
        String command = askUserCommand();
        if (!command.equals("encode")) {
            if (!command.equals("decode")){
                System.out.println("Invalid command.");
                return;
            }
        }
        
        if (command.equals("encode")) {
            offset = askUserOffset();
            String input = askUserText();
            String output = encode(input);
            System.out.println(output);
        }

        if (command.equals("decode")) {
            String input = askUserText();
            String output = decode(input);
            System.out.println(output);
        }
    }

    public static char offset = 'A';
    public static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public static String askUserCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Encode or decode?");
        String job = scanner.nextLine().toLowerCase();
        System.out.println(job);
        // close scanner causes next line error
        // scanner.close();
        return job;
    }

    public static char askUserOffset() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter offset:");
        String c = scanner.nextLine().toUpperCase();
        char offset = c.charAt(0);
        System.out.println("Offset is " + offset);
        // close scanner causes next line error
        // scanner.close();
        return offset;
    }

    public static String askUserText() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = scanner.nextLine();
        scanner.close();
        return text;
    }

    public static String encode(String plainText) {
        StringBuilder encoded = new StringBuilder();
        // append key
        encoded.append(offset);
        int shift = REFERENCE_TABLE.indexOf(offset);
        // iterate message to encode
        for (char c : plainText.toCharArray()) {
            int index = REFERENCE_TABLE.indexOf(c);
            if (index != -1) {

                index = (index - shift) % REFERENCE_TABLE.length();
                if (index < 0) {
                    index = index + REFERENCE_TABLE.length();
                }
                encoded.append(REFERENCE_TABLE.charAt(index));
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
    }

    public static String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        char[] codeArray = encodedText.toCharArray();
        // get key
        int shift = REFERENCE_TABLE.indexOf(codeArray[0]);
        // iterate to decode
        for (int i = 1; i < codeArray.length; i++) {
            
            int index = REFERENCE_TABLE.indexOf(codeArray[i]);
            if (index != -1) {
                index = (index + shift) % REFERENCE_TABLE.length();
                if (index < 0) {
                    index = index + REFERENCE_TABLE.length();
                }
                decoded.append(REFERENCE_TABLE.charAt(index));
            } else {
                decoded.append(codeArray[i]);
            }
        }
        return decoded.toString();
    }
}
