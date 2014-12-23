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
			
			//ִ�����
			int i = st.executeUpdate(sql);//���ظ��µļ�¼����
			//����ִ�н��
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
			
			//ִ�����
			int i = st.executeUpdate(sql);//���ظ��µļ�¼����
			//����ִ�н��
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
			//ִ�����
			rs = st.executeQuery("select empno,ename,job,mgr,hiredate from emp");
			//����ִ�н��
			while(rs.next())
			{
				System.out.println("Ա���ţ�"+rs.getString("empno")+"  "+"Ա����"+rs.getString("ename"));
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
