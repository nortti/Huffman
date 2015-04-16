package huffman.io;

import java.io.File;

// Class is work in progress (missing javadoc and tests)

public class ArgsParser {

    String action;
    File file;
    String errorMessage;

    public boolean parse(String[] args) {
        if (args.length != 2) {
            this.errorMessage = "Wrong number of args, expected: 2, was: " + args.length;
            return false;
        }
        this.action = args[0];
        this.file = new File(args[1]);

        String[] errors = new String[]{ "Incorrect argument #1, expected: 'c' or 'd', was: '" +
                                        args[0] + "'",
                                     "Invalid argument #2: '" + this.file.getPath() + "' is a " +
                                        "directory",
                                     "Invalid argument #2: '" + this.file.getPath() +
                                         "' does not exist",
                                     "Invalid argument #2: '" + this.file.getPath() +
                                         "' empty files can not be converted" };
        boolean[] validations = new boolean[] { (args[0].equals("c") || args[0].equals("d")),
                                                !this.file.isDirectory(), this.file.exists(),
                                                this.file.length() > 0};

        for (int i = 0; i < validations.length; i++) {
            if (!validations[i]) {
               this.errorMessage = errors[i];
               return false;
            }
        }
        return true;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean getIsCompressing() {
        return action.equals("c");
    }

    public File getFile() {
        return this.file;
    }
}
