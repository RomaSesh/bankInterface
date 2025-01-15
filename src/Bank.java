import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

// Класс, представляющий банк
public class Bank {
    private List<Client> clients; // Список клиентов банка

    // Конструктор класса
    public Bank() {
        clients = new ArrayList<>();
    }

    // Метод для добавления клиента
    public void addClient(Client client) {
        clients.add(client);
    }

    // Метод для удаления клиентов с закрытыми счетами
    public void removeClosedAccounts() {
        clients.removeIf(client -> client.getAccounts().stream().allMatch(account -> account.getClosingDate() != null));
    }

    // Метод для перевода средств между счетами
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            fromAccount.withdraw(amount); // Снимаем средства с исходного счета
            toAccount.deposit(amount); // Зачисляем средства на целевой счет
        } else {
            throw new IllegalArgumentException("Неверный номер счета.");
        }
    }

    // Метод для нахождения счета по номеру
    private Account findAccountByNumber(String accountNumber) {
        for (Client client : clients) {
            for (Account account : client.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null; // Если счет не найден
    }

    // Метод для нахождения клиента с максимальной суммой процентов
    public void findMaxInterestClient() {
        Client maxClient = null;
        double maxInterest = 0;
        for (Client client : clients) {
            double interest = client.calculateTotalInterest();
            if (interest > maxInterest) {
                maxInterest = interest;
                maxClient = client;
            }
        }
        if (maxClient != null) {
            System.out.println("Client with max interest: " + maxClient.getLastName() + ", Interest: " + maxInterest);
        }
    }

    // Метод для нахождения счета с минимальным вкладом
    public void findMinDepositAccount() {
        Account minAccount = null;
        for (Client client : clients) {
            for (Account account : client.getAccounts()) {
                if (minAccount == null || account.getDepositAmount() < minAccount.getDepositAmount()) {
                    minAccount = account;
                }
            }
        }
        if (minAccount != null) {
            System.out.println("Min deposit account: " + minAccount.getAccountNumber() + ", Amount: " + minAccount.getDepositAmount() +
                    ", Year of opening: " + minAccount.getOpeningDate());
        }
    }

    // Метод для сортировки клиентов по фамилии
    public void sortClients() {
        clients.sort(Comparator.comparing(Client::getLastName));
    }

    // Главный метод для тестирования функциональности
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Пример добавления клиентов и счетов
        Client client1 = new BankClient("Иванов", "123456");
        client1.addAccount(new BankAccount("001", 5.0, 1000, new Date(System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000), null));
        bank.addClient(client1);

        Client client2 = new BankClient("Петров", "654321");
        client2.addAccount(new BankAccount("002", 4.0, 500, new Date(System.currentTimeMillis() - 730 * 24 * 60 * 60 * 1000), null));
        bank.addClient(client2);

        // Вызов методов
        bank.findMaxInterestClient();
        bank.findMinDepositAccount();
        bank.sortClients();
        bank.removeClosedAccounts();
    }
}