import java.util.List;

// Интерфейс для клиента банка
interface Client {
    void addAccount(Account account); // Метод для добавления счета
    List<Account> getAccounts(); // Метод для получения списка счетов
    String getLastName(); // Метод для получения фамилии клиента
    double calculateTotalInterest(); // Метод для расчета общей суммы процентов
    boolean hasLongTermDeposits(); // Метод для проверки наличия долгосрочных вкладов
}
