package Database;

import java.util.Date;

public class DBConstants {

    // List of status: NEW, COMPLETED, REFUSED, REJECTED, FROZEN
    public static String clientsByDealStatus(String status) {
        return String.format("SELECT client.id as client_id, client.last_name as client_last_name,\n" +
                "client.first_name as client_first_name,\n" +
                "consultancy.id as consultancy_id, consultancy.description as consultancy_description, deal.status as deal_status,\n" +
                "deal.created_date as deal_created_date, deal.close_date as deal_close_date\n" +
                "FROM deal\n" +
                "LEFT JOIN client\n" +
                "   ON client_id = deal.client_id\n" +
                "RIGHT JOIN consultancy\n" +
                "   ON consultancy_id = deal.consultancy_id\n" +
                "WHERE deal.status='%s' ORDER BY client_id LIMIT 10;", status);
    }


    public static String contractInfoOfClient(String firstName, String lastName) {
        return String.format("SELECT contract.id as contract_id, contract.created_date as contract_created_date, \n" +
                "contract.last_modified_date as contract_last_modified_date, contract.close_date as contract_close_date, \n" +
                "contract.close_type as contract_close_type, contract.contract_date as contract_date\n" +
                "FROM client \n" +
                "INNER JOIN deal\n" +
                "    ON deal.client_id = client.id\n" +
                "RIGHT JOIN contract\n" +
                "    ON contract.deal_id = deal.id\n" +
                "LEFT JOIN invoice\n" +
                "    ON invoice.contract_id = contract_id\n" +
                "LEFT JOIN money\n" +
                "    ON money.id = contract.price_id\n" +
                "WHERE client.first_name = '%s' AND client.last_name = '%s';", firstName, lastName);
    }

    public static String salaryPaid(String consultancy, Date paymentDate, Date salaryDate) {
        return String.format("SELECT COUNT(payment.date_paid) as inflow, " +
                "COUNT(salary.date_salary) as outflow\n" +
                "FROM contract\n" +
                "INNER JOIN employee\n" +
                "    ON employee.id = contract.employee_id\n" +
                "INNER JOIN salary_item\n" +
                "    ON salary_item.employee_id = employee.id\n" +
                "INNER JOIN salary\n" +
                "    ON salary_item.salary_id = salary.id\n" +
                "INNER JOIN deal\n" +
                "    ON deal.id = contract.deal_id\n" +
                "INNER JOIN invoice\n" +
                "    ON invoice.contract_id = contract.id\n" +
                "INNER JOIN consultancy\n" +
                "    ON consultancy.id = deal.consultancy_id\n" +
                "INNER JOIN payment\n" +
                "    ON payment.invoice_id = invoice.id\n" +
                "WHERE consultancy.name = '%s' AND payment.date_paid >= '%tF\t' " +
                "AND salary.date_salary <= '%tF\t';", consultancy, paymentDate, salaryDate);
    }

    public static String clientsByStatusDeal(String dealStatus) {
        return String.format("SELECT client.first_name as fname, " +
                "client.last_name as lname, deal.* as deal_status\n" +
                "FROM client\n" +
                "INNER JOIN deal\n" +
                "    ON deal.client_id = client.id\n" +
                "WHERE deal.status='%s' ORDER BY RANDOM() limit 1;", dealStatus);
    }

    public static String clientsOfEmployee(int countOfClient, int limit) {
        return String.format("SELECT employee.first_name as fname, " +
                "employee.last_name as lname\n" +
                "FROM employee\n" +
                "WHERE employee.max_clients >= %d " +
                "ORDER BY RANDOM() limit %d;", countOfClient, limit);
    }

    public static String clientWithRangeOfDateContract(Date startDate, Date endDate, int limit) {
        return String.format("SELECT client.* \n" +
                "FROM client\n" +
                "INNER JOIN deal\n" +
                "    ON deal.client_id = client.id\n" +
                "INNER JOIN contract\n" +
                "    ON contract.deal_id = deal.id\n" +
                "WHERE contract_date BETWEEN '%tF' AND '%tF' ORDER BY RANDOM() limit %d;", startDate, endDate, limit);

    }

    public static String selectDataFromClient(String firstName, String lastName) {
        return String.format("SELECT first_name, last_name, email, country, city, phone_number, skype FROM client \n" +
                "WHERE first_name = '%s' AND last_name = '%s';", firstName, lastName);
    }

    public static String contactInfoOfClient(String firstName, String lastName) {
        return String.format("SELECT contract.id as contract, client.first_name as client_first_name,\n" +
                "client.last_name as client_last_name, consultancy.name as consultancy,\n" +
                "employee.first_name as employee_first_name, employee.last_name as employee_last_name,\n" +
                "contract.created_date as contract_date, contract.payment_type as type,\n" +
                "deal.status as status\n" +
                "FROM contract\n" +
                "INNER JOIN deal\n" +
                "    ON deal.id = contract.deal_id\n" +
                "RIGHT JOIN client\n" +
                "    ON client.id = deal.client_id\n" +
                "LEFT JOIN consultancy\n" +
                "     ON consultancy.id = deal.consultancy_id\n" +
                "LEFT JOIN employee\n" +
                "     ON employee.id = contract.employee_id\n" +
                "WHERE client.first_name = '%s' AND client.last_name = '%s';", firstName, lastName);
    }

    public static String randomDataOfInvoice() {
        return "SELECT invoice.id as id, client.first_name as client_fname, client.last_name as client_lname,\n" +
                "contract.id as contract_id, money.currency as currency, money.amount as money_amount,\n" +
                "invoice.creation_date as payment_date\n" +
                "FROM invoice\n" +
                "INNER JOIN contract\n" +
                "ON contract.id = invoice.contract_id\n" +
                "INNER JOIN deal\n" +
                "ON deal.id = contract.deal_id\n" +
                "INNER JOIN client\n" +
                "ON client.id = deal.client_id\n" +
                "INNER JOIN money\n" +
                "ON money.id = invoice.price_id\n" +
                "INNER JOIN payment\n" +
                "ON payment.invoice_id = invoice.id\n" +
                "ORDER BY random() limit 1;";
    }

    public static String randomDataOfDeal() {
        return "SELECT deal.id as id, client.first_name as client_fname, client.last_name as client_lname,\n" +
                "consultancy.name as consultancy, deal.status as status, deal.open_date as open_date,\n" +
                "deal.close_date as close_date\n" +
                "FROM deal\n" +
                "INNER JOIN client\n" +
                "ON client.id = deal.client_id\n" +
                "INNER JOIN consultancy\n" +
                "ON consultancy.id = deal.consultancy_id\n" +
                "ORDER BY random() limit 1;";
    }

    public static String randomClient() {
        return "SELECT client.id as id, client.first_name as fname, client.last_name as lname, client.email as email,\n" +
                "client.country as country, client.city as city, client.skype as skype, client.phone_number as phone,\n" +
                "money.amount as balance, money.currency as currency\n" +
                "FROM client\n" +
                "INNER JOIN personal_account\n" +
                "ON personal_account.id = client.personal_account_id\n" +
                "INNER JOIN personal_account_money\n" +
                "ON personal_account_money.personal_account_id = personal_account.id\n" +
                "INNER JOIN money\n" +
                "ON money.id = personal_account_money.money_id\n" +
                "ORDER BY random() limit 1;";

    }

    public static String getFullDataOfClient(String firstName, String lastName) {
        return String.format("SELECT client.id as id, client.first_name as fname, client.last_name as lname, client.email as email,\n" +
                "client.country as country, client.city as city, client.skype as skype, client.phone_number as phone,\n" +
                "money.amount as balance, money.currency as currency\n" +
                "FROM client\n" +
                "INNER JOIN personal_account\n" +
                "ON personal_account.id = client.personal_account_id\n" +
                "INNER JOIN personal_account_money\n" +
                "ON personal_account_money.personal_account_id = personal_account.id\n" +
                "INNER JOIN money\n" +
                "ON money.id = personal_account_money.money_id\n" +
                "WHERE client.first_name = '%s' AND client.last_name = '%s';", firstName, lastName) ;

    }
}
