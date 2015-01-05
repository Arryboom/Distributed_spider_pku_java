package basic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD
{
	static void delete()
	{
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			ct = JdbcUtil.getConnection();
			st = ct.createStatement();
			String sql="delete from emp where empno=8883";
			
			//执行语句
			int i = st.executeUpdate(sql);//返回更新的记录条数
			//处理执行结果
			System.out.println(i);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcUtil.free(rs, st, ct);
			
		}
	}
	static void update()
	{
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			ct = JdbcUtil.getConnection();
			st = ct.createStatement();
			String sql="update emp set sal=sal+10";
			
			//执行语句
			int i = st.executeUpdate(sql);//返回更新的记录条数
			//处理执行结果
			System.out.println(i);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcUtil.free(rs, st, ct);
			
		}
	}
	
	 	static void read()
	{
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			ct = JdbcUtil.getConnection();
			st = ct.createStatement();
			//执行语句
			rs = st.executeQuery("select empno,ename,job,mgr,hiredate from emp");
			//处理执行结果
			while(rs.next())
			{
				System.out.println("员工号："+rs.getString("empno")+"  "+"员工："+rs.getString("ename"));
			}			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcUtil.free(rs, st, ct);
			
		}
	 }
	public static void main(String[] args)
	{
//		read();
//		create();
//		update();
		delete();
	}
}
