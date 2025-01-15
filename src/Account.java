import java.util.Date;

// Интерфейс для банковского счета
interface Account {
    double calculateInterest(); // Метод для расчета процентов
    String getAccountNumber(); // Метод для получения номера счета
    double getDepositAmount(); // Метод для получения суммы вклада
    Date getOpeningDate(); // Метод для получения даты открытия
    Date getClosingDate(); // Метод для получения даты закрытия
    void deposit(double amount); // Метод для внесения средств
    void withdraw(double amount); // Метод для снятия средств
}
