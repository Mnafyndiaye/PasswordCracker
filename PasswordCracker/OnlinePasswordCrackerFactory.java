package PasswordCracker;

// Concrete Factory: OnlinePasswordCrackerFactory
public class OnlinePasswordCrackerFactory implements PasswordCrackerFactory {
    @Override
    public OnlinePasswordCracker createOnlinePasswordCracker() {
        return new BruteForceOnlinePasswordCracker(); // Or any other implementation
    }

    @Override
    public LocalPasswordCracker createLocalPasswordCracker() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLocalPasswordCracker'");
    }
}
