
import java.sql.*;

public class DataBaseInsert {

    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/server_bd";
    static final String USER = "postgres";
    static final String PASS = "1234";
    static Connection connection = null;
    static Statement statement;


    public static void insertInto(Object fio) throws SQLException {


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найдет, подключите его в библиотеке");
            e.printStackTrace();
            return;
        }

        System.out.println("Успешное подключение драйвера");

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Подключение не удалось");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("Вы успешно подключились к БД");
        } else {
            System.out.println("Подключение к БД не удалось");
        }
        // Объект для отправки SQL запросов к БД


        statement = connection.createStatement();
        statement.executeUpdate("insert into json(fio) values('"+fio+"')");

        ResultSet resultSet = statement.executeQuery("select * from json");
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("fio"));
            System.out.println("********************************");


//
//        statement.executeUpdate("insert into json(name) values('Даниил')");
//        statement.executeUpdate("insert into json(name) values('Иван')");
        //statement.executeUpdate("drop table json;");
        // statement.executeUpdate("create table json (id serial not null, fio VARCHAR(30) not null, primary key(id));");

    }

        }

    }


