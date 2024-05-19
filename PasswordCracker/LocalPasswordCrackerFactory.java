package PasswordCracker;

// Concrete Factory: LocalPasswordCrackerFactory
public class LocalPasswordCrackerFactory implements PasswordCrackerFactory {
    @Override
    public LocalPasswordCracker createLocalPasswordCracker() {
        return new BruteForceLocalPasswordCracker(); // Or any other implementation
    }

    @Override
    public OnlinePasswordCracker createOnlinePasswordCracker() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOnlinePasswordCracker'");
    }

}
