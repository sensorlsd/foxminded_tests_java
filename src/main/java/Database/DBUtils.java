package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBUtils {

    public static Map<String, String> handlingQuery(ResultSet data, String... rows) throws SQLException {

        Map<String, String> getData = new HashMap<String, String>();

        for (int i = 0; i < rows.length; i++) {
            getData.put(rows[i].toLowerCase(), data.getString(rows[i].toString()));
        }

        return getData;
    }

    public static ArrayList<Map<String, Object>> handlingQuery_Multi(ResultSet data, String... rows) throws SQLException {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> row = new HashMap<String, Object>();
        for (int i = 0; i < rows.length; i++) {

            row.put(rows[i], data.getObject(rows[i]));
        }

        result.add(row);

        for (Map<String, Object> getData : result) {
            getData.forEach((key, value) -> System.out.println(key + " : " + value));
        }

        return result;
    }
}
