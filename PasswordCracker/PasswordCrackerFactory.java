package PasswordCracker;

public interface PasswordCrackerFactory {
    LocalPasswordCracker createLocalPasswordCracker();
    OnlinePasswordCracker createOnlinePasswordCracker();
}



