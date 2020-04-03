package Database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static Database.DBConstants.randomDataOfInvoice;
import static Database.DBUtils.handlingQuery;

public class DataBase {

    DBCredentials baseCredentials = null;
    DBHelper dataBaseHelper = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DataBase() throws Exception {
        baseCredentials = new DBCredentials();
        dataBaseHelper = new DBHelper();
        connection = dataBaseHelper.connection(baseCredentials.getDataBaseURL(), baseCredentials.getDataBasePORT(),
                baseCredentials.getDataBaseNAME(), baseCredentials.getDataBaseLOGIN(), baseCredentials.getDataBasePASS());
    }

    public Map<String, String> queryRequest(String query, String... rows) throws SQLException {
        Map<String, String> dataFromDB = new HashMap<String, String>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                dataFromDB = handlingQuery(resultSet, rows);
            }
        } catch (SQLException ex) {
            throw new SQLDataException("Data not found: " + ex.getMessage());
        } finally {
            resultSet.close();
        }
        return dataFromDB;
    }

    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase();

//        // Из таблицы на странице http://serviceacc.foxminded.com.ua/admin/contracts выбрать любого клиента и проверить
//        // в БД информацию о контракте http://serviceacc.foxminded.com.ua/admin/contracts/ [номерконтракта]
//        try {
//            dataBase.queryRequest(contractInfoOfClient("Raven", "Darkhölme"),
//                    "contract_id", "contract_created_date", "contract_last_modified_date",
//                    "contract_close_date", "contract_close_type", "contract_date");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // На странице http://serviceacc.foxminded.com.ua/admin/reports/cashflow выбрать Begin date End и date. Проверить информацию в БД,
//        // которая выводится на этой странице в таблицах : Inflow, Outflow. И вывести количесто записей для каждой таблицы.
//        try {
//            dataBase.queryRequest(salaryPaid("Mentoring", new Date(2019, 9, 1),
//                    new Date(2019, 12, 10)), "inflow", "outflow");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Из таблицы Client и Deal  выбрать Клиента (рандомного), у которого deal status=ACTIVE:
//        try {
//            dataBase.queryRequest(clientsByStatusDeal("ACTIVE"),
//                    "fname", "lname", "status");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Из таблицы Employee выбрать first and last name (рандомного), у которого maх_client>10
//        try {
//            dataBase.queryRequest(clientsOfEmployee(10, 2),
//                    "fname", "lname");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Из таблицы  Client и Contract выбрать Клиента (рандомного), у которого Contract date впромежутке Jun-Oct
//        try {
//            dataBase.queryRequest(clientWithRangeOfDateContract(new Date(2019, 06, 01),
//                    new Date(2019, 10, 30), 1), "first_name", "last_name", "id");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Display client with diff deal status : List of statuses: NEW, COMPLETED, REFUSED, REJECTED, FROZEN
//        try {
//            dataBase.queryRequest(clientsByDealStatus("COMPLETED"),
//                    "client_last_name", "client_first_name", "consultancy_id", "deal_status", "deal_created_date", "deal_close_date");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            dataBase.queryRequest(selectDataFromClient("Bobby", "Drake"), "first_name", "last_name", "email",
//                    "country", "city", "phone_number", "skype");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//       Map<String, String> dataOfContact = new HashMap<String, String>();
//        try {
//           dataBase.queryRequest(contactInfoOfClient("Bobby", "Drake"),"contract_id", "client_first_name",
//                    "client_last_name", "consultancy_name", "employee_first_name", "employee_last_name", "created_date",
//                    "payment_type", "deal_status");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Map<String, String> data = new HashMap<String, String>();
        try {
          data = dataBase.queryRequest(randomDataOfInvoice(),"id", "client_fname", "client_lname",
                  "contract_id", "currency", "money_amount", "payment_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

    }

}

