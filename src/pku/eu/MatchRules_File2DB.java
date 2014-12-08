package cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MatchRules_File2DB
{

	public static void main(String[] args)
	{

		MatchRules_File2DB mr = new MatchRules_File2DB();
		mr.startPool();
	}

	public void startPool()
	{

		MyRunnable2 tp = new MyRunnable2();
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 6, 1, TimeUnit.DAYS, queue);

		for (int i = 0; i < 5; i++)
		{
			executor.execute(tp);
		}
		executor.shutdown();
	}

}

class MyRunnable2 implements Runnable
{

	//����д�������ǲ�д���ܳ��� %>_<%
	String rulePath = "e:/java/rule.dict";

	String filePath = "e:/java/file";

	String resultTable = "result1";

	String ruleTable = "rule1";

	List<File> filePathsList = new ArrayList<File>();

	int index = 0;

	public MyRunnable2()
	{

		//��ô������ļ�
		File f = new File(filePath);
		getFileList(f);
	}

	//������д�������ļ�
	private void getFileList(File f)
	{

		File[] filePaths = f.listFiles();
		for (File s : filePaths)
		{
			if (s.isFile())
			{
				filePathsList.add(s);
			}
		}
	}

	//�����ݿ��л�ù�������
	public static ResultSet getRule(String ruleTable)
	{

		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select * from " + ruleTable;
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			return rs;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//���ȽϺ�Ľ����hashmap��д�����ݿ����
	public static void add(HashMap<String, Integer> hm, String ruleTable)
	{

		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();

		PreparedStatement pstmt = null;

		Iterator<Entry<String, Integer>> it = hm.entrySet().iterator();
		String str = null;
		int count = 0;

		while (it.hasNext())
		{
			Entry<String, Integer> entry = (Entry<String, Integer>) it.next();
			str = entry.getKey();
			count = entry.getValue();

			String insert = "insert into " + ruleTable + " (str, count) values (?,?)";
			try
			{
				pstmt = conn.prepareStatement(insert);
				pstmt.setString(1, str);
				pstmt.setInt(2, count);
				pstmt.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run()
	{

		File file = null;
		while (index < filePathsList.size())
		{
			synchronized (this)
			{
				file = filePathsList.get(index);
				if (file == null)
				{
					continue;
				}
				index++;
			}
			try
			{
				Thread.sleep(300);
			}
			catch (InterruptedException e2)
			{
				e2.printStackTrace();
			}

			//�������������ݿ��ȡ�������ݣ����û���ã������У�
			BufferedReader br = null;
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			//ʹ�ù���Ƚ�����
			try
			{

				//��ȡ�ļ�����
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()),
						"UTF-8"));
				System.out.println("��ǰʹ�õ��߳��ǣ�" + Thread.currentThread().getName() + ",���ڶ��ļ�:"
						+ filePathsList.indexOf(file));
				String s = null;

				while ((s = br.readLine()) != null)
				{
					String[] str = s.toString().split("\\s+");
					//�����ݿ��л�ȡ��������
					ResultSet rs = getRule("rule1");
					while (rs.next())
					{
						if (str[0].equals(rs.getString(1)))
						{
							hm.put(str[0], Integer.valueOf(str[1]));
						}
					}
				}
				add(hm, resultTable);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

		}

	}
}
