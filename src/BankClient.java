import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Класс, представляющий клиента банка
class BankClient implements Client {
    private String lastName; // Фамилия клиента
    private String passportNumber; // Номер паспорта
    private List<Account> accounts; // Список счетов клиента

    // Конструктор класса
    public BankClient(String lastName, String passportNumber) {
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.accounts = new ArrayList<>();
    }

    // Метод для добавления счета
    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Геттер для получения списка счетов
    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    // Геттер для получения фамилии клиента
    @Override
    public String getLastName() {
        return lastName;
    }

    // Метод для расчета общей суммы процентов по всем счетам
    @Override
    public double calculateTotalInterest() {
        return accounts.stream().mapToDouble(Account::calculateInterest).sum();
    }

    // Метод для проверки наличия долгосрочных вкладов (2 года и более)
    @Override
    public boolean hasLongTermDeposits() {
        Date twoYearsAgo = new Date(System.currentTimeMillis() - 2L * 365 * 24 * 60 * 60 * 1000);
        for (Account account : accounts) {
            if (account.getOpeningDate().before(twoYearsAgo) && (account.getClosingDate() == null || account.getClosingDate().after(new Date()))) {
                return true;
            }
        }
        return false;
    }
}