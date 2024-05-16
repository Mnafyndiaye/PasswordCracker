package PasswordCracker;

public class PasswordCrackerFactory {
    public static LocalPasswordCracker getLocalPasswordCracker(String type) {
        switch (type.toLowerCase()) {
            case "bruteforce":
                return new BruteForceLocalPasswordCracker();
            case "dictionary":
                return new DictionnaryLocalPasswordCracker();
            default:
                throw new IllegalArgumentException("Unknown cracker type");
        }
    }

    public static OnlinePasswordCracker getOnlinePasswordCracker(String type) {
        switch (type.toLowerCase()) {
            case "bruteforce":
                return new BruteForceOnlinePasswordCracker();
            case "dictionary":
                return new DictionnaryOnlinePasswordCracker();
            default:
                throw new IllegalArgumentException("Unknown cracker type");
        }
    }
}
