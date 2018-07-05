package jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cellargalaxy on 18-5-10.
 */
public class Jdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		main();
	}

	public static final void main() throws ClassNotFoundException, SQLException {
		//加载mysql的驱动
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false";
		//通过url，账号密码获取Connection
		Connection connection = DriverManager.getConnection(url, "root", "pass");

		//执行事务前设置不提交
		connection.setAutoCommit(false);
		String sql = "select * from engine_cost";
		Object[] objects = new Object[]{};
		//用connection创建一个PreparedStatement
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < objects.length; i++) {//给问号填空
			preparedStatement.setObject(i + 1, objects[i]);
		}

		//执行PreparedStatement
		try (PreparedStatement ps = preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {
			//从结果里获取列别名
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			String[] columnLabels = new String[resultSetMetaData.getColumnCount()];
			for (int i = 0; i < columnLabels.length; i++) {
				columnLabels[i] = resultSetMetaData.getColumnLabel(i + 1);
			}

			//把指针指到结果集的最后
			resultSet.last();
			//获取结果集有多少条结果
			Map<String, Object>[] maps = new Map[resultSet.getRow()];
			//把指针指到结果集的最前
			resultSet.beforeFirst();
			int i = 0;
			while (resultSet.next()) {
				maps[i] = new HashMap<String, Object>();
				for (String columnName : columnLabels) {
					//通过列名获取相应的值
					maps[i].put(columnName, resultSet.getObject(columnName));
				}
				i++;
			}

			for (Map<String, Object> map : maps) {
				System.out.println(map);
			}

			//事务完成后提交
			connection.commit();
		} catch (Exception e) {
			//发生异常回滚
			connection.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 批量处理增删改
	 * @param connection
	 * @throws SQLException
	 */
	public static final void main(Connection connection) throws SQLException {
		//批量处理要先设置不提交
		connection.setAutoCommit(false);
		String sql = "insert into tableName(id) values(?)";
		Object[] objects = new Object[]{"id"};
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		try (PreparedStatement ps = preparedStatement) {
			for (int i = 0; i < 10000; i++) {//一万次sql
				for (int j = 0; j < objects.length; j++) {
					preparedStatement.setObject(j + 1, objects[j]);
				}
				preparedStatement.addBatch();//批量处理里添加一个

				if (i % 1000 == 0) {
					int[] lens = preparedStatement.executeBatch();//每1000次提交一次批处理
					System.out.println("批处理影响的行(?): "+Arrays.toString(lens));
					connection.commit();//批处理完成后提交
					preparedStatement.clearBatch();//清理之前的批处理
				}
			}

			//批处理完成后提交
			connection.commit();
		} catch (Exception e) {
			//批处理发生异常回滚
			connection.rollback();
			e.printStackTrace();
		}
	}
}
