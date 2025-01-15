import java.util.Date;

// Класс, представляющий банковский счет
class BankAccount implements Account {
    private String accountNumber; // Номер счета
    private double annualPercentage; // Годовой процент
    private double depositAmount; // Размер вклада
    private Date openingDate; // Дата открытия
    private Date closingDate; // Дата закрытия (может быть null, если счет открыт)

    // Конструктор класса
    public BankAccount(String accountNumber, double annualPercentage, double depositAmount, Date openingDate, Date closingDate) {
        this.accountNumber = accountNumber;
        this.annualPercentage = annualPercentage;
        this.depositAmount = depositAmount;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
    }

    // Метод для расчета начисленных процентов
    @Override
    public double calculateInterest() {
        return depositAmount * (annualPercentage / 100);
    }

    // Геттеры для получения информации о счете
    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getDepositAmount() {
        return depositAmount;
    }

    @Override
    public Date getOpeningDate() {
        return openingDate;
    }

    @Override
    public Date getClosingDate() {
        return closingDate;
    }

    // Метод для внесения средств
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            depositAmount += amount;
        } else {
            throw new IllegalArgumentException("Сумма должна быть положительной.");
        }
    }

    // Метод для снятия средств
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= depositAmount) {
            depositAmount -= amount;
        } else {
            throw new IllegalArgumentException("Недостаточно средств или сумма некорректна.");
        }
    }
}